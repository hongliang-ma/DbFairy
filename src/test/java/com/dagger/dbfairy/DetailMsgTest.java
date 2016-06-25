/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.dbfairy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.dagger.transfer.model.ConfigEnvelope;
import com.dagger.transfer.model.TransferModel;

/**
 * 
 * @author 马洪良
 * @version $Id: DetailMsgTest.java, v 0.1 2014年1月27日 下午2:26:14 马洪良 Exp $
 */
public class DetailMsgTest {
	@Test
	public void testMsg() {

		try {
			String line = IOUtils.toString(new FileInputStream(new File(
					"D:/test/test.xml")));
			TransferModel transferModel = ConfigEnvelope.getDetailMsg(line);
			System.out.println(JSON.toJSONString(transferModel));
		} catch (FileNotFoundException e) {
			System.err.println("文件不存在");
		} catch (IOException e) {
			System.err.println("文件读取错误");
		}
	}
}
