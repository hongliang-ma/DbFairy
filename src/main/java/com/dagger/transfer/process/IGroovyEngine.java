/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.process;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 马洪良
 * @version $Id: IGroovyEngine.java, v 0.1 2014年1月22日 下午11:46:26 马洪良 Exp $
 */
public interface IGroovyEngine {

	/**
	 * 类型为数量的比较,数量比较的时候需要实现该方法
	 * 
	 * @param srcDate
	 *            原始的测试数据
	 * @param dstDate
	 *            新的测试数据
	 * @return 每个结果都必须返回成功或则失败
	 */
	public List<Boolean> excuseIntGroovy(List<Integer> srcDate,
			List<Integer> dstDate);

	/**
	 * 类型为普通结果的比较,非数量比较的时候，请实现该方法
	 * 
	 * @param srcDate
	 *            原始的测试数据
	 * @param dstDate
	 *            新的测试数据
	 * @return 每个结果都必须返回成功或则失败
	 */
	public List<Boolean> excuseListMapGroovy(List<Map<String, Object>> srcDate,
			List<Map<String, Object>> dstDate);

}
