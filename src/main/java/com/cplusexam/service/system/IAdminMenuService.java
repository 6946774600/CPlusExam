package com.cplusexam.service.system;

import java.util.List;
import java.util.Map;

/***
 * 
 * @ClassName:  IAdminMenuService   
 * @Description:管理员菜单表对应的service请求 
 * @author: FanD
 * @date:   2019年1月21日 下午5:08:33
 */
public interface IAdminMenuService {

	
	/***
	 * 
	 * @Title: getMenuTree   
	 * @Description: 将所有的菜单信息  已树的形式返回
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getMenuTree(int pid);
	
}
