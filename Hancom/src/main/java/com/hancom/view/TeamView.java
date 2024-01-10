package com.hancom.view;

import java.util.List;

import com.hancom.dto.TeamVO;

public class TeamView {

	public static void print(List<TeamVO> teamlist) {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━[MYTEAM]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		teamlist.stream().forEach(team -> {
			System.out.printf(
					"Group ◈ 그룹명 [%s] | 만든 사람 [%s] | 연습명 [%s] | 인원 수 [%s명] | 나의 타수 [%s점] | 팀 평균 타수 [%.2f점] | 연습 [%s]%n",
					team.getTeam_name(), team.getTeam_owner(), team.getPractice_title(), team.getNumMembers(),
					team.getScore(), team.getAvgScore(), team.getTitle());
		});
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	public static void printTeamInfo(List<TeamVO> teamlist) {
		System.out.println("┴┬┴┬┴┬┴┬┬┴┬┴┬┴┬┴┬┬┴┬┴┴┴┬┬┴┬┴┴┴┬ 팀 정보 ┴┬┴┬┴┬┴┬┬┴┬┴┬┴┬┴┬┬┴┬┴┴┴┬┬┴┬┴┴┴┬");
		System.out.printf("%-20s %-15s %-10s %-15s%n", "[팀 이름]", "[만든 사람]", "[멤버 수]", "[평균 점수]");

		for (TeamVO team : teamlist) {
			String teamName = String.format("%-20s", team.getTeam_name());
			String teamOwner = String.format("%-15s", team.getTeam_owner());
			String numMembers = String.format("%-10d", team.getNumMembers());
			String avgScore = String.format("%-15.2f", team.getAvgScore());

			System.out.printf("%s %s %s %s%n", teamName, teamOwner, numMembers, avgScore);
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
	}

}