package com.hancom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hancom.dto.CategoryVO;
import com.hancom.util.DBUtil;

public class CategoryDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	// 음악 카테고리 가져오기
	public List<CategoryVO> getMusicCategories() {
		List<CategoryVO> categories = new ArrayList<>();

		String sql = "SELECT DISTINCT TITLE, ARTIST FROM CATEGORY WHERE CATEGORY_ID = 1";
		conn = DBUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setTitle(rs.getString("TITLE"));
				category.setArtist(rs.getString("ARTIST"));
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return categories;
	}

	// 명언 가져오기
	public List<CategoryVO> getAdvices() {
		List<CategoryVO> advices = new ArrayList<>();

		String sql = "SELECT TITLE FROM CATEGORY WHERE CATEGORY_ID = 3";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setTitle(rs.getString("TITLE"));
				advices.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return advices;
	}

	// 단어 가져오기
	public List<CategoryVO> getWords() {
		List<CategoryVO> words = new ArrayList<>();

		String sql = "SELECT TITLE FROM CATEGORY WHERE CATEGORY_ID = 2";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();

			while (rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setTitle(rs.getString("TITLE"));
				words.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return words;
	}

	// 가사 가져오기
	public List<CategoryVO> getLyrics(String title) {
		List<CategoryVO> lyrics = new ArrayList<>();
		String sql = "SELECT TEXT FROM CATEGORY WHERE TITLE = ? AND CATEGORY_ID=1";
		conn = DBUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			rs = pst.executeQuery();

			while (rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setTitle(rs.getString("TEXT"));
				lyrics.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return lyrics;
	}

	public int getCategoryID(String title) {
		int categoryId = 0;
		String sql = "SELECT DISTINCT CATEGORY_ID FROM CATEGORY C WHERE C.TITLE = ?";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			rs = pst.executeQuery();
			if (rs.next()) {
				categoryId = rs.getInt("CATEGORY_ID");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return categoryId;
	}

	// 특정한 title명을 사용하여 단어를 가져오는 메소드
	public List<CategoryVO> getSpecificWords(String title) {
		List<CategoryVO> words = new ArrayList<>();
		String sql = "SELECT TITLE FROM CATEGORY WHERE CATEGORY_ID = 2 AND TITLE = ?";
		conn = DBUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);

			rs = pst.executeQuery();

			while (rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setTitle(rs.getString("TITLE"));
				words.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return words;
	}

	// 특정한 title명을 사용하여 명언을 가져오는 메소드
	public List<CategoryVO> getSpecificAdvices(String title) {
		List<CategoryVO> advices = new ArrayList<>();

		String sql = "SELECT TITLE FROM CATEGORY WHERE CATEGORY_ID = 3 AND TITLE = ?";

		conn = DBUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);

			rs = pst.executeQuery();

			while (rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setTitle(rs.getString("TITLE"));
				advices.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return advices;
	}

	// 카테고리 기반 그룹 연습
	public List<CategoryVO> getPractice(String teamName, String nickname, String title) {
		List<CategoryVO> practice = new ArrayList<>();

		// 카테고리 아이디 가져오기
		int categoryId = getCategoryID(title);

		// 카테고리 아이디를 기반으로 다른 메소드 호출
		switch (categoryId) {
		case 1:
			practice.addAll(getLyrics(title));
			break;
		case 2:
			practice.addAll(getSpecificWords(title));
			break;
		case 3:
			practice.addAll(getSpecificAdvices(title));
			break;
		default:
			// 기본적으로 빈 리스트를 반환합니다.
			break;
		}

		return practice;
	}

	// 카테고리 추가
	public boolean addCategory(int categoryId, String title, String categoryName, String artist, String country,
			String lyrics) {
		String sql;
		if (categoryId == 1) {
			sql = "INSERT INTO CATEGORY (ID, CATEGORY_ID, TITLE, CATEGORY_NAME, ARTIST, COUNTRY, TEXT) VALUES (CATEGORY_SEQ.nextval, 1, ?, ?, ?, ?, ?)";
		} else if (categoryId == 2) {
			sql = "INSERT INTO CATEGORY (ID, CATEGORY_ID, TITLE, CATEGORY_NAME) VALUES (CATEGORY_SEQ.nextval, 2, ?, ?)";
		} else if (categoryId == 3) {
			sql = "INSERT INTO CATEGORY (ID, CATEGORY_ID, TITLE, CATEGORY_NAME) VALUES (CATEGORY_SEQ.nextval, 3, ?, ?)";
		} else {
			System.out.println("(o`ㅅ`o) . ·잘못된 CATEGORY_ID입니다<!>· .\n");
			return false;
		}

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); // Auto-commit 모드 해제

			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			pst.setString(2, categoryName);

			if (categoryId == 1) {
				// 음악 카테고리일 때 추가 필드도 설정
				pst.setString(3, artist);
				pst.setString(4, country);
				pst.setString(5, lyrics);
			}

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println(". · (o > v < o) 카테고리 추가 성공!\n");
				conn.commit(); // 트랜잭션 커밋
				return true; // 카테고리 추가 성공
			} else {
				System.out.println(". · (o > v < o) 카테고리 추가 실패!\n");
				conn.rollback(); // 트랜잭션 롤백
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.rollback(); // 트랜잭션 롤백
				}
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.setAutoCommit(true); // Auto-commit 모드 복원
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.dbDisconnect(conn, pst, null);
		}

		return false; // 카테고리 추가 실패
	}

}