package com.in.secure.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class TestController {

	@Value("${example.message}")
	private String message;
	@GetMapping("/msg")
	public String getSecurityMessage() {
		return message;
	}

	@GetMapping("/hello")
	public String userHello() {
		return "Hello User!";
	}
}
