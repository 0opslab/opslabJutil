package com.opslab.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用的Ajax请求结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult {
    private String code;
    private String msg;
    private Object data;
}
