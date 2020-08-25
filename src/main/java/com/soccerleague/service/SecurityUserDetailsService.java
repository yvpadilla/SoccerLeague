package com.soccerleague.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soccerleague.domain.SecurityUserDetails;
import com.soccerleague.domain.User;
import com.soccerleague.repository.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public SecurityUserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(usuario);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + usuario));	
		return user.map(SecurityUserDetails::new).get();
	}
	
	public void updateTryQuantityDone(String usuario) throws UsernameNotFoundException {
		User user = userRepository.findByUser(usuario);
		user.setTryquantitydone(user.getTryquantitydone()+1);		
		userRepository.save(user);
	}

	public void initialieTryQuantityDone(String usuario, Integer tryquantitydone) throws UsernameNotFoundException {
		User user = userRepository.findByUser(usuario);
		user.setTryquantitydone(tryquantitydone);		
		userRepository.save(user);
	}	
}
