package com.cplusexam.service.impl.exam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.exam.OfficialExamPaper;
import com.cplusexam.dao.exam.OfficialExamPaperMapper;
import com.cplusexam.service.exam.IOfficialExamPaperService;

@Service
public class OfficialExamPaperServiceImpl implements IOfficialExamPaperService {

	@Autowired
	private OfficialExamPaperMapper officialExamPaperMapper;

	@Override
	public int addOfficialExamPapers(List<OfficialExamPaper> list) throws Exception {
		return officialExamPaperMapper.addOfficialExamPapers(list);
	}

	@Override
	public String getUserOptionOne(Map<String, Object> parm) throws Exception {
		return officialExamPaperMapper.getUserOptionOne(parm);
	}

	@Override
	public int updateOfficialExamPaperUesrOption(OfficialExamPaper officialExamPaper) throws Exception {
		return officialExamPaperMapper.updateOfficialExamPaperUesrOption(officialExamPaper);
	}

	@Override
	public List<Map<String, Object>> getOfficialExamPaperOptionType(Map<String, Object> parm) throws Exception {
		return officialExamPaperMapper.getOfficialExamPaperOptionType(parm);
	}

	@Override
	public int getOffifialExamscoreCByExamId(int examId) throws Exception {
		return officialExamPaperMapper.getOffifialExamscoreCByExamId(examId);
	}
}
