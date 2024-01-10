package com.hancom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hancom.dto.UserVO;
import com.hancom.util.DBUtil;
import com.hancom.view.UserView;

public class UserDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	public boolean login(String nickname, String user_pwd) {
		conn = DBUtil.getConnection();
		boolean flag = false;
		String sql = "SELECT NICKNAME, USER_PWD FROM LOGIN WHERE NICKNAME = ? AND USER_PWD = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, nickname);
			pst.setString(2, user_pwd);
			rs = pst.executeQuery();

			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return flag;
	}

	public boolean register(String username, String nickname, String user_pwd, int user_type, int user_goal,
			String university, String user_level) {
		conn = DBUtil.getConnection();
		boolean flag = false;
		String sql = "INSERT INTO LOGIN (USER_ID, NICKNAME, USER_NAME, USER_PWD, USER_TYPE, USER_GOAL, UNIVERSITY, USER_LEVEL) "
				+ "VALUES (LOGIN_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, nickname);
			pst.setString(2, username);
			pst.setString(3, user_pwd);
			pst.setInt(4, user_type);
			pst.setInt(5, user_goal);
			pst.setString(6, university);
			pst.setString(7, user_level);

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				flag = true;
			} else {
				flag = false; // 실패
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return flag;
	}

	// 닉네임 중복 확인
	public boolean isNicknameExists(String nickname) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) FROM LOGIN WHERE NICKNAME = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, nickname);
			rs = pst.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0; // 닉네임이 이미 존재하면 true 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return false; // 닉네임이 존재하지 않으면 false 반환
	}

	// 유저의 타수 업데이트
	public int updateTypeAndLevel(String nickname, int newUserType) {

		int updatedUserType = -1;

		try {
			conn = DBUtil.getConnection();

			String sql = "UPDATE LOGIN SET USER_TYPE = ? WHERE NICKNAME = ? AND USER_TYPE < ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, newUserType);
			pst.setString(2, nickname);
			pst.setInt(3, newUserType);

			int rowsUpdated = pst.executeUpdate();

			if (rowsUpdated > 0) {
				updatedUserType = newUserType; // 타입 업데이트 성공

				// 사용자 레벨 업데이트
				String userLevel = calculateUserLevel(newUserType);
				sql = "UPDATE LOGIN SET USER_LEVEL = ? WHERE NICKNAME = ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, userLevel);
				pst.setString(2, nickname);
				pst.executeUpdate();
				System.out.println("< (! o !) LEVEL UPDATE !> [ " + newUserType + "타/" + userLevel + " ]\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}

		return updatedUserType;
	}

	// 점수에 의한 레벨 업데이트
	public String calculateUserLevel(int userType) {
		String userLevel;
		if (userType < 200) {
			userLevel = "BRONZE";
		} else if (userType <= 400) {
			userLevel = "SILVER";
		} else {
			userLevel = "GOLD";
		}
		return userLevel;
	}

	// 개인정보 설정 - 정보 보여주기
	public void userProfile(String nickname) {
		String sql = "SELECT * FROM LOGIN WHERE NICKNAME = ?";
		List<UserVO> users = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, nickname);
			rs = pst.executeQuery();

			if (rs.next()) {
				UserVO user = new UserVO();
				user.setUser_name(rs.getString("USER_NAME"));
				user.setNickname(rs.getString("NICKNAME"));
				user.setUser_pwd(rs.getString("USER_PWD"));
				user.setUser_type(rs.getInt("USER_TYPE"));
				user.setUser_goal(rs.getInt("USER_GOAL"));
				user.setUniversity(rs.getString("UNIVERSITY"));
				user.setUser_level(rs.getString("USER_LEVEL"));
				users.add(user);
				UserView.printUserInfo(users);

			} else {
				System.out.println("(o`ㅅ`o) . · 해당 닉네임의 사용자 정보를 찾을 수 없습니다 · .");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	// Added - 리스트로 반환하기
	public List<UserVO> getUserProfileList(String nickname) {
		String sql = "SELECT * FROM LOGIN WHERE NICKNAME = ?";
		List<UserVO> users = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, nickname);
			rs = pst.executeQuery();

			while (rs.next()) {
				UserVO user = new UserVO();
				user.setUser_name(rs.getString("USER_NAME"));
				user.setNickname(rs.getString("NICKNAME"));
				user.setUser_pwd(rs.getString("USER_PWD"));
				user.setUser_type(rs.getInt("USER_TYPE"));
				user.setUser_goal(rs.getInt("USER_GOAL"));
				user.setUniversity(rs.getString("UNIVERSITY"));
				user.setUser_level(rs.getString("USER_LEVEL"));
				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return users;
	}

	public boolean updateUserProfile(String newNickname, String newName, String newUniv, int newGoal, String nickName) {
		String sql = "UPDATE LOGIN SET USER_NAME = ?, UNIVERSITY = ?, USER_GOAL = ?, NICKNAME = ? WHERE NICKNAME =?";

		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, newName);
			pst.setString(2, newUniv);
			pst.setInt(3, newGoal);
			pst.setString(4, newNickname);
			pst.setString(5, nickName);

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				return true; // 정보 업데이트 성공 시 true 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}

		return false; // 정보 업데이트 실패 시 false 반환
	}

	public boolean deleteUser(String nickname) {
		String sql = "DELETE FROM LOGIN WHERE NICKNAME = ?";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, nickname);
			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}
		return false;
	}

	public void getAllUserRank() {
		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT NICKNAME, USER_NAME, USER_TYPE, UNIVERSITY, USER_LEVEL " + "FROM LOGIN "
					+ "ORDER BY USER_TYPE DESC";

			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			UserView.printUserRank(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public List<UserVO> getAllUserRankByList() {
		List<UserVO> userRanks = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT NICKNAME, USER_NAME, USER_TYPE, UNIVERSITY, USER_LEVEL FROM LOGIN ORDER BY USER_TYPE DESC";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				UserVO userRank = new UserVO();
				userRank.setNickname(rs.getString("NICKNAME"));
				userRank.setUser_name(rs.getString("USER_NAME"));
				userRank.setUser_type(rs.getInt("USER_TYPE"));
				userRank.setUniversity(rs.getString("UNIVERSITY"));
				userRank.setUser_level(rs.getString("USER_LEVEL"));

				userRanks.add(userRank);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return userRanks;
	}

	public void getUserRankByLevel(String level) {
		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT NICKNAME, USER_NAME, USER_TYPE, UNIVERSITY, USER_LEVEL " + "FROM LOGIN "
					+ "WHERE USER_LEVEL = ? " + "ORDER BY USER_TYPE DESC";

			pst = conn.prepareStatement(sql);
			pst.setString(1, level);
			rs = pst.executeQuery();

			UserView.printUserRank(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public List<UserVO> getUserRankByLevelToList(String level) {
		List<UserVO> userRanks = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT NICKNAME, USER_NAME, USER_TYPE, UNIVERSITY, USER_LEVEL " + "FROM LOGIN "
					+ "WHERE USER_LEVEL = ? " + "ORDER BY USER_TYPE DESC";

			pst = conn.prepareStatement(sql);
			pst.setString(1, level);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				UserVO userRank = new UserVO();
				userRank.setNickname(rs.getString("NICKNAME"));
				userRank.setUser_name(rs.getString("USER_NAME"));
				userRank.setUser_type(rs.getInt("USER_TYPE"));
				userRank.setUniversity(rs.getString("UNIVERSITY"));
				userRank.setUser_level(rs.getString("USER_LEVEL"));

				userRanks.add(userRank);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return userRanks;
	}

}