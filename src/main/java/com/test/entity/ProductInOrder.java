package com.test.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "ProductInOrder")
@Table(name = "product_in_order")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductInOrder extends AbstractEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "product_product_in_order_fk"))
    private Product product;

    @Column(name = "amount", nullable = false, unique = true)
    private Double amount;
}
