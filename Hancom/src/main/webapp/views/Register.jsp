<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HANCOM</title>
<link rel="stylesheet" type="text/css" href="../static/css/login.css">
</head>
<body>
	<div id="container">
		<div id="sub-container">
			<div id="wrapper-container">
				<div id="login-wrapper" style="margin-bottom: 30px;">
					<div id="wrapper-box">
						<p id="registrationMessage">
							<%=request.getAttribute("registrationFailedMessage") != null? request.getAttribute("registrationFailedMessage")
		: "로그인 후 한컴타자를 즐기세요"%>
						</p>
						<button id="register-button"
							onclick="location.href='Login.jsp';">로그인하기</button>
					
				</div>
				<div id="register-wrapper">
					<form action="registerCheck">
						<div id="register-form">
							닉네임 <input type="text" name="nickname" placeholder="닉네임">
							이름 <input type="text" name="name" placeholder="이름"> 비밀번호
							<input type="password" name="password" placeholder="비밀번호">
							학교 <input type="text" name="university"
								placeholder="없을 시 일반/풀 대학 네임 기재"> 목표타수<input
								type="number" name="goal" placeholder="목표 타수"> 현재타수<input
								type="number" name="typing" placeholder="없을 시 0 기재">
						</div>
						<div id="button-wrapper">
							<button id="submit" type="submit">가입하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>