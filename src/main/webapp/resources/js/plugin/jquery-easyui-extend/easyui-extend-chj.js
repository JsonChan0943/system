/**
 * 自定义拓展规则
 */
$(function() {
	/**
	 * @author chenhuaijie
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		/*
		 * 必须和某个字段相等
		 */
		equalTo : {
			validator : function(value, param) {
				return $(param[0]).val() == value;
			},
			message : '两次输入不一致'
		},
		/*
		 * 输入正确的身份号码
		 */
		idcard : {
			validator : function(value, param) {
				return idCardNoUtil.checkIdCardNo(value);
			},
			message : '请输入正确的身份证号码'
		},
		/*
		 * 校验整数
		 */
		checkNum : {
			validator : function(value, param) {
				return /^([0-9]+)$/.test(value);
			},
			message : '请输入整数'
		},
		/*
		 * 请输入合法的数字
		 */
		checkFloat : {
			validator : function(value, param) {
				return /^[+|-]?([0-9]+\.[0-9]+)|[0-9]+$/.test(value);
			},
			message : '请输入合法数字'
		}
	});
});