package com.cplusexam.util;

/***
 * 
* @ClassName: StringUtil
* @Description: 一些判断字符串的工具类
* @author 韩帆
* @date 2018年10月26日
*
 */
public class StringUtil {

	/***
	 * 
	* @Title: isEmpt
	* @Description: 判断为空
	* @param @param temp
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean isEmpt(String temp) {
		if(temp==null || "".equals(temp)) {
			return true;
		}else { 
			return false;
		}
	}
	
	
	/***
	 * 
	* @Title: isNotEmpt
	* @Description: 判断不为空
	* @param @param temp
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean isNotEmpt(String temp) {
		return !isEmpt(temp);
	}
	
}
