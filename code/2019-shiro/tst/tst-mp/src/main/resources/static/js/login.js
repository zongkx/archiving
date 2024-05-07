toastr.options.positionClass = 'toast-center-center';
$(function() {

	$('#registDialogBtn').click(function() {
		$("#myModalLabel").text("注册");
		$('#myModal').modal();
	});

	$('#loginBtn').click(function() {
		var nickname = $('#nickname').val().trim();
		var pswd = $('#pswd').val().trim();
		$.ajax({
			type : "POST",
			url : "/login",
			contentType : "application/json",// @RequestBody 需要这个来确定
			data : JSON.stringify({
				"nickname" : nickname,
				"pswd" : pswd
			}),
			dataType : "json",
			success : function(data) {
				if (data.code === 1) {
					location.href = '/index';
				}
				if (data.code === -1) {
					toastr.error('賬戶錯誤');
				}
			},
			error : function(data) {
				toastr.error(data.msg);
			}
		});
	});

	$('#registBtn').click(function() {
		var nickname = $('#nickname1').val().trim();
		var pswd = $('#pswd1').val().trim();
		$.ajax({
			type : "POST",
			url : "/registUser",
			contentType : "application/json",// @RequestBody 需要这个来确定
			data : JSON.stringify({
				"nickname" : nickname,
				"pswd" : pswd
			}),
			dataType : "json",
			success : function(data) {
				if (data.code === 1) {
					toastr.success('注冊成功');
					setTimeout(function() {
						$("#myModal").modal("hide")
					}, 500);
				}
				if (data.code === -1) {
					$('#nickname1').val('');
					$('#pswd1').val('');
					toastr.error('用户已存在');
				}
			},
			error : function(data) {
				toastr.error(data.msg);
			}
		});
	});
});