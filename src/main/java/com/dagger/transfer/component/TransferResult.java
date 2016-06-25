/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.component;

import java.util.List;

import com.dagger.transfer.exception.ErrorCode;

/**
 * 
 * @author 马洪良
 * @version $Id: TransferData.java, v 0.1 2014年1月13日 下午4:13:03 马洪良 Exp $
 */
public final class TransferResult {
	//
	private Boolean bGood = false;

	private ErrorCode errorCode;

	// 工具处理的对象和返回的就结果
	private String description;

	private String sqlSrc;

	private String sqlDst;

	private List<String> listError;

	private List<String> listSucss;

	/**
	 * Getter method for property <tt>bGood</tt>.
	 * 
	 * @return property value of bGood
	 */
	public Boolean getbGood() {
		return bGood;
	}

	/**
	 * Getter method for property <tt>description</tt>.
	 * 
	 * @return property value of description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Getter method for property <tt>errorCode</tt>.
	 * 
	 * @return property value of errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * Getter method for property <tt>listError</tt>.
	 * 
	 * @return property value of listError
	 */
	public List<String> getListError() {
		return listError;
	}

	/**
	 * Getter method for property <tt>listSucss</tt>.
	 * 
	 * @return property value of listSucss
	 */
	public List<String> getListSucss() {
		return listSucss;
	}

	/**
	 * Getter method for property <tt>sqlDst</tt>.
	 * 
	 * @return property value of sqlDst
	 */
	public String getSqlDst() {
		return sqlDst;
	}

	/**
	 * Getter method for property <tt>sqlSrc</tt>.
	 * 
	 * @return property value of sqlSrc
	 */
	public String getSqlSrc() {
		return sqlSrc;
	}

	/**
	 * Setter method for property <tt>bGood</tt>.
	 * 
	 * @param bGood
	 *            value to be assigned to property bGood
	 */
	public void setbGood(Boolean bGood) {
		this.bGood = bGood;
	}

	/**
	 * Setter method for property <tt>description</tt>.
	 * 
	 * @param description
	 *            value to be assigned to property description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Setter method for property <tt>errorCode</tt>.
	 * 
	 * @param errorCode
	 *            value to be assigned to property errorCode
	 */
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Setter method for property <tt>listError</tt>.
	 * 
	 * @param listError
	 *            value to be assigned to property listError
	 */
	public void setListError(List<String> listError) {
		this.listError = listError;
	}

	/**
	 * Setter method for property <tt>listSucss</tt>.
	 * 
	 * @param listSucss
	 *            value to be assigned to property listSucss
	 */
	public void setListSucss(List<String> listSucss) {
		this.listSucss = listSucss;
	}

	/**
	 * Setter method for property <tt>sqlDst</tt>.
	 * 
	 * @param sqlDst
	 *            value to be assigned to property sqlDst
	 */
	public void setSqlDst(String sqlDst) {
		this.sqlDst = sqlDst;
	}

	/**
	 * Setter method for property <tt>sqlSrc</tt>.
	 * 
	 * @param sqlSrc
	 *            value to be assigned to property sqlSrc
	 */
	public void setSqlSrc(String sqlSrc) {
		this.sqlSrc = sqlSrc;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransferResult [bGood=");
		builder.append(bGood);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", description=");
		builder.append(description);
		builder.append(", sqlSrc=");
		builder.append(sqlSrc);
		builder.append(", sqlDst=");
		builder.append(sqlDst);
		builder.append(", listError=");
		builder.append(listError);
		builder.append("]");
		return builder.toString();
	}

}
