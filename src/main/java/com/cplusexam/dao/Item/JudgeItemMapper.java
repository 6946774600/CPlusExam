package com.cplusexam.dao.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.JudgeItem;

public interface JudgeItemMapper {

	
	/***
	 * 
	 * @Title: addJudgeItem   
	 * @Description: 判断题的添加操作
	 * @param: @param judgeItem
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addJudgeItem(JudgeItem judgeItem);
	
	
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
	 * @Description: 获取当前用户录入的判断题  包含文件信息（文件没实现 为预留）
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<JudgeItem>      
	 * @throws
	 */
	public List<JudgeItem> getItemListForMy(String userId);
	
	
	/***
	 * 
	 * @Title: getItemCountForAll   
	 * @Description: 获取所有的判断题数量
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getItemCountForAll();
	
	/***
	 * 
	 * @Title: getItemListForAll   
	 * @Description: 获取所有的判断题信息 包含图片，录入用户的名称。
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getItemListForAll();
	
	
	/***
	 * 
	 * @Title: getItemById   
	 * @Description: 根据id  获取判断题的信息  包含文件信息
	 * @param: @param id
	 * @param: @return      
	 * @return: JudgeItem      
	 * @throws
	 */
	public JudgeItem getItemById(int id);
	
	
	/***
	 * 
	 * @Title: updateItem   
	 * @Description: 修改判断题操作
	 * @param: @param judgeItem
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateItem(JudgeItem judgeItem);
	
	
	/***
	 * 
	 * @Title: getJudgeItemRandom   
	 * @Description: 随机获取指定数量的 判断题  题目id
	 * @param: @param limit
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws
	 */
	public List<Integer> getJudgeItemRandom(int limit);
}
