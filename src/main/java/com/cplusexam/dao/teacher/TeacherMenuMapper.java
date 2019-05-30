package com.cplusexam.dao.teacher;

import java.util.List;

import com.cplusexam.bean.teacher.TeacherMenu;

public interface TeacherMenuMapper {
	
	/***
	 * 
	 * @Title: getTeacherMenu   
	 * @Description: 获取菜单下拉树
	 * @param: @return      
	 * @return: List<TeacherMenu>      
	 * @throws
	 */
	public List<TeacherMenu> getTeacherMenuByPid(int pid);
	
}
