package com.soccerleague.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.soccerleague.domain.SecurityUserDetails;
import com.soccerleague.service.SecurityUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	boolean tokenRestricted;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		try {
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwt = authorizationHeader.substring(7);
				username = jwtUtil.extractUsername(jwt);
			}
					
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				SecurityUserDetails userDetails = this.securityUserDetailsService.loadUserByUsername(username);
	
				// Found if token is restricted
				userDetails.getAuthorities().forEach (e -> { 
					if(e.getAuthority().equals("FREE_USER")) {
						tokenRestricted = true;
					}
				});
				
				if ((userDetails.getTryquantitydone() < userDetails.getTryquantityallowed() && tokenRestricted) || !tokenRestricted) { 
					if(jwtUtil.validateToken(jwt, userDetails)) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new 
								UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken 
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
						if (tokenRestricted) {
							this.securityUserDetailsService.updateTryQuantityDone(username);
						}
					}
				}
			}
		} catch(Exception e) {
			System.out.println("Warning : "+e.getMessage());
		}
		chain.doFilter(request, response);
	}
	
	
}
