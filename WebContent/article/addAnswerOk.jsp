<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${not empty requestScope.answer }">
	<c:forEach var="answer" items="${requestScope.answer}">
		ID <br>${answer.getUser_id()}<br><br>
		내용 <br><div class="answerDiv${answer.getId()}"><p class="answer${answer.getId()}">${answer.getText() }</p></div><br><br>
		<c:choose>
			<c:when test="${answer.getUser_id() eq sessionScope.id}">
				<div class="U_Button${answer.getId()}">
					<button type="button" onclick="updateClick('${answer.getArticle_id()}','${answer.getId()}')">수정</button>
				</div>
					<button type="button" onclick="deleteClick('${answer.getArticle_id()}','${answer.getId()}')">삭제</button>
			</c:when>
			<c:when test="${sessionScope.admin eq '1'}">
					<button type="button" onclick="deleteClick('${answer.getArticle_id()}','${answer.getId()}')">삭제</button>
			</c:when>
		</c:choose>
		<br><br>
	</c:forEach>
</c:if>


