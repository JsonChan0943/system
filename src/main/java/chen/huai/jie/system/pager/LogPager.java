package chen.huai.jie.system.pager;

import chen.huai.jie.base.pager.Pager;

/**
 * 日志分页对象
 * 
 * @author chenhuaijie
 * 
 */
public class LogPager extends Pager {
	private static final long serialVersionUID = 2940815524620994032L;
	private String subject;
	private String operator;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
