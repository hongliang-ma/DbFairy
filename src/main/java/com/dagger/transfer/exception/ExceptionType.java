/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.exception;

/**
 * 
 * @author 马洪良
 * @version $Id: ExceptionType.java, v 0.1 2014年1月21日 下午4:38:41 马洪良 Exp $
 */
public enum ExceptionType {

	/** 系统异常 */
	SYSTEM_ERROR,

	/** 初始化出错 */
	INITIT_ERROR,

	/** 工具处理异常 */
	HANDLE_ERROR,

	/** 位置异常 */
	UNKOWND_ERROR;

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return this.name();
	}
}
