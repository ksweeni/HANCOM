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

@WebServlet("/views/categoryCheck")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoryServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		System.out.println(categoryId);

		if (categoryId != null) {
			if (categoryId.equals("1")) {
				CategoryDAO cat = new CategoryDAO();
				List<CategoryVO> musics = cat.getMusicCategories();
				request.setAttribute("categoryList", musics);
				request.setAttribute("title", "음악");
			} else if (categoryId.equals("2")) {
				CategoryDAO cat = new CategoryDAO();
				List<CategoryVO> words = cat.getWords();
				request.setAttribute("categoryList", words);
				request.setAttribute("title", "단어");
			} else if (categoryId.equals("3")) {
				CategoryDAO cat = new CategoryDAO();
				List<CategoryVO> advices = cat.getAdvices();
				request.setAttribute("categoryList", advices);
				request.setAttribute("title", "명언");
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("../views/SelectTyping.jsp");
		rd.forward(request, response);
	}
}