<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

	<div class="container">
	<form>
		<div class="form-group mt-2">
		  <label>제목 : </label>
		  <input type="text" class="form-control" id="title">
		</div>
		<div class="form-group">
		  <label>내용 : </label>
		  <textarea class="form-control summernote" rows="5" id="content" ></textarea>
		</div>
	</form>
		<div class="d-flex justify-content-end">
			<button class="btn btn-info" id="btn-board-save" type="button">저장</button>
		</div>
		<br/>
		<br/>
	</div>
	
	<script>
      $('.summernote').summernote({
        placeholder: '내용을 입력해주세요',
        tabsize: 2,
        height: 300
      });
    </script>

<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp" %>