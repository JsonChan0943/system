package chen.huai.jie.system.pager;

import chen.huai.jie.base.pager.Pager;

/**
 * 字典表-键-分页对象
 * 
 * @author chenhuaijie
 * 
 */
public class TDicKeyPager extends Pager {
	private static final long serialVersionUID = 6451188515774589526L;

	private String key_name;// 键名字
	private String key_code;// 键的编码

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

}
