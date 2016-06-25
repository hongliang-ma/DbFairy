/**
 * Blade.com.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.dagger.transfer.util;

/**
 * XML转义字符工具类
 * 
 * @author 马洪良
 * @version $Id: XmlUtil.java, v 0.1 2014年1月12日 下午7:20:55 马洪良 Exp $
 */
public final class XmlUtil {
	/**
	 * 还原字符串中特殊字符
	 */
	public static String decodeString(String strData) {
		strData = replaceString(strData, "&lt;", "<");
		strData = replaceString(strData, "&gt;", ">");
		strData = replaceString(strData, "&apos;", "'");
		strData = replaceString(strData, "&quot;", "\"");
		strData = replaceString(strData, "&amp;", "&");
		return strData;
	}

	/**
	 * 替换字符串中特殊字符
	 */
	public static String encodeString(String strData) {
		if (strData == null) {
			return "";
		}

		strData = replaceString(strData, "&", "&amp;");
		strData = replaceString(strData, "<", "&lt;");
		strData = replaceString(strData, ">", "&gt;");
		strData = replaceString(strData, "'", "&apos;");
		strData = replaceString(strData, "\"", "&quot;");

		return strData;
	}

	/**
	 * 替换一个字符串中的某些指定字符
	 * 
	 * @param strData
	 *            String 原始字符串
	 * @param regex
	 *            String 要替换的字符串
	 * @param replacement
	 *            String 替代字符串
	 * @return String 替换后的字符串
	 */
	public static String replaceString(String strData, String regex,
			String replacement) {
		if (strData == null) {
			return null;
		}

		int index;
		index = strData.indexOf(regex);

		String strNew = "";
		if (index >= 0) {
			while (index >= 0) {
				strNew += strData.substring(0, index) + replacement;
				strData = strData.substring(index + regex.length());
				index = strData.indexOf(regex);
			}

			strNew += strData;
			return strNew;
		}

		return strData;
	}
}
