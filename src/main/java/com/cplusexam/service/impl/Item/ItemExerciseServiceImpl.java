package com.cplusexam.service.impl.Item;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.Item.ItemExercise;
import com.cplusexam.dao.Item.ItemExerciseMapper;
import com.cplusexam.dao.Item.RadioItemMapper;
import com.cplusexam.service.Item.IItemExerciseService;

@Service
public class ItemExerciseServiceImpl implements IItemExerciseService {

	@Autowired
	private ItemExerciseMapper itemExerciseMapper;
	

	@Override
	public Map<String, Object> getRadioItemOne(String LoginName) throws Exception{
		//先获取当前用户还没有练习过的单选题
		//判断选择题中   id没有在集合中的所有数据
		List<Map<String, Object>> list = itemExerciseMapper.getRadioItemOne(LoginName);
		//根据返回数据的长度  生成随机数  随机生成一道选择题
		int size = list.size();
		int radom = (int)(Math.random()*size);
		Map<String, Object> map = list.get(radom);
		map.put("item_option", "");
		return map;
	}


	@Override
	public boolean addExercise(ItemExercise itemExercise) throws Exception {
		return itemExerciseMapper.addExercise(itemExercise)!=0;
	}


	@Override
	public int getRadioSurplusCount(String userId) throws Exception {
		return itemExerciseMapper.getRadioSurplusCount(userId);
	}


	@Override
	public int deleteExercise(Map<String, Object> map) throws Exception {
		return itemExerciseMapper.deleteExercise(map);
	}


	@Override
	public int getExercisedCount(Map<String, Object> map) throws Exception{
		return itemExerciseMapper.getExercisedCount(map);
	}


	@Override
	public Map<String, Integer> getExercisedTOFEc(Map<String, Object> map) throws Exception {
		return itemExerciseMapper.getExercisedTOFEc(map);
	}


	@Override
	public Map<String, Object> getCheckItemOne(String LoginName) throws Exception {
		List<Map<String, Object>> list = itemExerciseMapper.getCheckItemOne(LoginName);
		int size = list.size();
		int radom = (int)(Math.random()*size);
		Map<String, Object> map = list.get(radom);
		map.put("item_option", "");
		return map;
	}


	@Override
	public int getCheckSurplusCount(String userId) throws Exception {
		return itemExerciseMapper.getCheckSurplusCount(userId);
	}


	@Override
	public Map<String, Object> getGapItemOne(String LoginName) throws Exception {
		List<Map<String, Object>> list = itemExerciseMapper.getGapItemOne(LoginName);
		int size = list.size();
		int radom = (int)(Math.random()*size);
		Map<String, Object> map = list.get(radom);
		map.put("item_option", "");
		return map;
	}


	@Override
	public int getGapSurplusCount(String userId) throws Exception {
		return itemExerciseMapper.getGapSurplusCount(userId);
	}


	@Override
	public Map<String, Object> getJudgeItemOne(String LoginName) throws Exception {
		List<Map<String, Object>> list = itemExerciseMapper.getJudgeItemOne(LoginName);
		int size = list.size();
		int radom = (int)(Math.random()*size);
		Map<String, Object> map = list.get(radom);
		map.put("item_option", "");
		return map;
	}


	@Override
	public int getJudgeSurplusCount(String userId) throws Exception {
		return itemExerciseMapper.getJudgeSurplusCount(userId);
	}
	
}
