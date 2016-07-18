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
		url : webroot + "system/organ/getAllOrgan.html",
		checkbox : false,
		lines : true,
		onClick : function(node) {
			$("#organ_code_show").val(node.attributes.organ_code);
			$("#organ_name_show").val(node.text);
			$("#sort_show").val(node.attributes.sort);
			$("#description_show").val(node.attributes.description);
			$("#remark_show").val(node.attributes.remark);
			$("#organ_level_show").val(node.attributes.organ_level);
		}
	});

	/**
	 * addOrgan
	 */
	$("#addOrganDiv").dialog({
		title : '新增机构',
		iconCls : "icon-add",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		modal : true
	});
	$("#addOrganDiv").dialog("close");

	/**
	 * modifyMenu
	 */
	$("#modifyOrganDiv").dialog({
		title : '编辑机构',
		iconCls : "icon-edit",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 300,
		modal : true
	});
	$("#modifyOrganDiv").dialog("close");
});

/**
 * 增加菜单
 */
function addOrgan() {
	var node = $("#menutree").tree("getSelected");
	if (node == null) {
		$.messager.alert('警告', '请选择一个父节点.');
	} else {
		// 判断以此节点是否可作为父节点创建菜单
		$.ajax({
			type : "post",
			url : webroot + 'system/organ/canCreateSubOrgan.html',
			dataType : "json",
			data : {
				id : node.id
			},
			success : function(result) {
				if (result.success) {
					$("#addOrganDiv").dialog("open");
					// 清空表单
					addMenu_Reset();
					$("#parent_organ_name").val(node.text);
					$("#pid").val(node.id);
					$("#parent_organ_level").val(node.attributes.organ_level);
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
function addOrgan_Save() {
	if ($("#addOrganForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/organ/addOrgan.html',
			dataType : "json",
			data : $("#addOrganForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					// 刷新树
					$("#menutree").tree("reload");
					// 关闭增加菜单面板
					$("#addOrganDiv").dialog("close");
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

// ============================================编辑机构===========================================
/**
 * 编辑菜单
 */
function editOrgan() {
	var node = $("#menutree").tree("getSelected");
	if (node == null) {
		$.messager.alert('警告', '请选择一个节点.');
	} else {
		$("#modifyOrganDiv").dialog("open");
		$("#id_modify").val(node.id);
		$("#organ_code_modify").val(node.attributes.organ_code);
		$("#organ_name_modify").val(node.text);
		$("#sort_modify").val(node.attributes.sort);
		$("#description_modify").val(node.attributes.description);
		$("#remark_modify").val(node.attributes.remark);
	}
}

/**
 * 编辑机构-保存
 */
function modifyOrgan_Save() {
	if ($("#modifyOrganForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'system/organ/modifyOrgan.html',
			dataType : "json",
			data : $("#modifyOrganForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					// 刷新树
					$("#menutree").tree("reload");
					// 关闭增加菜单面板
					$("#modifyOrganDiv").dialog("close");
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

// =====================================删除机构==========================================
/**
 * 删除菜单
 */
function delOrgan() {
	var node = $("#menutree").tree("getSelected");
	if (node == null) {
		$.messager.alert('警告', '请选择一个节点.');
	} else {
		$.messager.confirm('提示', '您想要删除【' + node.text + '】机构吗？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : webroot + 'system/organ/delOrgan.html',
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
