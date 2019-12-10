package com.opslab.factory;

import com.opslab.bean.UiResult;

public class UiResultFactory {

    public final static int SYS_NOLOGIN = -2;
    public static String SYSMSG_NOLOGIN = "还未登录";
    //成功
    public final static int SYS_SUCCESS = 0;
    public static String SYSMSG_SUCCESS = "操作成功";
    //失败
    public final static int SYS_ERROR = 1;
    public static String SYSMSG_ERROR = "操作失败";


    //无记录
    public final static int SYS_NORECORD = -3;
    public final static String SYSMSG_NORECORD = "未找到相应的记录";


    //异常
    public static int SYS_EXCEPTION = -99;
    public static String SYSMSG_EXCEPTION = "系统异常";


    /**
     * 请求成功
     */
    public static UiResult success() {
        UiResult result = new UiResult();
        result.setCode(SYS_SUCCESS);
        result.setMsg(SYSMSG_SUCCESS);
        return result;
    }

    public static UiResult success(Object data) {
        UiResult result = new UiResult();
        result.setCode(SYS_SUCCESS);
        result.setMsg(SYSMSG_SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 请求错误
     */
    public static UiResult error() {
        UiResult result = new UiResult();
        result.setCode(SYS_ERROR);
        result.setMsg(SYSMSG_ERROR);
        result.setData("");
        return result;
    }

    public static UiResult error(String errorMsg) {
        UiResult result = new UiResult();
        result.setCode(SYS_ERROR);
        result.setMsg(errorMsg);
        result.setData("");
        return result;
    }

    public static UiResult error(String errorMsg, Object data) {
        UiResult result = new UiResult();
        result.setCode(SYS_ERROR);
        result.setMsg(errorMsg);
        result.setData(data);
        return result;
    }


    /**
     * 请求异常
     */
    public static UiResult exception() {
        UiResult result = new UiResult();
        result.setCode(SYS_EXCEPTION);
        result.setMsg(SYSMSG_EXCEPTION);
        result.setData("");
        return result;
    }

    public static UiResult exception(String errorMsg) {
        UiResult result = new UiResult();
        result.setCode(SYS_EXCEPTION);
        result.setMsg(SYSMSG_EXCEPTION);
        result.setData("");
        return result;
    }


    /**
     * 未登录
     */
    public static UiResult noLogin() {
        UiResult result = new UiResult();
        result.setCode(SYS_NOLOGIN);
        result.setMsg(SYSMSG_NOLOGIN);
        result.setData("");
        return result;
    }

    /**
     * 未找到记录
     */
    public static UiResult noRecord() {
        UiResult result = new UiResult();
        result.setCode(SYS_NORECORD);
        result.setMsg(SYSMSG_NORECORD);
        result.setData("");
        return result;
    }

    public static UiResult result(int code,String msg,Object data){
        UiResult result = new UiResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
