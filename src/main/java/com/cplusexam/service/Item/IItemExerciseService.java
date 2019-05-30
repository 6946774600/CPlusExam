package com.cplusexam.service.Item;

import java.util.List;
import java.util.Map;

import com.cplusexam.bean.Item.ItemExercise;

public interface IItemExerciseService {

	/***
	 * 
	 * @Title: getRadioItemOne   
	 * @Description: 获取当前用户没有练习过的单选题   只获取一道
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> getRadioItemOne(String LoginName) throws Exception;
	
	
	
	/***
	 * 
	 * @Title: addExercise   
	 * @Description: 进行用户已练习题目的添加操作
	 * @param: @param itemExercise
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean addExercise(ItemExercise itemExercise) throws Exception;
	
	
	/***
	 * 
	 * @Title: getRadioSurplusCount   
	 * @Description:获取登录用户  剩余的单选题的数量
	 * @param: @param String
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getRadioSurplusCount(String userId) throws Exception;
	
	
	/***
	 * 
	 * @Title: deleteExercise   
	 * @Description: 删除当前 用户 指定考题的  练习历史记录
	 * @param: @param map
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int deleteExercise(Map<String, Object> map) throws Exception;
	
	
	/***
	 * 
	 * @Title: getExercisedCount   
	 * @Description: 获取 指定用户 指定题型  已练习的数量
	 * @param: @param map
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getExercisedCount(Map<String, Object> map) throws Exception;
	
	
	/***
	 * 
	 * @Title: getExercisedTOFEc   
	 * @Description: 获取指定用户 指定题型  正确数与错误数
	 * @param: @param map
	 * @param: @return      
	 * @return: Map<String,Integer>      
	 * @throws
	 */
	public Map<String, Integer> getExercisedTOFEc(Map<String, Object> map) throws Exception;
	
	
	/***
	 * 
	 * @Title: getCheckItemOne   
	 * @Description: 获取当前用户没有练习过的多选题   只获取一道
	 * @param: @return      
	 * @return: Map<String, Object>
	 * @throws
	 */
	public Map<String, Object> getCheckItemOne(String LoginName) throws Exception;
	
	
	/***
	 * 
	 * @Title: getCheckSurplusCount   
	 * @Description:获取登录用户  剩余的多选题的数量
	 * @param: @param String
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getCheckSurplusCount(String userId) throws Exception;
	
	
	/***
	 * 
	 * @Title: getGapItemOne   
	 * @Description: 获取当前用户没有练习过的填空题   只获取一道
	 * @param: @return      
	 * @return: Map<String, Object>
	 * @throws
	 */
	public Map<String, Object> getGapItemOne(String LoginName) throws Exception;
	
	
	/***
	 * 
	 * @Title: getGapSurplusCount   
	 * @Description:获取登录用户  剩余的填空题的数量
	 * @param: @param String
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getGapSurplusCount(String userId) throws Exception;
	
	
	/***
	 * 
	 * @Title: getJudgeItemOne   
	 * @Description: 获取当前用户没有练习过的判断题     只获取一道
	 * @param: @return      
	 * @return: Map<String, Object>
	 * @throws
	 */
	public Map<String, Object> getJudgeItemOne(String LoginName) throws Exception;
	
	
	/***
	 * 
	 * @Title: getJudgeSurplusCount   
	 * @Description:获取登录用户  剩余的判断题的数量
	 * @param: @param String
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int getJudgeSurplusCount(String userId) throws Exception;
	
}
