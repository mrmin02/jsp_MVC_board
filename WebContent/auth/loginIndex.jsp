<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div><h1>로그인 페이지</h1></div>
	<form action="/loginPost.do" method="post">
		<div>
		아이디 <input id="id" name="id" type="text"/><br>	
		비밀번호<input id="pwd" name="pwd" type="password"/>
		<button type="submit">로그인</button>
		</div>
	</form>	
	<div>
		<a href="/auth/registerIndex.jsp">회원가입</a>
	</div>
</body>
</html>