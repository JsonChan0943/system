<!DOCTYPE html>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<html>
<head>
	<title>用户管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>   
	<!-- 搜索框 -->
	<div>
		登陆用户名：<input class="easyui-textbox" style="width:150px;height:20px" name="user_login_name" id="user_login_name">
		用户名：<input class="easyui-textbox" style="width:150px;height:20px" name="user_name" id="user_name">
		<a id="search_id" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		<a id="reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'">重置</a>
	</div>
	</br>
	
	<!-- 数据列表区域 -->
	<div id="dataGridDiv" style="height:400px;">
		<table id="dataGrid">
			
		</table>
	</div>
	
	<!-- 增加用户 -->
	<div id="addUserDiv" style="display:none">
		</br>
		<form id="addUserForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">用户登陆名:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="easyui-validatebox textbox" style="width:200px;height:20px" data-options="required:true" id="user_login_name" name="user_login_name">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">登陆名：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" name="user_name" id="user_name">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">用户所属机构：</font></span>
					</td>
					<td style="text-align: left;">
						<input id="organ_code_id" name="organ_code" class="easyui-validatebox textbox" style="width:200px;height:20px">
						<input type="hidden" id="organ_name_id" name="organ_name"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">角色：</font></span>
					</td>
					<td style="text-align: left;">
						<input id="roles" name="roles" class="easyui-validatebox textbox" style="width:200px;height:20px">
					</td>
				</tr>
				
				</br>
				<tr style=" text-align:center;">
					<td colspan="4">
						<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="addUser_Save();">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="addUser_Reset();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 修改用户信息 -->
	<div id="modifyUserDiv" style="display:none">
		</br>
		<form id="modifyUserForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">用户登陆名:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="easyui-validatebox textbox" style="width:200px;height:20px" data-options="required:true" id="user_login_name_modify" name="user_login_name">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">登陆名：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" name="user_name" id="user_name_modify">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">用户所属机构：</font></span>
					</td>
					<td style="text-align: left;">
						<input id="organ_code_id_modify" name="organ_code" class="easyui-validatebox textbox" style="width:200px;height:20px">
						<input type="hidden" id="organ_name_id_modify" name="organ_name"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">角色：</font></span>
					</td>
					<td style="text-align: left;">
						<input id="roles_modify" name="roles" class="easyui-validatebox textbox" style="width:200px;height:20px">
					</td>
				</tr>
				<input type="hidden" id="id_modify" name="id"><!-- 用户记录主键 -->
				</br>
				<tr style=" text-align:center;">
					<td colspan="4">
						<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="modifyUser_Save();">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="modifyUser_Reset();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" src="${path}/resources/js/ui/system/user/SystemUserIndex.js"></script>
</body>
</html>
