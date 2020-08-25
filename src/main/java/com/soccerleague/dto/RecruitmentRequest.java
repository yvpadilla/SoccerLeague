package com.soccerleague.dto;

public class RecruitmentRequest {

	private int team_id;
	private int player_id;
	
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public RecruitmentRequest(int player_id, int team_id) {
		super();
		this.player_id = player_id;
		this.team_id = team_id;
	}
	public RecruitmentRequest() {
		super();
	}
	
}
