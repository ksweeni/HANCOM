<%@page import="com.hancom.dto.TeamVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String userNickname = (String) session.getAttribute("userNickname");
boolean inGroup = (boolean) request.getAttribute("inGroup");
List<TeamVO> myTeams = (List<TeamVO>) request.getAttribute("myTeamList");
Boolean QuitSuccess = (Boolean) request.getAttribute("QuitSuccess");
boolean quitSuccess = false;

if (QuitSuccess != null) {
	quitSuccess = QuitSuccess.booleanValue();
}
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>HANCOM</title>

<link rel="stylesheet" type="text/css" href="../static/css/style.css?s">
<link rel="stylesheet" type="text/css" href="../static/css/group.css?s">
<style>
body {
	background-image: url("../static/image/space-background.jpg");
}
</style>
</head>
<body>
	<div id="container">
		<h1 id="title">
			<span id="hancom-title-dark">그룹 탈퇴</span>
		</h1>
		<div id="sub-container">
			<div id="create-group-container">
				<div id="group-list">
					<!-- inGroup이 true일 때만 팀 리스트를 보여줍니다. -->
					<%
					if (inGroup) {
					%>
					<table id="group-table">
						<tr id="group-header">
							<th>팀 명</th>
							<th>만든사람</th>
							<th>멤버 수</th>
							<th>평균 점수</th>
						</tr>
						<%
						for (TeamVO team : myTeams) {
						%>
						<tr>
							<td><%=team.getTeam_name()%></td>
							<td><%=team.getTeam_owner()%></td>
							<td><%=team.getNumMembers()%></td>
							<td><%=team.getAvgScore()%></td>
							<%
							}
							%>
						</tr>
					</table>
					<div id="group-create-list" style="margin-left: 300px;">
						<form action="groupCheck" method="post">
							<!-- Input fields here -->
							<p>본인의 닉네임을 입력하세요</p>
							<input type="text" id="group-create-typed" name="teamMember">
							<p>탈퇴할 그룹명을 입력하세요</p>
							<input type="text" id="group-create-typed" name="teamName">
							<!-- Hidden input to send the button value -->
							<input type="hidden" name="groupId" value="5">
							<!-- Submit button to send the data -->
							<input type="submit"
							style="background-color: darkslateblue; box-shadow: inset -1px -1px 3px white; color: floralwhite; border-radius: 5px;" type="submit" value="탈퇴하기">
						</form>
						<!-- 탈퇴에 성공한 경우 메시지 표시 -->

						<%
						} else {
						%>
						<!-- inGroup이 false일 때 보여줄 내용을 여기에 추가하세요. -->
						<p>그룹에 속해있지 않습니다!</p>
						<%
						}
						%>
					</div>

				</div>
				<div id="user-typing" style="width: 40%; margin: 0 auto;">
					<div id="user-name"
						style="margin: 0 auto; background-color: blue; margin-bottom: 15px; width: 50%;">
						+<%=userNickname%></div>

					<div id="user-profile">
						<img id="user-img" src="../static/image/typing-boy.png">
					</div>
					<div id="group-user-category">
						<p id="making-group">모든 입력을 완료한 후 탈퇴할 수 있습니다</p>
					</div>
				</div>
			</div>
		</div>
		<div id="finish-type">
			<img id="mario" src="../static/image/mario.png" alt="Mario">
			<button id="finish-type-button" onclick="location.href='Group.jsp';">돌아가기</button>
		</div>
	</div>
	<script>
		if (
	<%=quitSuccess%>
		) {
			document.getElementById("category-p-title").textContent = "탈퇴 성공 !";
		}
	</script>
</body>
</html>
