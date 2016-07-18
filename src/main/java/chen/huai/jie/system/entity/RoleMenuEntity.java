package chen.huai.jie.system.entity;

import java.io.Serializable;

/**
 * 角色菜单表实体类
 * 
 * @author chenhuaijie
 * 
 */
public class RoleMenuEntity implements Serializable {
	private static final long serialVersionUID = -3693669085786294405L;

	private String id;// 主键
	private String role_id;// 角色id
	private String menu_id;// 菜单id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
}
