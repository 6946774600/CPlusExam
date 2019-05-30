package com.cplusexam.dao.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.RadioItem;

/***
 * 
 * @ClassName:  RadioItemMapper   
 * @Description:考题表对应的dao
 * @author: FanD
 * @date:   2019年2月25日 下午3:04:57
 */
public interface RadioItemMapper {

	
	/***
	 * 
	 * @Title: addRadioItem   
	 * @Description: 进行单选题的添加操作
	 * @param: @param radioItem
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addRadioItem(RadioItem radioItem);
	
	
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
	 * @Description: 获取当前用户录入的考题信息
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<RadioItem>      
	 * @throws
	 */
	public List<RadioItem> getItemListForMy(String userId);
	
	
	/***
	 * 
	 * @Title: getItemCountForAll   
	 * @Description: 获取所有的单选题数量
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getItemCountForAll();
	
	
	/***
	 * 
	 * @Title: getItemListForAll   
	 * @Description: 获取所有的单选题信息 包含图片，录入用户的名称。
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getItemListForAll();
	
	
	/***
	 * 
	 * @Title: getItemById   
	 * @Description: 根据id  获取单选题的信息  包含文件信息
	 * @param: @param id
	 * @param: @return      
	 * @return: RadioItem      
	 * @throws
	 */
	public RadioItem getItemById(int id);
	
	
	/***
	 * 
	 * @Title: updateItem   
	 * @Description: 修改单选题考题
	 * @param: @param radioItem
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateItem(RadioItem radioItem);
	
	
	/***
	 * 
	 * @Title: getRadioItemById   
	 * @Description: 根据id  获取当前单选题的所有信息
	 * @param: @param id
	 * @param: @return      
	 * @return: RadioItem      
	 * @throws
	 */
	public RadioItem getRadioItemById(int id);
	
	
	/***
	 * 
	 * @Title: getOptionByIdAOption   
	 * @Description:  根据单选题id和选项  获取该选项的值
	 * @param: @param map
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String getOptionByIdAOption(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: getRadioItemRandom   
	 * @Description: 随机指定数量的获取单选题id 
	 * @param: @param limit
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws
	 */
	public List<Integer> getRadioItemRandom(int limit); 
	
}
