package com.opslab.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用的Ajax请求结果
 */
@Data
public class UiResult implements Serializable {
    private static final long serialVersionUID = 1123241343242134234L;
    private int code;
    private String msg;
    private Object data;

    public boolean isSuccess(){
        return 0 == code;
    }

}
