package com.opslab.factory;

import com.opslab.Opslab;
import com.opslab.entity.AjaxResult;

public class ResultFactory {
    /**
     * 请求成功
     *
     * @param data
     * @return
     */
    public static AjaxResult success(Object data) {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_SUCCESS);
        result.setResultDesc(Opslab.SYS_SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 请求错误
     *
     * @param errorMsg
     * @return
     */
    public static AjaxResult error(String errorMsg) {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_ERROR);
        result.setResultDesc(Opslab.SYS_ERROR);
        result.setData("");
        return result;
    }

    /**
     * 请求异常
     *
     * @param errorMsg
     * @return
     */
    public static AjaxResult exception(String errorMsg) {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_EXCEPTION);
        result.setResultDesc(errorMsg);
        result.setData("");
        return result;
    }

    /**
     * 未登录
     *
     * @return
     */
    public static AjaxResult noLogin() {
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_NOLOGIN);
        result.setResultDesc("当前用户为登录");
        result.setData("");
        return result;
    }

    /**
     * 未找到记录
     */
    public static AjaxResult noRecord(){
        AjaxResult result = new AjaxResult();
        result.setResult(Opslab.SYS_NORECORD);
        result.setResultDesc("未找到相应的记录");
        result.setData("");
        return result;
    }
}
