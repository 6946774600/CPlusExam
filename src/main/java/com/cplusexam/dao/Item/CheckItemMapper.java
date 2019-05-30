package com.cplusexam.dao.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.CheckItem;

/***
 * 
 * @ClassName:  CheckItem   
 * @Description:多选题题库 dao层
 * @author: FanD
 * @date:   2019年2月26日 下午3:11:44
 */
public interface CheckItemMapper {

	/***
	 * 
	 * @Title: addCheckItem   
	 * @Description: 添加多选题操作
	 * @param: @param checkItem
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addCheckItem(CheckItem checkItem);
	
	
	/***
	 * 
	 * @Title: getCountByUserId   
	 * @Description: 获取当前教师录入的考题数量
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getCountByUserId(String userId);
	
	
	/***
	 * 
	 * @Title: getItemListForMy   
	 * @Description: 获取用户录入的多选题  包含文件信息
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<CheckItem>      
	 * @throws
	 */
	public List<CheckItem> getItemListForMy(String userId);
	
	
	/***
	 * 
	 * @Title: getItemCountForAll   
	 * @Description: 获取所有的多选题数量
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getItemCountForAll();
	
	
	/***
	 * 
	 * @Title: getItemListForAll   
	 * @Description: 获取所有的多选题信息 包含图片，录入用户的名称。
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getItemListForAll();
	
	
	/***
	 * 
	 * @Title: getItemById   
	 * @Description: 根据id  获取多选题的信息  包含文件信息
	 * @param: @param id
	 * @param: @return      
	 * @return: CheckItem      
	 * @throws
	 */
	public CheckItem getItemById(int id);
	
	
	/***
	 * 
	 * @Title: updateItem   
	 * @Description: 修改多选题考题 
	 * @param: @param checkItem
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateItem(CheckItem checkItem);
	
	
	/***
	 * 
	 * @Title: getOptionByIdAOption   
	 * @Description: 获取指定考题  指定的选项内容
	 * @param: @param map
	 * @param: @return      
	 * @return: String     
	 * @throws
	 */
	public String getOptionByIdAOption(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: getCheckItemRandom   
	 * @Description: 随机获取指定数量的多选题  题目id
	 * @param: @param limit
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws
	 */
	public List<Integer> getCheckItemRandom(int limit);
}
