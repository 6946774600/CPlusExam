package com.cplusexam.dao.exam;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.exam.ExamModel;

public interface ExamModelMapper {

	
	/***
	 * 
	 * @Title: addExamModel   
	 * @Description: 添加考卷基本信息
	 * @param: @param examModel
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addExamModel(ExamModel examModel);
	
	
	/***
	 * 
	 * @Title: getExamModelPageMsg   
	 * @Description: 获取考卷定义的所有信息
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getExamModelPageMsg();
	
	
	/***
	 * 
	 * @Title: getExamModelById   
	 * @Description: 获取当前id对应的考题信息
	 * @param: @param id
	 * @param: @return      
	 * @return: ExamModel      
	 * @throws
	 */
	public ExamModel getExamModelById(int id);
	
	
	/***
	 * 
	 * @Title: getiExamModelList   
	 * @Description:获取考题定义的所有信息
	 * @param: @return      
	 * @return: List<ExamModel>      
	 * @throws
	 */
	public List<ExamModel> getiExamModelList();
	
	
	/***
	 * 
	 * @Title: deleteExamModel   
	 * @Description: 根据id  删除当前id对应的考题基本信息 
	 * @param: @param id      
	 * @return: int      
	 * @throws
	 */
	public int deleteExamModel(int id);
	
}
