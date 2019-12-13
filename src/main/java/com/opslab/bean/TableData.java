package com.opslab.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
 * 表数据
 */
@Data
public class TableData implements Serializable {
    private static final long serialVersionUID = 1428759991234L;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 列表数据
     */
    private List<?> rows;
}
