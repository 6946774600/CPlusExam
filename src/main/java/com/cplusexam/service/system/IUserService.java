package com.cplusexam.service.system;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.cplusexam.bean.system.User;

/***
 * 
 * @ClassName:  IUserService   
 * @Description:用户表操作service层
 * @author: FanD
 * @date:   2019年1月21日 上午11:21:47
 */
public interface IUserService {

	/***
	 * 
	 * @Title: getUserByLoginName   
	 * @Description: 根据登录名 获取用户表中的信息  （登录名即主键）
	 * @param: @return      
	 * @return: User      
	 * @throws
	 */
	public User getUserById(String loginName);
	
	
	/***
	 * 
	 * @Title: getUserAllById   
	 * @Description: 根据登录名  获取用户的所有信息（包括权限，班级信息）
	 * @param: @param loginName
	 * @param: @return      
	 * @return: Map<String,Object>    
	 * @throws
	 */
	public Map<String, Object> getUserAllById(String loginName);
	
	
	/***
	 * 
	 * @Title: getUserShiroEnNameById   
	 * @Description: 根据登录用户  获取该用户的权限信息
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String getUserShiroEnNameById(String loginName);
	
	
	/***
	 * 
	 * @Title: getStrudentMsg   
	 * @Description: 获取学生信息维护页面的数据 
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getStrudentMsg(User user);
	
	
	/***
	 * 
	 * @Title: studentIdOnaly   
	 * @Description: 判断学号是否唯一
	 * @param: @param loginName
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int studentIdOnaly(String loginName);
	
	
	/***
	 * 
	 * @Title: addUser   
	 * @Description: 进行用户的添加
	 * @param: @param user
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addUser(User user);
	
	
	/***
	 * 
	 * @Title: updateUser   
	 * @Description: 修改学生信息
	 * @param: @param user
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateUser(User user);
	
	
	/***
	 * 
	 * @Title: deleteUserBuId   
	 * @Description: 根据id  删除用户信息 支持批量删除
	 * @param: @param loginNames
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean deleteUserBuId(List<String> loginNames);
	
	
	/***
	 * 
	 * @Title: modelTeacherFile   
	 * @Description: 创建教师信息的录入模版
	 * @param: @param sheet
	 * @param: @return      
	 * @return: Row      
	 * @throws
	 */
	public Sheet modelTeacherFile(Workbook workbook);
	
	/***
	 * 
	 * @Title: improtTeacherFile   
	 * @Description:进行模版数据的导入
	 * @param: @param sheet
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String improtTeacherFile(Sheet sheet);
	
	
	/***
	 * 
	 * @Title: modelStudentFile   
	 * @Description: 创建学生信息的录入模版
	 * @param: @param sheet
	 * @param: @return      
	 * @return: Row      
	 * @throws
	 */
	public Sheet modelStudentFile(Workbook workbook);
	
	/***
	 * 
	 * @Title: improtStudentFile   
	 * @Description:进行学生信息模版数据的导入
	 * @param: @param sheet
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String improtStudentFile(Sheet sheet);
	
	
	/***
	 * 
	 * @Title: updatePassword   
	 * @Description: 根据登录用户 修改用户密码
	 * @param: @param map
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updatePassword(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: updateUserByHome   
	 * @Description: 首页上的修改信息按钮
	 * @param: @param user
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateUserByHome(User user);
}
