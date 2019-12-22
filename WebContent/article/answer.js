$(document).ready(function(){
	function aaa(article_id, user_id){
		$.ajax({
			type: "POST",
			url: "#",
			data: {article_id:article_id,user_id:user_id},
			success: function(data){
			   	window.location.href="#";
			   },
			error:function(err) {
				console.log(err);
			}
		});
	}
});