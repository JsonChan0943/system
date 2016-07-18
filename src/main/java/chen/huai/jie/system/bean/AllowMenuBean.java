package chen.huai.jie.system.bean;

import java.io.Serializable;

public class AllowMenuBean implements Serializable {
	private static final long serialVersionUID = 2879909986847308631L;

	private String pid;
	private String user_id;

	public AllowMenuBean() {
		super();
	}

	public AllowMenuBean(String pid, String user_id) {
		super();
		this.pid = pid;
		this.user_id = user_id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
