package com.cplusexam.controller.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.service.Item.ICheckItemService;
import com.cplusexam.service.Item.IGapItemService;
import com.cplusexam.service.Item.IJudgeItemService;
import com.cplusexam.service.Item.IRadioItemService;
import com.cplusexam.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/***
 * 
 * @ClassName:  AdminItemListController   
 * @Description:处理管理员界面所有考题的请求   
 * @author: FanD
 * @date:   2019年3月4日 下午3:26:06
 */

@RequestMapping("/adminItemList")
@Controller
public class AdminItemListController {

	
	private Logger logger = Logger.getLogger(AdminItemListController.class);
	
	@Autowired
	private IRadioItemService radioItemService;

	@Autowired
	private ICheckItemService checkItemService;

	@Autowired
	private IGapItemService gapItemService;
	
	@Autowired
	private IJudgeItemService judgeItemService;
	
	
	/***
	 * 
	 * @Title: getItemCountForAll   
	 * @Description: 获取当前教师，录入的选题的数量
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getItemCountForAll")
	@ResponseBody
	public Msg getItemCountForAll() {
		Msg msg = new Msg();
		logger.info("获取所有选题数量信息");
		Map<String, Object> res = new HashMap<>();
		try {
			res.put("radioCount", radioItemService.getItemCountForAll());
			res.put("checkCount", checkItemService.getItemCountForAll());
			res.put("gapCount", gapItemService.getItemCountForAll());
			res.put("judgeCount", judgeItemService.getItemCountForAll());
			msg.setSuccess(true);
			msg.addResult("count", res);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("入库题数数据接口获取异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getRadioItemListForAll   
	 * @Description:获取所有的单选题 
	 * @param: @param limit
	 * @param: @param curr
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getRadioItemListForAll")
	@ResponseBody
	public Msg getRadioItemListForAll(@RequestParam("limit")int limit,@RequestParam("curr") int curr) {
		Msg msg = new Msg();
		logger.info("获取所有的单选题");
		try {
			//进行分页
			PageHelper.startPage(curr, limit);
			List<Map<String, Object>> radios = radioItemService.getItemListForAll();
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(radios);
			msg.addResult("radios", pageInfo.getList());
		} catch (Exception e) {
			
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getCheckItemListForAll   
	 * @Description:获取所有的多选题信息
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getCheckItemListForAll")
	@ResponseBody
	public Msg getCheckItemListForAll(@RequestParam("limit")int limit,@RequestParam("curr") int curr) {
		Msg msg = new Msg();
		logger.info("获取所有多选题");
		try {
			//进行分页
			PageHelper.startPage(curr, limit);
			List<Map<String, Object>> radios = checkItemService.getItemListForAll();
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(radios);
			msg.addResult("checks", pageInfo.getList());
		} catch (Exception e) {
			
		}
		return msg;
	}
	
	
	
	/***
	 * 
	 * @Title: getGapItemListForALL   
	 * @Description: 获取所有的填空题信息
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getGapItemListForALL")
	@ResponseBody
	public Msg getGapItemListForALL(@RequestParam("limit")int limit,@RequestParam("curr") int curr) {
		Msg msg = new Msg();
		logger.info("获取所有的填空题题");
		try {
			//进行分页
			PageHelper.startPage(curr, limit);
			List<Map<String, Object>> radios = gapItemService.getItemListForAll();
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(radios);
			msg.addResult("gaps", pageInfo.getList());
		} catch (Exception e) {
			
		}
		return msg;
	}
	
	
	
	/***
	 * 
	 * @Title: getJudgeItemListForALL   
	 * @Description: 获取当所有的判断题
	 * @param: @param limit
	 * @param: @param curr
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getJudgeItemListForALL")
	@ResponseBody
	public Msg getJudgeItemListForALL(@RequestParam("limit")int limit,@RequestParam("curr") int curr) {
		Msg msg = new Msg();
		logger.info("获取所有的判断题");
		try {
			//进行分页
			PageHelper.startPage(curr, limit);
			List<Map<String, Object>> radios = judgeItemService.getItemListForAll();
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(radios);
			msg.addResult("judges", pageInfo.getList());
		} catch (Exception e) {
			
		}
		return msg;
	}
	
}
