package chen.huai.jie.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志实体类
 * 
 * @author chenhuaijie
 * 
 */
public class LogEntity implements Serializable {
	private static final long serialVersionUID = 9159695618029911294L;
	private String id;
	private String subject;// 日志主题
	private String content;// 日志内容
	private String operator;// 操作人
	private Date create_time;// 时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
