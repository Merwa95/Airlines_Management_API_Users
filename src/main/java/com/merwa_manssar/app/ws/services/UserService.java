package com.merwa_manssar.app.ws.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.merwa_manssar.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto userDto);
    UserDto getUser (String email);
}
