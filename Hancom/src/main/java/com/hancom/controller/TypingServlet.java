package com.hancom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hancom.dto.CategoryVO;
import com.hancom.model.CategoryDAO;

@WebServlet("/views/typingCheck")
public class TypingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TypingServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int typeID = Integer.parseInt(request.getParameter("typeID"));
		RequestDispatcher rd;
		CategoryDAO cat = new CategoryDAO();
		List<CategoryVO> typings;

		if (typeID == 1) {
			String title = request.getParameter("title");
			System.out.println(title);
			if ("음악".equals(title)) {
				// Get the selected categories
				String selectedCategories = request.getParameter("selectedCategories");
				// Use 'selectedCategories' to retrieve the selected lyrics or perform other
				// operations as needed
				typings = cat.getLyrics(selectedCategories);
			} else if ("단어".equals(title)) {
				typings = cat.getWords();
			} else {
				typings = cat.getAdvices();
			}
		} else { // 그룹 타이핑
			String teamName = request.getParameter("teamName");
			String teamPractieName = request.getParameter("teamPractieName");
			String nickName = request.getParameter("nickName");
			System.out.println(teamName + " : " + teamPractieName + " : " + nickName);

			typings = cat.getPractice(teamName, nickName, request.getParameter("title"));
			request.setAttribute("teamName", teamName);
			request.setAttribute("teamPractieName", teamPractieName);
			request.setAttribute("title", request.getParameter("title"));
		}

		request.setAttribute("typings", typings);
		request.setAttribute("typeID", typeID);
		rd = request.getRequestDispatcher("../views/Typing.jsp");
		rd.forward(request, response);
	}
}
