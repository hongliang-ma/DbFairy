/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dagger.tools.common.LoggerUtil;
import com.dagger.tools.lang.AliStringUtil;
import com.dagger.transfer.component.ComponetFactory;
import com.dagger.transfer.component.TransferResult;
import com.dagger.transfer.exception.AssertUtil;
import com.dagger.transfer.exception.code.SystemErrorCode;
import com.dagger.transfer.model.ConfigEnvelope;
import com.dagger.transfer.util.CreateHtml;

/**
 * 
 * @author 马洪良
 * @version $Id: MainEntry.java, v 0.1 2014年1月26日 下午5:18:00 马洪良 Exp $
 */
public final class MainEntry {

	private static final Logger logger = LoggerFactory
			.getLogger(MainEntry.class);

	private static RouterEngine routerEngine;

	private static void HintLine() {
		System.out.println("请输入需要待比较的文件，格式为:");
		System.out.println("如果只是一个xml：-Dxmlfile=test.xml");
		System.out
				.println("如果要比较多个xml，格式为：-Dxmlfile=test.xml -Dxmlfile=test2.xml ....");
		System.out.println("如果数据的来源是一个csv，格式为：-Dcsvfile=test.csv");
	}

	private static void initStart() {
		ComponetFactory.startInit();
		if (null == routerEngine) {
			routerEngine = new RouterEngineImpl();
		}
	}

	public static void main(String[] args) throws Exception {
		// 初始化操作
		initStart();

		// 进行参数读取
		readPara(args);
	}

	private static void readPara(final String[] args) {
		List<String> input = Arrays.asList(args);
		int iCont = 0;
		String realFile = null;
		String line = null;
		List<TransferResult> listTotalResult = new ArrayList<TransferResult>();
		for (String files : input) {
			if (AliStringUtil.contains(files, "Dxmlfile")
					&& AliStringUtil.contains(files, ".xml")) {
				realFile = AliStringUtil.substringAfter(files, "Dxmlfile=");
				LoggerUtil.info(logger, "开始执行xml参数", realFile);
				try {
					line = IOUtils.toString(new FileInputStream(new File(
							realFile)));
				} catch (FileNotFoundException e) {
					logger.error("文件打开失败", e);
				} catch (IOException e) {
					logger.error("文件读写错误", e);
				}
				AssertUtil.isTrue(AliStringUtil.isNotEmpty(line),
						SystemErrorCode.INPUT_PARA_ISNULL);

				try {
					List<TransferResult> listResult = routerEngine.route(
							ConfigEnvelope.getDetailMsg(line), true);
					listTotalResult.addAll(listResult);
					LoggerUtil.info(logger, "执行完毕一个脚本");

				} catch (Exception e) {
					LoggerUtil.warn(logger, "出现未知异常，异常为", e);
					return;
				}

			} else if (AliStringUtil.contains(files, "Dcsvfile")
					&& AliStringUtil.contains(files, ".csv")) {
				realFile = AliStringUtil.substringAfter(files, "Dcsvfile=");
				LoggerUtil.info(logger, "开始执行cvs的文件内容", realFile);
			} else {
				iCont++;
			}
		}
		if (iCont == input.size()) {
			HintLine();
		} else {
			// 进行结果报告
			reportResult(listTotalResult);
			LoggerUtil.info(logger, "运行结束");
		}
	}

	/**
	 * 
	 * @param listResult
	 */
	private static void reportResult(final List<TransferResult> listResult) {
		CreateHtml.startCreate(listResult);
	}

}
