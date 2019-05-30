package com.cplusexam.dao.student;

import java.util.List;

import com.cplusexam.bean.student.StudentMenu;

public interface StudentMenuMapper {

	
	/***
	 * 
	 * @Title: getStudentMenuByPid   
	 * @Description: 获取菜单下拉树
	 * @param: @return      
	 * @return: List<StudentMenu>      
	 * @throws
	 */
	public List<StudentMenu> getStudentMenuByPid(int pid);
	
}
