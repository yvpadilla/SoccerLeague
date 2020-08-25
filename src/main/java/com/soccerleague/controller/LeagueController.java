package com.soccerleague.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccerleague.domain.League;
import com.soccerleague.service.LeagueService;

@RestController
@RequestMapping("")
public class LeagueController {
	
	private LeagueService service;

    public LeagueController(LeagueService service) {
        this.service = service;
    }
    
	// Listado de las ligas de fútbol nacional
	@GetMapping("/leagues")
	public Iterable<League> list() {
		return service.list();
	}
	
	// Listado de todas las ligas en que participará un equipo en particular
	@GetMapping("/teams/{team_id}/leagues")
	public List<League> listById(@PathVariable String team_id) {	
		Integer id = Integer.parseInt(team_id);
		return service.listByTeam(id);
	}	
}