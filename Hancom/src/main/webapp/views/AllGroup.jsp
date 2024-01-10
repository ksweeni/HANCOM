<%@page import="com.hancom.dto.TeamVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<TeamVO> teamlist = (List<TeamVO>) request.getAttribute("teamList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HANCOM</title>
<link rel="stylesheet" type="text/css" href="../static/css/style.css?s">
<link rel="stylesheet" type="text/css" href="../static/css/group.css?d">
</head>
<style>
body {
	background-image: url("../static/image/space-background.jpg");
}


</style>
<body>
	<div id="container">
		<h1 id="title">
			<span id="hancom-title-dark">그룹조회</span>
		</h1>


		<div id="sub-container">

			<div id="group-list">
				<table id="group-table">
					<tr id="group-header">
						<th>팀 명</th>
						<th>만든사람</th>
						<th>멤버 수</th>
						<th>평균 점수</th>
					</tr>
					<%
					for (TeamVO team : teamlist) {
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
				<div id="finish-type">
					<img id="mario" src="../static/image/mario.png" alt="Mario">
					<button id="finish-type-button" onclick="history.go(-1);">돌아가기</button>
				</div>
			</div>

		</div>

	</div>

</body>
</html>

