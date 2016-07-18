package chen.huai.jie.system.entity;

import java.io.Serializable;

/**
 * 菜单实体类
 * 
 * @author chenhuaijie
 * 
 */
public class MenuEntity implements Serializable {
	private static final long serialVersionUID = -3170430653401956512L;

	private String id;// 主键
	private String pid;// 父菜单id
	private String menu_code;// 菜单编码
	private String menu_name;// 菜单名
	private String menu_url;// 菜单url
	private Integer is_leaf;// 是否是叶子节点
	private String description;// 菜单描述
	private String remark;// 备注
	private Integer menu_level;// 菜单等级:1、一级菜单；2、二级菜单；3、三级菜单

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public Integer getIs_leaf() {
		return is_leaf;
	}

	public void setIs_leaf(Integer is_leaf) {
		this.is_leaf = is_leaf;
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

	public Integer getMenu_level() {
		return menu_level;
	}

	public void setMenu_level(Integer menu_level) {
		this.menu_level = menu_level;
	}

}
