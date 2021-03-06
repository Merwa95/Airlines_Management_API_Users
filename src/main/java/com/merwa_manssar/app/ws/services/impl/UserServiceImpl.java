package com.merwa_manssar.app.ws.services.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.merwa_manssar.app.ws.entities.UserEntity;
import com.merwa_manssar.app.ws.repositories.UserRepository;
import com.merwa_manssar.app.ws.services.UserService;
import com.merwa_manssar.app.ws.shared.Utils;
import com.merwa_manssar.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils utils;

	@Override
	public UserDto createUser(UserDto userDto) {
		UserEntity checkUser = userRepository.findByEmail(userDto.getEmail());
		if (checkUser != null)
			throw new RuntimeException("USER ALREADY EXISTS");

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		// les valeus par defauts pour les autres attributs

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setUserId(utils.generateStringId(32));

		// persist
		UserEntity usercreated = userRepository.save(userEntity);
		UserDto userDt = new UserDto();
		BeanUtils.copyProperties(usercreated, userDt);
		// System.out.print(userDt.getUserId());

		return userDt;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

}
