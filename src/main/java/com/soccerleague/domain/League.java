package com.soccerleague.domain;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@Embeddable
@Table(name="league")
public class League {
	@Id
	@Column(name="league_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private Date beginDate = new Date();
	@Column
	private Date endDate = new Date();;
	@Column
	private int dateQuantity;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                }, mappedBy = "leagues")
	//@ManyToMany
	@JsonIgnoreProperties("league")
	@JsonIgnore
	private Set<Team> teams = new HashSet<>();;
	
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
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getDateQuantity() {
		return dateQuantity;
	}
	public void setDateQuantity(int dateQuantity) {
		this.dateQuantity = dateQuantity;
	}	
	public League() {}
	public League(String name, Date beginDate, Date endDate, int dateQuantity) {
		this.name = name;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.dateQuantity = dateQuantity;
	}
	public Set<Team> getTeams() {
		return teams;
	}
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
}