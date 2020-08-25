package com.soccerleague.domain;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@Embeddable
@Table(name="player")
public class Player {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private String surname;
	
	//@ManyToOne(optional =true, fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="team_id", nullable = true)
	@JsonIgnoreProperties("players")
	@JsonIgnore
	private Team team;
	
	public Player() {
	}   
	/*
	public Player(String name, String surname, Team team) {
		this.name = name;
		this.surname = surname;	
		this.team = team;
	}
	*/
	public Player(String name, String surname) {
		this.name = name;
		this.surname = surname;	
	}
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
}