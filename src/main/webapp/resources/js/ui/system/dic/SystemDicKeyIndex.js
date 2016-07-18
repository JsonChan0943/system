$(function() {
	var columns = [ [
			{
				field : 'id',
				title : '主键',
				width : 300,
				align : 'center',
				sortable : true
			},
			{
				field : 'key_code',
				title : '键的编码',
				width : 120,
				align : 'center'
			},
			{
				field : 'key_name',
				title : '键的名字',
				width : 120,
				align : 'center',
				sortable : true
			},
			{
				field : 'remark',
				title : '备注',
				width : 120,
				align : 'center'
			},
			{
				field : 'opt',
				title : '操作',
				width : 100,
				align : 'center',
				formatter : function(value, row, index) {
					var btn = '<a href="javascript:void(0)" class="editcls" onclick="dicValueManage(\'' + row.key_code
							+ '\');">值管理</a>';
					return btn;
				}
			} ] ];

	$('#dataGridDicKey').datagrid({
		fit : true,
		height : 400,
		url : webroot + 'system/dickey/list.html',
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
			 var key_code = $("#key_code_search").val();
			 var key_name = $("#key_name_search").val();
			 param.key_code = key_code;
			 param.key_name = key_name;
		},
		onLoadSuccess : function(data) {
			$('.editcls').linkbutton({
				plain : true,
				iconCls : 'icon-edit'
			});
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
		} ]
	});

	/**
	 * 查询
	 */
	$('#search_id').bind('click', function() {
		$("#dataGridDicKey").datagrid("reload");
	});

	/**
	 * 重置
	 */
	$("#reset_id").bind("click", function() {
		$("#key_code_search").val("");
		$("#key_name_search").val("");
	});

	/**
	 * addDicKey
	 */
	$("#addDicKeyDiv").dialog({
		title : '新增数据字典键',
		iconCls : "icon-add",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 200,
		modal : true
	});
	$("#addDicKeyDiv").dialog("close");

	/**
	 * modifyDicKey
	 */
	$("#modifyDicKeyDiv").dialog({
		title : '新增数据字典键',
		iconCls : "icon-add",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 200,
		modal : true
	});
	$("#modifyDicKeyDiv").dialog("close");
});

// ======================================增加数据字典键=================================
/**
 * 增加数据字典键
 */
function addFuc() {
	$("#addDicKeyDiv").show();
	$("#addDicKeyDiv").dialog("open");
}

/**
 * 增加数据字典键-保存
 */
function addTDicKey_Save() {
	if ($("#addDicKeyForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/dickey/addDicKey.html',
			dataType : "json",
			data : $("#addDicKeyForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					$("#dataGridDicKey").datagrid("reload");// 重新加载数据
					$("#addDicKeyDiv").dialog("close");
					$("#addDicKeyDiv").hide();
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

/**
 * 增加数据字典键-重置
 */
function addTDicKey_Reset() {
	$("#key_name_id").val("");
	$("#key_code_id").val("");
	$("#remark_id").val("");
}

// ========================================编辑数据字典键===========================================
/**
 * 编辑数据字典键
 */
function modifyFuc() {
	var rows = $('#dataGridDicKey').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择一条记录.');
	} else if (rows.length != 1) {
		$.messager.alert('警告', '一次只能操作一条记录.');
	} else {
		addTDicKey_Reset();
		$("#id_modify").val("");
		$("#key_name_id_modify").val(rows[0].key_name);
		$("#key_code_id_modify").val(rows[0].key_code);
		$("#remark_id_modify").val(rows[0].remark);
		$("#id_modify").val(rows[0].id);
		$("#modifyDicKeyDiv").show();
		$("#modifyDicKeyDiv").dialog("open");
	}
}

/**
 * 编辑数据字典键-保存
 */
function modifyTDicKey_Save() {
	if ($("#modifyDicKeyForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/dickey/modifyDicKey.html',
			dataType : "json",
			data : $("#modifyDicKeyForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					$("#dataGridDicKey").datagrid("reload");// 重新加载数据
					$("#modifyDicKeyDiv").dialog("close");
					$("#modifyDicKeyDiv").hide();
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

/**
 * 编辑数据字典键-重置
 */
function modifyTDicKey_Reset() {
	$("#key_name_id_modify").val("");
	$("#key_code_id_modify").val("");
	$("#remark_id_modify").val("");
}

// ==================================删除数据字典键====================================
/**
 * 删除数据字典键
 */
function deleteFuc() {
	var rows = $('#dataGridDicKey').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择一条记录.');
	} else if (rows.length != 1) {
		$.messager.alert('警告', '一次只能操作一条记录.');
	} else {
		$.messager.confirm('提示', '您想要删除名字为【' + rows[0].key_name + '】记录吗？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : webroot + 'system/dickey/delDicKey.html',
					dataType : "json",
					data : {
						id : rows[0].id
					},
					success : function(result) {
						if (result.success) {
							$.messager.alert('提示', result.msg, 'info');
							$("#dataGridDicKey").datagrid("reload");// 重新加载数据
						} else {
							$.messager.alert('警告', result.msg);
						}
					}
				});
			}
		});
	}
}
// ===========================================================================
function dicValueManage(key_code) {
	$("#dicValueManage").dialog({
		href : webroot + 'system/dicvalue/index.html',
		title : "数据字典值管理",
		width : 1000,
		height : 450,
		modal : true,
		method : "POST",
		loadingMessage : "页面加载中....",
		queryParams : {
			key_code : key_code
		}
	});
}
// ======================================清除所有选择============================
function cleanAllSelected() {
	$("#dataGridDicKey").datagrid("uncheckAll");// 清除所有选中
}