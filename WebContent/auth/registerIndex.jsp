<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div> <h4>ȸ������</h4> </div>
	<div>
	<form action="/registerPost.do" method="post">
		<table>
			<tr>
				<td>�̸�</td>
				<td><input name="name" type="text" required/></td>
			</tr>
			<tr>
				<td>���̵�</td>
				<td><input name="user_id" type="text" required/></td>
			</tr>
			<tr>
				<td>��й�ȣ</td>
				<td><input name="pwd" type="password" required/></td>
			</tr>
			<tr>
				<td>�̸���</td>
				<td><input name="email" type="email" required/></td>
			</tr>
			<tr>
				<td>��ȭ��ȣ</td>
				<td><input name="phone" type="text" requiredc/></td>
			</tr>
		</table>
		<button type="submit">����</button>
	</form>		
	</div>	
</body>
</html>