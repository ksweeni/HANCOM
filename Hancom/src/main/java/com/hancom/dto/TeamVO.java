package com.hancom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeamVO {
	private int team_id;
	private int score;
	private String team_name;
	private String team_owner;
	private String team_member;
	private String practice_title;
	private String category_name;
	private int category_id;
	private String title;
	private int numMembers;
	private double avgScore;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Group ◈");
		if (team_name != null) {
			builder.append("  그룹명 [").append(team_name).append("] ");
		}
		if (team_owner != null) {
			builder.append("| 만든 사람 [").append(team_owner).append("] ");
		}
		if (practice_title != null) {
			builder.append("| 연습명 [").append(practice_title).append("] ");
		}
		if (numMembers != 0) {
			builder.append("| 인원 수 [").append(numMembers).append("명] ");
		}
		if (score >= 0) {
			builder.append("| 멤버 점수 [").append(score).append("점] ");
		}
		if (avgScore >= 0) {
			builder.append("| 팀 평균 점수 [").append(avgScore).append("점] ");
		}
		if (title != null) {
			builder.append("| 문제명 [").append(title).append("] ");
		}
		return builder.toString();
	}

}