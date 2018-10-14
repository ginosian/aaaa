package com.test.service.waiter.impl;


import com.google.common.collect.Sets;
import com.test.entity.Order;
import com.test.entity.Product;
import com.test.entity.ProductInOrder;
import com.test.entity.RestaurantTable;
import com.test.misc.OrderStatus;
import com.test.repository.ApiUserRepository;
import com.test.repository.OrderRepository;
import com.test.repository.ProductRepository;
import com.test.repository.RestaurantTableRepository;
import com.test.service.OrderCreationRequest;
import com.test.service.OrderUpdateRequest;
import com.test.service.ProductInOrderUpdateRequest;
import com.test.service.waiter.WaiterService;
import jdk.internal.joptsimple.internal.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

@Service
public class WaiterServiceImpl implements WaiterService{

    @Autowired
    private ApiUserRepository apiUserRepository;

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<RestaurantTable> getTables(String waiterId) {
        notEmpty(waiterId, "waiterId can not be null");
        return restaurantTableRepository.findAllByWaiter_idAndDeletedFalse(waiterId);
    }


    @Transactional
    @Override
    public Order createOrder(OrderCreationRequest orderCreationRequest) {
        notNull(orderCreationRequest, "orderCreationRequest can not be null");
        final String tableId = orderCreationRequest.getTableId();
        notEmpty(tableId, "orderCreationRequest.tableId can not be null or empty");
        final RestaurantTable table = restaurantTableRepository.getOne(tableId);
        final List<ProductInOrder> productInOrders =
                orderCreationRequest.getProductInOrderCreationRequests()
                .stream()
                .filter(Objects::nonNull)
                .map(productInOrderCreationRequest -> {
                    final String productId = productInOrderCreationRequest.getProductId();
                    return createProductInOrder(productId, productInOrderCreationRequest.getAmount());
                })
                .collect(Collectors.toList());

        final Order order = new Order();
        order.setOrderStatus(OrderStatus.OPEN);
        order.setProductsInOrder(Sets.newHashSet(productInOrders));
        order.setRestaurantTable(table);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(OrderUpdateRequest orderUpdateRequest) {
        notNull(orderUpdateRequest, "orderUpdateRequest can not be null");
        final String orderId = orderUpdateRequest.getOrderId();
        notEmpty(orderId, "orderUpdateRequest.orderId can not be null or empty");
        final Order order = orderRepository.getOne(orderId);
        final Set<ProductInOrder> productInOrders = order.getProductsInOrder();
        final OrderStatus orderStatus = orderUpdateRequest.getOrderStatus();
        if(orderStatus != null){
            order.setOrderStatus(orderStatus);
        }
        final Set<ProductInOrderUpdateRequest> productInOrderUpdateRequests = orderUpdateRequest.getProductInOrderUpdateRequests();
        updateWithProducts(productInOrders, productInOrderUpdateRequests);
        return orderRepository.save(order);
    }

    private ProductInOrder createProductInOrder(final String productId, final double amount) {
        notEmpty(productId, "productId can not be null or empty");
        final Product product = productRepository.getOne(productId);
        final ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setProduct(product);
        productInOrder.setAmount(amount);
        return productInOrder;
    }

    private void updateWithProducts(final Set<ProductInOrder> productInOrders, final Set<ProductInOrderUpdateRequest> productInOrderUpdateRequests){
        final Set<ProductInOrder> newProducts = new HashSet<>();
        final Map<String, Double> productUpdates = new HashMap<>();
        productInOrderUpdateRequests.forEach(productInOrderUpdateRequest -> {
            final String productInOrderId = productInOrderUpdateRequest.getProductInOrderId();
            final String productId = productInOrderUpdateRequest.getProductId();
            if(Strings.isNullOrEmpty(productInOrderId)){
                newProducts.add(createProductInOrder(productId, productInOrderUpdateRequest.getAmount()));
            } else {
                productUpdates.put(productInOrderId, productInOrderUpdateRequest.getAmount());
            }
        });

        productInOrders.forEach(productInOrder -> {
            final Double amount = productUpdates.get(productInOrder.getId());
            if(amount != null){
                productInOrder.setAmount(amount);
            }
        });
        productInOrders.addAll(newProducts);
    }
}
