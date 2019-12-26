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
			select * from articles order by created_time DESC
	</sql:query>
	
	<table>
		<tr>
			<td>No</td>
			<td>����</td>
			<td>�ۼ���</td>
			<td>�ð�</td>
		</tr>	
		<c:forEach var="rs" items="${rs.rows}">
			
			<tr>
				<td><c:out value="${rs['id']}"></c:out></td>
				<td>
					<div style="width:50px; text-overflow: ellipsis;overflow: hidden;">
					<a href="/article/show.jsp?id=<c:out value="${rs['id']}"></c:out>">
						<c:set var="articleId" value="${rs['id']}"/>
						<c:out value="${rs['title']}"></c:out>
					</div>
					</a>		
				</td>
				<td><c:out value="${rs['user_id']}"></c:out></td>
				<td><c:out value="${rs['created_time']}"></c:out></td>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>