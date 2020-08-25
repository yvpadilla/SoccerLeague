package com.soccerleague.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.soccerleague.domain.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	@Query("FROM User WHERE username = ?1")
	Optional<User> findByUsername(String username);

	@Query("FROM User WHERE username = ?1")
	User findByUser(String username);	
}
