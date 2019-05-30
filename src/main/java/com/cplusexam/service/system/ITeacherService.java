package com.cplusexam.service.system;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.system.User;

/***
 * 
 * @ClassName:  ITeacherService   
 * @Description:老师处理service层
 * @author: FanD
 * @date:   2019年1月25日 上午11:48:27
 */

public interface ITeacherService {

	/***
	 * 
	 * @Title: getTeacherSelect   
	 * @Description: 获取教师信息的下拉框数据
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getTeacherSelect();
	
	
	/***
	 * 
	 * @Title: getTeacherMsg   
	 * @Description: 获取教师信息维护页面信息  涵盖查询 
	 * @param: @param user
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getTeacherMsg(User user);
	
}
