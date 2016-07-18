$(function() {
	var columns = [ [ {
		field : 'id',
		title : '主键',
		width : 300,
		align : 'center',
		sortable : true
	}, {
		field : 'role_code',
		title : '角色编码',
		width : 120,
		align : 'center',
		sortable : true
	}, {
		field : 'role_name',
		title : '角色名',
		width : 120,
		align : 'center'
	}, {
		field : 'description',
		title : '描述',
		width : 120,
		align : 'center'
	}, {
		field : 'remark',
		title : '备注',
		width : 120,
		align : 'center'
	} ] ];

	$('#dataGrid').datagrid({
		fit : true,
		height : 340,
		url : webroot + 'system/role/list.html',
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
			var role_code = $("#role_code").val();
			var role_name = $("#role_name").val();
			param.role_code = role_code;
			param.role_name = role_name;
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
		$("#role_code").val("");
		$("#role_name").val("");
	});

	/**
	 * addRole
	 */
	$("#addRoleDiv").dialog({
		title : '新增角色',
		iconCls : "icon-add",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		modal : true
	});
	$("#addRoleDiv").dialog("close");

	/**
	 * modifyRole
	 */
	$("#modifyRoleDiv").dialog({
		title : '编辑角色',
		iconCls : "icon-edit",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		modal : true
	});
	$("#modifyRoleDiv").dialog("close");
});

// ======================================新增角色=================================
/**
 * 增加角色
 */
function addFuc() {
	/**
	 * 树形下单单
	 */
	$('#menus').combotree({
		url : webroot + "system/role/addRoleGetAllMenu.html",
		multiple : true,
		separator : "|",
		checkbox : true,
		lines : true
	});
	$("#addRoleDiv").dialog("open");
}

/**
 * 新增角色-保存
 */
function addRole_Save() {
	if ($("#addRoleForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/role/addRole.html',
			dataType : "json",
			data : $("#addRoleForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					$("#dataGrid").datagrid("reload");// 重新加载数据
					$("#addRoleDiv").dialog("close");
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

/**
 * 增加角色-重置
 */
function addRole_Reset() {

}

// ========================================编辑菜单===========================================
/**
 * 编辑菜单
 */
function modifyFuc() {
	var rows = $('#dataGrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择一条记录.');
	} else if (rows.length != 1) {
		$.messager.alert('警告', '一次只能操作一条记录.');
	} else {
		$("#id_modify").val(rows[0].id);
		$("#role_code_modify").val(rows[0].role_code);
		$("#role_name_modify").val(rows[0].role_name);
		$("#description_modify").val(rows[0].description);
		$("#remark_modify").val(rows[0].remark);
		/**
		 * 树形下单单
		 */
		$('#menus_modify').combotree({
			url : webroot + "system/role/modifyRoleGetAllMenu.html",
			multiple : true,
			separator : "|",
			checkbox : true,
			lines : true,
			onBeforeLoad : function(node, param) {// 过滤查询功能的实现
				param.roleId = rows[0].id;
			},
		});
		$("#modifyRoleDiv").dialog("open");
	}
}

/**
 * 修改角色
 */
function modifyRole_Save() {
	if ($("#modifyRoleForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/role/modifyRole.html',
			dataType : "json",
			data : $("#modifyRoleForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					$("#dataGrid").datagrid("reload");// 重新加载数据
					$("#modifyRoleDiv").dialog("close");
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}
// ==================================删除角色====================================
/**
 * 删除角色
 */
function deleteFuc() {
	var rows = $('#dataGrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择一条记录.');
	} else if (rows.length != 1) {
		$.messager.alert('警告', '一次只能操作一条记录.');
	} else {
		$.messager.confirm('提示', '您想要删除【' + rows[0].role_name + '】角色吗？', function(r) {
			if (r) {
				var roleId = rows[0].id;
				$.ajax({
					type : "post",
					url : webroot + 'system/role/delRole.html',
					dataType : "json",
					data : {
						roleId : roleId
					},
					success : function(result) {
						if (result.success) {
							$.messager.alert('提示', result.msg, 'info');
							$("#dataGrid").datagrid("reload");// 重新加载数据
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