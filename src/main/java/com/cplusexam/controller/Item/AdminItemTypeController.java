package com.cplusexam.controller.Item;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.service.Item.IItemTypeService;
import com.cplusexam.util.Grid;
import com.cplusexam.util.Msg;

@RequestMapping("/adminItem")
@Controller
public class AdminItemTypeController {

	
	private Logger logger = Logger.getLogger(AdminItemTypeController.class);
	
	@Autowired
	private IItemTypeService itemTypeService;
	
	
	@RequestMapping("/getItemTypeList")
	@ResponseBody
	public Grid getItemTypeList() {
		Grid grid = new Grid();
		logger.info("获取提醒维护页面数据");
		grid.setdata(itemTypeService.getItemTypeList());
		return grid;
	}
	
	@RequestMapping("/updateItemShow")
	@ResponseBody
	public Msg updateItemShow(@RequestParam("id") int id ,@RequestParam("show_index")int show_index) {
		Msg msg = new Msg();
		Map<String, Object> parm = new HashMap<>();
		parm.put("id", id);
		parm.put("show_index", show_index);
		logger.info("进行考题启用禁用状态修改");
		msg.setSuccess(itemTypeService.updateItemShow(parm));
		return msg;
	}
	
	@RequestMapping("/updateDefScore")
	@ResponseBody
	public Msg updateDefScore(@RequestParam("id") int id ,@RequestParam("def_score")int def_score) {
		Msg msg = new Msg();
		Map<String, Object> parm = new HashMap<>();
		parm.put("id", id);
		parm.put("def_score", def_score);
		logger.info("进行考题默认分值修改");
		msg.setSuccess(itemTypeService.updateDefScore(parm));
		return msg;
	}
	
	
}
