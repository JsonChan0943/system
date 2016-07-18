package chen.huai.jie.system.entity;

import java.io.Serializable;

/**
 * 角色表实体类
 * @author chenhuaijie
 *
 */
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = -8834110035474400577L;
	
	private String id;// 主键
	private String role_code;// 角色编码
	private String role_name;// 角色名
	private String description;// 角色描述
	private String remark;// 备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
