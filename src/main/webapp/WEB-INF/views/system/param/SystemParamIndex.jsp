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
		参数编码：<input class="easyui-textbox" style="width:150px;height:20px" name="param_code" id="param_code">
		参数名：<input class="easyui-textbox" style="width:150px;height:20px" name="param_name" id="param_name">
		<a id="search_id" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		<a id="reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'">重置</a>
	</div>
	</br>
	<!-- 数据列表区域 -->
	<div id="dataGridDiv" style="height:400px;">
		<table id="dataGrid">
			
		</table>
	</div>
	
	<!-- 增加系统参数 -->
	<div id="addParamDiv" style="display:none">
		</br>
		<form id="addParamForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">参数编码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="easyui-validatebox textbox" style="width:200px;height:20px" data-options="required:true" id="param_code_add" name="param_code" onblur="canParamCodeUse();">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">参数名：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="param_name_add" name="param_name" >
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">参数值：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="param_value_add" name="param_value">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">备注：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="remark_add" name="remark">
					</td>
				</tr>
				
				</br>
				<tr style=" text-align:center;">
					<td colspan="4">
						<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="addParam_Save();">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="addParam_Reset();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 修改系统参数 -->
	<div id="modifyParamDiv" style="display:none">
		</br>
		<form id="modifyParamForm">
			<table>
				<tr>
					<td style="width:150px;text-align: right">
						<span><font size="18px">参数编码:</font></span>
					</td>
					<td style="width:250px;text-align: left;">
						<input class="textbox" style="width:200px;height:20px" id="param_code_modify" name="param_code" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">参数名：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="param_name_modify" name="param_name" >
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">参数值：</font></span>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="param_value_modify" name="param_value">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						<span><font size="18px">备注：</font></span>
						<input type="hidden" name="id" id="id_id"/>
					</td>
					<td style="text-align: left;">
						<input class="easyui-validatebox textbox" data-options="required:true" style="width:200px;height:20px" id="remark_modify" name="remark">
					</td>
				</tr>
				</br>
				<tr style=" text-align:center;">
					<td colspan="4">
						<a id="add_save_id" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="modifyParam_Save();">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="add_reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" onclick="modifyParam_Reset();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" src="${path}/resources/js/ui/system/param/SystemParamIndex.js"></script>
</body>
</html>
