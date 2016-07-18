package chen.huai.jie.system.pager;

import chen.huai.jie.base.pager.Pager;

/**
 * 字典表-值-分页对象
 * 
 * @author chenhuaijie
 * 
 */
public class TDicValuePager extends Pager {
	private static final long serialVersionUID = 4090886681546170112L;
	private String key_code;

	private String value_code;
	private String value_name;

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

}
