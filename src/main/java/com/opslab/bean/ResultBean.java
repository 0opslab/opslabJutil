package com.opslab.bean;

import lombok.Data;

/**
 * 通用的结果集封装
 */
@Data
public class ResultBean {
    private String code;
    private Object data;
    private boolean success = false;

    public ResultBean() {
    }

    public ResultBean(String code, Object data, boolean success) {
        this.code = code;
        this.data = data;
        this.success = success;
    }

}
