package chen.huai.jie.system.pager;

import chen.huai.jie.base.pager.Pager;

/**
 * 系统参数分页对象
 * 
 * @author chenhuaijie
 * 
 */
public class TsysParamPager extends Pager {
	private static final long serialVersionUID = 6066544469439021271L;
	private String param_code;// 参数编码
	private String param_name;// 参数名

	public String getParam_code() {
		return param_code;
	}

	public void setParam_code(String param_code) {
		this.param_code = param_code;
	}

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

}
