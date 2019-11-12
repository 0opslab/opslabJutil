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
        result.setCode(Opslab.SYS_SUCCESS);
        result.setMsg(SYSMSG_SUCCESS);
        return result;
    }

    public static AjaxResult success(Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(Opslab.SYS_SUCCESS);
        result.setMsg(SYSMSG_SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 请求错误
     */
    public static AjaxResult error(){
        AjaxResult result = new AjaxResult();
        result.setCode(Opslab.SYS_ERROR);
        result.setMsg(SYSMSG_ERROR);
        result.setData("");
        return result;
    }

    public static AjaxResult error(String errorMsg) {
        AjaxResult result = new AjaxResult();
        result.setCode(Opslab.SYS_ERROR);
        result.setMsg(errorMsg);
        result.setData("");
        return result;
    }

    public static AjaxResult error(String errorMsg,Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(Opslab.SYS_ERROR);
        result.setMsg(errorMsg);
        result.setData(data);
        return result;
    }

    public static AjaxResult error(int code,String msg){
        AjaxResult result = new AjaxResult();
        result.setCode(String.valueOf(code));
        result.setMsg(msg);
        return result;
    }
    /**
     * 请求异常
     */
    public static AjaxResult exception() {
        AjaxResult result = new AjaxResult();
        result.setCode(Opslab.SYS_EXCEPTION);
        result.setMsg(SYSMSG_EXCEPTION);
        result.setData("");
        return result;
    }

    public static AjaxResult exception(String errorMsg) {
        AjaxResult result = new AjaxResult();
        result.setCode(Opslab.SYS_EXCEPTION);
        result.setMsg(SYSMSG_EXCEPTION);
        result.setData("");
        return result;
    }



    /**
     * 未登录
     */
    public static AjaxResult noLogin() {
        AjaxResult result = new AjaxResult();
        result.setCode(Opslab.SYS_NOLOGIN);
        result.setMsg(SYSMSG_NOLOGIN);
        result.setData("");
        return result;
    }

    /**
     * 未找到记录
     */
    public static AjaxResult noRecord(){
        AjaxResult result = new AjaxResult();
        result.setCode(Opslab.SYS_NORECORD);
        result.setMsg(SYSMSG_NORECORD);
        result.setData("");
        return result;
    }
}
