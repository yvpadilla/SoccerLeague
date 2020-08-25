package com.soccerleague.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.soccerleague.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer>{
	@Query("SELECT a FROM Player a JOIN a.team b WHERE b.id = ?1")
    List<Player> findAllByTeam(Integer team_id);	

    @Query("FROM Player WHERE id = ?1")
    Player listById(Integer id);	
}
