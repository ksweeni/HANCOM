<%@page import="com.hancom.dto.CategoryVO"%>
<%@page import="com.hancom.dto.TeamVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<CategoryVO> categoryList = (List<CategoryVO>) request.getAttribute("categoryList");
String categoryTitle = (String) request.getAttribute("categoryTitle");
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
	background-image: url('../static/image/list.jpg');
	background-position: center center;
	background-repeat: no-repeat;
	background-size: 60%;
}

#group-table {
	margin-left: 360px;
	width: 300px;
	display: flex;
	margin-top: 220px;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 300px;
	display: flex;
	margin-top: 150px;
	flex-direction: column;
	justify-content: center;
}

#group-create-list {
	color: black;
	margin: 0 auto;
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 30%;
	margin-right: 250px;
}

input {
	background-color: transparent;
}

#add-button {
	height: 20px;
	border-radius: 5px;
	border: 1px solid lightgray;
	background-color: lightgray;
	color: black;
	box-shadow: inset -5px -1px 5px white;
}

#hancom-category-title {
	font-family: 'NeoDunggeunmoPro-Regular';
	color: gainsboro;
	font-weight: bold;
	font-size: 50px;
	text-shadow: 3px 3px 2px gray;
}
</style>
</head>
<body>
	<div id="container">
		<h1 id="title">
			<span id="hancom-category-title">문제생성</span>
		</h1>

		<div id="sub-container">

			<div id="group-list">
				<table id="group-table">
					<tr id="group-header">
						<th>list</th>
					</tr>
					<%
					for (CategoryVO cat : categoryList) {
					%>
					<tr style="color: gray; text-shadow: 4px 3px 2px gainsboro;">
						<td><%=cat.getTitle()%></td>
						<%
						}
						%>
					</tr>
				</table>
				<div id="group-create-list">
					<form action="createCategory" method="post">
						<input type="hidden" name="categoryId" value="4"> <input
							type="hidden" name="categoryTitle" value="<%=categoryTitle%>">
						<p>
							새로운
							<%=categoryTitle%>
							title을 입력하세요
						</p>
						<input type="text" id="newTitle" name="newTitle">
						<%
						if ("음악".equals(categoryTitle)) {
						%>
						<input type="hidden" name="categoryId" value="5">
						<p>가수를 입력하세요</p>
						<input type="text" id="artist" name="artist">
						<p>한국/외국 국적을 입력하세요</p>
						<input type="text" id="nationality" name="nationality">
						<p>가사를 입력하세요</p>
						<textarea id="lyrics" name="lyrics"></textarea>
						<%
						}
						%>
						<input type="submit" id="add-button" value="추가하기">
					</form>
				</div>
				<div id="finish-type">
					<button id="finish-type-button"
						onclick="location.href='CreateCategory.jsp';">돌아가기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
