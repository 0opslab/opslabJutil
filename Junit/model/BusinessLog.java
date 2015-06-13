package model;


public class BusinessLog {
    private String operationName;

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @Override
    public String toString() {
        return "BusinessLog{" +
                "operationName='" + operationName + '\'' +
                '}';
    }
}
