package com.sam.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sam.model.Member;
import com.sam.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping("/api/account/login")
	public ResponseEntity login(@RequestBody Map<String,String> params,
			HttpServletRequest request) {
		Member member = memberService.findByEmailAndPassword(params.get("email"), params.get("password"));
		if(member != null) {
			Integer id = member.getId();
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			return new ResponseEntity<>(id,HttpStatus.OK);
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/api/account/logout")
	public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/api/account/check")
	public ResponseEntity check(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id") != null) {
			Integer id = (Integer)session.getAttribute("id");
			return new ResponseEntity<>(id,HttpStatus.OK);
		}
		return new ResponseEntity<Member>(new Member(), HttpStatus.OK);
	}
	
}
