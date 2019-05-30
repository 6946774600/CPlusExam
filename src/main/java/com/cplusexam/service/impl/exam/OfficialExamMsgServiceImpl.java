package com.cplusexam.service.impl.exam;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.exam.OfficialExamMsg;
import com.cplusexam.dao.exam.OfficialExamMsgMapper;
import com.cplusexam.service.exam.IOfficialExamMsgService;

@Service
public class OfficialExamMsgServiceImpl implements IOfficialExamMsgService {
	
	@Autowired
	private OfficialExamMsgMapper officialExamMsgMapper;

	@Override
	public int addOfficialExamMsg(OfficialExamMsg officialExamMsg) throws Exception{
		return officialExamMsgMapper.addOfficialExamMsg(officialExamMsg);
	}

	@Override
	public OfficialExamMsg getOfficialExamMsgById(int id) throws Exception {
		return officialExamMsgMapper.getOfficialExamMsgById(id);
	}

	@Override
	public int updateExamType(Map<String, Object> map) throws Exception {
		return officialExamMsgMapper.updateExamType(map);
	}

	@Override
	public int updateExamScoreC(Map<String, Object> parm) throws Exception {
		return officialExamMsgMapper.updateExamScoreC(parm);
	}
	
}
