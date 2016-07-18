<!DOCTYPE html>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<html>
<head>
	<title>菜单管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>   
	<div id="index_layout">
		<div data-options="region:'west',title:'系统菜单'" style="width:400px;">
			<div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="search_id" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addMenu();">新增菜单</a>&nbsp;
			<a id="reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="editMenu();">编辑菜单</a>&nbsp;
			<a id="reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="delMenu();">删除菜单</a>
			</div>
			<hr>
			<div>
				<ul id="menutree"></ul>
			</div>
		</div>
		<div data-options="region:'center',title:'菜单详情'">
			<div>
				<table style="width:500px;" border="1px;">
					<tr>
						<td style="text-align: right">菜单名字：</td>
						<td style="text-align: left;">
							<input type="text" id="menu_name_show" style="width:200px;height:20px" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">菜单编码：</td>
						<td style="text-align: left;">
							<input type="text" id="menu_code_show" style="width:200px;height:20px" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">菜单url:</td>
						<td style="text-align: left;">
							<input type="text" id="menu_url_show" style="width:200px;height:20px" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">描述：</td>
						<td style="text-align: left;">
							<input type="text" id="description_show" style="width:200px;height:20px" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">备注：</td>
						<td style="text-align: left;">
							<input type="text" id="remark_show" style="width:200px;height:20px" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">菜单等级：</td>
						<td style="text-align: left;">
							<input type="text" id="menu_level_show" style="width:200px;height:20px" readonly="readonly">
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 增加菜单 -->
		<div id="addMenuDiv">
			</br>
			<form id="addMenuForm">
				<table>
					<tr>
						<td style="width:150px;text-align: right">
							<span><font size="18px">父菜单名字:</font></span>
						</td>
						<td style="width:250px;text-align: left;">
							<input class="textbox" style="width:200px;height:20px" id="parent_menu_name" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">
							<span><font size="18px">菜单编码:</font></span>	
						</td>
						<td style="text-align: left;">
							<input class="easyui-validatebox textbox" data-options="prompt:'请输入姓名.',required:true" style="width:200px;height:20px" name="menu_code" id="menu_code">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">
							<span><font size="18px">菜单名字：</font></span>
						</td>
						<td style="text-align: left;">
							<input class="easyui-validatebox textbox" data-options="prompt:'请输入性别.',required:true" style="width:200px;height:20px" name="menu_name" id="menu_name">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">
							<span><font size="18px">菜单url：</font></span>
						</td>
						<td style="text-align: left;">
							<input class="easyui-validatebox textbox" style="width:200px;height:20px" name="menu_url" id="menu_url">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">
							<span><font size="18px">菜单描述：</font></span>
						</td>
						<td style="text-align: left;">
							<input class="easyui-validatebox textbox" data-options="prompt:'请输入邮箱.',required:true" style="width:200px;height:20px" name="description" id="description">
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
						<input type="hidden" id="pid" name="pid"><!-- 父节点id -->
						<input type="hidden" id="parent_menu_level" name="parent_menu_level"><!-- 父节点菜单级别 -->
					</tr>
					</br>
					<tr style=" text-align:center;">
						<td colspan="4">
							<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="addMenu_Save();">保存</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="addMenu_Reset();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<!-- 编辑菜单 -->
		<div id="modifyMenuDiv">
			</br>
			<form id="modifyMenuForm">
				<table>
					<tr>
						<td style="width:150px;text-align: right">
							<span><font size="18px">菜单编码:</font></span>
						</td>
						<td style="width:250px;text-align: left;">
							<input class="easyui-validatebox textbox" data-options="prompt:'请输入姓名.',required:true" style="width:200px;height:20px" name="menu_code" id="menu_code_modify">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">
							<span><font size="18px">菜单名字:</font></span>
						</td>
						<td style="text-align: left;">
							<input class="easyui-validatebox textbox" data-options="prompt:'请输入性别.',required:true" style="width:200px;height:20px" name="menu_name" id="menu_name_modify">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">
							<span><font size="18px">菜单url：</font></span>
						</td>
						<td style="text-align: left;">
							<input class="easyui-validatebox textbox" style="width:200px;height:20px" name="menu_url" id="menu_url_modify">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">
							<span><font size="18px">菜单描述：</font></span>
						</td>
						<td style="text-align: left;">
							<input class="easyui-validatebox textbox" data-options="prompt:'请输入邮箱.',required:true" style="width:200px;height:20px" name="description" id="description_modify">
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
						<input type="hidden" id="id_modify" name="id"><!-- 菜单id -->
					</tr>
					</br>
					<tr style=" text-align:center;">
						<td colspan="4">
							<a id="modify_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="modifyMenu_Save();">保存</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a id="modify_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="modifyMenu_Reset();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
<script type="text/javascript" src="${path}/resources/js/ui/system/menu/SystemMenuIndex.js"></script>
</body>
</html>
