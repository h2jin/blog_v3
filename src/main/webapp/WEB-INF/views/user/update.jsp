<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
	
	<form action="#" method="post" class="p-5">
	<input type="hidden" id="id" value="${principal.user.id}">
	  <div class="form-group">
	    <label for="username">User name:</label>
	    <input class="form-control" name="username" value="${principal.user.username}" placeholder="Enter name" id="username">
	  </div>
	  <div class="form-group">
	    <label for="password">Password:</label>
	    <input type="password" class="form-control" value="" name="password" placeholder="Enter password" id="password">
	  </div>
	  <div class="form-group">
			<label for="email"> email : </label>
			<input type="text" value="${principal.user.email}" name="email" id="email" class="form-control">
		</div>
	  <div class="form-group form-check">
	    <label class="form-check-label">
	      <input class="form-check-input" type="checkbox"> Remember me
	    </label>
	  </div>
	  <button type="button" class="btn btn-info" id="btn-update">회원 수정완료</button>
	</form>
		
<script src="/js/user.js"></script>
	
<%@ include file="../layout/footer.jsp" %>