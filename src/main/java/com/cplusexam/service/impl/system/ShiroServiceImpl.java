package com.cplusexam.service.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.system.Shiro;
import com.cplusexam.dao.system.ShiroMapper;
import com.cplusexam.service.system.IShiroService;

@Service
public class ShiroServiceImpl implements IShiroService {

	@Autowired
	private ShiroMapper shiroMapper;
	
	@Override
	public List<Map<String, Object>> getShiroList() {
		return shiroMapper.getShiroList();
	}

	@Override
	public Shiro getShiroById(int id) {
		return shiroMapper.getShiroById(id);
	}

}
