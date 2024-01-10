<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
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
		<h1 id="title">
			한글과 컴퓨터 <span id="hancom-title">타자연습</span>
		</h1>
		<div id="welcome-container">
			<p id="user-welcome"><%=userNickname%>님, 환경설정을 위해 카테고리를 골라주세요.
			</p>
		</div>
		<div id="sub-container-row">
			<div id="button-container">
				<form id="user-form" method="post" action="userSetting">
					<input type="hidden" name="userNickname" value="<%=userNickname%>">
					<input type="hidden" name="categoryId" id="categoryId" value="">
				</form>

				<button id="fix-info-button" ondblclick="sendPostRequest(1);">
					<img id="lyrics" src="../static/image/user-setting.JPG">
					<p>정보수정</p>
				</button>
				<button id="user-exit-button" ondblclick="sendPostRequest(3);">
					<img id="words" src="../static/image/quit-user.JPG">
					<p>회원탈퇴</p>
				</button>
				<button id="go-back" onclick="location.href='Main.jsp';">
					<img id="advice" src="../static/image/go-back.JPG">
					<p>돌아가기</p>
				</button>
			</div>
			<div id="describe-container">
				<p id="describe">개인 정보를 수정합니다.</p>
			</div>
		</div>
	</div>
	<script>
    
</script>
	<script>
	var userForm = document.getElementById("user-form");
    var categoryIdInput = document.getElementById("categoryId");
    var userNickname = '<%=userNickname%>';

		function sendPostRequest(categoryId) {
			// Update the categoryId input value
			categoryIdInput.value = categoryId;
			// Submit the form
			userForm.submit();
		}

		var fixInfoButton = document.getElementById("fix-info-button");
		var userExitButton = document.getElementById("user-exit-button");

		function displayText(text) {
			var describe = document.getElementById("describe");
			describe.textContent = text;
		}

		fixInfoButton.addEventListener("click", function() {
			displayText("개인 정보를 수정합니다.");
		});

		userExitButton.addEventListener("click", function() {
			displayText("회원 탈퇴 하시겠습니까?");
		});

		fixInfoButton.addEventListener("dblclick", function() {
			console.log(1);
			sendPostRequest(1); // 서블릿에 전달할 파라미터를 지정
		});

		userExitButton.addEventListener("dblclick", function() {
			console.log(3);
			sendPostRequest(3); // 서블릿에 전달할 파라미터를 지정
		});
	</script>

</body>
</html>

</html>