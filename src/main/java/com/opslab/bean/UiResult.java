package com.opslab.bean;

/**
 * 返回统一的数据格式
 */
public class UiResult {
    //返回编码
    private String code;
    //编码描述
    private String msg;
    //业务数据
    private Object data;

    public UiResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public UiResult() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
