package com.hancom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hancom.dto.TeamVO;
import com.hancom.util.DBUtil;


public class TeamDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	UserService us;

	public List<TeamVO> getAllTeams() {
		List<TeamVO> teams = new ArrayList<>();
		String sql = "SELECT T.TEAM_NAME, T.TEAM_OWNER, COUNT(T.TEAM_MEMBER) AS NUM_MEMBERS, AVG(T.SCORE) AS AVG_SCORE "
				+ "FROM TEAM T " + "GROUP BY T.TEAM_NAME, T.TEAM_OWNER";
		conn = DBUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				TeamVO team = new TeamVO();
				team.setTeam_name(rs.getString("TEAM_NAME"));
				team.setTeam_owner(rs.getString("TEAM_OWNER"));
				team.setNumMembers(rs.getInt("NUM_MEMBERS"));
				team.setAvgScore(rs.getDouble("AVG_SCORE"));
				teams.add(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return teams;
	}

	public List<TeamVO> getMyTeams(String nickname) {
		List<TeamVO> teams = new ArrayList<>();
		String sql = "SELECT DISTINCT TEAM_NAME, TEAM_OWNER, PRACTICE_TITLE, "
				+ "(SELECT COUNT(*) FROM TEAM T2 WHERE T2.TEAM_NAME = TEAM.TEAM_NAME) AS NUM_MEMBERS, "
				+ "(SELECT SCORE FROM TEAM T2 WHERE T2.TEAM_MEMBER = ? AND T2.TEAM_NAME = TEAM.TEAM_NAME AND T2.PRACTICE_TITLE = TEAM.PRACTICE_TITLE AND T2.TITLE = TEAM.TITLE) AS SCORE, "
				+ "(SELECT AVG(SCORE) FROM TEAM T2 WHERE T2.TEAM_NAME = TEAM.TEAM_NAME) AS AVG_SCORE, " + "TITLE "
				+ "FROM TEAM WHERE TEAM_MEMBER = ? ORDER BY 1";
		conn = DBUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, nickname);
			pst.setString(2, nickname);
			rs = pst.executeQuery();

			while (rs.next()) {
				TeamVO team = new TeamVO();
				team.setTeam_name(rs.getString("TEAM_NAME"));
				team.setTeam_owner(rs.getString("TEAM_OWNER"));
				team.setPractice_title(rs.getString("PRACTICE_TITLE"));
				team.setScore(rs.getInt("SCORE"));
				team.setTitle(rs.getString("TITLE"));
				team.setNumMembers(rs.getInt("NUM_MEMBERS"));
				team.setAvgScore(rs.getDouble("AVG_SCORE")); // 평균 점수 설정
				teams.add(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return teams;
	}

	// 팀 점수 업데이트, 업데이트 시 그 점수 반환, 없을 시 0
	public void updateMemberTypingResult(String nickname, String teamName, String practiceName, String title,
			int newScore) {
		String sqlUpdateScore = "UPDATE TEAM T SET T.SCORE = ? "
				+ "WHERE T.TEAM_MEMBER = ? AND T.PRACTICE_TITLE = ? AND T.TITLE = ?";

		String sqlUpdateAvgScore = "UPDATE TEAM T "
				+ "SET T.AVGSCORE = (SELECT AVG(SCORE) FROM TEAM WHERE TEAM_NAME = T.TEAM_NAME) "
				+ "WHERE T.TEAM_NAME = ?";

		conn = DBUtil.getConnection();
		us = new UserService(); // UserService 객체 초기화

		int updatedIndividualScore = 0;

		try {
			conn.setAutoCommit(false); // Auto-commit 모드 해제

			// 업데이트할 개인 점수
			pst = conn.prepareStatement(sqlUpdateScore);
			pst.setInt(1, newScore);
			pst.setString(2, nickname);
			pst.setString(3, practiceName);
			pst.setString(4, title);

			int updatedRows = pst.executeUpdate();

			if (updatedRows > 0) {
				// 개인 점수 업데이트 성공 시 USER_TYPE 업데이트
				us.updateType(nickname, newScore);
				updatedIndividualScore = newScore;
				System.out.println("<!>개인 점수가 업데이트 되었습니다. 새로운 타수 >> " + updatedIndividualScore+"<!>");
			}

			// 팀의 평균 점수 업데이트
			pst = conn.prepareStatement(sqlUpdateAvgScore);
			pst.setString(1, teamName);
			pst.executeUpdate();

			System.out.println("<!>팀의 점수 및 평균이 업데이트 되었습니다<!>\n");

			conn.commit(); // 트랜잭션 커밋
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback(); // 롤백
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true); // Auto-commit 모드 복원
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.dbDisconnect(conn, pst, null);
		}
	}

	public boolean leaveTeam(String nickname, String teamName) {
		String sqlDeleteMember = "DELETE FROM TEAM WHERE TEAM_MEMBER = ? AND TEAM_NAME = ?";
		String sqlUpdateAvgScore = "UPDATE TEAM T "
				+ "SET T.AVGSCORE = (SELECT AVG(SCORE) FROM TEAM WHERE TEAM_NAME = T.TEAM_NAME) "
				+ "WHERE T.TEAM_NAME = ?";

		conn = DBUtil.getConnection();

		try {
			conn.setAutoCommit(false); // Auto-commit 모드 해제

			// 팀에서 사용자 삭제
			pst = conn.prepareStatement(sqlDeleteMember);
			pst.setString(1, nickname);
			pst.setString(2, teamName);

			int deletedRows = pst.executeUpdate();

			if (deletedRows > 0) {
				// 팀에서 탈퇴 성공 시 평균 점수 업데이트
				pst = conn.prepareStatement(sqlUpdateAvgScore);
				pst.setString(1, teamName);
				pst.executeUpdate();

				// 팀의 OWNER와 입력된 닉네임이 같으면 팀 삭제
				String sqlCheckOwner = "SELECT TEAM_OWNER FROM TEAM WHERE TEAM_NAME = ?";
				pst = conn.prepareStatement(sqlCheckOwner);
				pst.setString(1, teamName);
				rs = pst.executeQuery();

				if (rs.next()) {
					String teamOwner = rs.getString("TEAM_OWNER");
					if (nickname.equals(teamOwner)) {
						String sqlDeleteTeam = "DELETE FROM TEAM WHERE TEAM_NAME = ?";
						pst = conn.prepareStatement(sqlDeleteTeam);
						pst.setString(1, teamName);
						pst.executeUpdate();
					}
				}

				conn.commit(); // 트랜잭션 커밋
				return true; // 탈퇴 성공
			}

			conn.rollback(); // 롤백
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback(); // 롤백
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true); // Auto-commit 모드 복원
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return false; // 탈퇴 실패
	}

	// NEW TEAM
	public boolean createNewTeam(String teamOwner, String teamMember, String teamName, String practiceName,
			String categoryName, String title) {
		int categoryID = -1;

		if ("음악".equals(categoryName)) {
			categoryID = 1;
		} else if ("단어".equals(categoryName)) {
			categoryID = 2;
		} else if ("명언".equals(categoryName)) {
			categoryID = 3;
		}

		if (categoryID == -1) {
			System.out.println("(o`ㅅ`o) . ·잘못된 카테고리명입니다<!>· .");
			return false;
		}

		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = null;

		try {
			conn.setAutoCommit(false); // 자동 커밋 비활성화

			String sql = "INSERT INTO TEAM (TEAM_NAME, TEAM_OWNER, TEAM_MEMBER, PRACTICE_TITLE, CATEGORY_NAME, CATEGORY_ID, TITLE, AVGSCORE) VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, teamName);
			pst.setString(2, teamOwner);
			pst.setString(3, teamMember);
			pst.setString(4, practiceName);
			pst.setString(5, categoryName);
			pst.setInt(6, categoryID);
			pst.setString(7, title);

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				conn.commit(); // 트랜잭션 커밋
				return true;
			} else {
				conn.rollback(); // 트랜잭션 롤백
				return false; // 팀 생성 실패
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback(); // 예외 발생 시 트랜잭션 롤백
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
			return false;
		} finally {
			try {
				conn.setAutoCommit(true); // 자동 커밋 모드 복원
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.dbDisconnect(conn, pst, null);
		}
	}

}