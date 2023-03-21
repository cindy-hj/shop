package com.sam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.model.Cart;
import com.sam.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	public List<Cart> findByMember(Integer memberId) {
		
		return cartRepository.findByMemberId(memberId);
	}
}
