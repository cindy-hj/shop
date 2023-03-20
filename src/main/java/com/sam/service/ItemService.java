package com.sam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.model.Item;
import com.sam.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Item save(Item item) {
		return itemRepository.save(item);
	}

	public List<Item> findAll() {
		
		return itemRepository.findAll();
	}
}
