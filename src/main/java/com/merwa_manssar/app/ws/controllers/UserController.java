package com.merwa_manssar.app.ws.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") //url de l'app localhhost:8080/user
public class UserController {
	@GetMapping
	public String getUser() {
		return "get user was called ";
	}
	@PostMapping
	public String createUser() {
		return "create uses was called";
	}
	@PutMapping
	public String updateUser() {
		return "update user was called ";
	}
	@DeleteMapping
	public String deleteUser() {
		return "delete uses was called";
	}
	
}
