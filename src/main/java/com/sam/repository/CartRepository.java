package com.sam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findByMemberIdAndItemId(Integer memberId, Integer itemId);

	List<Cart> findByMemberId(Integer memberId);

}
