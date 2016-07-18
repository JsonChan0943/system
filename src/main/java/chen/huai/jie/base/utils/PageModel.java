package chen.huai.jie.base.utils;

import chen.huai.jie.base.system.SysParams;



/**
 * 分页的工具类
 * @author Administrator
 *
 */
public class PageModel {
	public static final int PAGE_SIZE = SysParams.getPage_size();
	/**当前页*/
	private Integer current_page;
	/**总页数*/
	private Long total_page;
	/**要去到哪一页*/
	private Integer toPage;
	
	/**
	 * 根据传入的记录数计算总页数
	 * @param accouns
	 */
	public void countPage(Long accounts){
		if(accounts%PAGE_SIZE==0){
			this.total_page = accounts/PAGE_SIZE;
		}else{
			this.total_page = accounts/PAGE_SIZE+1;
		}
	}
	
	public Integer getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(Integer current_page) {
		this.current_page = current_page;
	}
	public Long getTotal_page() {
		return total_page;
	}
	public void setTotal_page(Long total_page) {
		this.total_page = total_page;
	}
	public Integer getToPage() {
		return toPage;
	}
	public void setToPage(Integer toPage) {
		this.toPage = toPage;
	}
}
