package com.cplusexam.service.exam;

import java.util.List;

import com.cplusexam.bean.exam.TestExamItemMsg;

public interface ITestExamItemMsgService {

	
	/***
	 * 
	 * @Title: addTestExamTtems   
	 * @Description: 批量添加模拟考试的考题信息
	 * @param: @param list
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addTestExamTtems(List<TestExamItemMsg> list) throws Exception;
	
	
	
	/***
	 * 
	 * @Title: getTestExamItemMsgAllByExamId   
	 * @Description:根据模拟考卷的id， 获取该id下的考题定义信息
	 * @param: @param testExamId
	 * @param: @return      
	 * @return: List<TestExamItemMsg>      
	 * @throws
	 */
	public List<TestExamItemMsg> getTestExamItemMsgAllByExamId(int testExamId) throws Exception;
}
