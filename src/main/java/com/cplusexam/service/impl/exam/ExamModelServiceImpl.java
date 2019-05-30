package com.cplusexam.service.impl.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.exam.ExamModel;
import com.cplusexam.dao.exam.ExamModelItemMapper;
import com.cplusexam.dao.exam.ExamModelMapper;
import com.cplusexam.service.exam.IExamModelService;

@Service
public class ExamModelServiceImpl implements IExamModelService {
	
	@Autowired
	private ExamModelMapper examModelMapper;
	
	@Autowired
	private ExamModelItemMapper examModelItemMapper;
	

	public int addExamModel(ExamModel examModel)  throws Exception{
		return examModelMapper.addExamModel(examModel);
	}


	@Override
	public List<Map<String, Object>> getExamModelPageMsg() throws Exception {
		return examModelMapper.getExamModelPageMsg();
	}


	@Override
	public ExamModel getExamModelById(int id) throws Exception {
		return examModelMapper.getExamModelById(id);
	}



	@Override
	public List<Map<String, Object>> getExamModelTree() throws Exception {
		List<ExamModel> lists = examModelMapper.getiExamModelList();
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		if(lists.size()>0) {
			for (ExamModel examModel : lists) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", examModel.getUuid());
				map.put("name",examModel.getExam_name());
				res.add(map);
			}
		}
		return res;
	}


	@Override
	public boolean deleteExamModel(int id) throws Exception {
		return examModelMapper.deleteExamModel(id)!=0;
	}


	@Override
	public void deleteExamAllMsg(int id) throws Exception {
		examModelItemMapper.deleteExamItem(id);
		examModelMapper.deleteExamModel(id);
	}

}
