package com.soccerleague.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soccerleague.domain.Player;
import com.soccerleague.repository.PlayerRepository;

@Service
public class PlayerService {

	private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    
	public List<Player> findAllByTeam(Integer team_id) {		
		return playerRepository.findAllByTeam(team_id);
	}

	public Player findById(Integer id) {		
		return playerRepository.listById(id);
	}
	
	public Player add(Player p) {
		// TODO Auto-generated method stub
		return playerRepository.save(p);
	}	
}
