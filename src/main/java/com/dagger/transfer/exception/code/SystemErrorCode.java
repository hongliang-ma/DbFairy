/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.exception.code;

import com.dagger.transfer.exception.ErrorCode;
import com.dagger.transfer.exception.ExceptionType;

/**
 * 
 * @author 马洪良
 * @version $Id: InitiErrorCode.java, v 0.1 2014年1月21日 下午4:45:37 马洪良 Exp $
 */
public enum SystemErrorCode implements ErrorCode {

	INPUT_PARA_ISNULL("参数为空"),

	INPUT_PARA_MISS("缺少必须的参数"),

	TOOL_INIT_ERROR("工具初始化失败"),

	TOOL_START_ERROR("工具启动错误"),

	GROOVY_START_ERROR("groovy脚本实例化");

	/** 异常描述 */
	private final String description;

	private SystemErrorCode(String description) {
		this.description = description;
	}

	/**
	 * @see com.dagger.transfer.exception.ErrorCode#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * @see com.dagger.transfer.exception.ErrorCode#getType()
	 */
	@Override
	public ExceptionType getType() {
		return ExceptionType.INITIT_ERROR;
	}

}
