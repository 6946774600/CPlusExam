package com.cplusexam.service.impl.exam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.exam.ExamTime;
import com.cplusexam.dao.exam.ExamTimeMapper;
import com.cplusexam.service.exam.IExamTimeService;


@Service
public class ExamTimeServiceImpl implements IExamTimeService {

	@Autowired
	private ExamTimeMapper examTimeMapper;

	@Override
	public List<Map<String, Object>> getExamTimeList() throws Exception {
		return examTimeMapper.getExamTimeList();
	}

	@Override
	public List<ExamTime> getExamTimeAll() throws Exception {
		return examTimeMapper.getExamTimeAll();
	}

	@Override
	public boolean getExamTimeByExamModelId(int id) throws Exception {
		boolean temp =false;
		if(examTimeMapper.getExamTimeByExamModelId(id)!=0)
			temp = true;
		else 
			temp = false;
		return temp;
	}

	@Override
	public boolean addExamItem(ExamTime examTime) throws Exception {
		return examTimeMapper.addExamItem(examTime)!=0;
	}

	@Override
	public ExamTime getExamTimeById(int id) throws Exception {
		return examTimeMapper.getExamTimeById(id);
	}
	
}
