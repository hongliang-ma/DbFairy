/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.component;

import java.util.Map;

import com.dagger.tools.dbutil.DBConfig;
import com.dagger.transfer.model.Flow;

/**
 * 
 * @author 马洪良
 * @version $Id: TransferData.java, v 0.1 2014年1月21日 下午7:23:45 马洪良 Exp $
 */
public final class TransferData {
	private Map<String, DBConfig> connMap;

	private Flow flow;

	/**
	 * Getter method for property <tt>connMap</tt>.
	 * 
	 * @return property value of connMap
	 */
	public Map<String, DBConfig> getConnMap() {
		return connMap;
	}

	/**
	 * Getter method for property <tt>flow</tt>.
	 * 
	 * @return property value of flow
	 */
	public Flow getFlow() {
		return flow;
	}

	/**
	 * Setter method for property <tt>connMap</tt>.
	 * 
	 * @param connMap
	 *            value to be assigned to property connMap
	 */
	public void setConnMap(Map<String, DBConfig> connMap) {
		this.connMap = connMap;
	}

	/**
	 * Setter method for property <tt>flow</tt>.
	 * 
	 * @param flow
	 *            value to be assigned to property flow
	 */
	public void setFlow(Flow flow) {
		this.flow = flow;
	}

}
