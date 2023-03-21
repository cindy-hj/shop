package com.sam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.model.Member;
import com.sam.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public Member findByEmailAndPassword(String email, String password) {

		return memberRepository.findByEmailAndPassword(email,password);
	}

	@Transactional
	public Member save(Member member) {
		
		return memberRepository.save(member);
	}
}
