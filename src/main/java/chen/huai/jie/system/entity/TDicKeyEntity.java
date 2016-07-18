package chen.huai.jie.system.entity;

import java.io.Serializable;

/**
 * 字典表-键-实体类
 * 
 * @author chenhuaijie
 * 
 */
public class TDicKeyEntity implements Serializable {
	private static final long serialVersionUID = 1810641181780282619L;

	private String id;// 主键
	private String key_name;// 键名字
	private String key_code;// 键的编码
	private String remark;// 备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey_name() {
		return key_name;
	}

	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
