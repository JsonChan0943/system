package chen.huai.jie.system.entity;

import java.io.Serializable;

/**
 * 用户角色表实体类
 * 
 * @author chenhuaijie
 * 
 */
public class UserRoleEntity implements Serializable {
	private static final long serialVersionUID = 5521129702635389842L;

	private String id;// 主键
	private String user_id;// 用户id
	private String role_id;// 角色id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

}
