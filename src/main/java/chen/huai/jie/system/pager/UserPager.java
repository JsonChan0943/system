package chen.huai.jie.system.pager;

import chen.huai.jie.base.pager.Pager;

/**
 * 用户分页对象
 * 
 * @author chenhuaijie
 * 
 */
public class UserPager extends Pager {
	private static final long serialVersionUID = 4661998491632677322L;

	private String user_login_name;// 用户登陆名
	private String user_name;// 密码

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

}
