package com.cplusexam.service.impl.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.teacher.TeacherMenu;
import com.cplusexam.dao.teacher.TeacherMenuMapper;
import com.cplusexam.service.teacher.ITeacherMenuService;

@Service
public class TeacherMenuServiceImpl implements ITeacherMenuService {

	@Autowired
	private TeacherMenuMapper teacherMenuMapper;
	
	@Override
	public List<Map<String, Object>> getMenuTree(int pid) {
		List<Map<String, Object>> tree = new ArrayList<>();
		List<TeacherMenu> res = teacherMenuMapper.getTeacherMenuByPid(pid);
		for(int i=0;i<res.size();i++) {
			TeacherMenu teacherMenu = res.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("id", teacherMenu.getMenuId());
			map.put("name", teacherMenu.getMenuName());
			map.put("icon", teacherMenu.getMenuIcon());
			map.put("url", teacherMenu.getMenuUrl());
			List<Map<String, Object>> childernList = getMenuTree(teacherMenu.getMenuId());
			if(childernList.size()>0) {
				map.put("children", childernList);
			}
			tree.add(map);
		}
		return tree;
	}

}
