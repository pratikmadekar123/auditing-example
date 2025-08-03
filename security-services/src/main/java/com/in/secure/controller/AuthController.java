package com.in.secure.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.secure.dto.LoginRequest;
import com.in.secure.dto.RegisterRequest;
import com.in.secure.entity.User;
import com.in.secure.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController  {

	  private final AuthService authService;

	    public AuthController(AuthService authService) {
	        this.authService = authService;
	    }

	    @PostMapping("/register")
	    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
	        return ResponseEntity.ok(authService.register(request));
	    }

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
	        return ResponseEntity.ok(authService.login(request));
	    }
}
