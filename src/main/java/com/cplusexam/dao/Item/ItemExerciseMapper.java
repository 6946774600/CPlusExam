package com.cplusexam.dao.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.ItemExercise;

/***
 * 
 * @ClassName:  ItemExerciseMapper   
 * @Description:题库练习dao层
 * @author: FanD
 * @date:   2019年3月13日 上午9:48:44
 */
public interface ItemExerciseMapper {

	/***
	 * 
	 * @Title: getRadioItemOne   
	 * @Description: 获取当前用户没有练习过的单选题  
	 * @param: @return      
	 * @return: List<Map<String, Object>>   
	 * @throws
	 */
	public List<Map<String, Object>> getRadioItemOne(String LoginName);
	
	
	/***
	 * 
	 * @Title: addExercise   
	 * @Description: 进行用户已练习题目的添加操作
	 * @param: @param itemExercise
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int addExercise(ItemExercise itemExercise);
	
	
	/***
	 * 
	 * @Title: getRadioSurplusCount   
	 * @Description:获取登录用户  剩余的单选题的数量
	 * @param: @param String
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getRadioSurplusCount(String userId);
	
	
	/***
	 * 
	 * @Title: deleteExercise   
	 * @Description: 删除当前 用户 指定考题的  练习历史记录
	 * @param: @param map
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int deleteExercise(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: getExercisedCount   
	 * @Description: 获取 指定用户 指定题型  已练习的数量
	 * @param: @param map
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getExercisedCount(Map<String, Object> map);
	
	
	/***
	 * 
	 * @Title: getExercisedTOFEc   
	 * @Description: 获取指定用户 指定题型  正确数与错误数
	 * @param: @param map
	 * @param: @return      
	 * @return: Map<String,Integer>      
	 * @throws
	 */
	public Map<String, Integer> getExercisedTOFEc(Map<String, Object> map);
	
	
	
	/***
	 * 
	 * @Title: getCheckItemOne   
	 * @Description: 获取当前用户没有练习过的多选题   
	 * @param: @return      
	 * @return: List<Map<String, Object>>   
	 * @throws
	 */
	public List<Map<String, Object>> getCheckItemOne(String LoginName);
	
	
	/***
	 * 
	 * @Title: getCheckSurplusCount   
	 * @Description:获取登录用户  剩余的多选题的数量
	 * @param: @param String
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getCheckSurplusCount(String userId);
	
	
	/***
	 * 
	 * @Title: getGapItemOne   
	 * @Description: 获取当前用户没有练习过的填空题   
	 * @param: @return      
	 * @return: List<Map<String, Object>>   
	 * @throws
	 */
	public List<Map<String, Object>> getGapItemOne(String LoginName);
	
	
	/***
	 * 
	 * @Title: getGapSurplusCount   
	 * @Description:获取登录用户  剩余的填空题的数量
	 * @param: @param String
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getGapSurplusCount(String userId);
	
	
	/***
	 * 
	 * @Title: getJudgeItemOne   
	 * @Description: 获取当前用户没有练习过的判断题   
	 * @param: @return      
	 * @return: List<Map<String, Object>>   
	 * @throws
	 */
	public List<Map<String, Object>> getJudgeItemOne(String LoginName);
	
	
	/***
	 * 
	 * @Title: getJudgeSurplusCount   
	 * @Description:获取登录用户  剩余的判断题的数量
	 * @param: @param String
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getJudgeSurplusCount(String userId);
}
