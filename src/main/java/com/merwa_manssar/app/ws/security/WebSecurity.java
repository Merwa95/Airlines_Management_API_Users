package com.merwa_manssar.app.ws.security;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.merwa_manssar.app.ws.services.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	


	//
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder  bCryptPasswordEncoder; 

	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.cors().and()//communiquer avec d'autre systemes 
		.csrf().disable()
		.authorizeRequests().antMatchers(HttpMethod.POST,SecurityConstants.SIGN_IN_URL)
		.permitAll()
		.anyRequest().authenticated();
	}
	
	@Override
	
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	
}
