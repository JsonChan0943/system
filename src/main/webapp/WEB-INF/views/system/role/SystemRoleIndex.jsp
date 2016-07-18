<!DOCTYPE html>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<html>
<head>
	<title>角色管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>   
	<!-- 搜索框 -->
	<div>
		角色编码：<input class="easyui-textbox" style="width:150px;height:20px" name="role_code" id="role_code">
		角色名：<input class="easyui-textbox" style="width:150px;height:20px" name="role_name" id="role_name">
		<a id="search_id" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		<a id="reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'">重置</a>
	</div>
	</br>
	
	<!-- 数据列表区域 -->
	<div id="dataGridDiv" style="height:400px;">
		<table id="dataGrid">
			
		</table>
	</div>
	
	<!-- 增加角色 -->
	<div id="addRoleDiv">
		<form id="addRoleForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">角色编码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" style="width:200px;height:20px" id="role_code" name="role_code" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">角色名:</font></span>	
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" name="role_name" id="role_name">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">描述：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" name="description" id="description">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">备注：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" style="width:200px;height:20px" name="remark" id="remark">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">分配菜单：</font></span>
					</td>
					<td style="text-align: left;">
						<input id="menus" name="menus" style="width:200px;height:20px">
					</td>
				</tr>
				</br>
				<tr style=" text-align:center;">
					<td colspan="4">
						<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="addRole_Save();">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="addRole_Reset();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 编辑角色 -->
	<div id="modifyRoleDiv">
		<form id="modifyRoleForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">角色编码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" style="width:200px;height:20px" id="role_code_modify" name="role_code" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">角色名:</font></span>	
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" name="role_name" id="role_name_modify">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">描述：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" name="description" id="description_modify">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">备注：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" style="width:200px;height:20px" name="remark" id="remark_modify">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">分配菜单：</font></span>
					</td>
					<td style="text-align: left;">
						<input id="menus_modify" name="menus" style="width:200px;height:20px">
					</td>
				</tr>
				</br>
				<input type="hidden" name="id" id="id_modify">
				<tr style=" text-align:center;">
					<td colspan="4">
						<a id="modify_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="modifyRole_Save();">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="modify_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="modifyRole_Reset();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" src="${path}/resources/js/ui/system/role/SystemRoleIndex.js"></script>
</body>
</html>
