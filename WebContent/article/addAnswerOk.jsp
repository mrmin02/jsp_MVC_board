<%@page import="bean.AnswerDataBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.google.gson.JsonObject"%>

<%
ArrayList<AnswerDataBean> arr = new ArrayList<AnswerDataBean>();  
arr = (ArrayList<AnswerDataBean>)request.getAttribute("all_article");

JsonObject data = new JsonObject();

System.out.println(data);
for(AnswerDataBean val : arr){
	JsonObject json = new JsonObject();
	json.addProperty("user_id",val.getArticle_id());
	json.addProperty("text",val.getText());
	System.out.println(json);
	data.addProperty(val.getId(), json.toString());
}



out.println(data.toString());

%> 



