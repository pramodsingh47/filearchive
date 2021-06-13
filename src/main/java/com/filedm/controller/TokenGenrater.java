package com.filedm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.filedm.dto.JwtRequest;
import com.filedm.dto.JwtResponse;
import com.filedm.helper.JwtUtil;
import com.filedm.service.UserDetailsServiceImp;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class TokenGenrater 
{
	@Autowired
	private UserDetailsServiceImp user;
	
	@Autowired
	private JwtUtil util;
	
	@Autowired
	private AuthenticationManager manager;



	@RequestMapping(value = "/token" , method = RequestMethod.POST )
	public ResponseEntity<?> getHello(@RequestBody JwtRequest jwtRequest ) throws Exception
	{
		System.out.println(jwtRequest);
		jwtRequest.setUserName("Pramod");
		jwtRequest.setPassword("pramod123");

		try
		{
			this.manager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		}
		catch (UsernameNotFoundException e)
		{
			e.printStackTrace();
			throw new Exception("Bed Credintials");
		}
		
		UserDetails detailsService = this.user.loadUserByUsername(jwtRequest.getUserName());
		String token = this.util.generateToken(detailsService);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
