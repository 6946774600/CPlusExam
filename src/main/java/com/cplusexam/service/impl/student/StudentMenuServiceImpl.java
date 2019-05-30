package com.cplusexam.service.impl.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.student.StudentMenu;
import com.cplusexam.dao.student.StudentMenuMapper;
import com.cplusexam.service.student.IStudentMenuService;

@Service
public class StudentMenuServiceImpl implements IStudentMenuService {

	@Autowired
	private StudentMenuMapper studentMenuMapper;
	
	@Override
	public List<Map<String, Object>> getMenuTree(int pid) throws Exception{
		List<Map<String, Object>> tree = new ArrayList<>();
		List<StudentMenu> res = studentMenuMapper.getStudentMenuByPid(pid);
		for(int i=0;i<res.size();i++) {
			StudentMenu studentMenu = res.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("id", studentMenu.getMenuId());
			map.put("name", studentMenu.getMenuName());
			map.put("icon", studentMenu.getMenuIcon());
			map.put("url", studentMenu.getMenuUrl());
			List<Map<String, Object>> childernList = getMenuTree(studentMenu.getMenuId());
			if(childernList.size()>0) {
				map.put("children", childernList);
			}
			tree.add(map);
		}
		return tree;
	}

}
