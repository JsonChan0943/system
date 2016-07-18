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
		值的编码：<input class="easyui-textbox" style="width:150px;height:20px" name="value_code" id="value_code_search">
		值的名字：<input class="easyui-textbox" style="width:150px;height:20px" name="value_name" id="value_name_search">
		<a id="search_id" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		<a id="reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'">重置</a>
	</div>
	</br>
	<!-- 数据列表区域 -->
	<div id="dataGridDicValueDiv">
		<table id="dataGridDicValue">
			
		</table>
	</div>
	<input type="hidden" id="key_code_idforValue" value="${requestScope.key_code}">
	
	<!-- 增加字典值 -->
	<div id="addDicValueDiv" style="display:none">
		</br>
		<form id="addDicValueForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">键的编码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" value="${requestScope.key_code}" readonly="readonly" id="key_code_dicvalue_id" name="key_code">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">值的编码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="value_code_dicvalue_id" name="value_code">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">值的名字:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="value_name_dicvalue_id" name="value_name">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">排序:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="sort_dicvalue_id" name="sort">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">备注:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="remark_dicvalue_id" name="remark">
					</td>
				</tr>
				<tr style=" text-align:center;">
					<td colspan="4">
						<input type="button" value="保存" onclick="addTDicValue_Save();">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="重置" onclick="addTDicValue_Reset();">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 修改字典值 -->
	<div id="modifyDicValueDiv" style="display:none">
		</br>
		<form id="modifyDicValueForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">键的编码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" value="${requestScope.key_code}" readonly="readonly" id="key_code_dicvalue_id_modify" name="key_code">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">值的编码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="value_code_dicvalue_id_modify" name="value_code">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">值的名字:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="value_name_dicvalue_id_modify" name="value_name">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">排序:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="sort_dicvalue_id_modify" name="sort">
					</td>
				</tr>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">备注:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="remark_dicvalue_id_modify" name="remark">
					</td>
				</tr>
				<input type="hidden" id="id_dicvalue_modify" name="id">
				<tr style=" text-align:center;">
					<td colspan="4">
						<input type="button" value="保存" onclick="modifyTDicValue_Save();">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="重置" onclick="modifyTDicValue_Reset();">
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" src="${path}/resources/js/ui/system/dic/SystemDicValueIndex.js"></script>
</body>
</html>
