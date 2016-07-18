<!DOCTYPE html>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<html>
<head>
	<title>机构管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>   
	<div id="index_layout">
		<div data-options="region:'west',title:'系统菜单'" style="width:400px;">
			<div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="search_id" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addOrgan();">新增机构</a>&nbsp;
			<a id="reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="editOrgan();">编辑机构</a>&nbsp;
			<a id="reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="delOrgan();">删除机构</a>
			</div>
			<hr>
			<div>
				<ul id="menutree"></ul>
			</div>
		</div>
		<div data-options="region:'center',title:'机构详情'">
			<!-- 查看机构详情 -->
			<div>
				<table style="width:500px;" border="1px;">
					<tr>
						<td style="text-align: right">机构编码：</td>
						<td style="text-align: left;">
							<input type="text" id="organ_code_show" style="width:200px;height:20px" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">机构名称：</td>
						<td style="text-align: left;">
							<input type="text" id="organ_name_show" style="width:200px;height:20px" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">排序位置:</td>
						<td style="text-align: left;">
							<input type="text" id="sort_show" style="width:200px;height:20px" readonly="readonly">
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
						<td style="text-align: right">机构等级：</td>
						<td style="text-align: left;">
							<input type="text" id="organ_level_show" style="width:200px;height:20px" readonly="readonly">
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<!-- 编辑机构 -->
		<div id="modifyOrganDiv">
			</br>
			<form id="modifyOrganForm">
				<table>
					<tr>
						<td style="width:150px;text-align: right">机构编码：</td>
						<td style="width:250px;text-align: left;">
							<input type="text" class="easyui-validatebox textbox" data-options="required:true" id="organ_code_modify" name="organ_code" style="width:200px;height:20px">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">机构名称：</td>
						<td style="text-align: left;">
							<input type="text" class="easyui-validatebox textbox" data-options="required:true" id="organ_name_modify" name="organ_name" style="width:200px;height:20px">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">排序位置:</td>
						<td style="text-align: left;">
							<input type="text" class="easyui-validatebox textbox" data-options="required:true" id="sort_modify" name="sort" style="width:200px;height:20px" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">描述：</td>
						<td style="text-align: left;">
							<input type="text" class="easyui-validatebox textbox" data-options="required:true" id="description_modify" name="description" style="width:200px;height:20px">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">备注：</td>
						<td style="text-align: left;">
							<input type="text" class="easyui-validatebox textbox" data-options="required:true" id="remark_modify" name="remark" style="width:200px;height:20px">
						</td>
					</tr>
					<tr>
						<input type="hidden" id="id_modify" name="id"><!-- 菜单id -->
					</tr>
					</br>
					<tr style=" text-align:center;">
						<td colspan="4">
							<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="modifyOrgan_Save();">保存</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="modifyOrgan_Reset();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<!-- 增加菜单 -->
		<div id="addOrganDiv">
			</br>
			<form id="addOrganForm">
				<table>
					<tr>
						<td style="width:150px;text-align: right">
							<span><font size="18px">父机构名字:</font></span>
						</td>
						<td style="width:250px;text-align: left;">
							<input class="textbox" style="width:200px;height:20px" id="parent_organ_name" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">机构编码：</td>
						<td style="text-align: left;">
							<input type="text" class="easyui-validatebox textbox" data-options="required:true" id="organ_code_show" name="organ_code" style="width:200px;height:20px">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">机构名称：</td>
						<td style="text-align: left;">
							<input type="text" class="easyui-validatebox textbox" data-options="required:true" id="organ_name_show" name="organ_name" style="width:200px;height:20px">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">排序位置:</td>
						<td style="text-align: left;">
							<input type="text" class="easyui-validatebox textbox" data-options="required:true" id="sort_show" name="sort" style="width:200px;height:20px">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">描述：</td>
						<td style="text-align: left;">
							<input type="text" class="easyui-validatebox textbox" data-options="required:true" id="description_show" name="description" style="width:200px;height:20px">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">备注：</td>
						<td style="text-align: left;">
							<input type="text" class="easyui-validatebox textbox" data-options="required:true" id="remark_show" name="remark" style="width:200px;height:20px">
						</td>
					</tr>
					<tr>
						<input type="hidden" id="pid" name="pid"><!-- 父节点id -->
						<input type="hidden" id="parent_organ_level" name="parent_organ_level"><!-- 父节点菜单级别 -->
					</tr>
					</br>
					<tr style=" text-align:center;">
						<td colspan="4">
							<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="addOrgan_Save();">保存</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="addOrgan_Reset();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
<script type="text/javascript" src="${path}/resources/js/ui/system/organ/SystemOrganIndex.js"></script>
</body>
</html>
