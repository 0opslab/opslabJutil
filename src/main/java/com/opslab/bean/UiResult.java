package com.opslab.bean;

import lombok.Data;

/**
 * 返回统一的数据格式
 */
@Data
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


}
