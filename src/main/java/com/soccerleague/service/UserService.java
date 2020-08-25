package com.soccerleague.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.soccerleague.domain.User;
import com.soccerleague.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
	public Optional<User> findByUsername(String username) {		
		return userRepository.findByUsername(username);
	}
	
	public User add(User p) {
		// TODO Auto-generated method stub
		return userRepository.save(p);
	}	
}
