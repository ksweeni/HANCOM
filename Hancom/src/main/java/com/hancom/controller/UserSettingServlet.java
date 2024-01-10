package com.hancom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hancom.dto.UserVO;
import com.hancom.model.UserService;

@WebServlet("/views/userSetting")
public class UserSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserSettingServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int categoryId = 0; // 1이면 유저 정보 보내는 쪽, 2이면 유저 정보 업데이트, 3이면 유저 탈퇴 처리
		RequestDispatcher rd;
		String categoryIdStr = request.getParameter("categoryId");
		if (categoryIdStr != null) {
			categoryId = Integer.parseInt(categoryIdStr);
		}

		System.out.println("categoryId: " + categoryId);
		String nickname = request.getParameter("userNickname");
		System.out.println(nickname);

		UserService us = new UserService();
		List<UserVO> userList = us.getUserProfileList(nickname);
		request.setAttribute("userInfo", userList);

		if (categoryId == 2) {
			String newUserName = request.getParameter("newUserName");
			String newNickname = request.getParameter("newNickname");
			int newGoal = Integer.parseInt(request.getParameter("newGoal"));
			String newUniversity = request.getParameter("newUniversity");
			System.out.println("updateing 2 ");
			System.out.println(newNickname);
			boolean updateSuccess = us.updateUserProfile(newNickname, newUserName, newUniversity, newGoal, nickname);
			System.out.println(updateSuccess);
			if (updateSuccess) {
				
				userList = us.getUserProfileList(newNickname);
				HttpSession session = request.getSession();
				session.setAttribute("userNickname", newNickname); // 세션의 닉네임 업데이트

				// Update the nickname in the session
				request.setAttribute("userInfo", userList);
			}
		}
		
		if(categoryId == 3) {
			rd = request.getRequestDispatcher("../views/UserExit.jsp");
			rd.forward(request, response);
			return;
		}
		
		if(categoryId == 4) {
			request.setAttribute("deleteSuccess", us.deleteUser(nickname));
			rd = request.getRequestDispatcher("../views/UserExit.jsp");
			rd.forward(request, response);
			return;
		}
		
		

		// 정보 수정
		// 사용자 정보 우선 출력 => 회원 탈퇴든, 정보 수정이든 동일

		rd = request.getRequestDispatcher("../views/UserInfo.jsp");
		rd.forward(request, response);
	}

}
