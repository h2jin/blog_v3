<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
	
	<form action="/auth/loginProc" method="post" class="p-5">
	  <div class="form-group">
	    <label for="username">User name:</label>
	    <input class="form-control" name="username" value="test1" placeholder="Enter name" id="username">
	  </div>
	  <div class="form-group">
	    <label for="password">Password:</label>
	    <input type="password" class="form-control" value="asd123" name="password" placeholder="Enter password" id="password">
	  </div>
	  <div class="form-group form-check">
	    <label class="form-check-label">
	      <input class="form-check-input" type="checkbox"> Remember me
	    </label>
	  </div>
	  <button type="submit" class="btn btn-outline-info" id="btn-login">로그인</button>
	</form>
		
	
<%@ include file="../layout/footer.jsp" %>