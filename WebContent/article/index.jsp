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
	<a href="/article/create.jsp">�۾���</a>
	<sql:query var="rs" dataSource="jdbc/orcl">
			select * from articles
	</sql:query>
	<table>
		<tr>
			<td>����</td>
			<td>�ۼ���</td>
			<td>�ð�</td>
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