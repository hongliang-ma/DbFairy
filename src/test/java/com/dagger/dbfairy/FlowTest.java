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
 * @version $Id: FlowTest.java, v 0.1 2014年1月26日 下午2:43:42 马洪良 Exp $
 */
public final class FlowTest {
	@Test
	public void testNormal() {
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
		oneFlow.setDescritption("测试同一个主订单下有子订单的数量");
		oneFlow.setGroovyDir("D:/selfCode/DbFairy/demo");
		oneFlow.setGroovyName("test.groovy");
		oneFlow.setHandleTool(HandleTool.COUNT);

		SqlDetail sqlsrc = new SqlDetail();
		sqlsrc.setDbAalias("aliyunDb");
		sqlsrc.setSql("select count(*) from order_line tc where tc.order_id=?");
		List<Object[]> sqlValues = new ArrayList<Object[]>();
		sqlValues.add(new Object[] { "201312310349901177" });
		sqlValues.add(new Object[] { "201312310349901230" });
		sqlValues.add(new Object[] { "201312310349901339" });
		sqlValues.add(new Object[] { "201312310349901464" });
		sqlValues.add(new Object[] { "201312310349901854" });
		sqlsrc.setSqlValues(sqlValues);

		SqlDetail sqlDst = new SqlDetail();
		sqlDst.setDbAalias("linyunDb");
		sqlDst.setSql("select count(*)  from upp_biz_order tc where tc.parent_id=?");
		sqlDst.setSqlValues(sqlValues);

		oneFlow.setSqlSrc(sqlsrc);
		oneFlow.setSqlDst(sqlDst);

		Flow secondFlow = new Flow();
		secondFlow.setDescritption("测试同一个主订单的订单总价是否正确");
		secondFlow.setGroovyDir("D:/selfCode/DbFairy/demo");
		secondFlow.setGroovyName("testPrice.groovy");
		secondFlow.setHandleTool(HandleTool.FILEDHANDLE);

		SqlDetail sqlPsrc = new SqlDetail();
		sqlPsrc.setDbAalias("aliyunDb");
		sqlPsrc.setSql("select tc.pay_amount from tc.order_id=? and tc.order_status=?");
		List<Object[]> sqlPValues = new ArrayList<Object[]>();
		sqlPValues.add(new Object[] { "201312310349901177", 2 });
		sqlPValues.add(new Object[] { "201312310349901230", 2 });
		sqlPValues.add(new Object[] { "201312310349901339", 2 });
		sqlPValues.add(new Object[] { "201312310349901464", 2 });
		sqlPValues.add(new Object[] { "201312310349901854", 2 });
		sqlPsrc.setSqlValues(sqlPValues);

		SqlDetail sqlPDst = new SqlDetail();
		sqlPDst.setDbAalias("linyunDb");
		sqlPDst.setSql("select tc.total_pay_fee from upp_biz_order tc where tc.outer_trade_code=? and tc.order_status=?");
		List<Object[]> sqlPNValues = new ArrayList<Object[]>();
		sqlPNValues.add(new Object[] { "201312310349901177", 2 });
		sqlPNValues.add(new Object[] { "201312310349901230", 2 });
		sqlPNValues.add(new Object[] { "201312310349901339", 2 });
		sqlPNValues.add(new Object[] { "201312310349901464", 2 });
		sqlPNValues.add(new Object[] { "201312310349901854", 2 });
		sqlPDst.setSqlValues(sqlPNValues);

		secondFlow.setSqlSrc(sqlPsrc);
		secondFlow.setSqlDst(sqlPDst);

		List<Flow> flows = new ArrayList<Flow>();
		flows.add(oneFlow);
		flows.add(secondFlow);
		TransferModel.setFlows(flows);

		String xml = ConfigEnvelope.formatDetail(TransferModel);
		System.out.println(xml);

		System.out.println(JSON.toJSONString(ConfigEnvelope.getDetailMsg(xml)));
	}

}
