package chen.huai.jie.system.pager;

import chen.huai.jie.base.pager.Pager;

/**
 * 角色分页对象
 * 
 * @author chenhuaijie
 * 
 */
public class RolePager extends Pager {
	private static final long serialVersionUID = -136657988496884388L;

	private String role_code;// 角色编码
	private String role_name;// 角色名

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

}
