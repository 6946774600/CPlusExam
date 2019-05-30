package com.cplusexam.service.impl.Item;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.Item.GapItem;
import com.cplusexam.dao.Item.GapItemMapper;
import com.cplusexam.service.Item.IGapItemService;

@Service
public class GapItemServiceImpl implements IGapItemService {

	@Autowired
	private GapItemMapper gapItemMapper;

	@Override
	public boolean addGapItem(GapItem gapItem)  throws Exception{
		return gapItemMapper.addGapItem(gapItem)!=0;
	}

	@Override
	public int getCountByUserId(String userId) throws Exception {
		return gapItemMapper.getCountByUserId(userId);
	}

	@Override
	public List<GapItem> getItemListForMy(String userId) throws Exception {
		return gapItemMapper.getItemListForMy(userId);
	}

	@Override
	public int getItemCountForAll() throws Exception {
		return gapItemMapper.getItemCountForAll();
	}

	@Override
	public List<Map<String, Object>> getItemListForAll() throws Exception {
		return gapItemMapper.getItemListForAll();
	}

	@Override
	public GapItem getItemById(int id) throws Exception {
		return gapItemMapper.getItemById(id);
	}

	@Override
	public boolean updateItem(GapItem gapItem) {
		return gapItemMapper.updateItem(gapItem)!=0;
	}
	
}
