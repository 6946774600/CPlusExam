package com.cplusexam.service.exam;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.exam.ExamTime;

public interface IExamTimeService {

	/***
	 * 
	 * @Title: getExamTimeList   
	 * @Description: 获取考试时间维护页面的所有信息
	 * @param: @return      
	 * @return: List<Map<String, Object>>    
	 * @throws
	 */
	public List<Map<String, Object>> getExamTimeList() throws Exception;
	
	
	
	/***
	 * 
	 * @Title: getExamTimeAll   
	 * @Description: 获取当前考试信息的所有信息
	 * @param: @return      
	 * @return: List<ExamTime> 
	 * @throws
	 */
	public List<ExamTime> getExamTimeAll() throws Exception;
	
	
	/***
	 * 
	 * @Title: getExamTimeByExamModelId   
	 * @Description: 判断当前试卷id  有多少正式考试在使用  
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean   true代表已经被使用  false代表没有被使用
	 * @throws
	 */
	public boolean getExamTimeByExamModelId(int id) throws Exception;
	
	
	/***
	 * 
	 * @Title: addExamItem   
	 * @Description: 添加一条考试时间信息
	 * @param: @param examTime
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addExamItem(ExamTime examTime) throws Exception;
	
	
	
	/***
	 * 
	 * @Title: getExamTimeById   
	 * @Description: 根据考试id  获取当前考卷信息
	 * @param: @return      
	 * @return: ExamTime      
	 * @throws
	 */
	public ExamTime getExamTimeById(int id) throws Exception;
}
