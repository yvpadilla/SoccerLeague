package com.soccerleague.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.soccerleague.domain.League;

public interface LeagueRepository extends CrudRepository<League, Integer>{
	
	//@Query("FROM League")
	//List<League> findAll();

    @Query("SELECT a FROM League a JOIN a.teams b WHERE b.id = ?1")
	List<League> listByTeam(Integer team_id);
	
    //League save(League l);	
        
}
