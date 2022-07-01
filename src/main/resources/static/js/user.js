let index = {
	init: function() {
		$("#btn-join").bind("click", () => {
			this.join();
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
		
	} // end of join() 
	
	
} // end of index

index.init();