<%@page import="com.hancom.dto.TeamVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String userNickname = (String) session.getAttribute("userNickname");
boolean inGroup = (boolean) request.getAttribute("inGroup");
List<TeamVO> myTeams = (List<TeamVO>) request.getAttribute("myTeamList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HANCOM</title>
<link rel="stylesheet" type="text/css" href="../static/css/style.css">
<link rel="stylesheet" type="text/css" href="../static/css/group.css">
<style>
body {
	background-image: url("../static/image/space-background.jpg");
}
</style>
</head>
<body>
	<div id="container">
		<h1 id="title">
			<span id="hancom-title-dark">그룹연습</span>
		</h1>
		<div id="sub-container">
			<div id="create-group-container">
				<div id="group-list">
					<%
					if (inGroup) {
					%>
					<table id="group-table">
						<tr id="group-header">
							<th>팀 명</th>
							<th>만든사람</th>
							<th>연습명</th>
							<th>인원 수</th>
							<th>나의 타수</th>
							<th>팀 평균 타수</th>
							<th>title</th>
						</tr>
						<%
						if (myTeams != null && !myTeams.isEmpty()) {
							for (TeamVO team : myTeams) {
						%>
						<tr>
							<td><%=team.getTeam_name()%></td>
							<td><%=team.getTeam_owner()%></td>
							<td><%=team.getPractice_title()%></td>
							<td><%=team.getNumMembers()%></td>
							<td><%=team.getScore()%></td>
							<td><%=team.getAvgScore()%></td>
							<td><%=team.getTitle()%></td>
						</tr>
						<%
						}
						}
						%>
					</table>
					<%
					if (myTeams != null && !myTeams.isEmpty()) {
					%>
					<div id="group-create-list" style="height: 650px;">
						<form action="typingCheck" method="post">
							<p>연습할 팀명을 입력하세요</p>
							<input type="text" id="group-create-typed" name="teamName">
							<p>연습명을 입력하세요</p>
							<input type="text" id="group-create-typed" name="teamPractieName">
							<p>연습할 title을 입력하세요</p>
							<input type="text" id="group-create-typed" name="title">
							<input type="hidden" name="typeID" value="2"> <input
								type="hidden" name="nickName" value="<%=userNickname%>">
							<input
								style="background-color: darkslateblue; box-shadow: inset -1px -1px 3px white; color: floralwhite; border-radius: 5px;"
								type="submit" value="연습하기">
						</form>
					</div>
					<%
					}
					%>
					<%
					} else {
					%>
					<p>그룹에 속해있지 않습니다! 그룹에 가입하거나 새 그룹을 생성하세요.</p>
					<%
					}
					%>
				</div>
				<div id="user-typing">
					<div id="user-name"
						style="margin: 0 auto; background-color: blue; margin-bottom: 15px; width: 50%;">
						+<%=userNickname%></div>
					<div id="user-profile">
						<img id="user-img" src="../static/image/typing-boy.png">
					</div>
					<div id="group-user-category">
						<p id="making-group">모든 입력을 완료한 후 생성할 수 있습니다</p>
						<p>표를 보고, 올바른 정보를 입력해주세요</p>
					</div>
				</div>
			</div>
			<div id="finish-type">
				<img id="mario" src="../static/image/mario.png" alt="Mario">
				<button id="finish-type-button" onclick="location.href='Group.jsp';">돌아가기</button>
			</div>
		</div>
	</div>
</body>
</html>
