$(function() {
	var columns = [ [ {
		field : 'id',
		title : '主键',
		hidden : true
	}, {
		field : 'subject',
		title : '主题',
		width : 100,
		align : 'center',
		sortable : true
	}, {
		field : 'content',
		title : '内容',
		width : 400,
		align : 'center'
	}, {
		field : 'operator',
		title : '操作人',
		width : 100,
		align : 'center',
		sortable : true
	}, {
		field : 'create_time',
		title : '记录时间',
		width : 150,
		align : 'center',
		sortable : true
	} ] ];

	$('#dataGrid').datagrid({
		fit : true,
		height : 340,
		url : webroot + 'system/log/list.html',
		method : 'POST',
		idField : 'id',
		loadMsg : '数据加载中请稍后……',
		striped : true,
		fitColumns : true,
		singleSelect : false,
		rownumbers : true,
		pagination : true,
		sortName : "create_time",
		sortOrder : "desc",
		nowrap : false,// 自动换行
		checkbox : true,
		pageSize : 10,
		pageList : [ 10, 20, 50, 100, 150, 200 ],
		showFooter : true,
		columns : columns,
		onBeforeLoad : function(param) {// 过滤查询功能的实现
			var subject = $("#subject").val();
			var operator = $("#operator").val();
			param.subject = subject;
			param.operator = operator;
		},
		onLoadSuccess : function(data) {

		},
		onLoadError : function() {

		},
		onClickCell : function(rowIndex, field, value) {

		},
		toolbar : [ {
			text : "导出日志列表",
			iconCls : 'icon-more',
			handler : function() {
				exportLogList();
			}
		} ]
	});

	/**
	 * 查询
	 */
	$('#search_id').bind('click', function() {
		$("#dataGrid").datagrid("reload");
	});

	/**
	 * 重置
	 */
	$("#reset_id").bind("click", function() {
		$("#subject_id").val("");
		$("#operator_id").val("");
	});
});

// =====================================exportUserList=====================================

/**
 * 导出日志列表
 */
function exportLogList() {
	var subject = $("#subject").val();
	var operator = $("#operator").val();
	$.ajax({
		type : 'post',
		url : webroot + 'system/log/exportLogList.html',
		dataType : "json",
		data : {
			subject : subject,
			operator : operator
		},
		success : function(fileName) {
			var url = webroot + "system/log/downLoadFile.html?fileName=" + fileName;
			window.location.href = url;
		}
	});
}
