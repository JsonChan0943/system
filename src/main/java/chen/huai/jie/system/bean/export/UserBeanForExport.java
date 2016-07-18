package chen.huai.jie.system.bean.export;

/**
 * 导出用户列表实体类
 * 
 * @author chenhuaijie
 * 
 */
public class UserBeanForExport {

	private String user_login_name;// 用户登陆名
	private String user_name;// 用户名
	private String stateDesc;
	private String organ_code;// 用户所属机构
	private String organ_name;// 所属机构名
	private String create_time;// 账号创建时间

	public String getUser_login_name() {
		return user_login_name;
	}

	public void setUser_login_name(String user_login_name) {
		this.user_login_name = user_login_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
