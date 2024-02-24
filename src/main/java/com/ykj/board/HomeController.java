package com.ykj.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.java.Log;
@Log
@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		log.info("home()");
		return "home";
	}
	@GetMapping("loginForm")
	public String loginForm() {
		log.info("loginForm()");
		
		return "loginForm";
	}
	@GetMapping("joinForm")
	public String joinForm() {
		log.info("joinForm()");
		
		return "joinForm";
	}

}
