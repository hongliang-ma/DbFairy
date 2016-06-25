/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dagger.tools.dbutil.DBConfig;
import com.dagger.transfer.util.CustomConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @author 马洪良
 * @version $Id: SqlConfigEnvelope.java, v 0.1 2014年1月13日 下午4:19:18 马洪良 Exp $
 */
public final class ConfigEnvelope {

	private final static XStream xstream = new XStream(new DomDriver());

	private static void configBefore() {
		xstream.registerConverter(new CustomConverter());
		xstream.alias("fairDbConfig", TransferModel.class);

		xstream.aliasField("dbSetSrc", TransferModel.class, "dbSetSrc");
		xstream.aliasField("dbSetDst", TransferModel.class, "dbSetDst");
		xstream.useAttributeFor(DBConfig.class, "alias");
		xstream.useAttributeFor(DBConfig.class, "driverClass");
		xstream.useAttributeFor(DBConfig.class, "connectionUrl");
		xstream.useAttributeFor(DBConfig.class, "username");
		xstream.useAttributeFor(DBConfig.class, "password");
		xstream.useAttributeFor(DBConfig.class, "schema");

		xstream.aliasField("flows", TransferModel.class, "flows");

		xstream.alias("flow", Flow.class);
		xstream.aliasField("sqlShare", Flow.class, "sqlShare");
		xstream.aliasField("sqlSrc", Flow.class, "sqlSrc");
		xstream.aliasField("sqlDst", Flow.class, "sqlDst");
		xstream.useAttributeFor(Flow.class, "descritption");
		xstream.useAttributeFor(Flow.class, "handleTool");
		xstream.useAttributeFor(Flow.class, "userShare");
		xstream.useAttributeFor(Flow.class, "groovyDir");
		xstream.useAttributeFor(Flow.class, "groovyName");

		xstream.alias("SqlDetail", SqlDetail.class);
	}

	/**
	 * 将一个DbTransferModel设置成XML
	 * 
	 * @param detailMsg
	 * @return XML保存到TemplateDetail的detailMsg
	 */
	public static String formatDetail(TransferModel dbTransferModel) {
		configBefore();

		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ xstream.toXML(dbTransferModel);
	}

	/**
	 * 从XMl字符串读到DbTransferModel
	 * 
	 * @param strXml
	 * @return DetailMsg
	 */
	public static TransferModel getDetailMsg(String strXml) {
		configBefore();

		return (TransferModel) xstream.fromXML(strXml);
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
