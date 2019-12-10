package com.opslab.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

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
