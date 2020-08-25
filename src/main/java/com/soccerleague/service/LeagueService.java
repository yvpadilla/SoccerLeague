package com.soccerleague.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soccerleague.domain.League;
import com.soccerleague.repository.LeagueRepository;

@Service
public class LeagueService {

	private LeagueRepository leagueRepository;

    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }
    
	public Iterable<League> list() {
		return leagueRepository.findAll();
	}
	
	public List<League> listByTeam(Integer id) {
		return leagueRepository.listByTeam(id);
	}
	
	public League add(League l) {
		// TODO Auto-generated method stub
		return leagueRepository.save(l);
	}
}
