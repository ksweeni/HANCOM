package com.hancom.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hancom.model.UserService;

@WebServlet("/views/loginCheck")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");

		UserService us = new UserService();
		boolean loginSuccess = us.login(nickname, password);
		System.out.println(loginSuccess);
		response.setContentType("text/html;charset=utf-8");

		if (loginSuccess) {
			// 로그인 성공 시 세션에 사용자 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("userNickname", nickname);

			// 로그인 성공 시 메인 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/views/Main.jsp");
		} else {
			// 로그인 실패 시 에러 메시지 표시
			request.setAttribute("loginFailedMessage", "로그인에 실패했습니다. 다시 시도해 주세요!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
			rd.forward(request, response);
		}
	}
}
