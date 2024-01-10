<%@page import="com.hancom.dto.CategoryVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String userNickname = (String) session.getAttribute("userNickname");
List<CategoryVO> typings = (List<CategoryVO>) request.getAttribute("typings");
int typeId = (Integer) request.getAttribute("typeID"); // 1번이면 개인연습, 2번이면 그룹 연습이므로 업뎃 방식 다름
String teamName = (String) request.getAttribute("teamName");
String teamPracticeName = (String) request.getAttribute("teamPractieName");
String title = (String) request.getAttribute("title");
/*if (typeId == 2) {
	// Handle the case where typings is not set in the request
	// You may want to provide a default value or an error message
}*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HANCOM</title>
<link rel="stylesheet" type="text/css" href="../static/css/style.css">
</head>

<body>

	<div id="container">
		<h1 id="typing-title">
			<span id="hancom-title">타자연습</span>
		</h1>
		<div id="sub-container">

			<div id="type-container">
				<div id="type-wrapper"></div>

				<div id="user-typing">
					<div id="user-name">
						+<%=userNickname%></div>
					<div id="user-profile">
						<img id="user-img" src="../static/image/typing-boy.png" style="box-shadow: 2px 3px 2px 1px black;">
					</div>
					<div id="user-category">
						<p id="category-p-title">타자 연습</p>

						<div id="timer">00:00</div>
						<p id="category-p-title">시간이 흘러갑니다</p>
					</div>
				</div>
			</div>
		</div>
		<div id="finish-type" style="margin-top: 20px;">
			<button id="finish-type-button" onclick="endTypingPractice();">연습
				종료</button>
		</div>
	</div>

	<script>
	var typedLength = 0; // 타자 수

var typings = [<%for (CategoryVO typing : typings) {%>
    { title: '<%=typing.getTitle()%>' },
<%}%>];

var currentIndex = 0;

function displayTyping(typings, currentIndex) {
    var typingContainer = document.getElementById('type-wrapper');
    typingContainer.innerHTML = '';
    for (var i = currentIndex; i < currentIndex + 5 && i < typings.length; i++) {
        var typing = typings[i];
       
        var typingElement = document.createElement('div');
        var typingTitle = document.createElement('p');
        typingTitle.textContent = typing.title;
        
        var typingInput = document.createElement('input');
        typingInput.type = 'text';
        typingInput.name = 'typed';
        typingInput.id = 'typed-' + i;
        typingInput.setAttribute('data-index', i);
        if (i === 0) {
            typingInput.autofocus = true;
            typingInput.addEventListener('click', function(event) {
                event.target.focus();
            });
        }
        if (i === currentIndex) {
        	  typingInput.addEventListener('keydown', function(event) {
        	    if (event.keyCode === 13) {
        	      var inputValue = event.target.value;
        	      var currentTyping = typings[currentIndex];
        	      
        	      if (inputValue === currentTyping.title) {
        	        currentIndex++;
        	        typedLength += inputValue.length;
        	        console.log(typedLength); // 입력된 올바른 타자 수 
        	        if (currentIndex < typings.length) {
        	          displayTyping(typings, currentIndex);
        	          document.getElementById('typed-' + currentIndex).focus();
        	        }
        	      } else {
        	        clearInterval(intervalId);
        	        //alert('종료!');
        	        document.getElementById('user-img').src = '../static/image/fail-boy.png';
        	        document.querySelectorAll('#category-p-title')[0].textContent = '오타발견 !';
        	        document.querySelectorAll('#category-p-title')[1].textContent = '종료되었습니다.';
        	        var inputs = document.querySelectorAll('input[name="typed"]');
        	        for (var i = 0; i < inputs.length; i++) {
        	          inputs[i].disabled = true;
        	          event.target.style.color = 'red';
        	        }
        	      }
        	    }
        	  });
        	} else {
        	  typingInput.disabled = true;
        	}
        typingElement.appendChild(typingTitle);
        typingElement.appendChild(typingInput);
        typingContainer.appendChild(typingElement);
    }
}

displayTyping(typings, currentIndex);

// timer
var timer = document.getElementById('timer');
var seconds = 0;
var minutes = 0;

function updateTimer() {
  seconds++;
  if (seconds >= 60) {
    seconds = 0;
    minutes++;
  }
  timer.textContent = ('0' + minutes).slice(-2) + ':' + ('0' + seconds).slice(-2);
}

var intervalId = setInterval(updateTimer, 1000);

function endTypingPractice() {
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "typingResultCheck");

    var nicknameInput = document.createElement("input");
    nicknameInput.type = "hidden";
    nicknameInput.name = "nickname";
    nicknameInput.value = "<%=userNickname%>";

    var finishTimeInput = document.createElement("input");
    finishTimeInput.type = "hidden";
    finishTimeInput.name = "finishTime";
    finishTimeInput.value = document.getElementById('timer').textContent;

    var typedLengthInput = document.createElement("input");
    typedLengthInput.type = "hidden";
    typedLengthInput.name = "typedLength";
    typedLengthInput.value = typedLength;

    var typeIDInput = document.createElement("input");
    typeIDInput.type = "hidden";
    typeIDInput.name = "typeID";
    typeIDInput.value = "<%=typeId%>"; // Include the typeID

    // Include the teamName, teamPracticeName, and title for typeID 2
    if (typeIDInput.value === "2") {
        var teamNameInput = document.createElement("input");
        teamNameInput.type = "hidden";
        teamNameInput.name = "teamName";
        teamNameInput.value = "<%=teamName%>";

        var teamPracticeNameInput = document.createElement("input");
        teamPracticeNameInput.type = "hidden";
        teamPracticeNameInput.name = "teamPracticeName";
        teamPracticeNameInput.value = "<%=teamPracticeName%>";

        var titleInput = document.createElement("input");
        titleInput.type = "hidden";
        titleInput.name = "title";
        titleInput.value = "<%=title%>";
    }

    form.appendChild(nicknameInput);
    form.appendChild(finishTimeInput);
    form.appendChild(typedLengthInput);
    form.appendChild(typeIDInput);

    // Include the team-related inputs if typeID is 2
    if (typeIDInput.value === "2") {
        form.appendChild(teamNameInput);
        form.appendChild(teamPracticeNameInput);
        form.appendChild(titleInput);
    }

    document.body.appendChild(form);
    form.submit();
}
</script>


</body>

</html>
