package chen.huai.jie.system.bean.export;

/**
 * 系统参数导出
 * 
 * @author chenhuaijie
 * 
 */
public class ParamBeanForExport {
	private String param_code;// 参数编码
	private String param_name;// 参数名
	private String param_value;// 参数值
	private String create_time;// 创建时间
	private String modify_time;// 修改时间
	private String remark;// 备注

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

	public String getParam_value() {
		return param_value;
	}

	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
