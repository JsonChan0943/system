/**
 * 菜单管理
 */
$(function() {
	/**
	 * 页面布局
	 */
	index_layout = $('#index_layout').layout({
		fit : true,
		collapsible : false
	});

	/**
	 * 菜单树
	 */
	$("#menutree").tree({
		url : webroot + "system/menu/getAllMenu.html",
		checkbox : false,
		lines : true,
		onClick : function(node) {
			$("#menu_code_show").val(node.attributes.menu_code);
			$("#menu_name_show").val(node.text);
			$("#menu_url_show").val(node.attributes.url);
			$("#description_show").val(node.attributes.description);
			$("#remark_show").val(node.attributes.remark);
			$("#menu_level_show").val(node.attributes.menu_level);
		}
	});

	/**
	 * addMenu
	 */
	$("#addMenuDiv").dialog({
		title : '新增菜单',
		iconCls : "icon-add",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		modal : true
	});
	$("#addMenuDiv").dialog("close");

	/**
	 * modifyMenu
	 */
	$("#modifyMenuDiv").dialog({
		title : '编辑菜单',
		iconCls : "icon-edit",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		modal : true
	});
	$("#modifyMenuDiv").dialog("close");
});

/**
 * 增加菜单
 */
function addMenu() {
	var node = $("#menutree").tree("getSelected");
	if (node == null) {
		$.messager.alert('警告', '请选择一个父节点.');
	} else {
		// 判断以此节点是否可作为父节点创建菜单
		$.ajax({
			type : "post",
			url : webroot + 'system/menu/canCreateSubMenu.html',
			dataType : "json",
			data : {
				id : node.id
			},
			success : function(result) {
				if (result.success) {
					$("#addMenuDiv").dialog("open");
					// 清空表单
					addMenu_Reset();
					$("#parent_menu_name").val(node.text);
					$("#pid").val(node.id);
					$("#parent_menu_level").val(node.attributes.menu_level);
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

/**
 * 增加菜单-保存
 */
function addMenu_Save() {
	if ($("#addMenuForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/menu/addMenu.html',
			dataType : "json",
			data : $("#addMenuForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					// 刷新树
					$("#menutree").tree("reload");
					// 关闭增加菜单面板
					$("#addMenuDiv").dialog("close");
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

/**
 * 增加菜单-重置
 */
function addMenu_Reset() {
	$("#menu_code").val("");
	$("#menu_name").val("");
	$("#menu_url").val("");
	$("#description").val("");
	$("#remark").val("");
}

/**
 * 编辑菜单
 */
function editMenu() {
	var node = $("#menutree").tree("getSelected");
	if (node == null) {
		$.messager.alert('警告', '请选择一个节点.');
	} else {
		$("#modifyMenuDiv").dialog("open");
		$("#id_modify").val(node.id);
		$("#menu_code_modify").val(node.attributes.menu_code);
		$("#menu_name_modify").val(node.text);
		$("#menu_url_modify").val(node.attributes.url);
		$("#description_modify").val(node.attributes.description);
		$("#remark_modify").val(node.attributes.remark);
	}
}

/**
 * 编辑菜单-保存
 */
function modifyMenu_Save() {
	if ($("#modifyMenuForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/menu/modifyMenu.html',
			dataType : "json",
			data : $("#modifyMenuForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					// 刷新树
					$("#menutree").tree("reload");
					// 关闭增加菜单面板
					$("#modifyMenuDiv").dialog("close");
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

/**
 * 编辑菜单-重置
 */
function modifyMenu_Reset() {
	$("#menu_code_modify").val("");
	$("#menu_name_modify").val("");
	$("#menu_url_modify").val("");
	$("#description_modify").val("");
	$("#remark_modify").val("");
}

/**
 * 删除菜单
 */
function delMenu() {
	var node = $("#menutree").tree("getSelected");
	if (node == null) {
		$.messager.alert('警告', '请选择一个节点.');
	} else {
		$.messager.confirm('提示', '您想要删除【' + node.text + '】菜单吗？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : webroot + 'system/menu/delMenu.html',
					dataType : "json",
					data : {
						id : node.id
					},
					success : function(result) {
						$("#menutree").tree("reload");// 刷新树
						if (result.success) {
							$.messager.alert('提示', result.msg, 'info');
						} else {
							$.messager.alert('警告', result.msg);
						}
					}
				});
			}
		});
	}
}
