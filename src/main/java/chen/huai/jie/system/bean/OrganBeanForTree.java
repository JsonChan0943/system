package chen.huai.jie.system.bean;

import java.io.Serializable;
import java.util.List;

import chen.huai.jie.system.entity.OrganEntity;

/**
 * 机构实体类-BeanForTree
 * 
 * @author chenhuaijie
 * 
 */
public class OrganBeanForTree implements Serializable {

	private static final long serialVersionUID = 6301641138818401804L;

	private String id; // 机构Id
	private String text; // 机构名称
	private String state; // 是否打开
	private boolean checked; // 是否选择
	private List<OrganBeanForTree> children;
	private Attributes attributes;

	public OrganBeanForTree(OrganEntity entity) {
		super();
		this.id = entity.getId();
		this.text = entity.getOrgan_name();
		this.checked = false;
		this.state = "open";
		attributes = new Attributes();
		attributes.setPid(entity.getPid());
		attributes.setOrgan_code(entity.getOrgan_code());
		attributes.setSort(entity.getSort());
		attributes.setDescription(entity.getDescription());
		attributes.setRemark(entity.getRemark());
		attributes.setOrgan_level(entity.getOrgan_level());
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

	public List<OrganBeanForTree> getChildren() {
		return children;
	}

	public void setChildren(List<OrganBeanForTree> children) {
		this.children = children;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	/**
	 * 内部类-属性类
	 * 
	 * @author chenhuaijie
	 * 
	 */
	protected class Attributes {
		private String pid;// 父节点主键
		private String organ_code;// 机构编码
		private Integer sort;// 在当前父菜单下的排序位置
		private String description;// 描述
		private String remark;// 备注
		private Integer organ_level;// 机构等级

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}

		public String getOrgan_code() {
			return organ_code;
		}

		public void setOrgan_code(String organ_code) {
			this.organ_code = organ_code;
		}

		public Integer getSort() {
			return sort;
		}

		public void setSort(Integer sort) {
			this.sort = sort;
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

		public Integer getOrgan_level() {
			return organ_level;
		}

		public void setOrgan_level(Integer organ_level) {
			this.organ_level = organ_level;
		}
	}
}
