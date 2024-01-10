package com.hancom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hancom.dto.TeamVO;
import com.hancom.model.TeamDAO;
import com.hancom.model.TeamService;

@WebServlet("/views/groupCheck")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GroupServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd;
		int groupId = Integer.parseInt(request.getParameter("groupId"));
		String nickname = request.getParameter("nickname");
		System.out.println(groupId + " : " + nickname);

		TeamService teamService = new TeamService();
		List<TeamVO> myTeams = teamService.getMyTeams(nickname);
		TeamDAO team = new TeamDAO();
		switch (groupId) {
		case 1: // 전부 조회
			teamService.getAllTeams();
			System.out.println("1번 처리 중 ");
			request.setAttribute("teamList", teamService.getAllTeams());
			break;
		case 2: // 그룹 연습
			if (myTeams.isEmpty()) {
				System.out.println("There's No Group");
				request.setAttribute("myTeamList", null);
				request.setAttribute("inGroup", false);
			} else {
				System.out.println("in the Group");
				request.setAttribute("inGroup", true);
				request.setAttribute("myTeamList", myTeams); // 내 팀 정보들 보내기
			}
			rd = request.getRequestDispatcher("../views/SelectGroupTyping.jsp");
			rd.forward(request, response);
			return;
		case 3: // 그룹 만들기는 여기로 나중에 옴
			String teamLeader = request.getParameter("teamLeader");
			String memberName = request.getParameter("memberName");
			String teamName = request.getParameter("teamName");
			String practiceName = request.getParameter("practiceName");
			String categoryName = request.getParameter("categoryName");
			String title = request.getParameter("title");

			System.out.println(
					teamLeader + " : " + memberName + " : " + practiceName + " : " + categoryName + " : " + title);
			boolean success = team.createNewTeam(teamLeader, memberName, teamName, practiceName, categoryName, title);
			System.out.println(success);
			if (success) {
				request.setAttribute("success", "팀 생성 성공이 완료되었습니다 !");
				// Forward to CreateGroup page
			} else {
				request.setAttribute("success", "올바른 팀 정보를 입력해주세요 !");
			}
			rd = request.getRequestDispatcher("../views/CreateGroup.jsp");
			rd.forward(request, response);
			return; // Make sure to return to prevent further execution
		default: // 그룹 나가기 (탈퇴 처리)
			if (myTeams.isEmpty()) {
				String message = "속한 그룹이 없으므로, 그룹 탈퇴가 불가합니다!";
				System.out.println("No Group");
				request.setAttribute("myTeamList", null);
				request.setAttribute("inGroup", false);
			} else {
				System.out.println("in the Group");
				request.setAttribute("inGroup", true);
				request.setAttribute("myTeamList", myTeams); // 내 팀 정보들 보내기
			}

			if (groupId == 5) {
				// 그룹 나가기 처리
				String membertoQuit = request.getParameter("teamMember");
				String teamnametoQuit = request.getParameter("teamName");
				System.out.println(membertoQuit + " out " + teamnametoQuit);
				try {
					boolean quitSuccess = team.leaveTeam(membertoQuit, teamnametoQuit);
					System.out.println("quitSuccess : " + quitSuccess);
					request.setAttribute("QuitSuccess", quitSuccess); // 탈퇴 여부 보내기
					
				} catch (Exception e) {
					e.printStackTrace(); // 예외 정보를 출력
					request.setAttribute("QuitSuccess", false); // 실패 시 false로 설정
				}
			}

			rd = request.getRequestDispatcher("../views/ExitGroup.jsp");
			rd.forward(request, response);
			return;

		}

		RequestDispatcher forAllGroupRd = request.getRequestDispatcher("../views/AllGroup.jsp");
		forAllGroupRd.forward(request, response);
	}

}
