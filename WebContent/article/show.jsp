<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.io.PrintWriter"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/article/answer.js" ></script> 
</head>
<body>
	<sql:query var="rs" dataSource="jdbc/orcl">
		select * from articles where id = <%= request.getParameter("id") %>
	</sql:query>
	<c:set var="rs" value="${rs.rows[0]}"/>
	No : <c:out value="${rs['id']}"></c:out><br>
	제목:	<c:out value="${rs['title']}"></c:out><br>
	작성자:<c:out value="${rs['user_id']}"></c:out><br>
	내용:	<c:out value="${rs['content']}"></c:out><br>
	일자: <c:out value="${rs['created_time']}"></c:out><br>
	
	<a href="/article/index.jsp">뒤로가기</a>	
	<c:if test="${rs['user_id']==sessionScope.id}" var="result">
		<a href="/article/edit.jsp?id=${rs['id']}">수정</a>
		<form action="/article/articleDelete.do?id=${rs['id']}"method="post">
			<button type="submit">삭제</button>
		</form>
	</c:if>
	<br>
	<c:choose>
		<c:when test="${empty sessionScope.id}">
			댓글을 등록하기 위해서는 로그인이 필요합니다.
			<a href="/auth/loginIndex.jsp">로그인 하러 가기</a>
	    </c:when>
		<c:otherwise>
			<form>
				댓글작성 <br><textarea id="answer_text" type="text" required></textarea>
				<button type="button" onclick="addAnswer('${rs['id']}','${sessionScope.id}')">답변등록</button>
			</form>
	    </c:otherwise>
	</c:choose>
	<div><h4>댓글</h4></div>
	<sql:query var="an" dataSource="jdbc/orcl">
		select * from answers where article_id = <%= request.getParameter("id") %> order by id ASC
	</sql:query>
	<div class="answers">
		<c:forEach var="an" items="${an.rows}">
			ID <br>
			<c:out value="${an['user_id']}"></c:out><br><br>
			내용 <br>
			<div class="newAnswer">
				<c:out value="${an['answer']}"></c:out>
			</div><br><br>
			<c:choose>
				<c:when test="${sessionScope.id eq an['user_id'] }">
					<div class="newButton">
						<button type="button" onclick="click_modify('${an['answer']}','${an['id']}','${an['article_id']}','${an['user_id']}','${sessionScope.admin}' )">수정</button>	
					</div>
					
					<button type="button" onclick="deleteAnswer('${an['id']}','<c:out value="${param.id}"/>','${sessionScope.admin}' )">삭제</button>
			    </c:when>
			    <c:when test="${sessionScope.admin eq '1' }">
					<button type="button" onclick="deleteAnswer('${an['id']}','<c:out value="${param.id}"/>','${sessionScope.admin}' )">삭제</button>
			    </c:when>
			</c:choose>
			<br>
		</c:forEach>
	</div>
</body>
</html>