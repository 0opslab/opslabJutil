package com.opslab.bean;

/**
 * 通用的结果集封装
 */
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}