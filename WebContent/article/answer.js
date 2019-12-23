function addAnswer(article_id, user_id){
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
			console.log(data);
		   },
		error:function(err) {
			console.log("에러발생");
		}
	});
}