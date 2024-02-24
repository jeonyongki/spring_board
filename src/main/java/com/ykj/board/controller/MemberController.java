package com.ykj.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ykj.board.dto.MemberDto;
import com.ykj.board.service.MemberService;

import lombok.extern.java.Log;
@Log
@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	//회원가입
	@PostMapping("memberInsert")
	public String memberInsert(MemberDto member, RedirectAttributes redirectAttributes) {
		log.info("memberInsert()");
		String view = memberService.memberInsert(member, redirectAttributes);
		return view;
	}
	//아이디 중복체크
	@GetMapping(value="idCheck")
	public @ResponseBody boolean idCheck(String id) {
		boolean isCount = memberService.memberIdCheck(id);
		log.info("idCheck() : "+isCount);
		return isCount; 
	}
	//로그인
	@PostMapping("loginProc")
	public String loginProc(MemberDto dto, RedirectAttributes redirectAttributes) {
		log.info("loginProc");
		String view = memberService.memberLogin(dto, redirectAttributes);
		return view;
	}
}
