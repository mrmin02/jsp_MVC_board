<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<a href="/article/create.jsp">글쓰기</a>
	<sql:query var="rs" dataSource="jdbc/orcl">
			select * from articles
	</sql:query>
	<table>
		<tr>
			<td>제목</td>
			<td>작성자</td>
			<td>시간</td>
		</tr>	
		<c:forEach var="rs" items="${rs.rows}">
			<tr>
				<td><div style="width:50px; text-overflow: ellipsis;overflow: hidden;"><c:out value="${rs['title']}"></c:out></div></td>
				<td><c:out value="${rs['user_id']}"></c:out></td>
				<td><c:out value="${rs['created_time']}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>