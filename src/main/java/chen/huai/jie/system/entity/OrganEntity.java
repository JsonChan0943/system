package chen.huai.jie.system.entity;

import java.io.Serializable;

/**
 * 机构实体类
 * 
 * @author chenhuaijie
 * 
 */
public class OrganEntity implements Serializable {
	private static final long serialVersionUID = 2287697658085993798L;

	private String id;// 主键
	private String pid;// 父节点主键
	private String organ_code;// 机构编码
	private String organ_name;// 机构名
	private Integer sort;// 在当前父菜单下的排序位置
	private String description;// 描述
	private String remark;// 备注
	private Integer organ_level;// 机构等级

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

	public String getOrgan_code() {
		return organ_code;
	}

	public void setOrgan_code(String organ_code) {
		this.organ_code = organ_code;
	}

	public String getOrgan_name() {
		return organ_name;
	}

	public void setOrgan_name(String organ_name) {
		this.organ_name = organ_name;
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
