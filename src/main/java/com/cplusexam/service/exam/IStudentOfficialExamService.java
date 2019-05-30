package com.cplusexam.service.exam;

import java.util.List;
import java.util.Map;

public interface IStudentOfficialExamService {

	/***
	 * 
	 * @Title: getOfficialExamTableMsg   
	 * @Description: 获取正式考试页面  正式考试信息的表格数据
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getOfficialExamTableMsg(String userId) throws Exception;
	
	
	
	/***
	 * 
	 * @Title: getOfficialExamUserTableMsg   
	 * @Description: 获取正式考试页面  当前登录用户的考试信息表格数据
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getOfficialExamUserTableMsg(String userId) throws Exception;
	
	
	/***
	 * 
	 * @Title: createOfficialExamPaper   
	 * @Description: 进行当前登录用户正式考试具体考题的信息的创建 
	 * @param: @param examId
	 * @param: @param examItems      
	 * @return: void      
	 * @throws
	 */
	public void createOfficialExamPaper(int examId , List<Map<String, Object>> examItems) throws Exception;
	
	
	/***
	 * 
	 * @Title: getOfficialExamItemids   
	 * @Description:获取指定正式考卷  指定考题类型的考题ids
	 * @param: @param parm
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws
	 */
	public List<Integer> getOfficialExamItemids(Map<String, Object> parm) throws Exception;
}
