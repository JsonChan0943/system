/**
 * 菜单加载
 */
function loadMenu() {
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		dataType : "json",
		url : webroot + "system/menu/getMyMenus.html",
		error : function() {
			$.messager.alert('警告', '系统菜单失败');
		},
		success : function(data) {
			var menuHTML = "";
			$.each(data, function() {
				// 一级菜单
				menuHTML += '<div title="' + this.title
						+ '" data-options="iconCls:\'icon-save\'" style="overflow: auto; padding: 10px;">';
				// 二级菜单
				$.each(this.subMenus, function(i) {
					menuHTML += '<div class="nav-item">'
					menuHTML += '	<a href="javascript:addTab(\'' + this.title + '\',\'' + this.url + '\')">';
					menuHTML += '		<img src="' + webroot + '/resources/image/system/user.jpg">'
					menuHTML += '		<span>' + this.title + '</span>'
					menuHTML += '	</a>'
					menuHTML += '</div>'
				});
				menuHTML += '</div>';
			});
			$("#menu_id").html(menuHTML);
		}
	});
}
loadMenu();