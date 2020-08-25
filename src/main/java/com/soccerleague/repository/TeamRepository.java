package com.soccerleague.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.soccerleague.domain.Team;

public interface TeamRepository extends CrudRepository<Team, Integer>{
	
	@Query("SELECT a FROM Team a JOIN a.leagues b WHERE b.id = ?1")
	List<Team> findByLeague(Integer league_id);
	
	@Query("FROM Team WHERE id = ?1")
	Team listById(Integer id);
	
	//Team save(Team t);	
}
