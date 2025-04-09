package com.products.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.model.ItemProduct;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemProduct, Long> {
    List<ItemProduct> findByApproved(boolean approved);
}
