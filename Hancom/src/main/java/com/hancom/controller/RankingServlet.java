package com.hancom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hancom.dto.UserVO;
import com.hancom.model.UserService;


@WebServlet("/views/rankingCheck")
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public RankingServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int option = Integer.parseInt(request.getParameter("option"));
		List<UserVO> userList;
		RequestDispatcher rd;
		UserService us = new UserService();
		System.out.println("option : " + option);
		
		if(option == 1) userList = us.getAllUserRankByList();
		else if(option == 2) userList = us.getUserRankByLevelToList("BRONZE");
		else if(option ==3) userList = us.getUserRankByLevelToList("SILVER");
		else userList = us.getUserRankByLevelToList("GOLD");
		
		request.setAttribute("userRanking", userList); 
		
		rd = request.getRequestDispatcher("/views/Rank.jsp");
		rd.forward(request, response);
	}

}
