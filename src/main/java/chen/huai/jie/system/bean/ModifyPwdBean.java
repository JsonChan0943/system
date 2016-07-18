package chen.huai.jie.system.bean;

import java.io.Serializable;

/**
 * 修改登录密码bean
 * 
 * @author chenhuaijie
 * 
 */
public class ModifyPwdBean implements Serializable {
	private static final long serialVersionUID = -4697516822451117471L;

	private String old_password;// 原密码
	private String new_password;// 新密码
	private String confirm_new_password;// 确认新密码

	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getConfirm_new_password() {
		return confirm_new_password;
	}

	public void setConfirm_new_password(String confirm_new_password) {
		this.confirm_new_password = confirm_new_password;
	}

}
