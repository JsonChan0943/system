/**
 * 项目主页面-chenhuaijie
 */
var index_layout;// 布局管理
var index_tabs;// 选项卡管理
var index_tabsMenu;// 菜单管理
var layout_west_tree;// 树管理
var layout_west_tree_url = '';// 获取菜单url

$(function() {
	/**
	 * 页面布局
	 */
	index_layout = $('#index_layout').layout({
		fit : true
	});

	/**
	 * 选项卡管理
	 */
	index_tabs = $('#index_tabs').tabs({
		fit : true,
		border : false,
		tools : [ {
			iconCls : 'icon-edit',
			handler : function() {
				index_tabs.tabs('select', 0);
			}
		}, {
			iconCls : 'icon-remove',
			handler : function() {
				var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
				index_tabs.tabs('getTab', index).panel('open').panel('refresh');
			}
		}, {
			iconCls : 'icon-cancel',
			handler : function() {
				var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
				var tab = index_tabs.tabs('getTab', index);
				if (tab.panel('options').closable) {
					index_tabs.tabs('close', index);
				}
			}
		} ]
	});

	/**
	 * modifyPwd
	 */
	$("#modifyPwdDiv").dialog({
		title : '修改密码',
		iconCls : "icon-edit",
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : false,
		width : 400,
		height : 200,
		modal : true
	});
	$("#modifyPwdDiv").dialog("close");
});

/**
 * 打开一个新的选项卡
 * 
 * @param title
 *            选项卡标题
 * @param url
 *            选项卡url
 */
function addTab(title, url) {
	url = webroot + url;
	var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
	var t = $('#index_tabs');
	var opts = {
		title : title,
		closable : true,
		iconCls : "icon-ok",
		content : iframe,
		border : false,
		fit : true
	};
	if (t.tabs('exists', opts.title)) {
		t.tabs('select', opts.title);
	} else {
		t.tabs('add', opts);
	}
}

/**
 * 登出
 */
function logout() {
	$.messager.confirm('提示', '确定要退出系统吗？', function(r) {
		if (r) {
			var url = webroot + "login/logOut.html";
			window.location.href = url;
		}
	});
}

// ====================================修改密码=====================================
/**
 * 修改密码
 */
function editPwd() {
	$("#modifyPwdDiv").dialog("open");
}

/**
 * 修改密码-保存
 */
function modifyPwd_Save() {
	if ($("#modifyPwdForm").form('validate')) {
		$.ajax({
			type : "post",
			url : webroot + 'login/modifyPwd.html',
			dataType : "json",
			data : $("#modifyPwdForm").serialize(),
			success : function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg);
					$("#modifyPwdDiv").dialog("close");
				} else {
					$.messager.alert('警告', result.msg);
				}
			}
		});
	}
}

/**
 * 修改密码-重置
 */
function modifyPwd_Reset() {
	$("#old_password_id").val("");
	$("#new_password_id").val("");
	$("#confirm_new_password_id").val("");
}

/**
 * 更换主题
 * 
 * @param obj
 */
function changeTheme(obj) {
	var themeName = obj.value;
	changeThemeFun(themeName);
}