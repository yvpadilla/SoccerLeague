package com.soccerleague;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.soccerleague.domain.League;
import com.soccerleague.domain.Player;
import com.soccerleague.domain.Team;
import com.soccerleague.domain.User;
import com.soccerleague.service.*;

@SpringBootApplication
public class SoccerLeagueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerLeagueApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(LeagueService leagueService, TeamService teamService, UserService userService, PlayerService playerService){
	    return args -> {

			// LEAGUES
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
			Date beginDate = ft.parse("2020-01-11"); 
			Date endDate = ft.parse("2020-11-11"); 
			League league1 = new League("League 1", beginDate, endDate, 50);
			League league2 = new League("League 2", beginDate, endDate, 45);
			League league3 = new League("League 3", beginDate, endDate, 39);
			League league4 = new League("League 4", beginDate, endDate, 14);
			leagueService.add(league1);
			leagueService.add(league2);
			leagueService.add(league3);
			leagueService.add(league4);
			
			// TEAMS
			Team team1 = new Team("Team 1");
			Team team2 = new Team("Team 2");
			Team team3 = new Team("Team 3");
			Team team4 = new Team("Team 4");
			teamService.add(team1);
			teamService.add(team2);
			teamService.add(team3);
			teamService.add(team4);
			team1.getLeagues().add(league1);
			team2.getLeagues().add(league1);
			team3.getLeagues().add(league1);
			team4.getLeagues().add(league1);			
			league1.getTeams().add(team1);
			league1.getTeams().add(team2);
			league1.getTeams().add(team3);
			league1.getTeams().add(team4);
			leagueService.add(league1);			
			
			Team team11 = new Team("Team 11");
			Team team12 = new Team("Team 12");
			Team team13 = new Team("Team 13");
			Team team14 = new Team("Team 14");
			teamService.add(team11);
			teamService.add(team12);
			teamService.add(team13);
			teamService.add(team14);
			team11.getLeagues().add(league2);
			team12.getLeagues().add(league2);
			team13.getLeagues().add(league2);
			team14.getLeagues().add(league2);
			league2.getTeams().add(team11);
			league2.getTeams().add(team12);
			league2.getTeams().add(team13);
			league2.getTeams().add(team14);
			leagueService.add(league2);			
			
			Team team21 = new Team("Team 21");
			Team team22 = new Team("Team 22");
			Team team23 = new Team("Team 23");
			Team team24 = new Team("Team 24");
			teamService.add(team21);
			teamService.add(team22);
			teamService.add(team23);
			teamService.add(team24);			
			team21.getLeagues().add(league3);
			team22.getLeagues().add(league3);
			team23.getLeagues().add(league3);
			team24.getLeagues().add(league3);
			league3.getTeams().add(team21);
			league3.getTeams().add(team22);
			league3.getTeams().add(team23);
			league3.getTeams().add(team24);			
			leagueService.add(league3);			

			Team team31 = new Team("Team 31");
			Team team32 = new Team("Team 32");
			Team team33 = new Team("Team 33");
			Team team34 = new Team("Team 34");
			teamService.add(team31);
			teamService.add(team32);
			teamService.add(team33);
			teamService.add(team34);						
			team31.getLeagues().add(league4);
			team32.getLeagues().add(league4);
			team33.getLeagues().add(league4);
			team34.getLeagues().add(league4);
			league3.getTeams().add(team31);
			league3.getTeams().add(team32);
			league3.getTeams().add(team33);
			league3.getTeams().add(team34);			
			leagueService.add(league4);			
			
			// PLAYERS
			Player player1 = new Player("Yuri", "Padilla");
			Player player2 = new Player("Martin", "Sedanos");		
			Player player3 = new Player("Robert", "Gabino");		
			Player player4 = new Player("Enrique", "Zela");		
			Player player5 = new Player("Enrique", "Olaya");		
			Player player6 = new Player("Julio", "Pacheco");		
			playerService.add(player1);
			playerService.add(player2);
			playerService.add(player3);
			playerService.add(player4);
			playerService.add(player5);
			playerService.add(player6);			
			
			player1.setTeam(team1);
			player2.setTeam(team1);
			player3.setTeam(team1);
			player4.setTeam(team1);
			player5.setTeam(team1);
			player6.setTeam(team1);			
			team1.getPlayers().add(player1);
			team1.getPlayers().add(player2);
			team1.getPlayers().add(player3);
			team1.getPlayers().add(player4);
			team1.getPlayers().add(player5);
			team1.getPlayers().add(player6);
			playerService.add(player1);
			playerService.add(player2);
			playerService.add(player3);
			playerService.add(player4);
			playerService.add(player5);
			playerService.add(player6);			
			
			
			// USERS
			User user1 = new User("payer_user", "password", true, "PAID_USER", 3, 0);
			User user2 = new User("free_user", "password", true, "FREE_USER", 3, 0);
			userService.add(user1);
			userService.add(user2);
			
			System.out.println("Database has been initialized");	    	
	    };
	}	
}
