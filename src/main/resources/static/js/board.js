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
	}
	
}

index.init();