/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.model;

import java.util.List;

/**
 * 一个具体的sql语句
 * 
 * @author 马洪良
 * @version $Id: SqlDetailConfig.java, v 0.1 2014年1月13日 下午3:49:47 马洪良 Exp $
 */
public final class SqlDetail {

	/**
	 * 数据库的别名
	 */
	public String dbAalias;
	/**
	 * 准备执行的sql语句
	 */
	public String sql;
	/**
	 * 填充sql查询部分的变量，List的目的是为了可以执行sql批量查询
	 */
	public List<Object[]> sqlValues;

	/**
	 * Getter method for property <tt>dbAalias</tt>.
	 * 
	 * @return property value of dbAalias
	 */
	public String getDbAalias() {
		return dbAalias;
	}

	/**
	 * Getter method for property <tt>sql</tt>.
	 * 
	 * @return property value of sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * Getter method for property <tt>sqlValues</tt>.
	 * 
	 * @return property value of sqlValues
	 */
	public List<Object[]> getSqlValues() {
		return sqlValues;
	}

	/**
	 * Setter method for property <tt>dbAalias</tt>.
	 * 
	 * @param dbAalias
	 *            value to be assigned to property dbAalias
	 */
	public void setDbAalias(String dbAalias) {
		this.dbAalias = dbAalias;
	}

	/**
	 * Setter method for property <tt>sql</tt>.
	 * 
	 * @param sql
	 *            value to be assigned to property sql
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * Setter method for property <tt>sqlValues</tt>.
	 * 
	 * @param sqlValues
	 *            value to be assigned to property sqlValues
	 */
	public void setSqlValues(List<Object[]> sqlValues) {
		this.sqlValues = sqlValues;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SqlDetail [dbAalias=" + dbAalias + ", sql=" + sql
				+ ", sqlValues=" + sqlValues + "]";
	}

}
