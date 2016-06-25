/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 马洪良
 * @version $Id: ComponetHandler.java, v 0.1 2014年1月19日 下午8:32:21 马洪良 Exp $
 */
public abstract class ComponetHandler {

	protected static final Logger logger = LoggerFactory
			.getLogger(ComponetHandler.class);

	/**
	 * 基本处理工具，处理从通讯传过来的数据
	 * 
	 * @param localTransferData
	 *            局部数据,系统初始化的时候就完成了
	 * @throws Exception
	 */
	protected abstract void process(TransferData localTransferData,
			TransferResult transferResult);
}
