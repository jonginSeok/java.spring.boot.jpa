package com.ngins.spring.jpa.postgresql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootHomeController {

	@GetMapping("/")
	public String home() {
		
		
		
		return "index";
		
	}
}
