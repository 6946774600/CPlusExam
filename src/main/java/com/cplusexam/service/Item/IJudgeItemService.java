package com.cplusexam.service.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.JudgeItem;

public interface IJudgeItemService {

	
	/***
	 * 
	 * @Title: addJudgeItem   
	 * @Description: 判断题的添加操作
	 * @param: @param judgeItem
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addJudgeItem(JudgeItem judgeItem) throws Exception;
	
	
	/***
	 * 
	 * @Title: getCountByUserId   
	 * @Description: 获取当前教师录入的考题数量
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getCountByUserId(String userId) throws Exception;
	
	
	/***
	 * 
	 * @Title: getItemListForMy   
	 * @Description: 获取当前用户录入的判断题  包含文件信息（文件没实现 为预留）
	 * @param: @param userId
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: List<JudgeItem>      
	 * @throws
	 */
	public List<JudgeItem> getItemListForMy(String userId) throws Exception;
	
	
	/***
	 * 
	 * @Title: getItemCountForAll   
	 * @Description: 获取所有的判断题考题数目
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: int      
	 * @throws
	 */
	public int getItemCountForAll() throws Exception;
	
	/***
	 * 
	 * @Title: getItemListForAll   
	 * @Description: 获取所有的判断题信息 包含图片，录入用户的名称。
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getItemListForAll() throws Exception;
	
	
	/***
	 * 
	 * @Title: getItemById   
	 * @Description: 根据考题id  获取该id下的判断题信息    包含文件信息 
	 * @param: @param id
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: JudgeItem      
	 * @throws
	 */
	public JudgeItem getItemById(int id) throws Exception;
	
	
	/***
	 * 
	 * @Title: updateItem   
	 * @Description: 修改判断题操作
	 * @param: @param judgeItem
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateItem(JudgeItem judgeItem);
}
