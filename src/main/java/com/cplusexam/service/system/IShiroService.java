package com.cplusexam.service.system;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.system.Shiro;

public interface IShiroService {

	/***
	 * 
	 * @Title: getShiroList   
	 * @Description: 获取权限信息展示页面的表格数据 
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getShiroList();
	
	
	/***
	 * 
	 * @Title: getShiroById   
	 * @Description: 根据shiroid  获取当前shiro的所有信息 
	 * @param: @param id
	 * @param: @return      
	 * @return: Shiro      
	 * @throws
	 */
	public Shiro getShiroById(int id);
	
}
