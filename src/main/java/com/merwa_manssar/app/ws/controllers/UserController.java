package com.merwa_manssar.app.ws.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merwa_manssar.app.ws.request.UserRequest;
import com.merwa_manssar.app.ws.response.UserResponse;
import com.merwa_manssar.app.ws.services.UserService;
import com.merwa_manssar.app.ws.shared.dto.UserDto;

@RestController
@RequestMapping("/users") // url de l'app localhhost:8080/user
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public String getUser() {
		return "get user was called ";
	}

	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest userRequest) {

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		UserDto createUser = userService.createUser(userDto);

		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(createUser, userResponse);
		return userResponse;
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
