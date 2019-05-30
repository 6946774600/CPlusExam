package com.cplusexam.service.impl.Item;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.Item.JudgeItem;
import com.cplusexam.dao.Item.JudgeItemMapper;
import com.cplusexam.service.Item.IJudgeItemService;


@Service
public class JudgeItemServiceImpl implements IJudgeItemService {

	@Autowired
	private JudgeItemMapper judgeItemMapper;

	@Override
	public boolean addJudgeItem(JudgeItem judgeItem) throws Exception {
		return judgeItemMapper.addJudgeItem(judgeItem)!=0;
	}

	@Override
	public int getCountByUserId(String userId) throws Exception {
		return judgeItemMapper.getCountByUserId(userId);
	}

	@Override
	public List<JudgeItem> getItemListForMy(String userId) throws Exception {
		return judgeItemMapper.getItemListForMy(userId);
	}

	@Override
	public int getItemCountForAll() throws Exception {
		return judgeItemMapper.getItemCountForAll();
	}

	@Override
	public List<Map<String, Object>> getItemListForAll() throws Exception {
		return judgeItemMapper.getItemListForAll();
	}

	@Override
	public JudgeItem getItemById(int id) throws Exception {
		return judgeItemMapper.getItemById(id);
	}

	@Override
	public boolean updateItem(JudgeItem judgeItem) {
		return judgeItemMapper.updateItem(judgeItem)!=0;
	}
	
}
