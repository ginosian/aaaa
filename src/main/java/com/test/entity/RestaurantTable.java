package com.test.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "RestaurantTable")
@Table(name = "restaurant_table")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RestaurantTable extends AbstractEntity{

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "restaurant_table_api_user_fk"))
    private ApiUser waiter;
}
