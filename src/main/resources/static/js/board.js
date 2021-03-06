let index = {
	
	init: function() {
		
		$("#btn-board-save").bind("click", () => {
			this.save();
		});
		
		$("#btn-board-update").bind("click", () => {
			this.update();
		});
		
		$("#btn-board-delete").bind("click", () => {
			this.delete();
		});
		
		$("#btn-reply-save").bind("click", () => {
			this.saveReply();
		});
		
		
	},
	
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			console.log(response)
			if(response.data) {
				alert("글 저장 완료");
				location.href = "/";	
			}
		}).fail(function() {
			alert("글 저장 실패")
		});
	},
	
	update: function() {
		let boardId = $("#boardId").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/board/update/" + boardId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			console.log(response)
			if(response.data) {
				alert("수정완료");
				location.href = "/";
			}
		}).fail(function(error) {
			console.log(error)
			alert("수정 실패");
		});
	},
	
	delete: function() {
		let boardId = $("#boardId").val();
		
		$.ajax({
			type: "DELETE",
			url: "/board/delete/" + boardId
		}).done(function(response) {
			if(response.data) {
				alert("삭제 완료");
				location.href = "/";
			}
		}).fail(function(error) {
			console.log(error)
			alert("삭제 실패");
		});
	},
	
	saveReply: function() {
		let data = {
			boardId:  $("#board-id").text(),
		 	content: $("#reply-content").val()
		}
		
		console.log("boardId : " + data.boardId);
		console.log("content : " + data.content);
		
		
		$.ajax({
			type: "POST",
			url: `/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(response) {
			if(response.status) {
				console.log(response.data)
				addReplyElement(response.data);
			}
			alert("댓글 등록 성공")
		}).fail(function() {
			alert("등록 실패")
		});
		
	}
	
	
	
	
}


index.init();


