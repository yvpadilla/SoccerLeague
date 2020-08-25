package com.soccerleague.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccerleague.dto.AuthenticationRequest;
import com.soccerleague.dto.AuthenticationResponse;
import com.soccerleague.domain.SecurityUserDetails;
import com.soccerleague.service.SecurityUserDetailsService;
import com.soccerleague.util.JwtUtil;

@RestController
@RequestMapping(path = "/authenticate")
public class SecurityController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUser(), authenticationRequest.getPassword())
			);
		} catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final SecurityUserDetails securityUserDetails = 
				securityUserDetailsService.loadUserByUsername(authenticationRequest.getUser());
		final String jwt = jwtTokenUtil.generateToken(securityUserDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}