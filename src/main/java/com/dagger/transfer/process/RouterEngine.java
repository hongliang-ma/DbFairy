/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.process;

import java.util.List;

import com.dagger.transfer.component.TransferResult;
import com.dagger.transfer.model.TransferModel;

/**
 * 
 * @author 马洪良
 * @version $Id: RoutingEngine.java, v 0.1 2014年1月13日 下午8:10:43 马洪良 Exp $
 */
public interface RouterEngine {

	/**
	 * 接收消息，处理后返回
	 * 
	 * @param description
	 *            传入的消息信封
	 * @param isContiue
	 *            发生错误之后，是否继续往下处理
	 * 
	 * @return
	 */
	public List<TransferResult> route(TransferModel description,
			boolean isContiue);
}
