<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String userNickname = (String) session.getAttribute("userNickname");
int typedLength = (Integer) request.getAttribute("typedLength");
int typingSpeed = (Integer) request.getAttribute("typingSpeed");
Long finishTimeInSeconds = (Long) request.getAttribute("finishTimeInSeconds");
String finishTimeInSecondsString = finishTimeInSeconds.toString();
int updatedType = (Integer) request.getAttribute("updatedType");
String updatedLevel = (String) request.getAttribute("updatedLevel");
int typeID = (Integer) request.getAttribute("typeID");
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
		<h1 id="typing-title">
			<span id="hancom-title"> 
			<%if (typeID == 2) {
			%> 그룹 연습 결과 <%
			} else {%>
			연습결과 
			<%}%>
			</span>
		</h1>

		<div id="sub-container">
			<div id="result-container">

				<div id="user-typing">
					<div id="user-name">
						+<%=userNickname%>
					</div>
					<div id="user-profile">
						<img id="user-img" src="../static/image/typing-boy.png" style="box-shadow: 2px 3px 2px 1px black;">
					</div>

					<div id="user-result">
						<p id="result-text-title">타자결과</p>

						<p id="result-text">
							글자 수 :
							<%=typedLength%>
						</p>
						<p id="result-text">
							분당 타수 :
							<%=typingSpeed%>
						</p>
						<p id="result-text">
							연습시간 :
							<%=finishTimeInSecondsString%>초
						</p>

						<p id="result-text">
							** LEVEL =>
							<%=updatedLevel%>
							! **
						</p>
					</div>

				</div>
				<div id="finish-type">
					<button id="finish-type-button" onclick="location.href='Main.jsp';">확인</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>