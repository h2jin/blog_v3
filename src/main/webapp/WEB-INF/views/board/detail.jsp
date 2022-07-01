<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

	<div class="container">
	<input type="hidden" id="boardId" value="${board.id}">
		<div>
			<div class="d-flex justify-content-between">
				<button class="btn mt-3" onclick="history.back();">
					<span class="material-symbols-outlined">arrow_back</span>
				</button>
				<c:choose>
					<c:when test="${principal.user.id eq board.userId.id}">
						<div class="d-flex p-3 mt-2">
							<a href="/board/updateForm/${board.id}" class="btn btn-outline-success mr-2" >수정</a>
							<a id="btn-board-delete" class="btn btn-outline-danger">삭제</a>
						</div>
					</c:when>
				</c:choose>
			</div>
			
			
			<div class="d-flex justify-content-between mt-3" >
				<h3>${board.title}</h3>
				<div>
					글 번호 : <span id="board-id"><i>${board.id}</i></span><br/>
					글 작성자 : <span><i>${board.userId.username}</i></span>
				</div>	
			</div> 
			<p>작성일 : ${board.createDate}</p> 
			<p>조회수 : ${board.count}</p> 
			<hr/>
			<h5>${board.content}</h5>
		</div>
	</div>
	
<script src="/js/board.js"></script>	
	
<%@ include file="../layout/footer.jsp" %>

