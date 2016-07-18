package chen.huai.jie.system.entity;

import java.io.Serializable;
import java.util.Date;

import chen.huai.jie.base.enums.enumc.UserStateEnum;
import chen.huai.jie.base.utils.EnumUtil;

/**
 * 用户表实体类
 * 
 * @author chenahuaijie
 * 
 */
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 3632764188180184866L;

	private String id;// 主键
	private String user_login_name;// 用户登陆名
	private String user_name;// 用户名
	private String password;// 密码
	private Integer state;// 用户状态
	private String organ_code;// 用户所属机构
	private String organ_name;// 所属机构名
	private Integer login_error_count;// 登陆错误次数
	private Date auto_unlock_time;// 自动解锁时间
	private Date create_time;// 账号创建时间
	private Date last_login_time;// 最近一次登录时间
	private Date last_pwd_modify_time;// 最近一次密码修改时间

	// 描述字段
	private String stateDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getStateDesc() {
		stateDesc = EnumUtil.getNameByValue(UserStateEnum.class, getState());
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public Integer getLogin_error_count() {
		return login_error_count;
	}

	public void setLogin_error_count(Integer login_error_count) {
		this.login_error_count = login_error_count;
	}

	public Date getAuto_unlock_time() {
		return auto_unlock_time;
	}

	public void setAuto_unlock_time(Date auto_unlock_time) {
		this.auto_unlock_time = auto_unlock_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public Date getLast_pwd_modify_time() {
		return last_pwd_modify_time;
	}

	public void setLast_pwd_modify_time(Date last_pwd_modify_time) {
		this.last_pwd_modify_time = last_pwd_modify_time;
	}

}
