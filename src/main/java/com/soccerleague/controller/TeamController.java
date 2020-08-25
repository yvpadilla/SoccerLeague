package com.soccerleague.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccerleague.domain.Team;
import com.soccerleague.service.TeamService;

@RestController
@RequestMapping("")
public class TeamController {
	
	private TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }
    
	// Listado de equipos que participarán de una liga en particular
	@GetMapping(value="/leagues/{league_id}/teams")
	public List<Team> listByTeam(@PathVariable String league_id) {
		Integer id = Integer.parseInt(league_id);
		return service.listByLeague(id);
	}
}