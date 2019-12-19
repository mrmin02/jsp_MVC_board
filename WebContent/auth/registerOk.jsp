<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	out.println("<script>alert('회원가입 완료');</script>");
	out.println("<script>window.location.href='/';</script>");
%>
<!--response.sendRedirect("/"); -->
<!-- pageContext.forward("/"); -->