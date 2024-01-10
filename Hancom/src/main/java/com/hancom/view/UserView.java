package com.hancom.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hancom.dto.UserVO;

public class UserView {

	public static void printUserRank(ResultSet rs) {
		try {
			System.out.println("\n┴┬┴┬┴┬┴┬┬┴┬┴┬┴┬┴┬┬┴┬┴┴┴┬┬┴┬┴┴┴┬┬┴┴┴┬ 전체 사용자 순위 ┬┴┴┴┬┴┬┴┬┴┬┴┬┬┴┬┴┬┴┬┴┬┬┴┬┴┴┴┬┬┴┬┴┴┴┬");
			System.out.printf("%-15s %-15s %-15s %-20s %-15s%n", "[Nickname]", "[Name]", "[Type]", "[University]",
					"[Level]");

			while (rs.next()) {
				String nickname = rs.getString("NICKNAME");
				String userName = rs.getString("USER_NAME");
				int userType = rs.getInt("USER_TYPE");
				String university = rs.getString("UNIVERSITY");
				String userLevel = rs.getString("USER_LEVEL");

				System.out.printf("%-15s %-15s %-15d %-20s %-15s%n", nickname, userName, userType, university,
						userLevel);
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void printUserInfo(List<UserVO> userList) {
		System.out.println("\n━━━━━━━━━━━━[USERINFO]━━━━━━━━━━━━");
		userList.stream().forEach(user -> {
			System.out.println("유저이름: " + user.getUser_name());
			System.out.println("닉네임: " + user.getNickname());
			System.out.println("비밀번호: " + user.getUser_pwd());
			System.out.println("타수: " + user.getUser_type());
			System.out.println("목표타수: " + user.getUser_goal());
			System.out.println("학교: " + user.getUniversity());
			System.out.println("레벨: " + user.getUser_level());
		});
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
	}

}