/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dagger.tools.common.LoggerUtil;
import com.dagger.tools.dbutil.DBConfig;
import com.dagger.tools.dbutil.DbToolUtil;
import com.dagger.tools.util.GroovyUtils;
import com.dagger.transfer.exception.AssertUtil;
import com.dagger.transfer.exception.DbFairException;
import com.dagger.transfer.exception.code.SystemErrorCode;
import com.dagger.transfer.model.Flow;
import com.dagger.transfer.model.SqlDetail;
import com.dagger.transfer.process.IGroovyEngine;

/**
 * 
 * @author 马洪良
 * @version $Id: MainHandle.java, v 0.1 2014年1月21日 下午8:49:47 马洪良 Exp $
 */
public final class CountHandle extends ComponetHandler {

	/**
	 * @see com.dagger.transfer.component.ComponetHandler#process(com.dagger.transfer.model.Flow,
	 *      com.dagger.transfer.component.TransferData)
	 */
	@Override
	protected void process(TransferData localTransferData,
			TransferResult transferResult) {
		transferResult.setDescription(localTransferData.getFlow()
				.getDescritption());
		try {
			Flow flowDate = localTransferData.getFlow();
			AssertUtil.isNotNull(flowDate, SystemErrorCode.INPUT_PARA_ISNULL);

			SqlDetail sqlDetailSrc = flowDate.getSqlSrc();
			AssertUtil.isNotNull(sqlDetailSrc,
					SystemErrorCode.INPUT_PARA_ISNULL);

			SqlDetail sqlDetailDst = flowDate.getSqlDst();
			AssertUtil.isNotNull(sqlDetailDst,
					SystemErrorCode.INPUT_PARA_ISNULL);
			String groovyDir = flowDate.getGroovyDir() + "/"
					+ flowDate.getGroovyName();
			LoggerUtil.info(logger, "开始处理公共部分的业务逻辑");
			// 执行第一个sql语句
			List<Object[]> sqlsrcValues = sqlDetailSrc.getSqlValues();
			List<Integer> srcCount = new ArrayList<Integer>(sqlsrcValues.size());
			Map<String, DBConfig> connMap = localTransferData.getConnMap();
			transferResult.setSqlSrc(sqlDetailSrc.getSql());
			// 第一个执行结果
			for (Object[] sqlValue : sqlsrcValues) {
				srcCount.add(DbToolUtil.getDbCount(
						connMap.get(sqlDetailSrc.getDbAalias()),
						sqlDetailSrc.getSql(), sqlValue));
			}
			List<Object[]> sqlDstValues = sqlDetailDst.getSqlValues();
			List<Integer> dstCount = new ArrayList<Integer>(sqlDstValues.size());
			transferResult.setSqlDst(sqlDetailDst.getSql());
			// 第二个执行结果
			for (Object[] objects : sqlDstValues) {
				dstCount.add(DbToolUtil.getDbCount(
						connMap.get(sqlDetailDst.getDbAalias()),
						sqlDetailDst.getSql(), objects));
			}
			// 执行脚本
			try {
				IGroovyEngine groovyObj = (IGroovyEngine) GroovyUtils
						.getGroovyObject(groovyDir);
				List<Boolean> bReturnList = groovyObj.excuseIntGroovy(srcCount,
						dstCount);
				List<String> listError = new ArrayList<String>();
				List<String> listSucss = new ArrayList<String>();
				int iCount = 0;
				for (Boolean bReturn : bReturnList) {
					if (bReturn) {
						listSucss.add("比较成功" + "原始的sql参数"
								+ sqlsrcValues.get(iCount).toString()
								+ "比较目标的sql参数" + dstCount.get(iCount));
					} else {
						listError.add("比较失败" + "原始的sql参数"
								+ sqlsrcValues.get(iCount).toString()
								+ "比较目标的sql参数" + dstCount.get(iCount));
					}
					iCount++;
				}
				transferResult.setListError(listError);
				transferResult.setListSucss(listSucss);
				transferResult.setbGood(true);
			} catch (Exception e) {
				LoggerUtil.warn(logger, "执行groovy脚本过程中发生异常");
				throw new DbFairException(SystemErrorCode.GROOVY_START_ERROR);
			}

		} catch (DbFairException e) {
			transferResult.setbGood(false);
			transferResult.setErrorCode(e.getErrorCode());
		}
	}
}
