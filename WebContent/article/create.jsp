<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>새로운 글 쓰기</h2>
<%
	if(session.getAttribute("id")==null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인이 필요한 서비스 입니다. 로그인 해주세요');");
		script.println("location.href='/auth/loginIndex.jsp';");
		script.println("</script>");
	
	}
	
%>
<c:if test="${message}!=null">
<script>alert(${message});</script>
</c:if>
	<form action="/article/createArticle.do" method="post">
		<br><h5>제목</h5><br>
		<input type="text" max="20" name="title" required/>
		<br><h5>내용</h5><br>
		<textarea type="text" name="content" required></textarea>
		<input type="hidden" name="id"value=<c:out value="${sessionScope.id}">
		</c:out> />
		<button type="submit">작성</button>	
	</form>
</body>
</html>