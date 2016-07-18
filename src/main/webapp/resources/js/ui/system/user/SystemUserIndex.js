$(function() {
	var columns = [ [
			{
				field : 'id',
				title : '主键',
				hidden : true
			},
			{
				field : 'user_login_name',
				title : '登陆用户名',
				width : 100,
				align : 'center',
				sortable : true
			},
			{
				field : 'user_name',
				title : '用户名',
				width : 100,
				align : 'center'
			},
			{
				field : 'stateDesc',
				title : '状态',
				width : 60,
				align : 'center'
			},
			{
				field : 'organ_name',
				title : '所属机构',
				width : 100,
				align : 'center'
			},
			{
				field : 'create_time',
				title : '创建时间',
				width : 120,
				align : 'center',
				sortable : true
			},
			{
				field : 'last_login_time',
				title : '最近一次登陆时间',
				width : 120,
				align : 'center'
			},
			{
				field : 'last_pwd_modify_time',
				title : '最近一次密码修改时间',
				width : 120,
				align : 'center'
			},
			{
				field : 'opt',
				title : '操作',
				width : 100,
				align : 'center',
				formatter : function(value, row, index) {
					if (row.state == 2) {
						var btn = '<a href="javascript:void(0)" class="editcls" onclick="unlockUser(\'' + row.id
								+ '\',\'' + row.user_name + '\');">解锁</a>';
					} else if (row.state == 1) {
						var btn = '<a href="javascript:void(0)" class="editcls" onclick="stopUser(\'' + row.id
								+ '\',\'' + row.user_name + '\');">停用</a>';
					} else if (row.state == 4) {
						var btn = '<a href="javascript:void(0)" class="editcls" onclick="startUser(\'' + row.id
								+ '\',\'' + row.user_name + '\');">启用</a>';
					}
					return btn;
				}
			} ] ];

	$('#dataGrid').datagrid({
		fit : true,
		height : 340,
		url : webroot + 'system/user/list.html',
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
			var user_login_name = $("#user_login_name").val();
			var user_name = $("#user_name").val();
			param.user_login_name = user_login_name;
			param.user_name = user_name;
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
			text : "注销",
			iconCls : 'icon-back',
			handler : function() {
				cancleUser();
			}
		}, '-', {
			text : "重置登陆密码",
			iconCls : 'icon-cut',
			handler : function() {
				resetLoginPwd();
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
				exportUserList();
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
		$("#user_login_name").val("");
		$("#user_name").val("");
	});

	/**
	 * addUser
	 */
	$("#addUserDiv").dialog({
		title : '新增用户',
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
	$("#addUserDiv").dialog("close");

	/**
	 * modifyUser
	 */
	$("#modifyUserDiv").dialog({
		title : '编辑用户',
		iconCls : "icon-edit",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		top : 30,
		modal : true
	});
	$("#modifyUserDiv").dialog("close");
});

// ======================================新增用户信息==========================
/**
 * 增加
 */
function addFuc() {
	/**
	 * 树形下拉框-角色信息
	 */
	$('#roles').combotree({
		url : webroot + 'system/user/addUserGetRoles.html',
		multiple : true,
		separator : "|",
		checkbox : true,
		lines : true
	});
	/**
	 * 树形下拉框-机构信息
	 */
	$('#organ_code_id').combotree({
		url : webroot + 'system/user/addUserGetOrgans.html',
		separator : "|",
		lines : true
	});
	$("#addUserDiv").show();
	$("#addUserDiv").dialog("open");
}

/**
 * 增加用户-保存
 */
function addUser_Save() {
	if ($("#addUserForm").form('validate')) {
		var organ_name = $('#organ_code_id').combotree('getText');
		$("#organ_name_id").val(organ_name);
		$.ajax({
			type : "post",
			url : webroot + 'system/user/addUser.html',
			dataType : "json",
			data : $("#addUserForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					$("#dataGrid").datagrid("reload");// 重新加载数据
					$("#addUserDiv").dialog("close");
					$("#addUserDiv").hide();
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
function addUser_Reset() {
	$("#user_login_name").val("");
	$("#user_name").val("");
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
		$("#user_login_name_modify").val(modifyItem.user_login_name);
		$("#user_name_modify").val(modifyItem.user_name);
		$("#id_modify").val(modifyItem.id);
		/**
		 * 树形下拉选
		 */
		$('#roles_modify').combotree({
			url : webroot + "system/user/modifyUserGetRoles.html",
			multiple : true,
			separator : "|",
			checkbox : true,
			lines : true,
			onBeforeLoad : function(node, param) {// 过滤查询功能的实现
				param.userId = rows[0].id;
			},
		});
		/**
		 * 树形下拉框-机构信息
		 */
		$('#organ_code_id_modify').combotree({
			url : webroot + 'system/user/modifyUserGetOrgans.html',
			separator : "|",
			lines : true,
			onLoadSuccess : function(data) {
				var organ_code = modifyItem.organ_code;
				var tree = $("#organ_code_id_modify").combotree('tree');
				var root = tree.tree("getRoot");
				if (root.attributes.organ_code == organ_code) {
					tree.tree("select", root.target);
				} else {
					var children = tree.tree("getChildren", root.target);
					for ( var i = 0; i < children.length; i++) {
						if (children[i].attributes.organ_code == organ_code) {
							$("#organ_code_id_modify").combotree("select", children[i].target);
							break;
						}
					}
				}
			}
		});
		$("#modifyUserDiv").show();
		$("#modifyUserDiv").dialog("open");
	}
}

/**
 * 修改用户信息-保存
 */
function modifyUser_Save() {
	if ($("#modifyUserForm").form("validate")) {
		var organ_name = $('#organ_code_id_modify').combotree('getText');
		if (organ_name != "") {
			$("#organ_name_id_modify").val(organ_name);
		}
		$.ajax({
			type : "post",
			url : webroot + 'system/user/mofifyUser.html',
			dataType : "json",
			data : $("#modifyUserForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					$("#dataGrid").datagrid("reload");// 重新加载数据
					$("#modifyUserDiv").dialog("close");
					$("#modifyUserDiv").hide();
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
function modifyUser_Reset() {
	$("#user_login_name_modify").val("");
	$("#user_name_modify").val("");
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
		var user_name = rows[0].user_name;
		$.messager.confirm('确认', "确认要删除名字为【" + user_name + "】的用户？", function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : webroot + 'system/user/delUser.html',
					dataType : "json",
					data : {
						userId : rows[0].id
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

// ======================================解锁用户=================================
/**
 * 解锁用户
 */
function unlockUser(id, user_name) {
	$.messager.confirm('确认', "确认要解锁名字为【" + user_name + "】的用户？", function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : webroot + 'system/user/unlockUser.html',
				dataType : "json",
				data : {
					userId : id
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

/**
 * 停用用户
 * 
 * @param id
 * @param user_name
 */
function stopUser(id, user_name) {
	$.messager.confirm('确认', "确认要停用名字为【" + user_name + "】的用户？", function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : webroot + 'system/user/stopUser.html',
				dataType : "json",
				data : {
					userId : id
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

/**
 * 启用用户
 * 
 * @param id
 * @param user_name
 */
function startUser(id, user_name) {
	$.messager.confirm('确认', "确认要启用名字为【" + user_name + "】的用户？", function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : webroot + 'system/user/startUser.html',
				dataType : "json",
				data : {
					userId : id
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

/**
 * 注销用户
 */
function cancleUser() {
	var rows = $('#dataGrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择一条记录.');
	} else if (rows.length != 1) {
		$.messager.alert('警告', '一次只能操作一条记录.');
	} else {
		var user_name = rows[0].user_name;
		$.messager.confirm('确认', "确认要注销名字为【" + user_name + "】的用户？", function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : webroot + 'system/user/cancleUser.html',
					dataType : "json",
					data : {
						userId : rows[0].id
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

// =====================================exportUserList=====================================

/**
 * 导出用户列表
 */
function exportUserList() {
	var user_login_name = $("#user_login_name").val();
	var user_name = $("#user_name").val();
	$.ajax({
		type : 'post',
		url : webroot + 'system/user/exportUserList.html',
		dataType : "json",
		data : {
			user_login_name : user_login_name,
			user_name : user_name
		},
		success : function(fileName) {
			var url = webroot + "system/user/downLoadFile.html?fileName=" + fileName;
			window.location.href = url;
		}
	});
}

// ======================================resetLoginPwd======================================
/**
 * 重置登陆密码
 */
function resetLoginPwd() {
	var rows = $('#dataGrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择一条记录.');
	} else if (rows.length != 1) {
		$.messager.alert('警告', '一次只能操作一条记录.');
	} else {
		var user_name = rows[0].user_name;
		$.messager.confirm('确认', "确认要重置名字为【" + user_name + "】的用户的登陆密码？", function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : webroot + 'system/user/resetLoginPwd.html',
					dataType : "json",
					data : {
						userId : rows[0].id
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
