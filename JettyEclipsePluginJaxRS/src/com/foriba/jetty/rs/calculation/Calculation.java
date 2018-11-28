package com.foriba.jetty.rs.calculation;

public class Calculation {

    private String operation;
    private int left;
    private int right;
    private int result;
    
    public Calculation() {
    }
    
    public Calculation(String operation, Integer left, Integer right) {
        super();
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public Integer getLeft() {
        return left;
    }
    
    public void setLeft(Integer left) {
        this.left = left;
    }
    
    public Integer getRight() {
        return right;
    }
    
    public void setRight(Integer right) {
        this.right = right;
    }
    
    public Integer getResult() {
        return result;
    }
    
    public void setResult(Integer result) {
        this.result = result;
    }

	@Override
	public String toString() {
		return "Calculation [operation=" + operation + ", left=" + left + ", right=" + right + ", result=" + result
				+ "]";
	}
}