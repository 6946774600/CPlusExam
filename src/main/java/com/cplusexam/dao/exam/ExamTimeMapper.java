package com.cplusexam.dao.exam;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.exam.ExamTime;

/***
 * 
 * @ClassName:  ExamTimeMapper   
 * @Description:考试时间dao层
 * @author: FanD
 * @date:   2019年3月11日 上午11:36:40
 */
public interface ExamTimeMapper {

	
	/***
	 * 
	 * @Title: getExamTimeList   
	 * @Description: 获取考试时间维护页面的所有信息
	 * @param: @return      
	 * @return: List<Map<String, Object>>    
	 * @throws
	 */
	public List<Map<String, Object>> getExamTimeList();
	
	
	/***
	 * 
	 * @Title: getExamTimeAll   
	 * @Description: 获取当前考试信息的所有信息
	 * @param: @return      
	 * @return: List<ExamTime> 
	 * @throws
	 */
	public List<ExamTime> getExamTimeAll();
	
	
	/***
	 * 
	 * @Title: getExamTimeByExamModelId   
	 * @Description: 判断当前试卷id  有多少正式考试在使用
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getExamTimeByExamModelId(int id);
	
	
	/***
	 * 
	 * @Title: addExamItem   
	 * @Description: 添加一条考试时间信息
	 * @param: @param examTime
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addExamItem(ExamTime examTime);
	
	
	/***
	 * 
	 * @Title: getExamTimeById   
	 * @Description: 根据考试id  获取当前考卷信息
	 * @param: @return      
	 * @return: ExamTime      
	 * @throws
	 */
	public ExamTime getExamTimeById(int id);
	
}
