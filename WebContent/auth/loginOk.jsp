<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("UTF-8"); %>

<c:set var="id" value="${id}" scope="session"/>
<c:set var="name" value="${name}" scope="session"/>
<c:set var="admin" value="${admin}" scope="session"/>


<% 
	out.println("<script>alert('로그인 성공');</script>");
	out.println("<script>window.location.href='/';</script>"); %>