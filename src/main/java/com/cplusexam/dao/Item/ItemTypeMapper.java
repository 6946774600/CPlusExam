package com.cplusexam.dao.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.ItemType;

public interface ItemTypeMapper {

	
	/***
	 * 
	 * @Title: getItemTypeList   
	 * @Description: 获取选项维护页面的数据
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> getItemTypeList();
	
	
	/***
	 * 
	 * @Title: updateItemShow   
	 * @Description: 修改启用禁用按钮  
	 * @param: @param parm
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateItemShow(Map<String, Object> parm);
	
	
	/***
	 * 
	 * @Title: updateDefScore   
	 * @Description: 修改考题的默认分值  
	 * @param: @param parm
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateDefScore(Map<String, Object> parm);
	
	
	/***
	 * 
	 * @Title: getItemTypeAll   
	 * @Description: 获取考题类型的所有信息   
	 * @param: @return      
	 * @return: List<ItemType>      
	 * @throws
	 */
	public List<ItemType> getItemTypeAll();
	
	
	/***
	 * 
	 * @Title: getItemTypeCountEc   
	 * @Description: 获取考题类型与对应数量的echarts数据
	 * @param: @return      
	 * @return: List<Map<String,Integer>>      
	 * @throws
	 */
	public List<Map<String, Integer>> getItemTypeCountEc(); 
	
	
}
