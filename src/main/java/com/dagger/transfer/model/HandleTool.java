/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.model;

/**
 * 
 * @author 马洪良
 * @version $Id: HandleTool.java, v 0.1 2014年1月12日 下午8:57:10 马洪良 Exp $
 */
public enum HandleTool {

	/**
	 * 数量比较工具
	 */
	COUNT("CountHandle", "数量型比较工具"),

	/**
	 * 字段比较工具
	 */
	FILEDHANDLE("FiledHandle", "字段比较工具");

	private String handleName;

	private String handDescrition;

	HandleTool(String handleName, String handDescrition) {
		this.handleName = handleName;
		this.handDescrition = handDescrition;
	}

	/**
	 * Getter method for property <tt>handDescrition</tt>.
	 * 
	 * @return property value of handDescrition
	 */
	public String getHandDescrition() {
		return handDescrition;
	}

	/**
	 * Getter method for property <tt>handleName</tt>.
	 * 
	 * @return property value of handleName
	 */
	public String getHandleName() {
		return handleName;
	}

	/**
	 * Setter method for property <tt>handDescrition</tt>.
	 * 
	 * @param handDescrition
	 *            value to be assigned to property handDescrition
	 */
	public void setHandDescrition(String handDescrition) {
		this.handDescrition = handDescrition;
	}

	/**
	 * Setter method for property <tt>handleName</tt>.
	 * 
	 * @param handleName
	 *            value to be assigned to property handleName
	 */
	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}

}
