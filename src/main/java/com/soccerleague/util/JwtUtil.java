package com.soccerleague.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

import com.soccerleague.domain.SecurityUserDetails;
import com.soccerleague.service.SecurityUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String SECRET_KEY = "secret";
	public boolean tokenRestricted = false;

	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(SecurityUserDetails securityUserDetails) {
		Map<String, Object> claims = new HashMap<>();
		
		securityUserDetails.getAuthorities().forEach (e -> { 
			if(e.getAuthority().equals("FREE_USER")) {
				tokenRestricted = true;
			}
		});
		if (tokenRestricted) {			
			securityUserDetailsService.initialieTryQuantityDone(securityUserDetails.getUsername(), 0);
			return createTokenRestricted(claims, securityUserDetails.getUsername());
		} else {
			return createTokenNoRestricted(claims, securityUserDetails.getUsername());			
		}
	}
	
	private String createTokenRestricted(Map<String, Object> claims, String subject) {
		// Expiration in 1 minute after creation
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1 ))	
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	private String createTokenNoRestricted(Map<String, Object> claims, String subject) {
		// No expiration
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
		// Expiration in 1000 years after creation		
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 365 ))	
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validateToken(String token, SecurityUserDetails securityUserDetails) {
		final String username = extractUsername(token);
		return (username.equals(securityUserDetails.getUsername()) && !isTokenExpired(token));
	}
}
