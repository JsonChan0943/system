package chen.huai.jie.system.bean;

import java.io.Serializable;
import java.util.List;

import chen.huai.jie.system.entity.MenuEntity;

/**
 * 菜单管理
 * 
 * @author chenhuaijie
 * 
 */
public class MenuBeanForTree implements Serializable {
	private static final long serialVersionUID = -753563660144081376L;

	private String id; // 菜单Id
	private String text; // 菜单名称
	private String state; // 是否打开
	private boolean checked; // 是否选择
	private List<MenuBeanForTree> children;
	private Attributes attributes;

	public MenuBeanForTree(MenuEntity entity) {
		super();
		this.id = entity.getId();
		this.text = entity.getMenu_name();
		this.state = "open";
		this.checked = false;
		Attributes attributes = new Attributes();
		attributes.setUrl(entity.getMenu_url());
		attributes.setMenu_level(entity.getMenu_level());
		attributes.setMenu_code(entity.getMenu_code());
		attributes.setDescription(entity.getDescription());
		attributes.setRemark(entity.getRemark());
		this.attributes = attributes;
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
		private String url; // 菜单地址
		private Integer menu_level;// 菜单等级:1、一级菜单；2、二级菜单；3、三级菜单
		private String menu_code;// 菜单编码
		private String description;// 菜单描述
		private String remark;// 备注

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Integer getMenu_level() {
			return menu_level;
		}

		public void setMenu_level(Integer menu_level) {
			this.menu_level = menu_level;
		}

		public String getMenu_code() {
			return menu_code;
		}

		public void setMenu_code(String menu_code) {
			this.menu_code = menu_code;
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
}
