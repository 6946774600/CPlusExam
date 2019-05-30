package com.cplusexam.service.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.RadioItem;

public interface IRadioItemService {

	/***
	 * 
	 * @Title: addRadioItem   
	 * @Description: 进行单选题的添加操作
	 * @param: @param radioItem
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addRadioItem(RadioItem radioItem) throws Exception;
	
	
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
	 * @Description: 获取当前用户录入的考题信息
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<RadioItem>      
	 * @throws
	 */
	public List<RadioItem> getItemListForMy(String userId) throws Exception;
	
	
	/***
	 * 
	 * @Title: getItemCountForAll   
	 * @Description: 获取所有的单选题考题数目
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: int      
	 * @throws
	 */
	public int getItemCountForAll()  throws Exception;
	
	
	/***
	 * 
	 * @Title: getItemListForAll   
	 * @Description: 获取所有的单选题信息 包含图片，录入用户的名称。
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getItemListForAll() throws Exception;
	
	
	/***
	 * 
	 * @Title: getItemById   
	 * @Description: 根据id  获取当前的单选题考题信息  包含文件  
	 * @param: @param id
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: RadioItem      
	 * @throws
	 */
	public RadioItem getItemById(int id) throws Exception;
	
	
	/***
	 * 
	 * @Title: updateItem   
	 * @Description: 修改单选题考题
	 * @param: @param radioItem
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateItem(RadioItem radioItem) throws Exception;
	
	
	/***
	 * 
	 * @Title: getOptionByIdAOption   
	 * @Description:  根据单选题id和选项  获取该选项的值
	 * @param: @param map
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String getOptionByIdAOption(Map<String, Object> map) throws Exception;
}
