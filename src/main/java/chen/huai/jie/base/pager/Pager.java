package chen.huai.jie.base.pager;

import java.io.Serializable;

/**
 * 分页工具类-基类
 *
 * @author chenhuaijie
 */
public class Pager implements Serializable {
    private static final long serialVersionUID = -4132648289180892916L;
    private Long page;// 请求的页码
    private Long rows;// 请求的记录数
    private String sort;// 排序字段
    private String order;// 排序类型（升序or降序）

    private Long startRow;// 开始行数

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Long getStartRow() {
        startRow = (page - 1) * rows;
        return startRow;
    }

    public void setStartRow(Long startRow) {
        this.startRow = startRow;
    }

}
