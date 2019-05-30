package com.cplusexam.service.impl.exam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.exam.ExamModelItem;
import com.cplusexam.dao.exam.ExamModelItemMapper;
import com.cplusexam.service.exam.IExamModelItemSerivce;

@Service
public class ExamModelItemServiceImpl implements IExamModelItemSerivce {
	
	@Autowired
	private ExamModelItemMapper examModelItemMapper;
	

	@Override
	public boolean addExamModelItems(List<ExamModelItem> lists) {
		return examModelItemMapper.addExamModelItems(lists)!=0;
	}


	@Override
	public List<Map<String, Object>> getExamModelItemByModelId(int id) throws Exception {
		return examModelItemMapper.getExamModelItemByModelId(id);
	}


	@Override
	public boolean deleteExamItem(int modelId) throws Exception {
		return examModelItemMapper.deleteExamItem(modelId)!=0;
	}

}
