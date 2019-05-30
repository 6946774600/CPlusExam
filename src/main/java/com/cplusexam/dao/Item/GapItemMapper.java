package com.cplusexam.dao.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.GapItem;

/***
 * 
 * @ClassName:  GapItemMapper   
 * @Description:填空题表对应dao层
 * @author: FanD
 * @date:   2019年2月26日 下午5:21:53
 */
public interface GapItemMapper {

	
	/***
	 * 
	 * @Title: addGapItem   
	 * @Description: 填空题添加
	 * @param: @param gapItem
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addGapItem(GapItem gapItem);
	
	
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
	 * @Description: 获取当前用户录入的填空题  包含文件信息（文件没实现 为预留）
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<GapItem>    
	 * @throws
	 */
	public List<GapItem> getItemListForMy(String userId);
	
	
	/***
	 * 
	 * @Title: getItemCountForAll   
	 * @Description: 获取所有的填空题数量
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getItemCountForAll();
	
	
	/***
	 * 
	 * @Title: getItemListForAll   
	 * @Description: 获取所有的填空题信息 包含图片，录入用户的名称。
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getItemListForAll();
	
	
	/***
	 * 
	 * @Title: getItemById   
	 * @Description: 根据id  获取填空题的信息  包含文件信息
	 * @param: @param id
	 * @param: @return      
	 * @return: GapItem      
	 * @throws
	 */
	public GapItem getItemById(int id);
	
	
	/***
	 * 
	 * @Title: updateItem   
	 * @Description: 修改填空题操作
	 * @param: @param gapItem
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateItem(GapItem gapItem);
	
	
	/***
	 * 
	 * @Title: getGapItemRandom   
	 * @Description: 随机获取指定数量的填空题  题目id
	 * @param: @param limit
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws
	 */
	public List<Integer> getGapItemRandom(int limit);
}
