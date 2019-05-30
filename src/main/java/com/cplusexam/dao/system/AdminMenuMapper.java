package com.cplusexam.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cplusexam.bean.system.AdminMenu;

/***
 * 
 * @ClassName:  AdminMenuMapper   
 * @Description:管理员菜单dao层
 * @author: FanD
 * @date:   2019年1月21日 下午5:02:57
 */
public interface AdminMenuMapper {

	/***
	 * 
	 * @Title: getAdminMenuByPid   
	 * @Description: 获取pid下的所有菜单信息
	 * @param: @return      
	 * @return: List<AdminMenu>      
	 * @throws
	 */
	public List<AdminMenu> getAdminMenuByPid(int pid);
	//查询pid是null的情况
	public List<AdminMenu> getAdminMenuByNullPid();
	/**
	 * 当传入数据只有一个时mybatis中<if>判断会出现There is no getter for property named 'pid' in 'class java.lang.Intege
	 */
	
	
	
}
