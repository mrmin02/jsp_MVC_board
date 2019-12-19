<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div>메인 페이지</div>
	<c:choose>
		<c:when test="${empty sessionScope.id}">
			<a href="/auth/loginIndex.jsp">로그인 하러 가기</a>
	    </c:when>
		<c:otherwise>
			<div>
				<a href="/auth/logout.jsp">로그아웃</a>
			</div>
	    </c:otherwise>
	</c:choose>
	<div><a href="/article/index.jsp">글 목록</a></div>
</body>
</html>