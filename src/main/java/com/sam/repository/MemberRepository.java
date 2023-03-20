package com.sam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	Member findByEmailAndPassword(String email, String password);

}
