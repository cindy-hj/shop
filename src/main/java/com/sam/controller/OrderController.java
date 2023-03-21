package com.sam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.sam.service.CartService;
import com.sam.service.OrderService;

@RestController
@CrossOrigin("*")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
}
