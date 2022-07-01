<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>

	<div class="container p-3">
		<c:forEach var="board" items="${pageable.content}">
		<div class="card p-4 m-2">
			<div class="card-body d-flex justify-content-between">
				<h4>${board.title}</h4>
				<h7>글쓴이 : ${board.userId.username}</h7>
			</div>
			<div class="d-flex justify-content-end">
				<a href="/board/${board.id}" class="btn btn-outline-info ">상세보기</a>
			</div>
		</div>			
		</c:forEach>

	</div>
	<hr/>
	
<%@ include file="layout/footer.jsp" %>	
