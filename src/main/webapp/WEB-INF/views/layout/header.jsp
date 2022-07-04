<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- TODO security 설정 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- TODO -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
	  <ul class="navbar-nav">
	    <li class="nav-item active">
	      <a class="nav-link" href="/">블로그</a>
	    </li>
	    <c:choose>
	    	<c:when test="${empty principal}">
	    		<li class="nav-item">
		      <a class="nav-link" href="/auth/login_form">로그인</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" href="/auth/join_form">회원가입</a>
		    </li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="nav-item">
			      <a class="nav-link" href="/board">글쓰기</a>
			    </li>
			    <li class="nav-item">
			      <a class="nav-link" href="/user/update_form">회원 정보</a>
			    <li class="nav-item">
			      <a class="nav-link" href="/logout">로그아웃</a>
			    </li>	
	    	</c:otherwise>
	    </c:choose>
  </ul>
	</nav>