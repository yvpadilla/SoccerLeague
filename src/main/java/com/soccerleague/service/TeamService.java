package com.soccerleague.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.soccerleague.domain.Team;
import com.soccerleague.repository.TeamRepository;

@Service
public class TeamService {

	private TeamRepository teamRepository;

    public TeamService (TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    
	public List<Team> listByLeague(Integer league_id) {
		return teamRepository.findByLeague(league_id);
	}

	public Team findById(Integer id) {
		return teamRepository.listById(id);
	}	
	
	public Team add(Team t) {
		// TODO Auto-generated method stub
		return teamRepository.save(t);
	}			
}
