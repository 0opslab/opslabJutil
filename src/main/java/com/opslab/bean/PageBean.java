package com.opslab.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页对象
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
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


    public PageBean(Integer currentPage, Integer pageSize, Long totalNum) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = totalNum;
        this.totalPage = ((Long) ((this.total + this.pageSize - 1) / this.pageSize)).intValue();
        this.startIndex = (this.currentPage - 1) * this.pageSize;
        this.isMore = this.currentPage >= this.totalPage ? 0 : 1;
    }


}
