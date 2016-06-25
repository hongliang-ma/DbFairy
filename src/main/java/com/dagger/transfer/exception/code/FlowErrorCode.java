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
 * @version $Id: FlowErrorCode.java, v 0.1 2014年1月21日 下午5:58:43 马洪良 Exp $
 */
public enum FlowErrorCode implements ErrorCode {

	HANDLE_NOTEXIS__FAIELD("没有设置handle参数"),

	DBEXPRESS_FAIELD("数据库比较的dbExpress没有设置"),

	SQLFIRST_NOT_EXITS("sqlSrc不存在"),

	SQLSECOND_NOT_EXITS("sqlDst不存在"),

	SQLRESULT_ERROR_EXITS("sql的结果检查失败"),

	FIRST_ALIAS_NOT_EXISE("sqlSrc的sql的数据库别名没有设置"),

	SECOND_ALIAS_NOT_EXISE("sqlDst的sql的数据库别名没有设置"),

	FIRST_ALIAS_NOT_EQUAL("sqlSrc的sql的数据库别名错误"),

	SECOND_ALIAS_NOT_EQUAL("sqlDst的sql的数据库别名错误"),

	FIRST_SQL_NOT_EXISE("sqlSrc的sql语句不存在"),

	SECOND_SQL_NOT_EXISE("sqlDst的sql语句不存在"),

	COMPONENT_HANDLE_ERROR("处理flow过程中产生异常"),

	SHARE_NOT_EXISEE("设置了有共享的参数，但是sqlShare没有设置"),

	SHARE_ALIAS_NOT_EXISE("设置了共享参数，但是sqlShare没有设置别名"),

	SHARE_SQL_NOT_EXISE("设置了共享参数，但是sqlShare没有设置sql语句"),

	SHARE_ALIAS_NOT_EQUAL("设置了有共享的参数，但是sqlShare的别名和sqlSrc不一样");

	/** 异常描述 */
	private final String description;

	private FlowErrorCode(String description) {
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
