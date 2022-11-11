package com.mphasis.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sec/api")
public class ApplicationController {
	
	@GetMapping("/greeting")
	public String greeting() {
		return "Welcome Spring Security";
	}

}
