<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.io.PrintWriter"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<script type="text/javascript" src="/article/answer.js" ></script> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<sql:query var="rs" dataSource="jdbc/orcl">
		select * from articles where id = <%= request.getParameter("id") %>
	</sql:query>

	<c:set var="rs" value="${rs.rows[0]}"/>
	No : <c:out value="${rs['id']}"></c:out><br>
	����:	<c:out value="${rs['title']}"></c:out><br>
	�ۼ���:<c:out value="${rs['user_id']}"></c:out><br>
	����:	<c:out value="${rs['content']}"></c:out><br>
	����: <c:out value="${rs['created_time']}"></c:out><br>
	
	<a href="/article/index.jsp">�ڷΰ���</a>	
	<c:if test="${rs['user_id']==sessionScope.id}" var="result">
		<a href="/article/edit.jsp?id=${rs['id']}">����</a>
		<form action="/article/articleDelete.do?id=${rs['id']}"method="post">
			<button type="submit">����</button>
		</form>
	</c:if>
	<br>
	<form>
		����ۼ� <br><textarea type="text"></textarea>
<!-- 		<button type="button" onclick="addAnswer(${rs['id']},${sessionScope.id})">�亯���</button> -->
		<button type="button" onclick="addAnswer(${rs['id']},${sessionScope.id})">�亯���</button>
	</form>
	
</body>
</html>