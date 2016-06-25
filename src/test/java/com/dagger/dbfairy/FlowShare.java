/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.dbfairy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.dagger.tools.dbutil.DBConfig;
import com.dagger.tools.dbutil.DBType;
import com.dagger.transfer.model.ConfigEnvelope;
import com.dagger.transfer.model.Flow;
import com.dagger.transfer.model.HandleTool;
import com.dagger.transfer.model.SqlDetail;
import com.dagger.transfer.model.TransferModel;

/**
 * 
 * @author 马洪良
 * @version $Id: FlowShare.java, v 0.1 2014年1月26日 下午4:11:02 马洪良 Exp $
 */
public final class FlowShare {

	@Test
	public void testShare() {
		TransferModel TransferModel = new TransferModel();
		DBConfig dbSetSrc = new DBConfig();
		dbSetSrc.setAlias("aliyunDb");
		dbSetSrc.setConnectionUrl("jdbc:mysql://10.125.224.24:3306/odbsyunying?uerUnicode=true&characterEncoding=gbk");
		dbSetSrc.setDriverClass(DBType.MYSQL);
		dbSetSrc.setUsername("odbsyunying");
		dbSetSrc.setPassword("odbsyunying");
		TransferModel.setDbSetSrc(dbSetSrc);

		DBConfig dbSetDst = new DBConfig();
		dbSetDst.setAlias("linyunDb");
		dbSetDst.setConnectionUrl("jdbc:oracle:thin:bss2@10.232.31.123:1521:huijin");
		dbSetDst.setDriverClass(DBType.ORACLE);
		dbSetDst.setUsername("bss2");
		dbSetDst.setPassword("bss2");
		TransferModel.setDbSetDst(dbSetDst);

		Flow oneFlow = new Flow();
		oneFlow.setDescritption("测试比较所有的已经支付的订单");
		oneFlow.setGroovyDir("D:/selfCode/DbFairy/demo");
		oneFlow.setGroovyName("test.groovy");
		oneFlow.setHandleTool(HandleTool.COUNT);
		oneFlow.setUserShare(true);

		SqlDetail sqlShare = new SqlDetail();
		sqlShare.setDbAalias("aliyunDb");
		sqlShare.setSql("select tc.order_id  from `order` tc where  tc.order_status=2");

		SqlDetail sqlsrc = new SqlDetail();
		sqlsrc.setDbAalias("aliyunDb");
		sqlsrc.setSql("select *  from order_line  where order_id=?  and order_status=?");
		List<Object[]> sqlValues = new ArrayList<Object[]>();
		sqlValues.add(new Object[] { "&get(order_id)", 2 });
		sqlsrc.setSqlValues(sqlValues);

		SqlDetail sqlDst = new SqlDetail();
		sqlDst.setDbAalias("linyunDb");
		sqlDst.setSql("select *  from upp_biz_order tc where tc.parent_id=?");
		List<Object[]> sqlNValues = new ArrayList<Object[]>();
		sqlNValues.add(new Object[] { "&get(order_id)" });
		sqlDst.setSqlValues(sqlNValues);

		oneFlow.setSqlSrc(sqlsrc);
		oneFlow.setSqlDst(sqlDst);
		oneFlow.setSqlShare(sqlShare);

		List<Flow> flows = new ArrayList<Flow>();
		flows.add(oneFlow);
		TransferModel.setFlows(flows);

		String xml = ConfigEnvelope.formatDetail(TransferModel);
		System.out.println(xml);
		System.out.println(JSON.toJSONString(ConfigEnvelope.getDetailMsg(xml)));
	}
}
