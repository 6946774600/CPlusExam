package com.cplusexam.dao.system;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.system.Shiro;

/***
 * 
 * @ClassName:  ShiroMapper   
 * @Description:角色权限表对应的dao
 * @author: FanD
 * @date:   2019年1月22日 下午5:35:12
 */
public interface ShiroMapper {

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
	
	
	/***
	 * 
	 * @Title: getShiroUserList   
	 * @Description: 根据权限id  获取该id下的所有用户信息
	 * @param: @param id
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getShiroUserList(int id);
	
}
