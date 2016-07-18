package chen.huai.jie.system.bean.export;

/**
 * 导出日志到excel
 * 
 * @author chenhuaijie
 * 
 */
public class LogBeanForExport {
	private String subject;// 日志主题
	private String content;// 日志内容
	private String operator;// 操作人
	private String create_time;// 时间

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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
