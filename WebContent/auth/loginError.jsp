<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.io.PrintWriter"%>


<!--
<c:if test="${message}!=null">
<script>alert("${message}");</script>
<script>window.location.href="/auth/login.jsp"</script>
</c:if>
-->
<% 
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.print("alert(\"");
	script.print(request.getAttribute("message"));
	script.println("\");");
	script.println("location.href='/auth/loginIndex.jsp'");
	script.println("</script>");

%>
