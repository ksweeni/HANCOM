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
<link rel="stylesheet" type="text/css" href="../static/css/group.css">
</head>
<body>

	<div id="container">
		<h1 id="title">
			한글과 컴퓨터 <span id="hancom-title">타자연습</span>
		</h1>
		<div id="welcome-container">
			<p id="user-welcome"><%=userNickname%>님, 개인 연습을 위해 카테고리를 골라주세요.
			</p>
		</div>
		<div id="sub-container-row">
			<div id="button-container">

				<button id="group-all-button" name="groupId" value="1">
					<img id="lyrics" src="../static/image/group-all.JPG">
					<p>그룹 조회</p>
				</button>
				<button id="group-practice-button" name="groupId" value="2">
					<img id="words" src="../static/image/group-typing.JPG">
					<p>그룹 연습</p>
				</button>
				<button id="group-create-button" name="groupId" value="3">
					<img id="advice" src="../static/image/group-create.JPG">
					<p>그룹 만들기</p> <!-- 그룹 만들기는 다른 페이지로 가서 입력 받구 서블렛 이동 -->
				</button>
				<button id="group-exit-button" name="groupId" value="4">
					<img id="advice" src="../static/image/group-exit.JPG">
					<p>그룹 나가기</p>
				</button>
				<button id="go-back" onclick="location.href='Main.jsp';">
					<img id="advice" src="../static/image/go-back.JPG">
					<p>돌아가기</p>
				</button>

			</div>
			<div id="describe-container">
				<p id="describe">모든 그룹을 조회합니다.</p>
			</div>
		</div>
	</div>
	<script>
		var userNickname = "<%=userNickname%>";
		var AllGroupButton = document.getElementById("group-all-button");
		var GroupPracticeButton = document
				.getElementById("group-practice-button");
		var GroupCreateButton = document.getElementById("group-create-button");
		var GroupExitButton = document.getElementById("group-exit-button");

		function displayText(text) {
			var describe = document.getElementById("describe");
			describe.textContent = text;
		}

		function sendPostRequest(buttonId, username) {
			var form = document.createElement("form");
			form.method = "post";
			form.action = "groupCheck"; // GroupServlet

			var inputFields = [ {
				name : "groupId",
				value : buttonId
			}, {
				name : "nickname",
				value : userNickname
			} ];

			inputFields.forEach(function(field) {
				var input = document.createElement("input");
				input.type = "hidden";
				input.name = field.name;
				input.value = field.value;
				form.appendChild(input);
			});

			document.body.appendChild(form);
			form.submit();
		}

		AllGroupButton.addEventListener("click", function() {
			displayText("모든 그룹을 조회합니다.");
		});

		GroupPracticeButton.addEventListener("click", function() {
			displayText("그룹 연습을 통해 실력을 업데이트 합니다.");
		});

		GroupCreateButton.addEventListener("click", function() {
			displayText("새로운 그룹을 생성해보세요");
		});

		GroupExitButton.addEventListener("click", function() {
			displayText("그룹을 탈퇴하시겠습니까?");
		});

		AllGroupButton.addEventListener("dblclick", function() {
			sendPostRequest(1, userNickname); // 서블릿에 전달할 파라미터를 지정
		});

		GroupPracticeButton.addEventListener("dblclick", function() {
			console.log(2);
			sendPostRequest(2, userNickname); // 서블릿에 전달할 파라미터를 지정
		});

		GroupCreateButton.addEventListener("dblclick", function() {
			console.log(3);
			window.location.href = "CreateGroup.jsp";
		});

		GroupExitButton.addEventListener("dblclick", function() {
			console.log(4);
			sendPostRequest(4, userNickname); // 서블릿에 전달할 파라미터를 지정
		});
	</script>

</body>
</html>

</html>