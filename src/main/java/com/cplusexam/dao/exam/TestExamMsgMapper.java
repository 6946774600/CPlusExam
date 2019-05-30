package com.cplusexam.dao.exam;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.exam.TestExamMsg;

/***
 * 
 * @ClassName:  TestExamMsgMapper   
 * @Description:模拟考试基本信息dao层
 * @author: FanD
 * @date:   2019年3月18日 上午11:08:09
 */
public interface TestExamMsgMapper {

	
	/***
	 * 
	 * @Title: addTestExamMsgRetId   
	 * @Description: 进行模拟考试基本信息的添加 并返回id
	 * @param: @param testExamMsg
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addTestExamMsgRetId(TestExamMsg testExamMsg);
	
	
	/***
	 * 
	 * @Title: updateTestExamMsg   
	 * @Description: 修改考题基本信息的 考试名称和时间选项  
	 * @param: @param testExamMsg
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateTestExam4AddPage(TestExamMsg testExamMsg);
	
	
	/***
	 * 
	 * @Title: deleteTestExamById   
	 * @Description: 根据id  删除当前模拟考试基本信息数据
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int deleteTestExamById(int id);
	
	
	/***
	 * 
	 * @Title: getTestExamMsgAll   
	 * @Description: 获取当前用户创建的所有模拟考试信息
	 * @param: @return      
	 * @return: List<TestExamMsg>      
	 * @throws
	 */
	public List<TestExamMsg> getTestExamMsgAll(String userId);
	
	
	/***
	 * 
	 * @Title: updateTestExamScore   
	 * @Description:修改模拟考卷的总分数据
	 * @param: @param map
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateTestExamScore(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: getTestExamItemMsg   
	 * @Description: 获取当前试卷id下  定义的相关习题信息
	 * @param: @param examtest_id
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getTestExamItemMsg(int examtest_id);
	
	
	/***
	 * 
	 * @Title: getTestExamMsgById   
	 * @Description: 根据模拟考卷的id  获取该考卷的基本信息
	 * @param: @param uuid
	 * @param: @return      
	 * @return: TestExamMsg      
	 * @throws
	 */
	public TestExamMsg getTestExamMsgById(int uuid);
	
	
	/***
	 * 
	 * @Title: updateTestExamType   
	 * @Description:修改当前模拟考卷的考试状态
	 * @param: @param type
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateTestExamType(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: updateTextExamTime   
	 * @Description: 修改当前考卷的考试剩余时间  
	 * @param: @param endTime      
	 * @return: void      
	 * @throws
	 */
	public void updateTextExamTime(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: getTestExamScoreByType   
	 * @Description: 获取当前模拟考卷各个题型的  总分数   
	 * @param: @param uuid
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getTestExamScoreByType(int uuid);
	
	
	/***
	 * 
	 * @Title: updateTextExamExam_Score   
	 * @Description: 修改当前模拟考卷的考试分数信息 
	 * @param: @param map      
	 * @return: void      
	 * @throws
	 */
	public void updateTextExamExam_Score(Map<String, Object> map);
	
}
