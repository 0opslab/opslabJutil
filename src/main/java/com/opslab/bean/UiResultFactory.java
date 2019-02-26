package com.opslab.bean;

public class UiResultFactory {
    public static String RESULT_STATUS_SUCCESS = "SUCCESS";

    public static String RESULT_STATUS_ERROR = "ERROR";

    public static String RESULT_STATUS_EXCEPTION = "EXCEPTION";

    public static UiResult success() {
        return new UiResult(RESULT_STATUS_SUCCESS, RESULT_STATUS_SUCCESS, null);
    }

    public static UiResult success(Object data) {
        return new UiResult(RESULT_STATUS_SUCCESS, RESULT_STATUS_SUCCESS, data);
    }

    public static UiResult error() {
        return new UiResult(RESULT_STATUS_ERROR, RESULT_STATUS_ERROR, null);
    }

    public static UiResult error(String error) {
        return new UiResult(RESULT_STATUS_ERROR, error, null);
    }


    public static UiResult result(String result, String msg, Object data) {
        return new UiResult(result, msg, data);
    }

}
