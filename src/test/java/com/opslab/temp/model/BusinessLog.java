package com.opslab.temp.model;


public class BusinessLog extends Log implements interfaces {

    private String operationName;

    private String operation_type;

    public String operationUser;

    protected String result;

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

    @Override
    public String toString() {
        return "BusinessLog{" +
                "log=" + super.toString() +
                "operationName='" + operationName + '\'' +
                ", operation_type='" + operation_type + '\'' +
                '}';
    }
}
