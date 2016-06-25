/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.dbfairy;

import org.junit.Test;

import com.dagger.transfer.process.MainEntry;

/**
 * 
 * @author 马洪良
 * @version $Id: TestFlowTotal.java, v 0.1 2014年1月26日 下午7:30:18 马洪良 Exp $
 */

public class TestFlowTotal {

	@Test
	public void saveTest() {
		try {
			MainEntry
					.main(new String[] { "-Dxmlfile=D:/selfCode/DbFairy/demo/test.xml" });
		} catch (Exception e) {
			System.err.println("发生错误");
		}
	}
}
