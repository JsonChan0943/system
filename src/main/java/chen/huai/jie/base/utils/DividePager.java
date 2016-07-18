package chen.huai.jie.base.utils;

import java.util.List;

/**
 * 分页对象
 * @author Administrator
 *
 */
public class DividePager {
	/**请求分页每页条数*/
	private int cntPerPage;
	/**请求分页页码*/
	private int reqPage;
	
	/**MySql分页参数*/
	private transient int startRow;//开始记录序号
	private transient int pageSize;//一共取多少数据
	
	/**分页查找找到的记录*/
	private List<?> list;
	/**分页查找找到的记录数*/
	private int findCnt;
	/**总条数*/
	private int totalCnt;
	/**总页数*/
	private int totalPage;
	
	
	public int getCntPerPage() {
		return cntPerPage;
	}
	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
		setPageSize(cntPerPage);
	}
	public int getReqPage() {
		return reqPage;
	}
	public void setReqPage(int reqPage) {
		this.reqPage = reqPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartRow() {
		return (reqPage-1)*cntPerPage;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public int getFindCnt() {
		return list.size();
	}
	public void setFindCnt(int findCnt) {
		this.findCnt = findCnt;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
		if(totalCnt<cntPerPage){
			this.totalPage = 1;
		}else{
			if(totalCnt%cntPerPage==0){
				this.totalPage = totalCnt/cntPerPage;
			}else{
				this.totalPage = totalCnt/cntPerPage+1;
			}
		}
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
