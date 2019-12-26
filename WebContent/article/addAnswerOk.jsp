<%@page import="com.google.gson.JsonArray"%>
<%@page import="bean.AnswerDataBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.google.gson.JsonObject"%>

<%

ArrayList<AnswerDataBean> arr = new ArrayList<AnswerDataBean>();
arr = (ArrayList<AnswerDataBean>)request.getAttribute("article");
JsonArray list = new JsonArray();

for(AnswerDataBean val : arr){
	JsonObject json = new JsonObject();
	json.addProperty("user_id",val.getUser_id());
	json.addProperty("text",val.getText());
	list.add(json);
}

out.println(list.toString());


%> 


