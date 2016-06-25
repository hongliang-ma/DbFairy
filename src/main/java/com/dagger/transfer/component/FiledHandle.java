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
 * @version $Id: FiledComHandle.java, v 0.1 2014年1月23日 上午12:04:49 马洪良 Exp $
 */
public final class FiledHandle extends ComponetHandler {

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

			// 检测脚本是否合法
			String groovyDir = flowDate.getGroovyDir() + "/"
					+ flowDate.getGroovyName();

			LoggerUtil.info(logger, "开始处理公共部分的业务逻辑");
			// 执行第一个sql语句
			List<Object[]> sqlsrcValues = sqlDetailSrc.getSqlValues();
			List<Map<String, Object>> srcList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> dstList = new ArrayList<Map<String, Object>>();
			Map<String, DBConfig> connMap = localTransferData.getConnMap();
			transferResult.setSqlSrc(sqlDetailSrc.getSql());

			List<Object[]> sqlDstValues = sqlDetailDst.getSqlValues();
			// 第二个执行结果
			transferResult.setSqlDst(sqlDetailDst.getSql());
			int iRount = 0;

			IGroovyEngine groovyObj = null;
			try {
				groovyObj = (IGroovyEngine) GroovyUtils
						.getGroovyObject(groovyDir);
			} catch (Exception e) {
				LoggerUtil.warn(logger, "执行groovy脚本过程中发生异常");
				throw new DbFairException(SystemErrorCode.GROOVY_START_ERROR);
			}

			List<Boolean> bReturnList = new ArrayList<Boolean>();
			List<String> listError = new ArrayList<String>();
			List<String> listSus = new ArrayList<String>();
			for (Object[] sqlValue : sqlsrcValues) {
				srcList.clear();
				srcList = DbToolUtil.getDbDateToMapList(
						connMap.get(sqlDetailSrc.getDbAalias()),
						sqlDetailSrc.getSql(), sqlValue);

				dstList.clear();
				dstList = DbToolUtil.getDbDateToMapList(
						connMap.get(sqlDetailDst.getDbAalias()),
						sqlDetailDst.getSql(), sqlDstValues.get(iRount));
				bReturnList.clear();
				bReturnList = groovyObj.excuseListMapGroovy(srcList, dstList);

				for (Boolean bReturn : bReturnList) {
					if (bReturn) {
						listSus.add("比较成功" + "原始的sql参数" + sqlValue.toString()
								+ "比较目标的sql参数" + sqlDstValues.get(iRount));
					} else {
						listError.add("比较失败" + "原始的sql参数" + sqlValue.toString()
								+ "比较目标的sql参数" + sqlDstValues.get(iRount));
					}
				}
				iRount++;
			}
			transferResult.setListError(listError);
			transferResult.setbGood(true);
		} catch (DbFairException e) {
			transferResult.setbGood(false);
			transferResult.setErrorCode(e.getErrorCode());
		}
	}
}
