package com.lottery.app.util.exception;

/**
 * 扩展的异常处理类
 * @author haow1
 *
 */
public class EsbException extends RuntimeException {
	private String errorCode;
	public EsbException(String message) {
		super(message);
	}
	
	public EsbException(String errorCode, String errorMsg){
		super(errorMsg);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode(){
		return this.errorCode;
	}
}
