/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.exception;

/**
 * 
 * @author 马洪良
 * @version $Id: DbFairException.java, v 0.1 2014年1月21日 下午4:42:25 马洪良 Exp $
 */
public final class DbFairException extends RuntimeException {

	/**  */
	private static final long serialVersionUID = -3923094951832863125L;
	/** 异常代码 */
	private final ErrorCode resultCode;

	/**
	 * 构造函数
	 * 
	 * @param resultCode
	 */
	public DbFairException(ErrorCode resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * 构造函数
	 * 
	 * @param resultCode
	 * @param message
	 */
	public DbFairException(ErrorCode resultCode, String message) {
		super(message);
		this.resultCode = resultCode;
	}

	/**
	 * 构造函数
	 * 
	 * @param resultCode
	 * @param message
	 * @param e
	 */
	public DbFairException(ErrorCode resultCode, String message, Throwable e) {
		super(message, e);
		this.resultCode = resultCode;
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 * @param e
	 */
	public DbFairException(ErrorCode resultCode, Throwable e) {
		super(e);
		this.resultCode = resultCode;
	}

	/**
	 * 获取异常代码
	 * 
	 * @return property value of resultCode
	 */
	public ErrorCode getErrorCode() {
		return resultCode;
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		if (null == super.getMessage()) {
			return this.resultCode.getDescription();
		} else {
			return super.getMessage();
		}
	}

	/**
	 * 获取异常类型
	 * 
	 * @return
	 */
	public ExceptionType getType() {
		return resultCode.getType();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DbFairException [resultCode=");
		builder.append(resultCode);
		builder.append("]");
		return builder.toString();
	}

}
