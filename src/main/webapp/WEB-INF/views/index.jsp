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
	<br/>
	<ul class="pagination justify-content-center">
	<c:set var="isDisabled" value="disabled"></c:set>
	<c:set var="isNotDisabled" value=""></c:set>
	<c:set var="isNowPage" value="active"></c:set>
		<li class="page-item ${pageable.first ? isDisabled : isNotDisabled}">
	  <a class="page-link" href="?page=${pageable.number - 1}">Previous</a>
	  </li>
	  <c:forEach var="num" items="${pageNumbers}">
	  	<c:choose>
	  		<c:when test="${pageable.number + 1 eq num}">
	  			<li class="page-item active"><a class="page-link" href="/board/search/?q=${searchTitle}&page=${num -1}">${num}</a></li>
	  		</c:when>
	  		<c:otherwise>
	  			<li class="page-item"><a class="page-link" href="/board/search/?q=${searchTitle}&page=${num -1}">${num}</a></li>	
	  		</c:otherwise>
	  	</c:choose>
	  </c:forEach>
	  <li class="page-item ${pageable.last ? isDisabled : isNotDisabled}">
	  <a class="page-link" href="?page=${pageable.number + 1}">Next</a>
	  </li>
	 
	</ul>
	<hr/>
	
<script src="/js/board.js"></script>
	
<%@ include file="layout/footer.jsp" %>	
