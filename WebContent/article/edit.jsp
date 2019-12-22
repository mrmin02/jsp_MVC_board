<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page import="java.io.PrintWriter"%>
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
	<form action="/article/articleUpdate.do?id=${rs['id']}" method="post">
		제목 <br><input type="text" name="title" value="${rs['title']}" required/><br>	
		내용 <br><textarea type="text" name="content" required>${rs['title']} </textarea>
		<button type="submit">수정 완료</button>
	</form>
	<a href="/article/show.jsp?id=${rs['id']}">뒤로가기</a>
</body>
</html>