package com.soccerleague.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccerleague.domain.Player;
import com.soccerleague.domain.Team;
import com.soccerleague.dto.RecruitmentRequest;
import com.soccerleague.service.PlayerService;
import com.soccerleague.service.TeamService;

@RestController
@RequestMapping("")
public class PlayerController {
	
	private PlayerService playerService;
	
	private TeamService teamService;

    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }
    
	// Listado de todos los jugadores de un equipo
	@GetMapping(value="/teams/{team_id}/players")
	public List<Player> listByTeam(@PathVariable String team_id) {
		Integer id = Integer.parseInt(team_id);
		return playerService.findAllByTeam(id);
	}
	
	// Creacion de un jugador
	@PostMapping(value="/players")
	public Player add(@RequestBody Player player) {
		Player p1 = new Player(player.getName(), player.getSurname());
		return playerService.add(p1);
	}	

	// Incorporación de jugador
	@PutMapping(value="/teams")
	public Player update(@RequestBody RecruitmentRequest recruitmentRequest) {
		int player_id = recruitmentRequest.getPlayer_id();
		Player p1 = playerService.findById(player_id);
		if (p1 != null) {
			int team_id = recruitmentRequest.getTeam_id();
			Team t1 = teamService.findById(team_id);		
			if (t1 != null) {
				p1.setTeam(t1);
				return playerService.add(p1);
			}
		}
		return null;
	}		
	
}