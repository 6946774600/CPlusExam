package com.cplusexam.service.impl.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.system.AdminMenu;
import com.cplusexam.dao.system.AdminMenuMapper;
import com.cplusexam.service.system.IAdminMenuService;

@Service
public class AdminMenuServiceImpl implements IAdminMenuService {

	@Autowired
	private AdminMenuMapper adminMenuMapper;
	
	@Override
	public List<Map<String, Object>> getMenuTree(int pid) {
		List<Map<String, Object>> resList = new ArrayList<>();
		List<AdminMenu> list = new ArrayList<>();
		if(pid==-1) {
			list = adminMenuMapper.getAdminMenuByNullPid();
		}else {
			list = adminMenuMapper.getAdminMenuByPid(pid);
		}
		if(list.size()>0) {
			for (AdminMenu adminMenu : list) {
				Map<String, Object> tempmap = new HashMap<>();
				tempmap.put("id", adminMenu.getMenuId());
				tempmap.put("name", adminMenu.getMenuName());
				tempmap.put("icon", adminMenu.getMenuIcon());
				tempmap.put("url", adminMenu.getMenuUrl());
				List<Map<String, Object>> childrenList = this.getMenuTree(adminMenu.getMenuId());    //递归  查询当前菜单节点的子菜单
				if(childrenList.size()>0) {
					tempmap.put("children", childrenList);
				}
				resList.add(tempmap);
			}
			return resList;
		}else {
			return resList;
		}
	}

}
