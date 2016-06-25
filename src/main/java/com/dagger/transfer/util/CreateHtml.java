/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dagger.transfer.component.TransferResult;

/**
 * 
 * @author 马洪良
 * @version $Id: CreateHtml.java, v 0.1 2014年1月27日 下午5:44:20 马洪良 Exp $
 */
public final class CreateHtml {
	private static final Logger logger = LoggerFactory
			.getLogger(CreateHtml.class);

	public static Boolean startCreate(List<TransferResult> listResult) {
		Boolean bCreate = false;
		// 生成html页面
		try {
			Template template = Velocity.getTemplate(
					"/src/main/resources/reprot.vm", "UTF-8");
			// 初始化上下文
			VelocityContext context = new VelocityContext();
			context.put("title", "数据迁移测试结果报告");
			context.put("listResult", listResult);
			PrintWriter pw = new PrintWriter("D:/TransferReport.html");
			template.merge(context, pw);
			pw.close();
			bCreate = true;
		} catch (FileNotFoundException e) {
			logger.error("vm文件或则报告生成的位置错误", e);
		}
		return bCreate;
	}
}
