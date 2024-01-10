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
			<p id="user-welcome"><%=userNickname%>님, 개인 연습을 위해 카테고리를 골라주세요.
			</p>
		</div>
		<div id="sub-container-row">
			<div id="button-container">

				<button id="lyrics-button" name="categoryId" value="1">
					<img id="lyrics" src="../static/image/music.JPG">
					<p>음 악</p>
				</button>
				<button id="words-button" name="categoryId" value="2">
					<img id="words" src="../static/image/words.JPG">
					<p>단 어</p>
				</button>
				<button id="advice-button" name="categoryId" value="3">
					<img id="advice" src="../static/image/advice.JPG">
					<p>명 언</p>
				</button>
				<button id="go-back" onclick="location.href='Main.jsp';">
					<img id="advice" src="../static/image/go-back.JPG">
					<p>돌아가기</p>
				</button>

			</div>
			<div id="describe-container">
				<p id="describe">가사 연습을 통해 실력을 업데이트 합니다.</p>
			</div>
		</div>
	</div>
	<script>
		var lyricsButton = document.getElementById("lyrics-button");
		var wordsButton = document.getElementById("words-button");
		var adviceButton = document.getElementById("advice-button");

		function displayText(text) {
			var describe = document.getElementById("describe");
			describe.textContent = text;
		}

		function sendPostRequest(buttonId) {
			var form = document.createElement("form");
			form.setAttribute("method", "post");
			form.setAttribute("action", "categoryCheck"); // 실제 서블릿 이름으로 변경해야 합니다.

			var input = document.createElement("input");
			input.setAttribute("type", "hidden");
			input.setAttribute("name", "categoryId");
			input.setAttribute("value", buttonId);

			form.appendChild(input);
			document.body.appendChild(form);

			form.submit();
		}

		lyricsButton.addEventListener("click", function() {
			displayText("가사 연습을 통해 실력을 업데이트 합니다.");
		});

		wordsButton.addEventListener("click", function() {
			displayText("단어 연습을 통해 실력을 업데이트 합니다.");
		});

		adviceButton.addEventListener("click", function() {
			displayText("명언 연습을 통해 실력을 업데이트 합니다.");
		});

		lyricsButton.addEventListener("dblclick", function() {
			console.log(1);
			sendPostRequest(1); // 서블릿에 전달할 파라미터를 지정
		});

		wordsButton.addEventListener("dblclick", function() {
			console.log(2);
			sendPostRequest(2); // 서블릿에 전달할 파라미터를 지정
		});

		adviceButton.addEventListener("dblclick", function() {
			console.log(3);
			sendPostRequest(3); // 서블릿에 전달할 파라미터를 지정
		});
	</script>

</body>
</html>

</html>