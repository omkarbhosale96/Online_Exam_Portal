package com.pojo;

public class Test {

	private int testId;
	private String tName;
	
	
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	
	@Override
	public String toString() {
		return "Test [testId=" + testId + ", tName=" + tName + "]";
	}
	
	
}
