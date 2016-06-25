/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.process;

import java.sql.Connection;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dagger.tools.common.ExceptionUtil;
import com.dagger.tools.common.LoggerUtil;
import com.dagger.tools.dbutil.DBConnetTool;
import com.dagger.tools.util.GroovyUtils;
import com.dagger.transfer.exception.AssertUtil;
import com.dagger.transfer.exception.DbFairException;
import com.dagger.transfer.exception.code.FlowErrorCode;
import com.dagger.transfer.exception.code.InitiErrorCode;
import com.dagger.transfer.exception.code.SystemErrorCode;
import com.dagger.transfer.model.Flow;
import com.dagger.transfer.model.SqlDetail;
import com.dagger.transfer.model.TransferModel;

/**
 * 
 * @author 马洪良
 * @version $Id: RouterCheck.java, v 0.1 2014年1月22日 下午4:21:54 马洪良 Exp $
 */
public final class RouterCheck {
	private static final Logger logger = LoggerFactory
			.getLogger(RouterCheck.class);

	/**
	 * flow的参数经常
	 * 
	 * @param tranferModel
	 * @param flow
	 */
	public static void AssertFlow(TransferModel tranferModel, Flow flow) {
		SqlDetail sqlSrc = flow.getSqlSrc();
		SqlDetail sqlDst = flow.getSqlDst();

		AssertUtil.isNotNull(flow.getHandleTool(),
				FlowErrorCode.HANDLE_NOTEXIS__FAIELD);
		// 检查2个sql对象
		AssertUtil.isNotNull(sqlSrc, FlowErrorCode.SQLFIRST_NOT_EXITS);
		AssertUtil.isNotNull(sqlDst, FlowErrorCode.SQLSECOND_NOT_EXITS);

		// 检查数据库的别名
		AssertUtil.isNotNull(sqlSrc.getDbAalias(),
				FlowErrorCode.FIRST_ALIAS_NOT_EXISE);

		AssertUtil
				.isTrue(StringUtils.equals(sqlSrc.getDbAalias(), tranferModel
						.getDbSetSrc().getAlias()),
						FlowErrorCode.FIRST_ALIAS_NOT_EQUAL);

		AssertUtil.isNotNull(sqlDst.getDbAalias(),
				FlowErrorCode.SECOND_ALIAS_NOT_EXISE);

		AssertUtil.isTrue(StringUtils.equals(sqlDst.getDbAalias(), tranferModel
				.getDbSetDst().getAlias()),
				FlowErrorCode.SECOND_ALIAS_NOT_EQUAL);

		// 检查sql语句
		AssertUtil.isTrue(StringUtils.isNotEmpty(sqlSrc.getSql()),
				FlowErrorCode.FIRST_SQL_NOT_EXISE);

		AssertUtil.isTrue(StringUtils.isNotEmpty(sqlDst.getSql()),
				FlowErrorCode.SECOND_SQL_NOT_EXISE);

		// 检查groovy
		AssertUtil.isTrue(StringUtils.isNotEmpty(flow.getGroovyDir()),
				InitiErrorCode.NOT_FIND_GROOVYBASE);

		AssertUtil.isTrue(StringUtils.isNotEmpty(flow.getGroovyName()),
				InitiErrorCode.NOT_FIND_GROOVYFILE);

		// 如果设置了共享的参数，需要检查共享参数
		if (flow.getUserShare()) {
			SqlDetail sqlShare = flow.getSqlShare();
			AssertUtil.isNotNull(sqlShare, FlowErrorCode.SHARE_NOT_EXISEE);
			AssertUtil.isNotNull(sqlShare.getDbAalias(),
					FlowErrorCode.SHARE_ALIAS_NOT_EXISE);
			AssertUtil.isNotNull(sqlShare.getSql(),
					FlowErrorCode.SHARE_SQL_NOT_EXISE);
			AssertUtil.isTrue(StringUtils.equals(sqlSrc.getDbAalias(),
					tranferModel.getDbSetSrc().getAlias()),
					FlowErrorCode.SHARE_ALIAS_NOT_EQUAL);
		}

		// 检测脚本是否合法
		String groovyDir = flow.getGroovyDir() + "/" + flow.getGroovyName();
		Boolean bCheck = GroovyUtils.validateGroovyScript(groovyDir);
		if (!bCheck) {
			LoggerUtil.warn(logger, "groovy脚本解析错误,脚本的名字为:", groovyDir);
			throw new DbFairException(SystemErrorCode.GROOVY_START_ERROR);
		}
	}

	/**
	 * 入参检查，并返回一个TransferData
	 * 
	 * @param TransferData
	 * @return
	 */
	public static void InputParaCheck(TransferModel tranferModel) {
		AssertUtil.isNotNull(tranferModel, SystemErrorCode.INPUT_PARA_ISNULL);
		AssertUtil.isNotNull(tranferModel.getDbSetSrc(),
				SystemErrorCode.INPUT_PARA_MISS);
		AssertUtil.isNotNull(tranferModel.getDbSetDst(),
				SystemErrorCode.INPUT_PARA_MISS);

		AssertUtil.notEmpty(tranferModel.getFlows(),
				InitiErrorCode.FLOWS_NOT_EXITS);
		// 测试一下数据库链接是否成功
		LoggerUtil.info(logger, "收到请求，开始处理,测试链接是否正常");
		Connection connection = null, conNext = null;
		try {
			connection = DBConnetTool.getConnection(tranferModel.getDbSetSrc());
			AssertUtil
					.isNotNull(connection, InitiErrorCode.CONNET_FIRST_FAIELD);

			conNext = DBConnetTool.getConnection(tranferModel.getDbSetDst());
			AssertUtil.isNotNull(conNext, InitiErrorCode.CONNET_SECOND_FAIELD);
		} catch (DbFairException e) {
			ExceptionUtil.caught(e, "链接数据库异常");
			throw new DbFairException(e.getErrorCode());
		} finally {
			DBConnetTool.shutDown(connection);
			DBConnetTool.shutDown(conNext);
		}
	}
}
