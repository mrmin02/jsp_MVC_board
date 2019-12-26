function drawAnswers(datas,user_id,admin){
	$(".answers").empty();
	datas.map((data)=>{
		var userId = data.user_id;
		var content = data.text;
		
		$(".answers").append(`ID<br>${userId}<br>내용<br><div class="newAnswer">${content}</div>`);
		
		if(user_id == userId){
			console.log("글 작성자");
			
		}else if(admin == '1'){
			console.log("관리자");
		}

	});
}

function addAnswer(article_id, user_id, admin){
	var answer = $("#answer_text").val();
	if($.trim(answer).length == 0){
		alert("댓글 내용을 입력해주세요");
		return;
	}
	$.ajax({
		type: "POST",
		url: "/article/addAnswer.do",
		dataType:"json",
		data: {article_id:article_id,user_id:user_id,answer:answer},
		success: function(data){
//		   	화면에 다시 그리는 활동
			console.log("ajax 성공");
			drawAnswers(data,user_id,admin);
		   },
		error:function(err) {
			console.log("에러발생");
		},
	});
	
}

function deleteAnswer(answer_id,article_id,user_id,admin){
	$.ajax({
		type: "POST",
		url: "/article/AnswerDelete.do",
		dataType:"json",
		data: {answer_id:answer_id,article_id:article_id},
		success: function(data){
//		   	화면에 다시 그리는 활동
			console.log("ajax 성공");
			drawAnswers(data,user_id,admin);
		   },
		error:function(err) {
			console.log("에러발생");
		},
	});
}

function modifyAnswer(answer_id,article_id,user_id,admin){
	var answer = $(".newText").val();
	$.ajax({
		type: "POST",
		url: "/article/AnswerModify.do",
		dataType:"json",
		data: {answer_id:answer_id,article_id:article_id,answer:answer},
		success: function(data){
//		   	화면에 다시 그리는 활동
			console.log("ajax 성공");
			drawAnswers(data,user_id,admin);
		   },
		error:function(err) {
			console.log("에러발생");
		},
	});
}

function click_modify(text,answer_id,article_id,user_id,admin){
	$(".newAnswer").empty();
	$(".newAnswer").append(`<textArea class="newText">${text}</textArea>`);
	$(".newButton").empty();
	$(".newButton").append(`<button type="button" onclick="modifyAnswer('${answer_id}','${article_id}','${user_id}','${admin}')">수정 완료</button>`);
}
//console.log(`request.getParameter('id')`);
