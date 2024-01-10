<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HANCOM - LOGIN</title>
<link rel="stylesheet" type="text/css" href="../static/css/login.css">
</head>
<body>
	<div id="container">
		<div id="sub-container" style="margin-bottom: 50px;">
			
			<div id="wrapper-container">
				<div id="login-wrapper">
					<div id="wrapper-box">
						<p><%=request.getAttribute("loginFailedMessage") != null? request.getAttribute("loginFailedMessage")
		: "회원가입을 통해 한컴타자를 즐겨보세요"%></p>
						<button id="register-button"
							onclick="location.href='Register.jsp';">회원가입 하기</button>
					</div>

				</div>
				<div id="register-wrapper">
					<form action="loginCheck">
						<div id="register-form">
							닉네임 <input type="text" placeholder="닉네임" name="nickname"> 
							비밀번호 <input type="password" placeholder="비밀번호" name="password">
						</div>

						<div id="button-wrapper">
							<button id="submit" type="submit">로그인 하기</button>
						</div>


					</form>


				</div>
			</div>

		</div>

	</div>


</body>
</html>