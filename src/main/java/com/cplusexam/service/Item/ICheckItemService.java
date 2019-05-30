package com.cplusexam.service.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.CheckItem;

public interface ICheckItemService {

	/***
	 * 
	 * @Title: addCheckItem   
	 * @Description: 添加多选题操作
	 * @param: @param checkItem
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addCheckItem(CheckItem checkItem) throws Exception;
	
	
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
	 * @Description: 获取用户录入的多选题  包含文件信息
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<CheckItem>      
	 * @throws
	 */
	public List<CheckItem> getItemListForMy(String userId) throws Exception;
	
	
	/***
	 * 
	 * @Title: getItemCountForAll   
	 * @Description: 获取所有的多选题考题数目
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: int      
	 * @throws
	 */
	public int getItemCountForAll()  throws Exception;
	
	/***
	 * 
	 * @Title: getItemListForAll   
	 * @Description: 获取所有的多选题信息 包含图片，录入用户的名称。
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getItemListForAll() throws Exception;
	
	
	/***
	 * 
	 * @Title: getItemById   
	 * @Description: 根据id  获取录入的多选题信息  包含文件信息   
	 * @param: @param id
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: CheckItem      
	 * @throws
	 */
	public CheckItem getItemById(int id) throws Exception;
	
	
	/***
	 * 
	 * @Title: updateItem   
	 * @Description: 修改多选题考题 
	 * @param: @param checkItem
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateItem(CheckItem checkItem) throws Exception;
	
	
	/***
	 * 
	 * @Title: getOptionByIdAOption   
	 * @Description: 获取指定考题  指定的选项内容
	 * @param: @param map
	 * @param: @return      
	 * @return: String     
	 * @throws
	 */
	public String getOptionByIdAOption(Map<String, Object> map) throws Exception;
}
