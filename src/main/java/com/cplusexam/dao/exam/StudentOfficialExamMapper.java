package com.cplusexam.dao.exam;

import java.util.List;
import java.util.Map;

/***
 * 
 * @ClassName:  StudentOfficialExamMapper   
 * @Description:处理学生正式考试页面相关底层操作  
 * @author: FanD
 * @date:   2019年3月28日 上午11:15:16
 */
public interface StudentOfficialExamMapper {

	
	/***
	 * 
	 * @Title: getOfficialExamTableMsg   
	 * @Description: 获取正式考试页面  正式考试信息的表格数据
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getOfficialExamTableMsg(String userId);
	
	
	/***
	 * 
	 * @Title: getOfficialExamUserTableMsg   
	 * @Description: 获取正式考试页面  当前登录用户的考试信息表格数据
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getOfficialExamUserTableMsg(String userId);
	
	
	/***
	 * 
	 * @Title: getOfficialExamItemids   
	 * @Description:获取指定正式考卷  指定考题类型的考题ids
	 * @param: @param parm
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws
	 */
	public List<Integer> getOfficialExamItemids(Map<String, Object> parm);
	
}
