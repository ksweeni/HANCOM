package com.hancom.model;

import java.util.List;
import java.util.Scanner;

import com.hancom.dto.TeamVO;

public class TeamService {
	TeamDAO team = new TeamDAO();
	private Scanner sc = new Scanner(System.in);

	public List<TeamVO> getAllTeams() {
		return team.getAllTeams();
	}

	public List<TeamVO> getMyTeams(String nickname) {
		return team.getMyTeams(nickname);
	}

	public void updateMemberTypingResult(String nickname, String teamName, String practiceName, String title,
			int score) {
		team.updateMemberTypingResult(nickname, teamName, practiceName, title, score);
	}

	public void leaveTeam(String nickname, String teamName) {
		if (team.leaveTeam(nickname, teamName))
			System.out.println(". · (o > v < o) 팀 탈퇴 성공 · .\n");
		else
			System.out.println("(o`ㅅ`o) . ·팀 탈퇴 불가 <!>· .\n");
	}

	public void createNewTeam(String teamOwner, String teamMember, String teamName, String practiceTitle,
			String categoryName, String title) {

		if (team.createNewTeam(teamOwner, teamMember, teamName, practiceTitle, categoryName, title))
			System.out.println(". · (o > v < o) 팀 생성 성공 · .\n");
		else
			System.out.println("(o`ㅅ`o) . ·팀 생성 실패 <!>· .\n");
	}

}