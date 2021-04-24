package com.merwa_manssar.app.ws.security;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.cors().and()//communiquer avec d'autre systemes 
		.csrf().disable()
		.authorizeRequests().antMatchers(HttpMethod.POST,"/users")
		.permitAll()
		.anyRequest().authenticated();
	}
}
