<%@page import="com.hancom.dto.CategoryVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<CategoryVO> categoryList = (List<CategoryVO>) request.getAttribute("categoryList");
String userNickname = (String) session.getAttribute("userNickname");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>HANCOM</title>
<link rel="stylesheet" type="text/css" href="../static/css/style.css?s">
</head>

<body>
	<div id="container">
		<div id="title-container">
			<h1 id="title">
				한글과 컴퓨터 <span id="hancom-title">타자연습</span>
			</h1>
		</div>

		<div id="welcome-container">
			<p id="user-welcome"><%=userNickname%>님,
				<%=request.getAttribute("title")%>연습을 위해 카테고리를 골라주세요.
			</p>
		</div>
		<div id="sub-container">
			<div id="category-container">
				<div id="category-wrapper">
					<p id="category-title"><%=request.getAttribute("title")%>선택
					</p>
				</div>
				<div id="category-list" style="height: 300px; overflow-y: scroll;">
					<!-- Iterate over categoryList and create clickable <p> elements -->
					<%
					for (CategoryVO category : categoryList) {
					%>
					<p>
						<%=category.getTitle()%>
						<%
						if (request.getAttribute("title").equals("음악")) {
						%>
						<input id="checkbox-input" type="checkbox" name="selectedCategories"
							value="<%=category.getTitle()%>">
						<%
						}
						%>
					</p>
					<%
					}
					%>
				</div>
				<div id="button-container">
					<button id="goto-type"
						onclick="redirectToSelectedCategories('<%=request.getAttribute("title")%>')";>타자연습하기</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		function redirectToSelectedCategories(title) {
			var selectedCategories = [];
			var checkboxes = document
					.querySelectorAll('input[name="selectedCategories"]:checked');
			for (var i = 0; i < checkboxes.length; i++) {
				selectedCategories.push(checkboxes[i].value);
			}
			var selectedCategoriesString = selectedCategories.join(',');

			var form = document.createElement("form");
			form.setAttribute("method", "post");
			form.setAttribute("action", "typingCheck");

			var inputCategories = document.createElement("input");
			inputCategories.setAttribute("type", "hidden");
			inputCategories.setAttribute("name", "selectedCategories");
			inputCategories.setAttribute("value", selectedCategoriesString);

			var inputType = document.createElement("input");
			inputType.setAttribute("type", "hidden");
			inputType.setAttribute("name", "typeID");
			inputType.setAttribute("value", "1"); // Set typeId to 1

			var inputTitle = document.createElement("input");
			inputTitle.setAttribute("type", "hidden");
			inputTitle.setAttribute("name", "title");
			inputTitle.setAttribute("value", title);

			form.appendChild(inputCategories);
			form.appendChild(inputType);
			form.appendChild(inputTitle);
			document.body.appendChild(form);
			form.submit();
		}
	</script>
</body>
</html>
