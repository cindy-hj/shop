package com.sam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
