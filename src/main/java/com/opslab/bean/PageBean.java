package com.opslab.bean;

import java.util.List;

/**
 * 分页对象
 *
 * @param <T>
 */
public class PageBean<T> {

    // 当前页
    private Integer currentPage = 0;
    // 每页显示的总条数
    private Integer pageSize = 10;
    // 总条数
    private Long total;
    // 是否有下一页
    private Integer isMore;
    // 总页数
    private Integer totalPage;
    // 开始索引
    private Integer startIndex;
    // 分页结果
    private List<T> rows;

    public PageBean() {
        super();
    }

    public PageBean(Integer currentPage, Integer pageSize, Long totalNum) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = totalNum;
        this.totalPage = ((Long)((this.total + this.pageSize - 1) / this.pageSize)).intValue();
        this.startIndex = (this.currentPage - 1) * this.pageSize;
        this.isMore = this.currentPage >= this.totalPage ? 0 : 1;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
