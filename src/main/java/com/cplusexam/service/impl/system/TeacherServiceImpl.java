package com.cplusexam.service.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.system.User;
import com.cplusexam.dao.system.UserMapper;
import com.cplusexam.service.system.ITeacherService;

@Service
public class TeacherServiceImpl implements ITeacherService {

	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public List<Map<String, Object>> getTeacherSelect() {
		return userMapper.getTeacherSelect();
	}


	@Override
	public List<Map<String, Object>> getTeacherMsg(User user) {
		return userMapper.getTeacherMsg(user);
	}

}
