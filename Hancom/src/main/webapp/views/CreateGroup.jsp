<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String userNickname = (String) session.getAttribute("userNickname");
String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>HANCOM</title>
<link rel="stylesheet" type="text/css" href="../static/css/style.css?s">
<link rel="stylesheet" type="text/css" href="../static/css/group.css?e">
<style>
body {
	background-image: url("../static/image/space-background.jpg");
}
</style>

</head>

<body>

	<div id="container">
		<h1 id="title">
			<span id="hancom-title-dark">그룹 생성</span>
		</h1>

		<div id="sub-container">
			<div id="create-group-container">
				<div id="group-create-list">
					<form action="groupCheck" method="post">
						<!-- Input fields here -->
						<p>그룹장을 입력하세요</p>
						<input type="text" id="group-create-typed" name="teamLeader">
						<p>멤버명을 입력하세요</p>
						<input type="text" id="group-create-typed" name="memberName">
						<p>그룹명을 입력하세요</p>
						<input type="text" id="group-create-typed" name="teamName">
						<p>연습명을 입력하세요</p>
						<input type="text" id="group-create-typed" name="practiceName">
						<p>카테고리명을 입력하세요 (음악/단어/명언)</p>
						<input type="text" id="group-create-typed" name="categoryName">
						<p>title을 입력하세요</p>
						<input type="text" id="group-create-typed" name="title">

						<!-- Hidden input to send the button value -->
						<input type="hidden" name="groupId" value="3">

						<!-- Submit button to send the data -->
						<input type="submit" value="생성하기"
							style="background-color: darkslateblue; box-shadow: inset -1px -1px 3px white; color: floralwhite; border-radius: 5px;">
					</form>
				</div>


				<div id="user-typing">
					<div id="user-name"
						style="margin: 0 auto; background-color: blue; margin-bottom: 15px; width: 50%;">+<%=userNickname%></div>
					<div id="user-profile">
						<img id="user-img" src="../static/image/typing-boy.png">
					</div>

					<div id="group-user-category">
						<p id="making-group">모든 입력을 완료한 후 생성할 수 있습니다</p>
						<p>그룹 생성시, 존재하는 닉네임으로 해주세요</p>
						<p>title 추가 시, 존재하는 title로 해주세요</p>
					</div>
				</div>
			</div>
			<div id="finish-type">
				<img id="mario" src="../static/image/mario.png" alt="Mario">
				<button id="finish-type-button" onclick="location.href='Group.jsp';">돌아가기</button>
			</div>

		</div>
	</div>
	<%-- Check if the success message is not null or empty --%>
	<% if (success != null && !success.trim().isEmpty()) { %>
	<script>
		document.getElementById("making-group").textContent = "<%=success%>";
	</script>
	<% } %>
</body>

</html>