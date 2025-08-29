/**
 * 
 */
package com.ngins.spring.jpa.postgresql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
public class HomeController {
	@GetMapping("/")
    public String index() {
        return "index"; // templates/index.html (Thymeleaf 등)
    }
}
