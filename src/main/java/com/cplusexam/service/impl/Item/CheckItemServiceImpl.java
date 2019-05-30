package com.cplusexam.service.impl.Item;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.Item.CheckItem;
import com.cplusexam.dao.Item.CheckItemMapper;
import com.cplusexam.service.Item.ICheckItemService;


@Service
public class CheckItemServiceImpl implements ICheckItemService {

	@Autowired
	private CheckItemMapper checkItemMapper;
	
	@Override
	public boolean addCheckItem(CheckItem checkItem) throws Exception {
		return checkItemMapper.addCheckItem(checkItem)!=0;
	}

	@Override
	public int getCountByUserId(String userId) throws Exception {
		return checkItemMapper.getCountByUserId(userId);
	}

	@Override
	public List<CheckItem> getItemListForMy(String userId) throws Exception {
		return checkItemMapper.getItemListForMy(userId);
	}

	@Override
	public int getItemCountForAll() throws Exception {
		return checkItemMapper.getItemCountForAll();
	}

	@Override
	public List<Map<String, Object>> getItemListForAll() throws Exception{
		return checkItemMapper.getItemListForAll();
	}

	@Override
	public CheckItem getItemById(int id) throws Exception {
		return checkItemMapper.getItemById(id);
	}

	@Override
	public boolean updateItem(CheckItem checkItem) throws Exception {
		return checkItemMapper.updateItem(checkItem)!=0;
	}

	@Override
	public String getOptionByIdAOption(Map<String, Object> map) throws Exception {
		return checkItemMapper.getOptionByIdAOption(map);
	}

	
}
