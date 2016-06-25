/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.exception;

import java.util.Collection;

import com.dagger.tools.lang.AliStringUtil;
import com.dagger.tools.lang.CollectionUtils;

/**
 * 
 * @author 马洪良
 * @version $Id: AssertUtil.java, v 0.1 2014年1月21日 下午5:13:07 马洪良 Exp $
 */
public final class AssertUtil {

	/**
	 * 期待的正确值为false，如果实际为true，抛出异常<code>AnymockException</code>
	 * 
	 * @param expression
	 * @param resutlCode
	 *            异常代码
	 * @throws AnymockException
	 */
	public static void isFalse(boolean expression, ErrorCode resutlCode)
			throws DbFairException {
		if (expression) {
			throw new DbFairException(resutlCode);
		}
	}

	/**
	 * 期待的正确值为false，如果实际为true，抛出异常<code>AnymockException</code>
	 * 
	 * @param expression
	 *            表达式
	 * @param resutlCode
	 *            错误代码
	 * @param message
	 *            异常说明
	 * @throws AnymockException
	 */
	public static void isFalse(boolean expression, ErrorCode resutlCode,
			String message) throws DbFairException {
		if (expression) {
			throw new DbFairException(resutlCode, message);
		}
	}

	/**
	 * 期待字符串为非空，如果检查字符串是空白：<code>null</code>、空字符串""或只有空白字符，抛出异常
	 * <code>AnymockException</code>
	 * 
	 * @param text
	 *            待检查的字符串
	 * @param resutlCode
	 *            异常代码
	 * @throws AnymockException
	 */
	public static void isNotBlank(String text, ErrorCode resutlCode)
			throws DbFairException {
		if (AliStringUtil.isBlank(text)) {
			throw new DbFairException(resutlCode);
		}
	}

	/**
	 * 期待对象为非空，如果检查的对象为<code>null</code>，抛出异常<code>AnymockException</code>
	 * 
	 * @param object
	 * @param resutlCode
	 * @throws AnymockException
	 */
	public static void isNotNull(Object object, ErrorCode resutlCode)
			throws DbFairException {
		if (object == null) {
			throw new DbFairException(resutlCode);
		}
	}

	/**
	 * 期待对象为非空，如果检查的对象为<code>null</code>，抛出异常<code>AnymockException</code>
	 * 
	 * @param object
	 * @param resutlCode
	 * @param message
	 *            异常说明
	 * @throws AnymockException
	 */
	public static void isNotNull(Object object, ErrorCode resutlCode,
			String message) throws DbFairException {
		if (object == null) {
			throw new DbFairException(resutlCode, message);
		}
	}

	/**
	 * 期待的正确值为true，如果实际为false，抛出异常<code>AnymockException</code>
	 * 
	 * @param expression
	 * @param resutlCode
	 *            异常代码
	 * @throws AnymockException
	 */
	public static void isTrue(boolean expression, ErrorCode resutlCode)
			throws DbFairException {
		if (!expression) {
			throw new DbFairException(resutlCode);
		}
	}

	/**
	 * 期待的正确值为true，如果实际为false，抛出异常<code>AnymockException</code>
	 * 
	 * @param expression
	 *            表达式
	 * @param resutlCode
	 *            错误代码
	 * @param message
	 *            异常说明
	 * @throws AnymockException
	 */
	public static void isTrue(boolean expression, ErrorCode resutlCode,
			String message) throws DbFairException {
		if (!expression) {
			throw new DbFairException(resutlCode, message);
		}
	}

	/**
	 * 期待集合对象为非空，如果检查集合对象是否为null或者空数据，抛出异常<code>AnymockException</code>
	 * 
	 * @param collection
	 *            集合对象
	 * @param resutlCode
	 *            异常代码
	 * @throws AnymockException
	 */
	public static void notEmpty(Collection<?> collection, ErrorCode resutlCode)
			throws DbFairException {
		if (CollectionUtils.isEmpty(collection)) {
			throw new DbFairException(resutlCode);
		}
	}

	/**
	 * 禁用构造函数
	 */
	private AssertUtil() {
		// 禁用构造函数
	}
}
