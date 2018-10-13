package com.test.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Product")
@Table(name = "product")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Product extends AbstractEntity{

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
