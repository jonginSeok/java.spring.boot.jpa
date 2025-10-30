package com.ngins.spring.jpa.postgresql.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/hello")
	public String hello(Model model) {
		
		model.addAttribute("name", "홍길동");
		model.addAttribute("isLogin", true);
		model.addAttribute("items", Arrays.asList("사과", "바나나", "포도"));
		return "hello"; // templates/hello.html
	}
	
	
	//  
	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/welcome")
	public String welcome(Model model) {
		
		model.addAttribute("name", "홍길동");
		model.addAttribute("isLogin", true);
		model.addAttribute("items", Arrays.asList("사과", "바나나", "포도"));
		return "welcome"; // templates/hello.html
	}
	
}
