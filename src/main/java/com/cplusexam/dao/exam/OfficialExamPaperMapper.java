package com.cplusexam.dao.exam;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.exam.OfficialExamPaper;

public interface OfficialExamPaperMapper {

	
	/***
	 * 
	 * @Title: addOfficialExamPapers   
	 * @Description: 添加考题具体信息  批量添加
	 * @param: @param list
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addOfficialExamPapers(List<OfficialExamPaper> list);

	
	/***
	 * 
	 * @Title: getUserOptionOne   
	 * @Description: 获取指定考卷 指定题型 指定题型id的用户答案 
	 * @param: @param parm
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String getUserOptionOne(Map<String, Object> parm);
	
	
	/***
	 * 
	 * @Title: updateOfficialExamPaperUesrOption   
	 * @Description: 修改当前具体考题的用户答案与是否正确数据
	 * @param: @param officialExamPaper
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateOfficialExamPaperUesrOption(OfficialExamPaper officialExamPaper);
	
	
	/***
	 * 
	 * @Title: getOfficialExamPaperOptionType   
	 * @Description: 获取指定考卷  指定题型的完成状态信息 
	 * @param: @param parm
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getOfficialExamPaperOptionType(Map<String, Object> parm);
	
	
	/***
	 * 
	 * @Title: getOffifialExamscoreCByExamId   
	 * @Description: 获取当前考卷id下  所有题型的总分数
	 * @param: @param examId
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getOffifialExamscoreCByExamId(int examId);
	
}
