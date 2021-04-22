package com.merwa_manssar.app.ws.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	Utils utils;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		UserEntity checkUser=userRepository.findByEmail(userDto.getEmail());
		if(checkUser != null) throw new RuntimeException("USER ALREADY EXISTS");
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		// les valeus par defauts pour les autres attributs

		userEntity.setEncryptedPassword("test password");
		userEntity.setUserId(utils.generateStringId(32));
		
		// persist
		UserEntity usercreated = userRepository.save(userEntity);
		UserDto userDt = new UserDto();
		BeanUtils.copyProperties(usercreated, userDt);
		//System.out.print(userDt.getUserId());

		return userDt;
	}

}
