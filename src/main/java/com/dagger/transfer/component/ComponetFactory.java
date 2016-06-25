/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.component;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dagger.tools.common.ExceptionUtil;
import com.dagger.tools.common.LoggerUtil;
import com.dagger.transfer.exception.DbFairException;
import com.dagger.transfer.exception.code.InitiErrorCode;
import com.dagger.transfer.exception.code.SystemErrorCode;

/**
 * 
 * @author 马洪良
 * @version $Id: ComponetFactory.java, v 0.1 2014年1月21日 下午5:48:55 马洪良 Exp $
 */
public final class ComponetFactory {
	/** logger */
	private static final Logger logger = LoggerFactory
			.getLogger(ComponetFactory.class);

	private static Map<String, ComponetHandler> protocolMap = new HashMap<String, ComponetHandler>();

	/**
	 * 所有的工具类注册在此
	 * 
	 */

	static {
		LoggerUtil.info(logger, "ComponetFactory工具开始初始化");
		registerProtocol("CountHandle", CountHandle.class);
		registerProtocol("FiledHandle", FiledHandle.class);
		LoggerUtil.info(logger, "ComponetFactory工具初始化完毕");

	}

	/**
	 * 获取具体的工具类议处理
	 * 
	 * @param <T>
	 * @param protocol
	 *            工具类
	 * @param transferData
	 *            从路由传送过来的数据
	 * @param localTransferData
	 *            该配置本身数据库中的配置
	 * @return
	 * @throws Exception
	 */
	public static void componetHandler(String protocol,
			final TransferData localTransferData, TransferResult transferResult)
			throws DbFairException {
		if (!protocolMap.containsKey(protocol)) {
			throw new DbFairException(InitiErrorCode.CANNOT_FIND_COMPONENT,
					protocol + "不存在");
		}
		protocolMap.get(protocol).process(localTransferData, transferResult);
	}

	/**
	 * 
	 * 注册协议处理器
	 * 
	 * @param <T>
	 * @param protocol
	 * @param clz
	 */
	private static void registerProtocol(String protocol,
			Class<? extends ComponetHandler> clz) {

		try {
			protocolMap.put(protocol, clz.newInstance());
		} catch (InstantiationException e) {
			ExceptionUtil.caught(e, "启动实例失败");
			throw new DbFairException(SystemErrorCode.TOOL_INIT_ERROR);
		} catch (IllegalAccessException e) {
			ExceptionUtil.caught(e, "启动实例IllegalAccessException");
			throw new DbFairException(SystemErrorCode.TOOL_START_ERROR);
		}

		LoggerUtil.info(logger, "注册协议", protocol);
	}

	public static void startInit() {
		LoggerUtil.info(logger, "ComponetFactory  初始化结束");
	}

	/**
	 * 禁用构造函数
	 */
	private ComponetFactory() {
		// 禁用构造函数
	}

}
