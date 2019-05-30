package com.cplusexam.dao.exam;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.exam.TestExamPaper;

public interface TestExamPaperMapper {

	
	/***
	 * 
	 * @Title: addTestExamPaper   
	 * @Description:添加一条试卷题型记录
	 * @param: @param testExamPaper
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addTestExamPaper(List<TestExamPaper> lists);
	
	
	/***
	 * 
	 * @Title: getTestExamPaperIds   
	 * @Description:  获取指定模拟考题下  指定考题类型的 考题ids
	 * @param: @param testExamId
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws
	 */
	public List<Integer> getTestExamPaperIds (Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: getUserOptionOne   
	 * @Description: 通过考卷id  题型id  和题目id  获取用户输入的答案 
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	public String getUserOptionOne(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: updateTestExamPaperUesrOption   
	 * @Description: 通过考卷id  题型id  和题目id 修改用户输入的答案以及答案的正确性
	 * @param: @param map      
	 * @return: void      
	 * @throws
	 */
	public void updateTestExamPaperUesrOption(TestExamPaper testExamPaper);
	
	
	/***
	 * 
	 * @Title: getTestExamPaperType   
	 * @Description: 根据模拟考卷的id  和题型id  获取当前题型下   考题的答案状态
	 * @param: @param map
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getTestExamPaperOptionType(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: getOveredTestExamItemMsg   
	 * @Description: 获取 已经完成的考试中  指定考试的  学生答题情况   
	 * @param: @param map   考题类型id  模拟考试id  以及查询哪个表（动态传入的哦）
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getOveredTestExamItemMsg(Map<String, Object> map);
}
