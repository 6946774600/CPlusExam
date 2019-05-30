package com.cplusexam.dao.system;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.system.User;

/***
 * 
 * @ClassName:  UserMapper   
 * @Description:用户表对应的dao
 * @author: FanD
 * @date:   2019年1月21日 上午11:01:34
 */
public interface UserMapper {

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
	 * @Description: 获取学生信息维护页面的数据   涵盖查询功能
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
	 * @Title: addStudent   
	 * @Description: 进行用户的添加
	 * @param: @param user
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addUser(User user);
	
	
	/***
	 * 
	 * @Title: updateUser   
	 * @Description: 修改学生信息
	 * @param: @param user
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateUser(User user);
	
	
	/***
	 * 
	 * @Title: deleteUserBuId   
	 * @Description: 根据id  删除用户信息 支持批量删除
	 * @param: @param loginNames
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int deleteUserBuId(List<String> loginNames);
	
	
	/***
	 * 
	 * @Title: getTeacherSelect   
	 * @Description: 获取教师下拉框数据信息
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getTeacherSelect();
	
	
	
	/***
	 * 
	 * @Title: getTeacherMsg   
	 * @Description: 获取教师信息维护页面信息  涵盖查询 
	 * @param: @param user
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getTeacherMsg(User user);
	
	
	/***
	 * 
	 * @Title: updatePassword   
	 * @Description: 根据登录用户 修改用户密码
	 * @param: @param map
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updatePassword(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: updateUserByHome   
	 * @Description: 首页上的修改信息按钮
	 * @param: @param user
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateUserByHome(User user);
	
}
