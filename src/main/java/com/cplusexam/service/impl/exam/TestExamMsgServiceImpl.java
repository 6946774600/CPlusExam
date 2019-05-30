package com.cplusexam.service.impl.exam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.exam.TestExamMsg;
import com.cplusexam.dao.exam.TestExamMsgMapper;
import com.cplusexam.service.exam.ITestExamMsgService;

@Service
public class TestExamMsgServiceImpl implements ITestExamMsgService {

	
	@Autowired
	private TestExamMsgMapper testExamMsgMapper;

	@Override
	public int addTestExamMsgRetId(TestExamMsg testExamMsg) throws Exception {
		return testExamMsgMapper.addTestExamMsgRetId(testExamMsg);
	}

	@Override
	public int updateTestExam4AddPage(TestExamMsg testExamMsg) throws Exception {
		return testExamMsgMapper.updateTestExam4AddPage(testExamMsg);
	}

	@Override
	public int deleteTestExamById(int id) throws Exception {
		return testExamMsgMapper.deleteTestExamById(id);
	}

	@Override
	public List<TestExamMsg> getTestExamMsgAll(String userId) throws Exception {
		return testExamMsgMapper.getTestExamMsgAll(userId);
	}

	@Override
	public int updateTestExamScore(Map<String, Object> map) throws Exception {
		return testExamMsgMapper.updateTestExamScore(map);
	}

	@Override
	public List<Map<String, Object>> getTestExamItemMsg(int examtest_id) throws Exception {
		return testExamMsgMapper.getTestExamItemMsg(examtest_id);
	}

	@Override
	public TestExamMsg getTestExamMsgById(int uuid) throws Exception {
		return testExamMsgMapper.getTestExamMsgById(uuid);
	}

	@Override
	public int updateTestExamType(Map<String, Object> map) throws Exception {
		return testExamMsgMapper.updateTestExamType(map);
	}

	@Override
	public void updateTextExamTime(Map<String, Object> map) throws Exception {
		testExamMsgMapper.updateTextExamTime(map);
	}

	@Override
	public List<Map<String, Object>> getTestExamScoreByType(int uuid) throws Exception {
		return testExamMsgMapper.getTestExamScoreByType(uuid);
	}

	@Override
	public void updateTextExamExam_Score(Map<String, Object> map) throws Exception {
		testExamMsgMapper.updateTextExamExam_Score(map);
	}

	
}
