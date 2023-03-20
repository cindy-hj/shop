package com.sam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sam.model.Item;
import com.sam.service.ItemService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/api/items/add")
	public ResponseEntity<Item> add(HttpServletRequest request, @RequestBody Item item) {
		HttpSession session = request.getSession();
		if(session.getAttribute("id") != null) {
			Item addItem = itemService.save(item);
			return new ResponseEntity<>(addItem,HttpStatus.CREATED);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/api/items")
	public List<Item> getItems(){
		List<Item> items = itemService.findAll();
		return items;
	}
	
}
