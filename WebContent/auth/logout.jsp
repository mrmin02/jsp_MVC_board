<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:remove var="id" scope="session"/>
<c:remove var="name" scope="session"/>
<c:remove var="admin" scope="session"/>

<%
	out.println("<script>alert('�α׾ƿ� �Ǿ����ϴ�.');</script>");
	out.println("<script>window.location.href='/';</script>");
 %>