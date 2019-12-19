<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	out.println("<script>alert('로그인 실패');</script>");
	out.println("<script>window.location.href='/login.do';</script>");
	%>