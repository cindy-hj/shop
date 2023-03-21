package com.sam.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sam.model.Cart;
import com.sam.model.Item;
import com.sam.repository.CartRepository;
import com.sam.repository.ItemRepository;
import com.sam.service.CartService;

@RestController
@CrossOrigin("*")
public class CartController {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private CartService cartService;
	
	// create
	@PostMapping("/api/cart/items") 
	public ResponseEntity pushCartItem(@RequestBody Map<String,String> params) {
		Integer memberId = Integer.parseInt(params.get("memberId"));
		Integer itemId = Integer.parseInt(params.get("itemId"));
		
		Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);
		if(cart == null) {
			Cart newCart = new Cart();
			newCart.setMemberId(memberId);
			newCart.setItemId(itemId);
			cartRepository.save(newCart);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/api/cart/items")
	public ResponseEntity removeCartItem(@RequestBody Map<String, String> params) {
		Integer memberId = Integer.parseInt(params.get("memberId"));
		Integer itemId = Integer.parseInt(params.get("itemId"));
		
		Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);
		cartRepository.delete(cart);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/api/cart/items/{memberId}")
	public ResponseEntity getCartItems(@PathVariable Integer memberId) {
		List<Cart> carts = cartRepository.findByMemberId(memberId);
		// stream은 데이터가 줄지어 있는것, map은 데이터를 둘러싸는것, Cart를 getItemId라는 형식으로 다시 싸는것
		List<Integer> itemIds = carts.stream().map(Cart::getItemId).toList();
		List<Item> items = itemRepository.findByIdIn(itemIds);
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@GetMapping("/api/carts/{memberId}")
	public ResponseEntity getCarts(@PathVariable Integer memberId) {
		List<Cart> carts = cartService.findByMember(memberId);
		return new ResponseEntity<>(carts, HttpStatus.OK);
	}
	
	
}

