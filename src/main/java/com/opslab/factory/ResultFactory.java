package com.opslab.factory;

import com.opslab.Opslab;
import com.opslab.bean.AjaxResult;

public class ResultFactory {
    public static String SYSMSG_SUCCESS = "操作成功";

    public static String SYSMSG_ERROR = "操作失败";

    public static String SYSMSG_EXCEPTION = "系统异常";

    public static String SYSMSG_NOLOGIN = "你还未登录";

    public static String SYSMSG_NORECORD ="未找到相应的记录";

    /**
     * 请求成功
     *
     */
    public static AjaxResult success() {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_SUCCESS);
        result.setResultDesc(SYSMSG_SUCCESS);
        return result;
    }

    public static AjaxResult success(Object data) {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_SUCCESS);
        result.setResultDesc(SYSMSG_SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 请求错误
     */
    public static AjaxResult error(){
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_ERROR);
        result.setResultDesc(SYSMSG_ERROR);
        result.setData("");
        return result;
    }

    public static AjaxResult error(String errorMsg) {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_ERROR);
        result.setResultDesc(errorMsg);
        result.setData("");
        return result;
    }

    public static AjaxResult error(String errorMsg,Object data) {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_ERROR);
        result.setResultDesc(errorMsg);
        result.setData(data);
        return result;
    }

    /**
     * 请求异常
     */
    public static AjaxResult exception() {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_EXCEPTION);
        result.setResultDesc(SYSMSG_EXCEPTION);
        result.setData("");
        return result;
    }

    public static AjaxResult exception(String errorMsg) {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_EXCEPTION);
        result.setResultDesc(SYSMSG_EXCEPTION);
        result.setData("");
        return result;
    }



    /**
     * 未登录
     */
    public static AjaxResult noLogin() {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_NOLOGIN);
        result.setResultDesc(SYSMSG_NOLOGIN);
        result.setData("");
        return result;
    }

    /**
     * 未找到记录
     */
    public static AjaxResult noRecord(){
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_NORECORD);
        result.setResultDesc(SYSMSG_NORECORD);
        result.setData("");
        return result;
    }
}
