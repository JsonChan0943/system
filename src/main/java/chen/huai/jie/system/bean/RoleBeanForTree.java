package chen.huai.jie.system.bean;

import java.io.Serializable;
import java.util.List;

import chen.huai.jie.system.entity.RoleEntity;

/**
 * 角色
 * 
 * @author chenhuaijie
 * 
 */
public class RoleBeanForTree implements Serializable {
	private static final long serialVersionUID = -2845270608755650456L;

	private String id; // 菜单Id
	private String text; // 菜单名称
	private String state; // 是否打开
	private boolean checked; // 是否选择
	private List<MenuBeanForTree> children;
	private Attributes attributes;

	public RoleBeanForTree(RoleEntity entity) {
		super();
		this.id = entity.getId();
		this.text = entity.getRole_name();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<MenuBeanForTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuBeanForTree> children) {
		this.children = children;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	protected class Attributes {

	}
}
