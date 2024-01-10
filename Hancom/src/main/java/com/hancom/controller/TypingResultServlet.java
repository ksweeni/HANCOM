package com.hancom.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hancom.model.TeamService;
import com.hancom.model.UserDAO;

@WebServlet("/views/typingResultCheck")
public class TypingResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TypingResultServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String finishTime = request.getParameter("finishTime");
		int typedLength = Integer.parseInt(request.getParameter("typedLength"));
		int typeID = Integer.parseInt(request.getParameter("typeID"));
		int typingSpeed = 0;
		System.out.println("type ID : " + typeID);

		// 타자 결과를 공통적으로 계산한다 => 개인/그룹
		// Parse finishTime in the format "mm:ss" to seconds
		String[] timeParts = finishTime.split(":");
		if (timeParts.length == 2) {
			int minutes = Integer.parseInt(timeParts[0]);
			int seconds = Integer.parseInt(timeParts[1]);
			long finishTimeInSeconds = (minutes * 60) + seconds;
			// Calculate typing speed (WPM)
			typingSpeed = (int) (typedLength / (finishTimeInSeconds / 60.0)); // Calculate WPM

			System.out.println("|typedLength: " + typedLength);
			System.out.println("|finishTimeInSeconds: " + finishTimeInSeconds + " seconds");
			System.out.println("|typingSpeed(WPM): " + typingSpeed + " words per minute");

			request.setAttribute("typedLength", typedLength);
			request.setAttribute("finishTimeInSeconds", finishTimeInSeconds);
			request.setAttribute("typingSpeed", typingSpeed);

			// 개인 점수로 결과 업데이트
			UserDAO user = new UserDAO();
			System.out.println("updatedType :" + user.updateTypeAndLevel(nickname, typingSpeed));
			System.out.println("updatedLevel :" + user.calculateUserLevel(typingSpeed));
			request.setAttribute("updatedType", user.updateTypeAndLevel(nickname, typingSpeed));
			request.setAttribute("updatedLevel", user.calculateUserLevel(typingSpeed));
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid time format.");
		}
		
		if (typeID==2) {
			// 팀 정보를 어떻게 받아오지 한 자리 건너 뛰어서 ..? 
			TeamService ts = new TeamService();
			String teamName = request.getParameter("teamName");
			String teamPracticeName = request.getParameter("teamPracticeName");
			String title = request.getParameter("title");
			// 닉네임 팀네임 연습명 타이틀 스코어 => 팀 점수도 업데이트 되면서 개인 점수도 업데이트 될 것 
			ts.updateMemberTypingResult(nickname, teamName, teamPracticeName, title, typingSpeed);
		}

		request.setAttribute("typeID", typeID); // 개인 연습 결과인지 / 그룹 연습 결과인지 
		RequestDispatcher rd = request.getRequestDispatcher("/views/TypingResult.jsp");
		rd.forward(request, response);
	}

}
