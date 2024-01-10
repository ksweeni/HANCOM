<%@page import="com.hancom.dto.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<UserVO> userRanking = (List<UserVO>) request.getAttribute("userRanking");
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
	background-image: url('../static/image/ranking.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	background-attachment: fixed;
}

#group-rank-list {
	margin: 0 auto;
	height: 50px;
	column-gap: 20px;
	display: flex;
	flex-direction: row;
	align-items: center;
}

#hancom-title-rank {
	font-family: 'NeoDunggeunmoPro-Regular';
	color: orangered;
	font-weight: bold;
	font-size: 50px;
	text-shadow: 2px 2px 1px yellow;
}

#rank-button {
	height: 40px;
	width: 80px;
	border: none;
	border-radius: 5px;
	background-color: coral;
	color: yellow;
	box-shadow: inset -1px -2px 5px yellow;
}
</style>
</head>
<body>
	<div id="container">
		<h1 id="title">
			<span id="hancom-title-rank">랭킹조회</span>
		</h1>

		<div id="sub-container">

			<div id="group-list">
				<table id="group-table" style="margin-top:300px;">
					<tr id="group-header">
						<th>닉네임</th>
						<th>이름</th>
						<th>학교</th>
						<th>타이핑</th>
						<th>레벨</th>
					</tr>
					<%
					for (UserVO user : userRanking) {
					%>
					<tr>
						<td><%=user.getNickname()%></td>
						<td><%=user.getUser_name()%></td>
						<td><%=user.getUniversity()%></td>
						<td><%=user.getUser_type()%></td>
						<td><%=user.getUser_level()%></td>
					</tr>
					<%
					}
					%>
				</table>
				<div id="group-rank-list">
					<form action="rankingCheck" method="post">
						<input type="hidden" name="option" value="1">
						<!-- For 전체 랭킹 -->
						<input id="rank-button" type="submit" value="전체 랭킹">
					</form>
					<form action="rankingCheck" method="post">
						<input type="hidden" name="option" value="2">
						<!-- For 레벨 별 랭킹 (브론즈) -->
						<input id="rank-button" type="submit" value="브론즈" style="background-color: brown;">
					</form>
					<form action="rankingCheck" method="post">
						<input type="hidden" name="option" value="3">
						<!-- For 레벨 별 랭킹 (실버) -->
						<input id="rank-button" type="submit" value="실버" style="background-color: darkslategray;">
					</form>
					<form action="rankingCheck" method="post">
						<input type="hidden" name="option" value="4">
						<!-- For 레벨 별 랭킹 (골드) -->
						<input id="rank-button" type="submit" value="골드" style="background-color: indigo;">
					</form>
				</div>
				<div id="finish-type">
					
					<button id="finish-type-button" onclick="location.href='Main.jsp';">돌아가기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
