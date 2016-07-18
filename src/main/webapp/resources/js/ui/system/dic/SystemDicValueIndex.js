$(function() {
	var columns = [ [ {
		field : 'id',
		title : '主键',
		width : 300,
		align : 'center',
		sortable : true,
		hidden : true
	}, {
		field : 'key_code',
		title : '键的编码',
		width : 120,
		align : 'center'
	}, {
		field : 'value_code',
		title : '值的编码',
		width : 120,
		align : 'center'
	}, {
		field : 'value_name',
		title : '值的名字',
		width : 120,
		align : 'center'
	}, {
		field : 'sort',
		title : '值的排序',
		width : 100,
		align : 'center'
	}, {
		field : 'remark',
		title : '备注',
		width : 100,
		align : 'center'
	} ] ];

	$('#dataGridDicValue').datagrid({
		height : 340,
		url : webroot + 'system/dicvalue/list.html',
		method : 'POST',
		idField : 'id',
		loadMsg : '数据加载中请稍后……',
		striped : true,
		fitColumns : true,
		singleSelect : false,
		rownumbers : true,
		pagination : true,
		sortName : "id",
		sortOrder : "desc",
		nowrap : false,// 自动换行
		checkbox : true,
		pageSize : 10,
		pageList : [ 10, 20, 50, 100, 150, 200 ],
		showFooter : true,
		columns : columns,
		onBeforeLoad : function(param) {// 过滤查询功能的实现
			param.value_code = $("#value_code_search").val();
			param.value_name = $("#value_name_search").val();
			param.key_code = $("#key_code_idforValue").val();
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
				addDicValueFuc();
			}
		}, '-', {
			text : "删除",
			iconCls : 'icon-remove',
			handler : function() {
				deleteDicValueFuc();
			}
		}, '-', {
			text : "修改",
			iconCls : 'icon-edit',
			handler : function() {
				modifyDicValueFuc();
			}
		}, '-', {
			text : "清除所有选择",
			iconCls : 'icon-undo',
			handler : function() {
				cleanAllSelected();
			}
		} ]
	});

	/**
	 * 查询
	 */
	$('#search_id').bind('click', function() {
		$("#dataGridDicValue").datagrid("reload");
	});

	/**
	 * 重置
	 */
	$("#reset_id").bind("click", function() {
		$("#key_code_search").val("");
		$("#key_name_search").val("");
	});

	/**
	 * addDicValue
	 */
	$("#addDicValueDiv").dialog({
		title : '新增数据字典值',
		iconCls : "icon-add",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		top : 60,
		modal : true
	});
	$("#addDicValueDiv").dialog("close");

	/**
	 * modifyDicValue
	 */
	$("#modifyDicValueDiv").dialog({
		title : '修改数据字典值',
		iconCls : "icon-edit",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		top : 60,
		modal : true
	});
	$("#modifyDicValueDiv").dialog("close");
	$("#modifyDicValueDiv").hide();

});
// ===============================addDicValueFuc===================================
/**
 * 增加数据字典值
 */
function addDicValueFuc() {
	$("#addDicValueDiv").show();
	$("#addDicValueDiv").dialog("open");
	addTDicValue_Reset();
}

/**
 * 增加数据字典值-保存
 */
function addTDicValue_Save() {
	if ($("#addDicValueForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/dicvalue/addDicValue.html',
			dataType : "json",
			data : $("#addDicValueForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					$("#dataGridDicValue").datagrid("reload");// 重新加载数据
					$("#addDicValueDiv").dialog("close");
					$("#addDicValueDiv").hide();
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

/**
 * 增加数据字典值-重置
 */
function addTDicValue_Reset() {
	$("#value_code_dicvalue_id").val("");
	$("#value_name_dicvalue_id").val("");
	$("#sort_dicvalue_id").val("");
	$("#remark_dicvalue_id").val("");
}
// ===============================deleteDicValueFuc===================================
/**
 * deleteDicValueFuc
 */
function deleteDicValueFuc() {
	var rows = $('#dataGridDicValue').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择一条记录.');
	} else if (rows.length != 1) {
		$.messager.alert('警告', '一次只能操作一条记录.');
	} else {
		$.messager.confirm('提示', '您想要删除名字为【' + rows[0].value_name + '】记录吗？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : webroot + 'system/dicvalue/delDicValue.html',
					dataType : "json",
					data : {
						id : rows[0].id
					},
					success : function(result) {
						if (result.success) {
							$.messager.alert('提示', result.msg, 'info');
							$("#dataGridDicValue").datagrid("reload");// 重新加载数据
						} else {
							$.messager.alert('警告', result.msg);
						}
					}
				});
			}
		});
	}
}
// ===============================modifyDicValueFuc===================================
/**
 * 修改
 */
function modifyDicValueFuc() {
	var rows = $('#dataGridDicValue').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择一条记录.');
	} else if (rows.length != 1) {
		$.messager.alert('警告', '一次只能操作一条记录.');
	} else {
		$("#key_code_dicvalue_id_modify").val(rows[0].key_code);
		$("#value_code_dicvalue_id_modify").val(rows[0].value_code);
		$("#value_name_dicvalue_id_modify").val(rows[0].value_name);
		$("#sort_dicvalue_id_modify").val(rows[0].sort);
		$("#remark_dicvalue_id_modify").val(rows[0].remark);
		$("#id_dicvalue_modify").val(rows[0].id);
		$("#modifyDicValueDiv").show();
		$("#modifyDicValueDiv").dialog("open");
	}
}

/**
 * 修改-保存
 */
function modifyTDicValue_Save() {
	if ($("#modifyDicValueForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/dicvalue/modifyDicValue.html',
			dataType : "json",
			data : $("#modifyDicValueForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					$("#dataGridDicValue").datagrid("reload");// 重新加载数据
					$("#modifyDicValueDiv").dialog("close");
					$("#modifyDicValueDiv").hide();
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

// ======================================清除所有选择============================
/**
 * 清除所有选择
 */
function cleanAllSelected() {
	$("#dataGridDicValue").datagrid("uncheckAll");// 清除所有选中
}