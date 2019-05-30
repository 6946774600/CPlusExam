package com.cplusexam.dao.exam;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.exam.ExamModelItem;

public interface ExamModelItemMapper {

	
	/***
	 * 
	 * @Title: addExamModelItems   
	 * @Description: 进行考卷题型信息的添加  批量添加
	 * @param: @param lists
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addExamModelItems(List<ExamModelItem> lists);
	
	
	/***
	 * 
	 * @Title: getExamModelItemByModelId   
	 * @Description: 获取指定试卷的题型信息
	 * @param: @param id
	 * @param: @return      
	 * @return: List<Map<String, Object>>     
	 * @throws
	 */
	public List<Map<String, Object>> getExamModelItemByModelId(int id);
	
	
	/***
	 * 
	 * @Title: deleteExamItem   
	 * @Description: 删除当前试卷id  对应的题型的所有信息
	 * @param: @param modelId
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int deleteExamItem(int modelId);
	
}
