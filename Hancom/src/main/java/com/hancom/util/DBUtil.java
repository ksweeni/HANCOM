package com.hancom.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
	
	// DBConnection Pooling 얻기 => 연결 속도가 빠르다 
	// 나중에 넣을 것 
	
	
	

	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userId = "hr";
		String passWord = "hr";
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("1. driver load 성공!");

			conn = DriverManager.getConnection(url, userId, passWord);
			// System.out.println("2. connection 성공!");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 자원을 반납 (DB연결 해제, statement 해제, ResultSet 해제)
	public static void dbDisconnect(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}