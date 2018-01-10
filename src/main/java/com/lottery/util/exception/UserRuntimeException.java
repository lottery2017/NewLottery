package com.lottery.util.exception;

/**
 * Created by gaojunc on 2018/1/7 20:55.
 * Created Reason:用户操作异常
 */
public class UserRuntimeException extends Exception {
    public UserRuntimeException() {
        super();
    }

    public UserRuntimeException(String message) {
        super(message);
    }

    public UserRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
