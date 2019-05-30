package com.cplusexam.service.impl.Item;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.Item.RadioItem;
import com.cplusexam.dao.Item.RadioItemMapper;
import com.cplusexam.service.Item.IRadioItemService;

@Service
public class RadioItemServiceImpl implements IRadioItemService {

	@Autowired
	private RadioItemMapper radioItemMapper;

	@Override
	public boolean addRadioItem(RadioItem radioItem) throws Exception {
		return radioItemMapper.addRadioItem(radioItem)!=0;
	}

	@Override
	public int getCountByUserId(String userId) throws Exception {
		return radioItemMapper.getCountByUserId(userId);
	}

	@Override
	public List<RadioItem> getItemListForMy(String userId) throws Exception{
		return radioItemMapper.getItemListForMy(userId);
	}

	@Override
	public int getItemCountForAll() throws Exception {
		return radioItemMapper.getItemCountForAll();
	}

	@Override
	public List<Map<String, Object>> getItemListForAll() throws Exception {
		return radioItemMapper.getItemListForAll();
	}

	@Override
	public RadioItem getItemById(int id) throws Exception {
		return radioItemMapper.getItemById(id);
	}

	@Override
	public boolean updateItem(RadioItem radioItem) throws Exception {
		return radioItemMapper.updateItem(radioItem)!=0;
	}

	@Override
	public String getOptionByIdAOption(Map<String, Object> map) throws Exception {
		return radioItemMapper.getOptionByIdAOption(map);
	}
	
	
}
