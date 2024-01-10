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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HANCOM</title>
<link rel="stylesheet" type="text/css" href="../static/css/style.css">
<link rel="stylesheet" type="text/css" href="../static/css/setting.css">
</head>
<body>
	<div id="container-setting">
		<h1 id="title">
			<span id="hancom-title">정보수정</span>
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
						<form id="userSettingForm" action="userSetting" method="POST">
							<input type="hidden" name="categoryId" value="2"> <input
								type="hidden" name="userNickname" value="<%=userNickname%>">

							<div id="user-new-setting-wrapper-input" style="width: 730px;">
								<p>
									유저이름: <input id="setting-input-tag" type="text"
										name="newUserName">
								</p>
								<p>
									닉네임: <input id="setting-input-tag" type="text"
										name="newNickname">
								</p>
								<p>
									목표타수: <input id="setting-input-tag" type="text" name="newGoal">
								</p>
								<p>
									학교: <input id="setting-input-tag" type="text"
										name="newUniversity">
								</p>
							</div>
							<div id="button-row-list"
								style="column-gap: 20px; display: flex; align-items: center; flex-direction: row; justify-content: center;">
								<!-- "설정완료" 버튼을 클릭하면 서블릿으로 데이터를 제출 -->
								<button id="finish-setting" type="button" onclick="submitForm()">설정완료</button>
								<!-- "돌아가기" 버튼을 클릭하면 페이지로 이동 -->
								<button id="finish-setting" type="button" onclick="goBack()">돌아가기</button>
							</div>
						</form>

						<script>
							function submitForm() {
								// 폼을 서블릿으로 제출
								document.getElementById("userSettingForm")
										.submit();
							}

							function goBack() {
								// 페이지로 이동
								location.href = 'Setting.jsp';
							}
						</script>

					</div>
					
				</div>
			</div>
		</div>
	</div>

</body>

</html>
