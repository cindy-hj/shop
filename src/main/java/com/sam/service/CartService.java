package com.sam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
}
