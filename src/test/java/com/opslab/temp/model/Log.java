package com.opslab.temp.model;


import java.io.Serializable;

public class Log implements Serializable {
    private Integer logId;

    public String logType;

    private String operationName;

    private String operation_type;

    protected String startTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", logType='" + logType + '\'' +
                ", operationName='" + operationName + '\'' +
                ", operation_type='" + operation_type + '\'' +
                '}';
    }
}
