/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.model;

import java.io.Serializable;
import java.util.List;

import com.dagger.tools.dbutil.DBConfig;

/**
 * 
 * @author 马洪良
 * @version $Id: DbTransferModel.java, v 0.1 2014年1月12日 下午7:22:24 马洪良 Exp $
 */
public final class TransferModel implements Serializable {

	/**  */
	private static final long serialVersionUID = 2895108592516404917L;

	/**
	 * 开始是数据库的基本配置，包含数据库的链接、别名等等
	 */

	public DBConfig dbSetSrc;

	public DBConfig dbSetDst;

	/**
	 * 全部的sql处理流程
	 */
	public List<Flow> flows;

	/**
	 * Getter method for property <tt>dbSetDst</tt>.
	 * 
	 * @return property value of dbSetDst
	 */
	public DBConfig getDbSetDst() {
		return dbSetDst;
	}

	/**
	 * Getter method for property <tt>dbSetSrc</tt>.
	 * 
	 * @return property value of dbSetSrc
	 */
	public DBConfig getDbSetSrc() {
		return dbSetSrc;
	}

	/**
	 * Getter method for property <tt>flows</tt>.
	 * 
	 * @return property value of flows
	 */
	public List<Flow> getFlows() {
		return flows;
	}

	/**
	 * Setter method for property <tt>dbSetDst</tt>.
	 * 
	 * @param dbSetDst
	 *            value to be assigned to property dbSetDst
	 */
	public void setDbSetDst(DBConfig dbSetDst) {
		this.dbSetDst = dbSetDst;
	}

	/**
	 * Setter method for property <tt>dbSetSrc</tt>.
	 * 
	 * @param dbSetSrc
	 *            value to be assigned to property dbSetSrc
	 */
	public void setDbSetSrc(DBConfig dbSetSrc) {
		this.dbSetSrc = dbSetSrc;
	}

	/**
	 * Setter method for property <tt>flows</tt>.
	 * 
	 * @param flows
	 *            value to be assigned to property flows
	 */
	public void setFlows(List<Flow> flows) {
		this.flows = flows;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransferModel [dbSetSrc=");
		builder.append(dbSetSrc);
		builder.append(", dbSetDst=");
		builder.append(dbSetDst);
		builder.append(", flows=");
		builder.append(flows);
		builder.append("]");
		return builder.toString();
	}

}
