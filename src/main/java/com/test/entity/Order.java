package com.test.entity;

import com.test.misc.OrderStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "Order")
@Table(name = "order")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractEntity{

    @OneToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "restaurant_table_fk"))
    private RestaurantTable restaurantTable;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductInOrder> productsInOrder;

    @Column(name = "order_status", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
