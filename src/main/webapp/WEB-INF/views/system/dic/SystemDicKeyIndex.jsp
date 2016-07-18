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
	<!-- 搜索框 -->
	<div>
		键的编码：<input class="easyui-textbox" style="width:150px;height:20px" name="key_code" id="key_code_search">
		键的名字：<input class="easyui-textbox" style="width:150px;height:20px" name="key_name" id="key_name_search">
		<a id="search_id" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		<a id="reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'">重置</a>
	</div>
	</br>
	
	<!-- 数据列表区域 -->
	<div id="dataGridDicKeyDiv" style="height:400px;">
		<table id="dataGridDicKey">
			
		</table>
	</div>
	
	<!-- 增加字典键 -->
	<div id="addDicKeyDiv" style="display:none">
		</br>
		<form id="addDicKeyForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">键名字:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="key_name_id" name="key_name">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">键编码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="key_code_id" name="key_code">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">备注:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="remark_id" name="remark">
					</td>
				</tr>
				<tr style=" text-align:center;">
					<td colspan="4">
						<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="addTDicKey_Save();">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="addTDicKey_Reset();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
		
	<!-- 修改字典键 -->
	<div id="modifyDicKeyDiv" style="display:none">
		</br>
		<form id="modifyDicKeyForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">键名字:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="key_name_id_modify" name="key_name">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">键编码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="key_code_id_modify" name="key_code">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">备注:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="remark_id_modify" name="remark">
					</td>
				</tr>
				<input type="hidden" id="id_modify" name="remark">
				<tr style=" text-align:center;">
					<td colspan="4">
						<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="modifyTDicKey_Save();">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="modifyTDicKey_Reset();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 字典值管理 -->
	<div id="dicValueManage">
		
	</div>
<script type="text/javascript" src="${path}/resources/js/ui/system/dic/SystemDicKeyIndex.js"></script>
</body>
</html>
