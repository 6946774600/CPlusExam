package com.cplusexam.service.student;

import java.util.List;
import java.util.Map;

public interface IStudentMenuService {

	
	/***
	 * 
	 * @Title: getMenuTree   
	 * @Description: 将所有的菜单信息  已树的形式返回
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getMenuTree(int pid) throws Exception;
	
}
