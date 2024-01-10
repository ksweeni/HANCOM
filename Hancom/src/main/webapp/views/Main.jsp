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
			<p id="user-welcome"><%=userNickname%>님, 어서오세요.
			</p>
		</div>
		<div id="sub-container-row">
			<div id="button-container">

				<button id="personal-button">
					<img id="personal" src="../static/image/personal.png">
					<p>개 인 연 습</p>
				</button>
				<button id="group-button">
					<img id="group" src="../static/image/group.png">
					<p>그 룹 연 습</p>
				</button>
				<button id="create-button">
					<img id="create" src="../static/image/create.png">
					<p>문제 만들기</p>
				</button>
				<button id="setting-button">
					<img id="setting" src="../static/image/boy.png">
					<p>환 경 설 정</p>
				</button>
				<button id="ranking-button">
					<img id="ranking" src="../static/image/ranking.png">
					<p>랭 크 조 회</p>
				</button>
				<button id="exit-button">
					<img id="exit" src="../static/image/exit.png">
					<p>종 료</p>
				</button>
			</div>
			<div id="describe-container" style="margin-bottom : 100px;">
				<p id="describe">개인 연습을 통해 실력을 업데이트 할 수 있습니다.</p>
			</div>
		</div>

	</div>
	<script>
		// 버튼 요소들을 가져옵니다.
		var personalButton = document.getElementById("personal-button");
		var groupButton = document.getElementById("group-button");
		var createButton = document.getElementById("create-button");
		var settingButton = document.getElementById("setting-button");
		var rankingButton = document.getElementById("ranking-button");
		var exitButton = document.getElementById("exit-button");

		// 클릭한 버튼에 해당하는 문구를 표시하는 함수
		function displayText(text) {
			var describe = document.getElementById("describe");
			describe.textContent = text;
		}

		// 각 버튼에 클릭 이벤트 핸들러를 추가합니다.
		personalButton.addEventListener("click", function() {
			displayText("개인 연습을 통해 실력을 업데이트 할 수 있습니다.");
		});

		groupButton.addEventListener("click", function() {
			displayText("그룹 연습을 통해 협력하고 경쟁하세요.");
		});

		createButton
				.addEventListener(
						"click",
						function() {
							displayText("문제 만들기를 통해 자신만의 타자 연습을 생성하세요. 단어 / 음악 / 명언 등 새로운 것을 추가하세요.");
						});

		settingButton.addEventListener("click", function() {
			displayText("환경 설정을 통해 사용자 정의를 할 수 있습니다.");
		});

		rankingButton.addEventListener("click", function() {
			displayText("랭크 조회를 통해 다른 사용자와 비교하세요.");
		});

		exitButton.addEventListener("click", function() {
			displayText("종료하시겠습니까?");
		});

		personalButton.addEventListener("dblclick", function() {
			window.location.href = "Practice.jsp";
		});

		groupButton.addEventListener("dblclick", function() {
			window.location.href = "Group.jsp";
		});

		createButton.addEventListener("dblclick", function() {
			window.location.href = "CreateCategory.jsp";
		});

		settingButton.addEventListener("dblclick", function() {
			window.location.href = "Setting.jsp";
		});

		rankingButton.addEventListener("dblclick", function() {
			var form = document.createElement("form");
			form.method = "post";
			form.action = "rankingCheck";
			
			var userIdInput = document.createElement("input");
			userIdInput.type = "hidden";
			userIdInput.name = "option";
			userIdInput.value = 1; 

			form.appendChild(userIdInput);
			document.body.appendChild(form);
			form.submit();
		});

		exitButton.addEventListener("dblclick", function() {
			window.location.href = "Finish.jsp";
		});
	</script>



</body>

</html>