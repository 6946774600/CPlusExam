package com.cplusexam.service.exam;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.exam.ExamModel;

public interface IExamModelService {

	
	/***
	 * 
	 * @Title: addExamModel   
	 * @Description: 添加考卷基本信息
	 * @param: @param examModel
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addExamModel(ExamModel examModel) throws Exception;
	
	
	/***
	 * 
	 * @Title: getExamModelPageMsg   
	 * @Description: 获取考卷定义的所有信息
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getExamModelPageMsg() throws Exception;
	
	
	/***
	 * 
	 * @Title: getExamModelById   
	 * @Description: 获取当前id对应的考题信息
	 * @param: @param id
	 * @param: @return      
	 * @return: ExamModel      
	 * @throws
	 */
	public ExamModel getExamModelById(int id) throws Exception;
	
	
	/***
	 * 
	 * @Title: getExamModelTree   
	 * @Description: 获取考卷信息树
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getExamModelTree() throws Exception;
	
	
	/***
	 * 
	 * @Title: deleteExamModel   
	 * @Description: 根据id  删除当前id对应的考题基本信息 
	 * @param: @param id      
	 * @return: boolean
	 * @throws
	 */
	public boolean deleteExamModel(int id) throws Exception;
	
	
	/***
	 * 
	 * @Title: deleteExamAllMsg   
	 * @Description: 删除当前试卷的所有信息  
	 * @param: @param id
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	public void deleteExamAllMsg(int id) throws Exception;
	
}
