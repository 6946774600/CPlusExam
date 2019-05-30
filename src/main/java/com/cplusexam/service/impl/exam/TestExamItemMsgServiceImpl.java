package com.cplusexam.service.impl.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.exam.TestExamItemMsg;
import com.cplusexam.dao.exam.TestExamItemMsgMapper;
import com.cplusexam.service.exam.ITestExamItemMsgService;


@Service
public class TestExamItemMsgServiceImpl implements ITestExamItemMsgService {

	@Autowired
	private TestExamItemMsgMapper testExamItemMsgMapper;

	@Override
	public int addTestExamTtems(List<TestExamItemMsg> list) throws Exception {
		return testExamItemMsgMapper.addTestExamTtems(list);
	}

	@Override
	public List<TestExamItemMsg> getTestExamItemMsgAllByExamId(int testExamId) throws Exception {
		return testExamItemMsgMapper.getTestExamItemMsgAllByExamId(testExamId);
	}
}
