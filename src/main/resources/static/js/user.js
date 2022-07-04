let index = {
	init: function() {
		$("#btn-join").bind("click", () => {
			this.join();
		});
		
		$("#btn-update").bind("click", () => {
			this.update();
		});
		
	},
	
	join: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			console.log(response);
			alert("회원가입 성공")
			location.href = "/";
		}).fail(function(error) {
			console.log(error);
			alert("회원가입 실패")
		});
		
	}, // end of join() 
	
	
	update: function() {
		let data = {
			username: $("#username").val(),
			id: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data) {
			if(data.status) {
				alert("회원정보 수정이 완료되었습니다.")
				location.href = "/";
			}			
		}).fail(function(error) {
			alert("회원정보 수정 실패")
		});
	} // end of update
	
	
} // end of index

index.init();