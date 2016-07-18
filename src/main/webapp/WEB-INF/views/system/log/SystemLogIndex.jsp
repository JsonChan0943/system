<!DOCTYPE html>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<html>
<head>
	<title>系统日志管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>   
	<!-- 搜索框 -->
	<div>
		主题：<input class="easyui-textbox" style="width:150px;height:20px" name="subject" id="subject_id">
		操作人：<input class="easyui-textbox" style="width:150px;height:20px" name="operator" id="operator_id">
		<a id="search_id" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		<a id="reset_id" class="easyui-linkbutton" data-options="iconCls:'icon-cut'">重置</a>
	</div>
	</br>
	
	<!-- 数据列表区域 -->
	<div id="dataGridDiv" style="height:400px;">
		<table id="dataGrid">
			
		</table>
	</div>
<script type="text/javascript" src="${path}/resources/js/ui/system/log/SystemLogIndex.js"></script>
</body>
</html>
