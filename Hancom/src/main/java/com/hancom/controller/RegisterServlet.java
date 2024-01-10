package com.hancom.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hancom.model.UserService;

@WebServlet("/views/registerCheck")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String university = request.getParameter("university");
		String goalParam = request.getParameter("goal");
		String typeParam = request.getParameter("type");
		int goal = 0;
		int type = 0;
		if (goalParam != null && !goalParam.isEmpty()) {
			try {
				goal = Integer.parseInt(goalParam);
			} catch (NumberFormatException e) {
				// Handle the exception or set an appropriate default value
			}
		}

		if (typeParam != null && !typeParam.isEmpty()) {
			try {
				type = Integer.parseInt(typeParam);
			} catch (NumberFormatException e) {
				// Handle the exception or set an appropriate default value
			}
		}

		UserService us = new UserService();
		boolean registered = us.register(name, nickname, password, type, goal, university);

		
		response.setContentType("text/html;charset=utf-8");

		System.out.println(registered);
		if (registered) {
		    RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
		    rd.forward(request, response);
		    request.setAttribute("registrationStatus", "success");
		} else {
		  
		    request.setAttribute("registrationFailedMessage", "등록에 실패했습니다. 다시 시도해 주세요.");
		    request.setAttribute("registrationStatus", "failure");
		    
		    
		    RequestDispatcher rd = request.getRequestDispatcher("/views/Register.jsp");
		    rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
