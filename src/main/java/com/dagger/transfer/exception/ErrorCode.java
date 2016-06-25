/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.exception;

/**
 * 
 * @author 马洪良
 * @version $Id: ErrorCode.java, v 0.1 2014年1月21日 下午4:38:08 马洪良 Exp $
 */
public interface ErrorCode {

	/**
	 * 获取错误描述
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * 获取异常类型
	 * 
	 * @return 异常类型枚举
	 */
	public ExceptionType getType();
}
