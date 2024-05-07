

function UserManager(){
	$.post({
		url : "/user/UserList",
		contentType : "application/json",// @RequestBody 需要这个来确定
		data : JSON.stringify({
			"nickname" : "",
			 
		}),
		dataType : "json",
		success : function(data) {
			alert(data);	
		},
		error : function(data) {
			toastr.error(data.msg);
		}
	});

	
	
	}
$(function() {
	

});