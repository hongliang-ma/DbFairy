/**
 * Blade.com.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.dagger.transfer.util;

import java.util.ArrayList;
import java.util.List;

import com.dagger.tools.lang.AliStringUtil;
import com.dagger.transfer.model.SqlDetail;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * 
 * @author hongliang.ma
 * @version $Id: MapCustomConverter.java, v 0.1 2012-6-19 下午3:12:22 hongliang.ma
 *          Exp $
 */
public final class CustomConverter implements Converter {

	public CustomConverter() {
		super();
	}

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class clazz) {
		String classname = clazz.getName();

		return (classname.indexOf("SqlDetail") >= 0) ? true : false;
	}

	protected void map2xml(Object value, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		SqlDetail sqlValues = (SqlDetail) value;

		writer.addAttribute("dbAalias", sqlValues.getDbAalias());

		writer.startNode("sql");
		writer.setValue(sqlValues.getSql());
		writer.endNode();
		if (null == sqlValues.getSqlValues()) {
			return;
		}
		for (Object[] objects : sqlValues.getSqlValues()) {
			if (null == objects) {
				continue;
			}
			writer.startNode("sqlPara");
			for (Object object : objects) {
				writer.setValue("($" + object + "$)");
			}
			writer.endNode();
		}
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer,
			MarshallingContext context) {

		map2xml(value, writer, context);
	}

	protected SqlDetail populateMap(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		SqlDetail sqlDetail = new SqlDetail();
		List<Object[]> listObj = new ArrayList<Object[]>();
		StringBuilder sbfParam = new StringBuilder();
		String paraDeTail;
		String paraDeTailListS;
		sqlDetail.setDbAalias(reader.getAttribute("dbAalias"));
		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if (AliStringUtil.equals("sql", reader.getNodeName())) {
				sqlDetail.setSql(reader.getValue());
			}

			if (AliStringUtil.equals("sqlPara", reader.getNodeName())) {
				String sqlPares = reader.getValue();

				if (AliStringUtil.isNotEmpty(sqlPares)) {
					int iCount = AliStringUtil.countMatches(sqlPares, "($");
					Object[] objList = new Object[iCount];
					int iMove = 0;
					sbfParam.append(sqlPares);
					while (AliStringUtil.contains(sbfParam.toString(), "($")) {
						paraDeTail = AliStringUtil.replaceOnce(
								AliStringUtil.substringBefore(
										sbfParam.toString(), "$)"), "($", "");
						if (AliStringUtil.isNotBlank(paraDeTail)) {
							objList[iMove] = paraDeTail;
						}
						paraDeTailListS = AliStringUtil.substringAfter(
								sbfParam.toString(), "$)");
						sbfParam.setLength(0);
						sbfParam.append(paraDeTailListS);
						iMove++;
					}
					listObj.add(objList);
				}
			}
			reader.moveUp();
		}
		sqlDetail.setSqlValues(listObj);

		return sqlDetail;
	}

	@Override
	public SqlDetail unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		SqlDetail map = populateMap(reader, context);

		return map;
	}
}
