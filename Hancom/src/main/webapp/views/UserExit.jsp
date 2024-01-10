<%@page import="com.hancom.dto.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String userNickname = (String) session.getAttribute("userNickname");

List<UserVO> userInfo = (List<UserVO>) request.getAttribute("userInfo");
if (userInfo != null && !userInfo.isEmpty()) {
	UserVO user = userInfo.get(0); // Assuming only one user is in the list
}

Boolean deleteSuccess = (Boolean) request.getAttribute("deleteSuccess");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HANCOM</title>
<link rel="stylesheet" type="text/css" href="../static/css/style.css">
<link rel="stylesheet" type="text/css" href="../static/css/setting.css">
<style>
#user-quit-wrapper-input {
	display: flex;
	font-size: 20px;
	margin-bottom: 10px;
	justify-content: center; background-color : lightslategrey; color :
	white; width : 720px; height : 190px; margin-right : 10px; box-shadow :
	inset 2px 3px 5px black; border-radius : 10px; flex-direction : column;
	align-items: center;
	justify-content: center;
	background-color: lightslategrey;
	color: white;
	width: 720px;
	height: 190px;
	margin-right: 10px;
	box-shadow: inset 2px 3px 5px black;
	border-radius: 10px;
	flex-direction: column;
	background-color: lightslategrey;
	color: white;
	width: 720px;
	height: 190px;
	margin-right: 10px;
	box-shadow: inset 2px 3px 5px black;
	border-radius: 10px;
	flex-direction: column;
}

#quit-input-tag {
	background-color: transparent;
	width: 300px;
	color: white;
	height: 20px;
	font-size: 20px;
	border-color: antiquewhite;
	margin-left: 5px;
}

#finish-button-container {
	display: flex;
	margin-top: 19px;
	flex-direction: row;
	justify-content: center;
	column-gap: 50px;
	align-items: center;
}

#quit-span {
	font-size: 25px;
}
</style>
</head>
<body>
	<div id="container-setting">
		<h1 id="title">
			<span id="hancom-title">회원탈퇴</span>
		</h1>

		<div id="sub-container-setting">
			<div id="type-container-col">

				<div id="top-div">
					<div id="user-setting-ground">
						<span id="windows">Windows US</span>
					</div>
					<div id="user-setting-list">
						<%
						for (UserVO currentUser : userInfo) {
						%>
						<p id="user-p-tag">
							유저이름:
							<%=currentUser.getUser_name()%></p>
						<p id="user-p-tag">
							닉네임:
							<%=currentUser.getNickname()%></p>
						<p id="user-p-tag">
							현재타수:
							<%=currentUser.getUser_type()%></p>
						<p id="user-p-tag">
							학교:
							<%=currentUser.getUniversity()%></p>
						<p id="user-p-tag">
							레벨:
							<%=currentUser.getUser_level()%></p>
						<%
						}
						%>
					</div>
					<div id="user-typing">
						<div id="user-profile">
							<img id="user-setting-img" src="../static/image/typing-boy.png">
						</div>
					</div>
				</div>

				<div id="user-new-setting-list">
					<div id="user-new-setting-wrapper">
						<form action="userSetting" method="POST">
							<input type="hidden" name="categoryId" value="4"> <input
								type="hidden" name="userNickname" value="<%=userNickname%>">
							<div id="user-quit-wrapper-input">
								<%
								if (deleteSuccess != null && deleteSuccess) {
									// Redirect to Login.jsp if withdrawal is successful
									response.sendRedirect("Login.jsp");
								} else {
								%>
								<span id="quit-span">한컴타자에서 정말 탈퇴하시겠습니까? </span>
								<div id="finish-button-container">
									<button id="finish-setting" type="submit">즐거웠어요</button>
									<button id="finish-setting" type="button" onclick="goBack()">돌아가기</button>
								</div>
								<%
								}
								%>
							</div>


						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function goBack() {
			// Navigate back to the previous page
			history.go(-1);
		}
	</script>
</body>

</html>
