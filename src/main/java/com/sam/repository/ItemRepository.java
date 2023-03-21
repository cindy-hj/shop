package com.sam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByIdIn(List<Integer> itemIds);

}
