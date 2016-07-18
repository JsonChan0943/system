<!DOCTYPE html>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<html>
<head>
	<title>主页</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>   
	<div id="index_layout">
		<div data-options="region:'north',border:false" style="height:90px;overflow:hidden;width: 1366px;background:url(${path}/resources/style/view/top.png)">
			<div style="text-align:right;">
				<b>
					欢迎<span style="color:red;">
					${sessionScope.userInfo.user_name}
					</span>登陆系统&nbsp;&nbsp;
					<span><b>更换皮肤:</b></span>
					<select onchange="changeTheme(this)">
					   <option value="default">default</option>
					   <option value="bootstrap">bootstrap</option>
					   <option value="gray">gray</option>
					   <option value="ui-cupertino">ui-cupertino</option>
					   <option value="ui-pepper-grinder">ui-pepper-grinder</option>
					   <option value="ui-sunny">ui-sunny</option>
				  	</select> 
					<a href="javascript:void(0)" onclick="editPwd();">修改密码</a>&nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="logout();">安全退出</a>
				</b>	
			</div>
		</div>
		<div data-options="region:'west',title:'导航菜单',split:true" style="width:200px; overflow:hidden;overflow-y:auto;">
			<div id="menu_id" class="easyui-accordion" fit="true" style="height: auto;">
	            <div>菜单加载中....</div>
	        </div>
		</div>
		<div data-options="region:'center'" style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="首页" data-options="border:false" style="overflow: hidden;">
					<div style="padding:10px 0 10px 10px">
						<h2>系统介绍</h2>
						<div class="light-info">
							<div class="light-tip icon-tip"></div>
							<div>JAVA快速开发平台。</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 修改密码 -->
	<div id="modifyPwdDiv">
		<form id="modifyPwdForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">原密码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input type="password" class="easyui-validatebox textbox" validType="length[4,12]" data-options="required:true" style="width:200px;height:20px" name="old_password" id="old_password_id">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">新密码:</font></span>	
					</td>
					<td style="text-align: left;">
						<input type="password" class="easyui-validatebox textbox" validType="length[4,12]" data-options="required:true" style="width:200px;height:20px" name="new_password" id="new_password_id">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">再输一遍：</font></span>
					</td>
					<td style="text-align: left;">
						<input type="password" class="easyui-validatebox textbox" validType="equalTo['#new_password_id']" invalidMessage="两次输入密码不匹配" data-options="required:true" style="width:200px;height:20px" name="confirm_new_password" id="confirm_new_password_id">
					</td>
				</tr>
				</br>
				<tr style=" text-align:center;">
					<td colspan="4">
						<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="modifyPwd_Save();">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="modifyPwd_Reset();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
		</div>
		<div data-options="region:'south',border:false" style="height: 30px;line-height:30px; overflow: hidden;text-align: center;background-color: #eee" >
			版权所有@飞奔的鸵鸟 
		</div>
	</div>
<script type="text/javascript" src="${path}/resources/js/ui/changeEasyuiTheme.js"></script>
<script type="text/javascript" src="${path}/resources/js/ui/index.js"></script>
<script type="text/javascript" src="${path}/resources/js/ui/menu.js"></script>
</body>
</html>
