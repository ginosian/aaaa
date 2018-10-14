package com.test.repository;

import com.test.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, String> {

    List<RestaurantTable> findAllByWaiter_idAndDeletedFalse(String waiter_id);
}
