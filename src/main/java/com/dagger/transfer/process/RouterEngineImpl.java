/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dagger.tools.common.ExceptionUtil;
import com.dagger.tools.common.LoggerUtil;
import com.dagger.tools.dbutil.DBConfig;
import com.dagger.tools.dbutil.DbToolUtil;
import com.dagger.tools.lang.AliStringUtil;
import com.dagger.transfer.component.ComponetFactory;
import com.dagger.transfer.component.TransferData;
import com.dagger.transfer.component.TransferResult;
import com.dagger.transfer.exception.DbFairException;
import com.dagger.transfer.exception.code.FlowErrorCode;
import com.dagger.transfer.model.Flow;
import com.dagger.transfer.model.HandleTool;
import com.dagger.transfer.model.SqlDetail;
import com.dagger.transfer.model.TransferModel;

/**
 * 
 * @author 马洪良
 * @version $Id: RoutingEngineImpl.java, v 0.1 2014年1月21日 下午3:54:11 马洪良 Exp $
 */

public class RouterEngineImpl implements RouterEngine {
	private static final Logger logger = LoggerFactory
			.getLogger(RouterEngineImpl.class);

	/**
	 * 如果设置了shardValue，则更改sql的参数
	 * 
	 * @param connMap
	 * @param flow
	 */
	private void changeParaMeter(final Map<String, DBConfig> connMap, Flow flow) {
		// 获取公共参数的所有值
		SqlDetail sqlShare = flow.getSqlShare();
		List<Object[]> listValues = sqlShare.getSqlValues();
		List<Map<String, Object>> listMapTemp = new ArrayList<Map<String, Object>>();
		// 保存所有的公共参数的结果
		List<Map<String, Object>> listMapReal = new ArrayList<Map<String, Object>>();
		if (null != listValues) {
			for (Object[] objects : listValues) {
				// 清空临时内容
				listMapTemp.clear();
				// 执行sql，获取结果
				listMapTemp = DbToolUtil.getDbDateToMapList(
						connMap.get(sqlShare.getDbAalias()), sqlShare.getSql(),
						objects);
				// 将所有的结果保存在列表中
				listMapReal.addAll(listMapTemp);
			}
		} else {
			listMapReal = DbToolUtil.getDbDateToMapList(
					connMap.get(sqlShare.getDbAalias()), sqlShare.getSql(),
					null);
		}
		// 生成新的src vaue值
		flow.getSqlSrc().setSqlValues(
				getNewParam(flow.getSqlSrc(), listMapReal));
		// 生成新的dst的value值
		flow.getSqlDst().setSqlValues(
				getNewParam(flow.getSqlDst(), listMapReal));
	}

	/**
	 * 组装新的sql的vaue值
	 * 
	 * @param flow
	 * @param listMapReal
	 * @param listNewValue
	 * @return
	 */
	private List<Object[]> getNewParam(final SqlDetail sqlDetail,
			final List<Map<String, Object>> listMapReal) {
		List<Object[]> listNewValue = new ArrayList<Object[]>();

		for (Map<String, Object> map : listMapReal) {
			// 替换掉原始数据的value,只进行第一条的组装，记住这个规则
			Object[] listSrcValues = sqlDetail.getSqlValues().get(0);

			int iTotalSize = listMapReal.size();
			for (int i = 0; i < iTotalSize; i++) {
				// 临时内容的存储
				Object[] ojbComTemp = new Object[listSrcValues.length];
				int iCount = 0;
				// 寻找含有字段请求替换的
				for (Object object : listSrcValues) {
					if (AliStringUtil.contains(object.toString(), "&get")) {
						ojbComTemp[iCount] = map.get(AliStringUtil
								.substringBetween(object.toString(), "get(",
										")"));
					} else {
						ojbComTemp[iCount] = object;
					}
					iCount++;
				}
				listNewValue.add(ojbComTemp);
				ojbComTemp = null;
			}
		}
		return listNewValue;
	}

	/**
	 * @see com.dagger.transfer.process.RouterEngine#route(com.dagger.transfer.model.TransferModel,
	 *      boolean)
	 */
	@Override
	public List<TransferResult> route(TransferModel tranferModel,
			boolean isContiue) {
		TransferData localTransferData = new TransferData();
		List<TransferResult> transfers = new ArrayList<TransferResult>();
		try {
			RouterCheck.InputParaCheck(tranferModel);
			Map<String, DBConfig> connMap = new HashMap<String, DBConfig>();

			connMap.put(tranferModel.getDbSetSrc().getAlias(),
					tranferModel.getDbSetSrc());
			connMap.put(tranferModel.getDbSetDst().getAlias(),
					tranferModel.getDbSetDst());
			localTransferData.setConnMap(connMap);
			HandleTool handTools = null;

			LoggerUtil.info(logger, "测试链接正常,开始循环处理工具类");
			for (Flow flow : tranferModel.getFlows()) {
				if (null == flow) {
					continue;
				}
				TransferResult transferResult = new TransferResult();
				transferResult.setDescription(flow.getDescritption());

				try {
					RouterCheck.AssertFlow(tranferModel, flow);
					handTools = flow.getHandleTool();
					// 如果使用指定公共参数，则需要公共参数的填写测试
					if (flow.getUserShare()) {
						changeParaMeter(connMap, flow);
					}
					localTransferData.setFlow(flow);
					LoggerUtil.info(logger, "开始处理flow流程，流程描述为",
							flow.getDescritption(), "处理工具为",
							handTools.getHandleName());

					ComponetFactory.componetHandler(handTools.getHandleName(),
							localTransferData, transferResult);
					transfers.add(transferResult);
				} catch (DbFairException e) {
					ExceptionUtil.caught(e, "出现异常，错误原因为", e.getErrorCode());
					transferResult.setbGood(false);
					transferResult.setErrorCode(e.getErrorCode());
					transferResult.setSqlDst(flow.getSqlSrc().getSql());
					transferResult.setSqlDst(flow.getSqlDst().getSql());
					if (!isContiue) {
						throw new DbFairException(
								FlowErrorCode.COMPONENT_HANDLE_ERROR);
					}
					transfers.add(transferResult);
				}
			}
		} catch (DbFairException e) {
			TransferResult transferResult = new TransferResult();
			transferResult.setbGood(false);
			transferResult.setErrorCode(e.getErrorCode());
			transfers.add(transferResult);
			ExceptionUtil.caught(e, "出现异常，错误原因为", e.getErrorCode());
		}

		return transfers;
	}
}
