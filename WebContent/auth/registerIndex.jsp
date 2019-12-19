<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div> <h4>회원가입</h4> </div>
	<div>
	<form action="/registerPost.do" method="post">
		<table>
			<tr>
				<td>이름</td>
				<td><input name="name" type="text" required/></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input name="user_id" type="text" required/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input name="pwd" type="password" required/></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input name="email" type="email" required/></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input name="phone" type="text" requiredc/></td>
			</tr>
		</table>
		<button type="submit">가입</button>
	</form>		
	</div>	
</body>
</html>