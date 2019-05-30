package com.cplusexam.dao.system;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.system.Grade;

/***
 * 
 * @ClassName:  GradeMapper   
 * @Description:班级信息dao层
 * @author: FanD
 * @date:   2019年1月23日 上午10:36:44
 */
public interface GradeMapper {

	
	/***
	 * 
	 * @Title: getGradeList   
	 * @Description: 获取班级信息的所有信息 
	 * @param: @return      
	 * @return: List<Grade>      
	 * @throws
	 */
	public List<Grade> getGradeList();
	
	
	/***
	 * 
	 * @Title: getGradeListAndCountUser   
	 * @Description: 获取班级信息的所有信息  包括相关统计信息  用户班级信息维护页面
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getGradeListAndCountUser();
	
	
	/***
	 * 
	 * @Title: gradeIdOnaly   
	 * @Description:判断输入的班级id是否已经存在
	 * @param: @param gradeId
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public int gradeIdOnaly(String gradeId);
	
	
	/***
	 * 
	 * @Title: addGrade   
	 * @Description: 添加班级信息
	 * @param: @param grade
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addGrade(Grade grade);
	
	
	/***
	 * 
	 * @Title: getGradeById   
	 * @Description: 根据班级id  获取该id的班级信息
	 * @param: @param gradeId
	 * @param: @return      
	 * @return: Grade      
	 * @throws
	 */
	public Grade getGradeById(String gradeId);
	
	
	
	/***
	 * 
	 * @Title: updateGradeById   
	 * @Description: 修改班级信息
	 * @param: @param grade
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateGradeById(Grade grade);
	
	
	/***
	 * 
	 * @Title: getUsedGradeById   
	 * @Description: 查询当前班级id是否已经有用户再使用
	 * @param: @param gradeId
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getUsedGradeById(String gradeId);
	
	
	/***
	 * 
	 * @Title: deleteGradeBuIds   
	 * @Description: 根据传入的多个id  删除该id对应的班级信息
	 * @param: @param gradeIds
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int deleteGradeBuIds(List<String> gradeIds);
	
	
	/***
	 * 
	 * @Title: getTeacherUsedById   
	 * @Description: 判断当前教师是否已经有任课班级 
	 * @param: @param teacherId
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getTeacherUsedById(String teacherId);
	
}
