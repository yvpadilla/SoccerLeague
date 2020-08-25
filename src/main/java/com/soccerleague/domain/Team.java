package com.soccerleague.domain;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@Embeddable
@Table(name="team")
public class Team {
	@Id
	@Column(name="team_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	
	@OneToMany (mappedBy="team", fetch = FetchType.LAZY)
	//@OneToMany(mappedBy="team", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("team")
	@JsonIgnore
	private Set<Player> players = new HashSet<>();
	
	@JoinTable(
	        name = "team_league",
	        joinColumns = {@JoinColumn(name = "team_id")},
	        inverseJoinColumns = {@JoinColumn(name="league_id")}	
	)
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })	
	//@ManyToMany (fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	@JsonIgnoreProperties("teams")
	@JsonIgnore
	private Set<League> leagues = new HashSet<>();
	
	public Set<League> getLeagues() {
		return leagues;
	}
	public void setLeagues(Set<League> leagues) {
		this.leagues = leagues;
	}
	
	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	
	public Team() {}
	
	public Team(String name) {
		this.name = name;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}