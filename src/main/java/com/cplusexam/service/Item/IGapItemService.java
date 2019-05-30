package com.cplusexam.service.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.GapItem;

public interface IGapItemService {

	
	/***
	 * 
	 * @Title: addGapItem   
	 * @Description: 填空题添加
	 * @param: @param gapItem
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addGapItem(GapItem gapItem) throws Exception;
	
	
	
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
	 * @Description: 获取当前用户录入的填空题  包含文件信息（文件没实现 为预留）
	 * @param: @param userId
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: List<GapItem>      
	 * @throws
	 */
	public List<GapItem> getItemListForMy(String userId) throws Exception;
	
	
	/***
	 * 
	 * @Title: getItemCountForAll   
	 * @Description: 获取所有的填空题考题数目
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: int      
	 * @throws
	 */
	public int getItemCountForAll()  throws Exception;
	
	/***
	 * 
	 * @Title: getItemListForAll   
	 * @Description: 获取所有的填空题信息 包含图片，录入用户的名称。
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getItemListForAll() throws Exception;
	
	
	/***
	 * 
	 * @Title: getItemById   
	 * @Description: 根据id  获取当前录入的填空题信息 包含文件信息
	 * @param: @param id
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: GapItem      
	 * @throws
	 */
	public GapItem getItemById(int id) throws Exception;
	
	
	/***
	 * 
	 * @Title: updateItem   
	 * @Description: 修改填空题操作
	 * @param: @param gapItem
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateItem(GapItem gapItem);
}
