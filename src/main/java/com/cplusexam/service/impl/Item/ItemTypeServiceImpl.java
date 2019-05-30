package com.cplusexam.service.impl.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.Item.ItemType;
import com.cplusexam.dao.Item.ItemTypeMapper;
import com.cplusexam.service.Item.IItemTypeService;

@Service
public class ItemTypeServiceImpl implements IItemTypeService {

	@Autowired
	private ItemTypeMapper itemTypeMapper;
	
	@Override
	public List<Map<String, Object>> getItemTypeList() {
		return itemTypeMapper.getItemTypeList();
	}

	@Override
	public boolean updateItemShow(Map<String, Object> parm) {
		return itemTypeMapper.updateItemShow(parm)!=0;
	}

	@Override
	public boolean updateDefScore(Map<String, Object> parm) {
		return itemTypeMapper.updateDefScore(parm)!=0;
	}

	@Override
	public List<ItemType> getItemTypeAll() throws Exception {
		return itemTypeMapper.getItemTypeAll();
	}

	@Override
	public List<Map<String, Object>> getItemTypeTree() throws Exception {
		List<ItemType> lists = itemTypeMapper.getItemTypeAll();
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		if(lists.size()>0) {
			for (ItemType itemType : lists) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", itemType.getItem_id());
				map.put("name",itemType.getItemType_name());
				res.add(map);
			}
		}
		return res;
	}

	@Override
	public List<Map<String, Integer>> getItemTypeCountEc() throws Exception {
		return itemTypeMapper.getItemTypeCountEc();
	}

}
