package com.cplusexam.dao.exam;

import java.util.Map;

import com.cplusexam.bean.exam.OfficialExamMsg;

public interface OfficialExamMsgMapper {

	
	/***
	 * 
	 * @Title: addOfficialExamMsg   
	 * @Description: 进行正式考试的报名操作  -- 录入一条正式考试的基本信息
	 * @param: @param officialExamMsg
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addOfficialExamMsg(OfficialExamMsg officialExamMsg);
	
	
	/***
	 * 
	 * @Title: getOfficialExamMsgById   
	 * @Description: 根据id  获取当前正式考试的基本信息 
	 * @param: @param id
	 * @param: @return      
	 * @return: OfficialExamMsg      
	 * @throws
	 */
	public OfficialExamMsg getOfficialExamMsgById(int id);
	
	
	/***
	 * 
	 * @Title: updateExamType   
	 * @Description: 根据id  修改当前考卷的状态信息 
	 * @param: @param map
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateExamType(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: updateExamScoreC   
	 * @Description: 修改指定考卷的总分数
	 * @param: @param parm
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateExamScoreC(Map<String, Object> parm);
}
