$(function() {
	var columns = [ [ {
		field : 'id',
		title : '主键',
		hidden : true
	}, {
		field : 'param_code',
		title : '参数编码',
		width : 100,
		align : 'center',
		sortable : true
	}, {
		field : 'param_name',
		title : '参数名',
		width : 100,
		align : 'center'
	}, {
		field : 'param_value',
		title : '参数值',
		width : 100,
		align : 'center'
	}, {
		field : 'create_time',
		title : '创建时间',
		width : 120,
		align : 'center',
		sortable : true
	}, {
		field : 'modify_time',
		title : '修改时间',
		width : 120,
		align : 'center',
		sortable : true
	}, {
		field : 'remark',
		title : '备注',
		width : 120,
		align : 'center'
	} ] ];

	$('#dataGrid').datagrid({
		fit : true,
		height : 400,
		url : webroot + 'system/param/list.html',
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
			var param_code = $("#param_code").val();
			var param_name = $("#param_name").val();
			param.param_code = param_code;
			param.param_name = param_name;
		},
		onLoadSuccess : function(data) {

		},
		onLoadError : function() {

		},
		onClickCell : function(rowIndex, field, value) {

		},
		toolbar : [ {
			text : "增加",
			iconCls : 'icon-add',
			handler : function() {
				addFuc();
			}
		}, '-', {
			text : "删除",
			iconCls : 'icon-remove',
			handler : function() {
				deleteFuc();
			}
		}, '-', {
			text : "修改",
			iconCls : 'icon-edit',
			handler : function() {
				modifyFuc();
			}
		}, '-', {
			text : "清除所有选择",
			iconCls : 'icon-undo',
			handler : function() {
				cleanAllSelected();
			}
		}, '-', {
			text : "导出用户列表",
			iconCls : 'icon-more',
			handler : function() {
				exportParamList();
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
		$("#param_code").val("");
		$("#param_name").val("");
	});

	/**
	 * addParam
	 */
	$("#addParamDiv").dialog({
		title : '新增系统参数',
		iconCls : "icon-add",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		top : 30,
		modal : true
	});
	$("#addParamDiv").dialog("close");

	/**
	 * modifyParam
	 */
	$("#modifyParamDiv").dialog({
		title : '修改系统参数',
		iconCls : "icon-add",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		top : 30,
		modal : true
	});
	$("#modifyParamDiv").dialog("close");
});

// ======================================新增用户信息==========================
var canParamCodeUseVar = false;
/**
 * 增加
 */
function addFuc() {
	$("#addParamDiv").show();
	$("#addParamDiv").dialog("open");
	addParam_Reset();
}

/**
 * 判断参数编码是否可用
 */
function canParamCodeUse() {
	$.ajax({
		type : "post",
		url : webroot + 'system/param/canParamCodeUse.html',
		dataType : "json",
		data : {
			param_code : $("#param_code_add").val()
		},
		success : function(result) {
			if (result.success) {
				canParamCodeUseVar = true;
			} else {
				canParamCodeUseVar = false;
				$.messager.alert('警告', result.msg);
			}
		}
	});
}

/**
 * 增加系统参数-保存
 */
function addParam_Save() {
	if (!canParamCodeUseVar) {
		$.messager.alert('警告', "参数编码不可用.");
		return;
	}
	if ($("#addParamForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/param/addParam.html',
			dataType : "json",
			data : $("#addParamForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					$("#dataGrid").datagrid("reload");// 重新加载数据
					$("#addParamDiv").dialog("close");
					$("#addParamDiv").hide();
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

/**
 * 增加用户-重置
 */
function addParam_Reset() {
	$("#param_code_add").val("");
	$("#param_name_add").val("");
	$("#param_value_add").val("");
	$("#remark_add").val("");
}

// ===============================修改学生信息============================
/**
 * 修改
 */
function modifyFuc() {
	var rows = $('#dataGrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择一条记录.');
	} else if (rows.length != 1) {
		$.messager.alert('警告', '一次只能操作一条记录.');
	} else {
		var modifyItem = rows[0];
		$("#id_id").val(modifyItem.id);
		$("#param_code_modify").val(modifyItem.param_code);
		$("#param_name_modify").val(modifyItem.param_name);
		$("#param_value_modify").val(modifyItem.param_value);
		$("#remark_modify").val(modifyItem.remark);
		$("#modifyParamDiv").show();
		$("#modifyParamDiv").dialog("open");
	}
}

/**
 * 修改用户信息-保存
 */
function modifyParam_Save() {
	if ($("#modifyParamForm").form("validate")) {
		$.ajax({
			type : "post",
			url : webroot + 'system/param/modifyParam.html',
			dataType : "json",
			data : $("#modifyParamForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					$("#dataGrid").datagrid("reload");// 重新加载数据
					$("#modifyParamDiv").dialog("close");
					$("#modifyParamDiv").hide();
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

/**
 * 修改用户信息-重置
 */
function modifyParam_Reset() {
	$("#param_name_modify").val("");
	$("#param_value_modify").val("");
	$("#remark_modify").val("");
}

// ====================================删除================================
/**
 * 删除
 */
function deleteFuc() {
	var rows = $('#dataGrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择一条记录.');
	} else if (rows.length != 1) {
		$.messager.alert('警告', '一次只能操作一条记录.');
	} else {
		var param_name = rows[0].param_name;
		$.messager.confirm('确认', "确认要删除名字为【" + param_name + "】的系统参数？", function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : webroot + 'system/param/delParam.html',
					dataType : "json",
					data : {
						id : rows[0].id
					},
					success : function(result) {
						if (result.success) {
							$.messager.alert('提示', result.msg, 'info');
							$("#dataGrid").datagrid("reload");// 重新加载数据
							$("#dataGrid").datagrid("uncheckAll");// 清除所有选中
						} else {
							$.messager.alert('警告', result.msg);
						}
					}
				});
			}
		});
	}
}

// ======================================清除所有选择============================
function cleanAllSelected() {
	$("#dataGrid").datagrid("uncheckAll");// 清除所有选中
}

// =====================================exportUserList=====================================

/**
 * 导出用户列表
 */
function exportParamList() {
	var param_code = $("#param_code").val();
	var param_name = $("#param_name").val();
	$.ajax({
		type : 'post',
		url : webroot + 'system/param/exportParamList.html',
		dataType : "json",
		data : {
			param_code : param_code,
			param_name : param_name
		},
		success : function(fileName) {
			var url = webroot + "system/param/downLoadFile.html?fileName=" + fileName;
			window.location.href = url;
		}
	});
}
