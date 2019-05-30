package com.cplusexam.dao.exam;

import java.util.List;

import com.cplusexam.bean.exam.TestExamItemMsg;

/***
 * 
 * @ClassName:  TestExamItemMsgMapper   
 * @Description:模拟考试题型表对应dao
 * @author: FanD
 * @date:   2019年3月18日 上午11:17:25
 */
public interface TestExamItemMsgMapper {

	
	/***
	 * 
	 * @Title: addTestExamTtems   
	 * @Description: 批量添加模拟考试的考题信息
	 * @param: @param list
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addTestExamTtems(List<TestExamItemMsg> list);
	
	
	/***
	 * 
	 * @Title: getTestExamItemMsgAllByExamId   
	 * @Description:根据模拟考卷的id， 获取该id下的考题定义信息
	 * @param: @param testExamId
	 * @param: @return      
	 * @return: List<TestExamItemMsg>      
	 * @throws
	 */
	public List<TestExamItemMsg> getTestExamItemMsgAllByExamId(int testExamId);
	
}
