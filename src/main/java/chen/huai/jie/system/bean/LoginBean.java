package chen.huai.jie.system.bean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户登陆Bean
 * 
 * @author chenhuaijie
 * 
 */
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 7237941663685399045L;
	@NotEmpty(message = "用户名不能为空.")
	private String user_login_name;
	@NotEmpty(message = "密码不能为空.")
	private String password;
	@NotEmpty(message = "验证码不能为空.")
	private String verifyCode;// 验证码

	public String getUser_login_name() {
		return user_login_name;
	}

	public void setUser_login_name(String user_login_name) {
		this.user_login_name = user_login_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
