package com.cplusexam.service.system;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.cplusexam.bean.system.Grade;

/***
 * 
 * @ClassName:  IGradeService   
 * @Description:班级操作service层
 * @author: FanD
 * @date:   2019年1月23日 上午11:44:21
 */
public interface IGradeService {

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
	 * @return: boolean      
	 * @throws
	 */
	public boolean gradeIdOnaly(String gradeId);
	
	
	/***
	 * 
	 * @Title: addGrade   
	 * @Description: 添加班级信息
	 * @param: @param grade
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addGrade(Grade grade);
	
	
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
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateGradeById(Grade grade);
	
	
	/***
	 * 
	 * @Title: getUsedGradeById   
	 * @Description: 查询当前班级id是否已经有用户再使用  有的话 返回被使用的班级id  并拼接为字符串
	 * @param: @param gradeIds
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String getUsedGradeById(List<String> gradeIds);
	
	
	/***
	 * 
	 * @Title: deleteGradeBuIds   
	 * @Description: 根据传入的多个id  删除该id对应的班级信息
	 * @param: @param gradeIds
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean deleteGradeBuIds(List<String> gradeIds);
	
	
	/***
	 * 
	 * @Title: excleImportGradeMsg   
	 * @Description: 解析excle表格 进行数据的添加
	 * @param: @param sheet
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String excleImportGradeMsg(Sheet sheet);
	
	
	/***
	 * 
	 * @Title: getGradeSelect   
	 * @Description: 获取班级信息下拉选择框 
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getGradeSelect();
	
	
	/***
	 * 
	 * @Title: getTeacherUsedById   
	 * @Description: 判断当前教师是否已经有任课班级 
	 * @param: @param teacherIds
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String getTeacherUsedById(List<String> teacherIds);
	
}
