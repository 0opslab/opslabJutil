package model;


public class Log {
    private Integer logId;

    private String logType;

    private String operationName;

    private String operation_type;

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
