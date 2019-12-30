function addAnswer(article_id,user_id){
	var answer = $("#answer_text").val();
	$.ajax({
		type: "POST",
		url: "/article/addAnswer.do",
		data:{article_id:article_id,user_id:user_id,answer:answer},
	}).done(function(data){
		console.log("ajax 성공");
		$(".answers").val('');
		$(".answers").html(data);
	});
}

function updateClick(article_id,answer_id){
	var preAnswer = $(".answer"+answer_id).html();
	$(".answerDiv"+answer_id).empty();
	$(".answerDiv"+answer_id).append(`<textArea class="newAnswer${answer_id}">${preAnswer}</textArea>`);
	$(".U_Button"+answer_id).html('');
	$(".U_Button"+answer_id).append(`<button type="button" onclick="updateCommitClick('${article_id}','${answer_id}')">수정완료</button>`);	
}

function deleteClick(article_id,answer_id){
	$.ajax({
		type: "POST",
		url: "/article/AnswerDelete.do",
		data:{article_id:article_id,answer_id:answer_id},
	}).done(function(data){
		console.log("ajax 성공");
		$(".answers").val('');
		$(".answers").html(data);
	});
}
function updateCommitClick(article_id,answer_id){
	var newAnswer =  $(".newAnswer"+answer_id).val();
	if(newAnswer==''){
		return alert("수정할 내용을 입력해 주세요");
	}
	$.ajax({
		type: "POST",
		url: "/article/AnswerUpdate.do",
		data:{article_id:article_id,answer_id:answer_id,newAnswer:newAnswer},
	}).done(function(data){
		console.log("ajax 성공");
		$(".answers").val('');
		$(".answers").html(data);
	});
}
$(document).ready(function(){
	var	article_id = window.location.search.split('?')[1].split('=')[1]
	$.ajax({
		type: "GET",
		url: "/article/AnswerLoding.do",
		data:{article_id:article_id},
	}).done(function(data){
		console.log("ajax 성공");
		$(".answers").val('');
		$(".answers").html(data);
	});
});