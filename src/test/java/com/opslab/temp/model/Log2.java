package com.opslab.temp.model;


public class Log2 {

    private Integer logId;

    private String logyype;

    private String operationname;

    private String operation_type;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogyype() {
        return logyype;
    }

    public void setLogyype(String logyype) {
        this.logyype = logyype;
    }

    public String getOperationname() {
        return operationname;
    }

    public void setOperationname(String operationname) {
        this.operationname = operationname;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    @Override
    public String toString() {
        return "Log2{" +
                "logId=" + logId +
                ", logyype='" + logyype + '\'' +
                ", operationname='" + operationname + '\'' +
                ", operation_type='" + operation_type + '\'' +
                '}';
    }
}
