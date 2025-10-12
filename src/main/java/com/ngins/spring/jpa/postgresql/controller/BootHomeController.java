package com.ngins.spring.jpa.postgresql.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootHomeController {

	@GetMapping("/")
	public String home(Model model) {
		
		return "index3";
	}

}
