package com.cplusexam.controller.Item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.bean.Item.CheckItem;
import com.cplusexam.bean.Item.GapItem;
import com.cplusexam.bean.Item.JudgeItem;
import com.cplusexam.bean.Item.RadioItem;
import com.cplusexam.service.Item.ICheckItemService;
import com.cplusexam.service.Item.IGapItemService;
import com.cplusexam.service.Item.IJudgeItemService;
import com.cplusexam.service.Item.IRadioItemService;
import com.cplusexam.service.util.IUpLoadFileService;
import com.cplusexam.util.Msg;
import com.cplusexam.util.ShiroSessionUtil;

/***
 * 
 * @ClassName: TeacherItemInputController
 * @Description:处理教师进行选题的录入请求
 * @author: FanD
 * @date: 2019年2月26日 上午11:39:22
 */

@Controller
@RequestMapping("/teacherItemInput")
public class TeacherItemInputController {

	private Logger logger = Logger.getLogger(TeacherItemInputController.class);

	@Autowired
	private IRadioItemService radioItemService;

	@Autowired
	private ICheckItemService checkItemService;

	@Autowired
	private IUpLoadFileService uploadFileService;
	
	@Autowired
	private IGapItemService gapItemService;
	
	@Autowired
	private IJudgeItemService judgeItemService;
	
	
	@RequestMapping("/addRadioItem")
	@ResponseBody
	public Msg addRadioItem(RadioItem radioItem) {
		Msg msg = new Msg();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		radioItem.setCreate_time(sdf.format(new Date()));
		radioItem.setCreate_teacherId(ShiroSessionUtil.getLoginUser().getLoginName());
		logger.info("进行单选题的添加操作");
		try {
			msg.setSuccess(radioItemService.addRadioItem(radioItem));
			if (msg.isSuccess()) {
				msg.setMsg("添加成功。");
				// 如果文件不为空 修改文件使用状态
				if (radioItem.getItem_imageId() != -1) {
					Map<String, Object> parm = new HashMap<>();
					parm.put("fileId", radioItem.getItem_imageId());
					parm.put("used", 1);
					uploadFileService.updateFileUsedById(parm);
				}
			} else
				msg.setMsg("添加失败");
		} catch (Exception e) {
			logger.info("单选题添加接口异常");
			msg.setSuccess(false);
			msg.setMsg("添加接口调用失败");
		}
		return msg;
	}

	@RequestMapping("/addCheckItem")
	@ResponseBody
	public Msg addCheckItem(CheckItem checkItem) {
		Msg msg = new Msg();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		checkItem.setCreate_time(sdf.format(new Date()));
		checkItem.setCreate_teacherId(ShiroSessionUtil.getLoginUser().getLoginName());
		logger.info("进行多选题的添加操作");

		try {
			msg.setSuccess(checkItemService.addCheckItem(checkItem));
			if (msg.isSuccess()) {
				msg.setMsg("添加成功。"); // 如果文件不为空 修改文件使用状态
				if (checkItem.getItem_imageId() != -1) {
					Map<String, Object> parm = new HashMap<>();
					parm.put("fileId", checkItem.getItem_imageId());
					parm.put("used", 1);
					uploadFileService.updateFileUsedById(parm);
				}
			} else
				msg.setMsg("添加失败");
		} catch (Exception e) {
			logger.info("单选题添加接口异常");
			msg.setSuccess(false);
			msg.setMsg("添加接口调用失败");
		}

		return msg;
	}
	
	@RequestMapping("/addGapItem")
	@ResponseBody
	public Msg addGapItem(GapItem gapItem) {
		Msg msg = new Msg();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		gapItem.setCreate_time(sdf.format(new Date()));
		gapItem.setCreate_teacherId(ShiroSessionUtil.getLoginUser().getLoginName());
		logger.info("进行填空题的添加操作");
		try {
			msg.setSuccess(gapItemService.addGapItem(gapItem));
			if (msg.isSuccess()) {
				msg.setMsg("添加成功。"); // 如果文件不为空 修改文件使用状态
				if (gapItem.getItem_imageId() != -1) {
					Map<String, Object> parm = new HashMap<>();
					parm.put("fileId", gapItem.getItem_imageId());
					parm.put("used", 1);
					uploadFileService.updateFileUsedById(parm);
				}
			} else
				msg.setMsg("添加失败");
		} catch (Exception e) {
			logger.info("填空题添加接口异常");
			msg.setSuccess(false);
			msg.setMsg("添加接口调用失败");
		}
		
		return msg;
	}
	
	
	/**
	 * 
	 * @Title: addJudgeItem   
	 * @Description:判断题的添加操作   因为页面功能的原因 这里的答案需要用另一个字段接收  并设置
	 * @param: @param judgeItem
	 * @param: @param item_option1
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addJudgeItem")
	@ResponseBody
	public Msg addJudgeItem(JudgeItem judgeItem,@RequestParam("item_option1") String item_option1) {
		Msg msg = new Msg();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		judgeItem.setCreate_time(sdf.format(new Date()));
		judgeItem.setCreate_teacherId(ShiroSessionUtil.getLoginUser().getLoginName());
		judgeItem.setItem_option(item_option1);   //手动设置正确答案的值
		logger.info("进行判断题的添加操作");
		try {
			msg.setSuccess(judgeItemService.addJudgeItem(judgeItem));
			if (msg.isSuccess()) {
				msg.setMsg("添加成功。"); // 如果文件不为空 修改文件使用状态
				if (judgeItem.getItem_imageId() != -1) {
					Map<String, Object> parm = new HashMap<>();
					parm.put("fileId", judgeItem.getItem_imageId());
					parm.put("used", 1);
					uploadFileService.updateFileUsedById(parm);
				}
			} else
				msg.setMsg("添加失败");
		} catch (Exception e) {
			logger.info("判断题添加接口异常");
			msg.setSuccess(false);
			msg.setMsg("添加接口调用失败");
		}
		return msg;
	}
}
