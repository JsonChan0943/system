/**
 * 登陆-chenhuaijie
 */
function login() {
	$("#userTip").html("");
	if ($("#loginForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'login/doLogin.html',
			dataType : "json",
			data : $("#loginForm").serialize(),
			success : function(result) {
				if (result.success) {
					// 登陆成功跳转
					var url = webroot + "login/goIndex.html";
					window.location.href = url;
				} else {
					$("#userTip").html(result.msg);
					$("#verifyCode_id").val("");
					refreshVerifyCodeImg();// 更新验证码
				}
			}
		});
	}
}

/**
 * 登陆重置
 */
function loginReset() {
	$("#user_login_name_id").val("");
	$("#password_id").val("");
	$("#verifyCode_id").val("");
	$("#userTip").html("");
}

/**
 * 回车事件登陆
 * 
 * @param event
 */
function handleEnter(event) {
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if (keyCode == 13) {
		login();
	}
}

/**
 * 更新验证码
 */
function refreshVerifyCodeImg() {
	var verifyCodeImg = document.getElementById("verifyCodeImg");
	verifyCodeImg.src = webroot + "login/verifyCode.html?" + Math.random();
}

$(function() {
	refreshVerifyCodeImg();
});