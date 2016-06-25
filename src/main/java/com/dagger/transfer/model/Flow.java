/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.model;

/**
 * 具体的一个流程
 * 
 * @author 马洪良
 * @version $Id: Flow.java, v 0.1 2014年1月12日 下午8:38:45 马洪良 Exp $
 */
public final class Flow {

	/**
	 * 流程的描述
	 */
	private String descritption;

	/**
	 * 流程的目的，就是选择比较还是转换
	 */
	private HandleTool handleTool;

	/**
	 * 共享的测试参数
	 */
	private SqlDetail sqlShare;

	private Boolean userShare = false;

	/**
	 * 需要比较的sql测试目标
	 */
	private SqlDetail sqlSrc;

	/**
	 * 需要比较的sql结果目标
	 */
	private SqlDetail sqlDst;

	private String groovyDir;

	private String groovyName;

	/**
	 * Getter method for property <tt>descritption</tt>.
	 * 
	 * @return property value of descritption
	 */
	public String getDescritption() {
		return descritption;
	}

	/**
	 * Getter method for property <tt>groovyDir</tt>.
	 * 
	 * @return property value of groovyDir
	 */
	public String getGroovyDir() {
		return groovyDir;
	}

	/**
	 * Getter method for property <tt>groovyName</tt>.
	 * 
	 * @return property value of groovyName
	 */
	public String getGroovyName() {
		return groovyName;
	}

	/**
	 * Getter method for property <tt>handleTool</tt>.
	 * 
	 * @return property value of handleTool
	 */
	public HandleTool getHandleTool() {
		return handleTool;
	}

	/**
	 * Getter method for property <tt>sqlDst</tt>.
	 * 
	 * @return property value of sqlDst
	 */
	public SqlDetail getSqlDst() {
		return sqlDst;
	}

	/**
	 * Getter method for property <tt>sqlShare</tt>.
	 * 
	 * @return property value of sqlShare
	 */
	public SqlDetail getSqlShare() {
		return sqlShare;
	}

	/**
	 * Getter method for property <tt>sqSrc</tt>.
	 * 
	 * @return property value of sqSrc
	 */
	public SqlDetail getSqlSrc() {
		return sqlSrc;
	}

	/**
	 * Getter method for property <tt>userShare</tt>.
	 * 
	 * @return property value of userShare
	 */
	public Boolean getUserShare() {
		return userShare;
	}

	/**
	 * Setter method for property <tt>descritption</tt>.
	 * 
	 * @param descritption
	 *            value to be assigned to property descritption
	 */
	public void setDescritption(String descritption) {
		this.descritption = descritption;
	}

	/**
	 * Setter method for property <tt>groovyDir</tt>.
	 * 
	 * @param groovyDir
	 *            value to be assigned to property groovyDir
	 */
	public void setGroovyDir(String groovyDir) {
		this.groovyDir = groovyDir;
	}

	/**
	 * Setter method for property <tt>groovyName</tt>.
	 * 
	 * @param groovyName
	 *            value to be assigned to property groovyName
	 */
	public void setGroovyName(String groovyName) {
		this.groovyName = groovyName;
	}

	/**
	 * Setter method for property <tt>handleTool</tt>.
	 * 
	 * @param handleTool
	 *            value to be assigned to property handleTool
	 */
	public void setHandleTool(HandleTool handleTool) {
		this.handleTool = handleTool;
	}

	/**
	 * Setter method for property <tt>sqlDst</tt>.
	 * 
	 * @param sqlDst
	 *            value to be assigned to property sqlDst
	 */
	public void setSqlDst(SqlDetail sqlDst) {
		this.sqlDst = sqlDst;
	}

	/**
	 * Setter method for property <tt>sqlShare</tt>.
	 * 
	 * @param sqlShare
	 *            value to be assigned to property sqlShare
	 */
	public void setSqlShare(SqlDetail sqlShare) {
		this.sqlShare = sqlShare;
	}

	/**
	 * Setter method for property <tt>sqSrc</tt>.
	 * 
	 * @param sqSrc
	 *            value to be assigned to property sqSrc
	 */
	public void setSqlSrc(SqlDetail sqlSrc) {
		this.sqlSrc = sqlSrc;
	}

	/**
	 * Setter method for property <tt>userShare</tt>.
	 * 
	 * @param userShare
	 *            value to be assigned to property userShare
	 */
	public void setUserShare(Boolean userShare) {
		this.userShare = userShare;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Flow [descritption=");
		builder.append(descritption);
		builder.append(", handleTool=");
		builder.append(handleTool);
		builder.append(", sqlShare=");
		builder.append(sqlShare);
		builder.append(", userShare=");
		builder.append(userShare);
		builder.append(", sqlSrc=");
		builder.append(sqlSrc);
		builder.append(", sqlDst=");
		builder.append(sqlDst);
		builder.append(", groovyDir=");
		builder.append(groovyDir);
		builder.append(", groovyName=");
		builder.append(groovyName);
		builder.append("]");
		return builder.toString();
	}
}
