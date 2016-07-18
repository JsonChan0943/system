package chen.huai.jie.system.entity;

import java.io.Serializable;

/**
 * 字典表-值-实体类
 * 
 * @author chenhuaijie
 * 
 */
public class TDicValueEntity implements Serializable {
	private static final long serialVersionUID = 2122310458269732167L;

	private String id;// 主键
	private String key_code;// 键的编码
	private String value_code;//
	private String value_name;// 值的名字
	private Integer sort;// 值的排序
	private String remark;// 备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public String getValue_code() {
		return value_code;
	}

	public void setValue_code(String value_code) {
		this.value_code = value_code;
	}

	public String getValue_name() {
		return value_name;
	}

	public void setValue_name(String value_name) {
		this.value_name = value_name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
