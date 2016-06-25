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
public enum InitiErrorCode implements ErrorCode {

	CONNET_FIRST_FAIELD("第一个链接失败,请检查配置"),

	CONNET_SECOND_FAIELD("第二个链接失败,请检查配置"),

	FLOWS_NOT_EXITS("flows设置错误或则不存在"),

	CANNOT_FIND_COMPONENT("需要处理的工具不存在,请重新设置"),

	NOT_FIND_GROOVYBASE("groovy的根目录不存在"),

	NOT_FIND_GROOVYFILE("groovy的文件没有指定");

	/** 异常描述 */
	private final String description;

	private InitiErrorCode(String description) {
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
