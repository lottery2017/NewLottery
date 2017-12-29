package com.lottery.app.util.exception;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface CodeDesc {
	String description();
	String code();
}
