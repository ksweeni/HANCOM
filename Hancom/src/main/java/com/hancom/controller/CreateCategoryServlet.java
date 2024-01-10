package com.hancom.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hancom.model.CategoryDAO;
import com.hancom.model.CategoryService;

@WebServlet("/views/createCategory")
public class CreateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateCategoryServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		RequestDispatcher rd;
		CategoryDAO cat = new CategoryDAO();
		CategoryService cs = new CategoryService();
		//System.out.println("categoryId: " + categoryId);
		String newTitle = request.getParameter("newTitle");
		String artist = request.getParameter("artist");
		String nationality = request.getParameter("nationality");
		String lyrics = request.getParameter("lyrics");
		String categoryTitle = request.getParameter("categoryTitle");
		int cateID = 0;

		switch (categoryId) {
		case 1:
			request.setAttribute("categoryList", cat.getMusicCategories());
			request.setAttribute("categoryTitle", "음악");
			break;
		case 2:
			request.setAttribute("categoryList", cat.getWords());
			request.setAttribute("categoryTitle", "단어");
			break;
		case 3:
			request.setAttribute("categoryList", cat.getAdvices());
			request.setAttribute("categoryTitle", "명언");
			break;
		case 4: // 단어나 명언을 추가하는 경우
			if (categoryTitle.equals("단어")) {
				cateID = 2;
				request.setAttribute("categoryTitle", "단어"); // Move this line here
				cs.addCategory(cateID, newTitle, categoryTitle, artist, nationality, lyrics);
				request.setAttribute("categoryList", cat.getWords());
			} else {
				cateID = 3;
				request.setAttribute("categoryTitle", "명언"); // Move this line here
				cs.addCategory(cateID, newTitle, categoryTitle, artist, nationality, lyrics);
				request.setAttribute("categoryList", cat.getAdvices());
			}
			break;
		default: // 음악을 추가하는 경우
			request.setAttribute("categoryTitle", "음악");
			cs.addCategory(1, newTitle, categoryTitle, artist, nationality, lyrics);
			request.setAttribute("categoryList", cat.getMusicCategories());
			break;
		}
		rd = request.getRequestDispatcher("../views/AddCategory.jsp");
		rd.forward(request, response);
	}

}
