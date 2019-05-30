package com.cplusexam.controller.Item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.bean.Item.CheckItem;
import com.cplusexam.bean.Item.GapItem;
import com.cplusexam.bean.Item.ItemExercise;
import com.cplusexam.bean.Item.JudgeItem;
import com.cplusexam.bean.Item.RadioItem;
import com.cplusexam.service.Item.ICheckItemService;
import com.cplusexam.service.Item.IGapItemService;
import com.cplusexam.service.Item.IItemExerciseService;
import com.cplusexam.service.Item.IItemTypeService;
import com.cplusexam.service.Item.IJudgeItemService;
import com.cplusexam.service.Item.IRadioItemService;
import com.cplusexam.util.Msg;
import com.cplusexam.util.ShiroSessionUtil;

@RequestMapping("/studentItem")
@Controller
public class StudentItemController {

	private Logger logger = Logger.getLogger(StudentItemController.class);
	
	
	@Autowired
	private IItemTypeService itemTypeService;
	
	@Autowired
	private IItemExerciseService itemExerciseService;
	
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
	 * @Title: getEcharts   
	 * @Description: 获取考题练习页面的echarts图标
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getItemTypeEc")
	@ResponseBody
	private Msg getEcharts() {
		Msg msg = new Msg();
		logger.info("查询考题练习页面统计数据");
		try {
			msg.addResult("itemTypeDate", itemTypeService.getItemTypeCountEc());
		} catch (Exception e) {
			logger.error("查询考题练习页面统计数据接口异常");
		}
		return msg;
	};
	
	
	/***
	 * 
	 * @Title: getRadioItemEc   
	 * @Description: 获取单选题统计图相关数据
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getRadioItemEc")
	@ResponseBody
	public Msg getRadioItemEc() {
		Msg msg = new Msg();
		int surplusCount =0;
		int exercised = 0;
		try {
			logger.info("获取单选题相关统计数据");
			//剩余练习数
			surplusCount = itemExerciseService.getRadioSurplusCount(ShiroSessionUtil.getLoginUser().getLoginName());
			//获取已练习数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", ShiroSessionUtil.getLoginUser().getLoginName());
			map.put("item_typeid", 1);
			exercised = itemExerciseService.getExercisedCount(map);
			//获取正确和错误率
			Map<String, Integer> tof = itemExerciseService.getExercisedTOFEc(map);
			//当数据库字段为 int 型(有符号int型)，但是如果在 sql 中使用了某些函数，jdbc 会自动转型为 long，例如：select sum(money) from account where accountId = 123。
			msg.addResult("trueCount", tof.get("trueCount"));
			msg.addResult("falseCount", tof.get("falseCount"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("单选题相关统计数据获取异常");
		}
		msg.addResult("surplusCount", surplusCount);
		msg.addResult("exercised", exercised);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: toRadioItemPage   
	 * @Description: 跳转单选题练习页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/toRadioExercisePage")
	public String toRadioExercisePage() {
		return "student/itemExercise/radioExercisePage";
	}
	
	
	/***
	 * 
	 * @Title: getRadioItemOne   
	 * @Description: 获取当前用户还没有练习过的单选题  获取一道
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getRadioItemOne")
	@ResponseBody
	public Msg getRadioItemOne() {
		Msg msg = new Msg();
		try {
			logger.info("获取一道登录用户未练习的单选题");
			msg.addResult("radio", itemExerciseService.getRadioItemOne(ShiroSessionUtil.getLoginUser().getLoginName()));
		} catch (Exception e) {
			logger.error("获取一道登录用户未练习的单选题接口异常");
		}
		return msg;
	}
	
	
	@RequestMapping("/addRadioExercise")
	@ResponseBody
	public Msg addRadioExercise(ItemExercise itemExercise) {
		Msg msg = new Msg();
		//进行该用户已练习题目的录入  并返回用户输入的答案是否正确
		itemExercise.setUser_id(ShiroSessionUtil.getLoginUser().getLoginName());
		itemExercise.setCreate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		itemExercise.setItem_type(1);  //题型为单选题
		try {
			//获取当前选题的所有信息
			RadioItem radioItem = radioItemService.getItemById(itemExercise.getItem_id());
			//进行正确答案的判断  0代表错误 1代表正确
			if(itemExercise.getUser_option().equals(radioItem.getItem_option())) {
				itemExercise.setOption_tof(1);
			}else {
				itemExercise.setOption_tof(0);
			}
			//进行已练习题目的添加
			logger.info("进行已练习单选题的添加");
			itemExerciseService.addExercise(itemExercise);
			msg.setMsg("添加成功");
			if(itemExercise.getOption_tof()==0)
				msg.addResult("tof", "<font color='#FF5722'>答案错误</font>");
			else
				msg.addResult("tof", "<font color='#FFB800'>答案正确</font>");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("option", radioItem.getItem_option());
			map.put("uuid", itemExercise.getItem_id());
			msg.addResult("radioOption", radioItemService.getOptionByIdAOption(map));
			map.put("option", itemExercise.getUser_option());
			msg.addResult("userOption", radioItemService.getOptionByIdAOption(map));
		} catch (Exception e) {
			logger.error("已练习单选题添加异常");
			msg.setMsg("添加接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getRadioSurplusCount   
	 * @Description: 获取单选题剩余的数量
	 * @param: @return   
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getRadioSurplusCount")
	@ResponseBody
	public Msg getRadioSurplusCount() {
		Msg msg = new Msg();
		logger.info("获取当前用户剩余的单选题数量");
		try {
			msg.addResult("count", itemExerciseService.getRadioSurplusCount(ShiroSessionUtil.getLoginUser().getLoginName()));
		} catch (Exception e) {
			logger.error("当前用户剩余的单选题数量获取异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: clsRadioExercise   
	 * @Description: 清除单选题已经练习过的题目 
	 * @param: @return   
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/clsRadioExercise")
	@ResponseBody
	public Msg clsRadioExercise() {
		Msg msg = new Msg();
		logger.info("进行当前用户清除已练习单选题操作");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", ShiroSessionUtil.getLoginUser().getLoginName());
		map.put("item_typeid", 1);
		try {
			itemExerciseService.deleteExercise(map);
			msg.setMsg("已清空单选题已练习记录");
		} catch (Exception e) {
			logger.error("当前用户清除已练习单选题操作接口异常");
			msg.setMsg("删除接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: clsCheckExercise   
	 * @Description: 清除当前用户已经练习的多选题信息
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/clsCheckExercise")
	@ResponseBody
	public Msg clsCheckExercise() {
		Msg msg = new Msg();
		logger.info("进行当前用户清除已练习多选题操作");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", ShiroSessionUtil.getLoginUser().getLoginName());
		map.put("item_typeid", 2);   //2为多选题
		try {
			itemExerciseService.deleteExercise(map);
			msg.setMsg("已清空多选题已练习记录");
		} catch (Exception e) {
			logger.error("当前用户清除已练习多选题操作接口异常");
			msg.setMsg("删除接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: toCheckExercisePage   
	 * @Description: 返回多选题练习页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/toCheckExercisePage")
	public String toCheckExercisePage() {
		return "student/itemExercise/checkExercisePage";
	}
	
	
	/***
	 * 
	 * @Title: getCheckItemOne   
	 * @Description: 随机获取一道用户还没有练习的多选题
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getCheckItemOne")
	@ResponseBody
	public Msg getCheckItemOne() {
		Msg msg = new Msg();
		try {
			logger.info("获取一道登录用户未练习的多选题");
			msg.addResult("check", itemExerciseService.getCheckItemOne(ShiroSessionUtil.getLoginUser().getLoginName()));
		} catch (Exception e) {
			logger.error("获取一道登录用户未练习的多选题接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getCheckSurplusCount   
	 * @Description: 获取当前用户还没有联系完成的多选题数量 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getCheckSurplusCount")
	@ResponseBody
	public Msg getCheckSurplusCount() {
		Msg msg = new Msg();
		logger.info("获取当前用户剩余的单选题数量");
		try {
			msg.addResult("count", itemExerciseService.getCheckSurplusCount(ShiroSessionUtil.getLoginUser().getLoginName()));
		} catch (Exception e) {
			logger.error("当前用户剩余的单选题数量获取异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: addCheckExercise   
	 * @Description: 进行已练习多选题的添加操作  并判断用户输入的结果是否正确
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addCheckExercise")
	@ResponseBody
	public Msg addCheckExercise(ItemExercise itemExercise) {
		Msg msg = new Msg();
		//进行该用户已练习题目的录入  并返回用户输入的答案是否正确
		itemExercise.setUser_id(ShiroSessionUtil.getLoginUser().getLoginName());
		itemExercise.setCreate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		itemExercise.setItem_type(2);  //题型为多选题
		try {
			CheckItem checkItem = checkItemService.getItemById(itemExercise.getItem_id());
			//进行正确答案的判断
			if(checkItem.getItem_option().equals(itemExercise.getUser_option()))
				itemExercise.setOption_tof(1);
			else
				itemExercise.setOption_tof(0);
			logger.info("进行用户已练习多选题的添加操作");
			itemExerciseService.addExercise(itemExercise);
			//获取相关返回信息
			if(itemExercise.getOption_tof()==0)
				msg.addResult("tof", "<font color='#FF5722'>答案错误</font>");
			else
				msg.addResult("tof", "<font color='#FFB800'>答案正确</font>");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("option", checkItem.getItem_option());
			map.put("uuid", itemExercise.getItem_id());
			msg.addResult("checkOption", checkItemService.getOptionByIdAOption(map));
			map.put("option", itemExercise.getUser_option());
			msg.addResult("userOption", checkItemService.getOptionByIdAOption(map));
		} catch (Exception e) {
			logger.error("用户已练习多选题的添加接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getCheckItemEc   
	 * @Description: 获取多选题先关echarts图表信息
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getCheckItemEc")
	@ResponseBody
	public Msg getCheckItemEc() {
		Msg msg = new Msg();
		int surplusCount =0;
		int exercised = 0;
		try {
			logger.info("获取多选题相关统计数据");
			//剩余练习数
			surplusCount = itemExerciseService.getCheckSurplusCount(ShiroSessionUtil.getLoginUser().getLoginName());
			//获取已练习数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", ShiroSessionUtil.getLoginUser().getLoginName());
			map.put("item_typeid", 2);
			exercised = itemExerciseService.getExercisedCount(map);
			//获取正确和错误率
			Map<String, Integer> tof = itemExerciseService.getExercisedTOFEc(map);
			//当数据库字段为 int 型(有符号int型)，但是如果在 sql 中使用了某些函数，jdbc 会自动转型为 long，例如：select sum(money) from account where accountId = 123。
			msg.addResult("trueCount", tof.get("trueCount"));
			msg.addResult("falseCount", tof.get("falseCount"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("多选题相关统计数据获取异常");
		}
		msg.addResult("surplusCount", surplusCount);
		msg.addResult("exercised", exercised);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: toGapExercisePage   
	 * @Description: 跳转填空题练习页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/toGapExercisePage")
	public String toGapExercisePage() {
		return "student/itemExercise/gapExercisePage";
	}
	
	
	/***
	 * 
	 * @Title: clsGapExercise   
	 * @Description: 清除当前登录用户的填空题练习记录
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/clsGapExercise")
	@ResponseBody
	public Msg clsGapExercise() {
		Msg msg = new Msg();
		logger.info("进行当前用户清除已练习填空题操作");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", ShiroSessionUtil.getLoginUser().getLoginName());
		map.put("item_typeid", 3);
		try {
			itemExerciseService.deleteExercise(map);
			msg.setMsg("已清空填空题已练习记录");
		} catch (Exception e) {
			logger.error("当前用户清除已练习填空题操作接口异常");
			msg.setMsg("删除接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getGapItemOne   
	 * @Description:随机获取一道当前用户还没有练习过的填空题
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getGapItemOne")
	@ResponseBody
	public Msg getGapItemOne() {
		Msg msg = new Msg();
		try {
			logger.info("获取一道登录用户未练习的填空题");
			msg.addResult("gap", itemExerciseService.getGapItemOne(ShiroSessionUtil.getLoginUser().getLoginName()));
		} catch (Exception e) {
			logger.error("获取一道登录用户未练习的填空题接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getGapSurplusCount   
	 * @Description: 获取当前用户还没有练习过的填空题的数量
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getGapSurplusCount")
	@ResponseBody
	public Msg getGapSurplusCount() {
		Msg msg = new Msg();
		logger.info("获取当前用户剩余的填空题数量");
		try {
			msg.addResult("count", itemExerciseService.getGapSurplusCount(ShiroSessionUtil.getLoginUser().getLoginName()));
		} catch (Exception e) {
			logger.error("当前用户剩余的填空题数量获取异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: addGapExercise   
	 * @Description: 进行已练习填空题的添加操作  并判断用户输入的结果是否正确
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addGapExercise")
	@ResponseBody
	public Msg addGapExercise(ItemExercise itemExercise) {
		Msg msg = new Msg();
		//进行该用户已练习题目的录入  并返回用户输入的答案是否正确
		itemExercise.setUser_id(ShiroSessionUtil.getLoginUser().getLoginName());
		itemExercise.setCreate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		itemExercise.setItem_type(3);  //题型为填空题
		try {
			GapItem gapItem = gapItemService.getItemById(itemExercise.getItem_id());
			//进行正确答案的判断
			if(gapItem.getItem_option().equals(itemExercise.getUser_option()))
				itemExercise.setOption_tof(1);
			else
				itemExercise.setOption_tof(0);
			logger.info("进行用户已练习填空题的添加操作");
			itemExerciseService.addExercise(itemExercise);
			//获取相关返回信息
			if(itemExercise.getOption_tof()==0)
				msg.addResult("tof", "<font color='#FF5722'>答案错误</font>");
			else
				msg.addResult("tof", "<font color='#FFB800'>答案正确</font>");
			msg.addResult("checkOption", gapItem.getItem_option());
			msg.addResult("userOption", itemExercise.getUser_option());
		} catch (Exception e) {
			logger.error("用户已练习填空题的添加接口异常");
		}
		return msg;
	}
	
	
	@RequestMapping("/getGapItemEc")
	@ResponseBody
	public Msg getGapItemEc() {
		Msg msg = new Msg();
		int surplusCount =0;
		int exercised = 0;
		try {
			logger.info("获取填空题相关统计数据");
			//剩余练习数
			surplusCount = itemExerciseService.getGapSurplusCount(ShiroSessionUtil.getLoginUser().getLoginName());
			//获取已练习数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", ShiroSessionUtil.getLoginUser().getLoginName());
			map.put("item_typeid", 3);
			exercised = itemExerciseService.getExercisedCount(map);
			//获取正确和错误率
			Map<String, Integer> tof = itemExerciseService.getExercisedTOFEc(map);
			msg.addResult("trueCount", tof.get("trueCount"));
			msg.addResult("falseCount", tof.get("falseCount"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("填空题相关统计数据获取异常");
		}
		msg.addResult("surplusCount", surplusCount);
		msg.addResult("exercised", exercised);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: toJudgeExercisePage   
	 * @Description: 跳转判断题练习页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/toJudgeExercisePage")
	public String toJudgeExercisePage() {
		return "student/itemExercise/judgeExercisePage";
	}
	
	
	
	/***
	 * 
	 * @Title: clsJudgeExercise   
	 * @Description: 清除当前用户已经练习的判断题记录 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/clsJudgeExercise")
	@ResponseBody
	public Msg clsJudgeExercise() {
		Msg msg = new Msg();
		logger.info("进行当前用户清除已练习判断题操作");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", ShiroSessionUtil.getLoginUser().getLoginName());
		map.put("item_typeid", 4);
		try {
			itemExerciseService.deleteExercise(map);
			msg.setMsg("已清空判断题已练习记录");
		} catch (Exception e) {
			logger.error("当前用户清除已练习判断题操作接口异常");
			msg.setMsg("删除接口异常");
		}
		return msg;
	}
	
	
	@RequestMapping("/getJudgeItemOne")
	@ResponseBody
	public Msg getJudgeItemOne() {
		Msg msg = new Msg();
		try {
			logger.info("获取一道登录用户未练习的判断题");
			msg.addResult("judge", itemExerciseService.getJudgeItemOne(ShiroSessionUtil.getLoginUser().getLoginName()));
		} catch (Exception e) {
			logger.error("获取一道登录用户未练习的判断题接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getJudgeSurplusCount   
	 * @Description: 获取当前登录用户还没有练习的判断题数量
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getJudgeSurplusCount")
	@ResponseBody
	public Msg getJudgeSurplusCount() {
		Msg msg = new Msg();
		logger.info("获取当前用户剩余的判断题数量");
		try {
			msg.addResult("count", itemExerciseService.getJudgeSurplusCount(ShiroSessionUtil.getLoginUser().getLoginName()));
		} catch (Exception e) {
			logger.error("当前用户剩余的判断题数量获取异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: addJudgeExercise   
	 * @Description: 已练习判断题的添加操作 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addJudgeExercise")
	@ResponseBody
	public Msg addJudgeExercise(ItemExercise itemExercise) {
		Msg msg = new Msg();
		//进行该用户已练习题目的录入  并返回用户输入的答案是否正确
		itemExercise.setUser_id(ShiroSessionUtil.getLoginUser().getLoginName());
		itemExercise.setCreate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		itemExercise.setItem_type(4);  //题型为判断题
		try {
			JudgeItem judgeItem = judgeItemService.getItemById(itemExercise.getItem_id());
			//进行正确答案的判断
			if(judgeItem.getItem_option().equals(itemExercise.getUser_option()))
				itemExercise.setOption_tof(1);
			else
				itemExercise.setOption_tof(0);
			logger.info("进行用户已练习判断题的添加操作");
			itemExerciseService.addExercise(itemExercise);
			//获取相关返回信息
			if(itemExercise.getOption_tof()==0)
				msg.addResult("tof", "<font color='#FF5722'>答案错误</font>");
			else
				msg.addResult("tof", "<font color='#FFB800'>答案正确</font>");
			msg.addResult("checkOption", judgeItem.getItem_option().equals("true")?"✔":"✖");
			msg.addResult("userOption", itemExercise.getUser_option().equals("true")?"✔":"✖");
		} catch (Exception e) {
			logger.error("用户已练习判断题题的添加接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getJudgeItemEc   
	 * @Description: 获取当前用户判断题相关图表信息
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getJudgeItemEc")
	@ResponseBody
	public Msg getJudgeItemEc() {
		Msg msg = new Msg();
		int surplusCount =0;
		int exercised = 0;
		try {
			logger.info("获取判断题相关统计数据");
			//剩余练习数
			surplusCount = itemExerciseService.getGapSurplusCount(ShiroSessionUtil.getLoginUser().getLoginName());
			//获取已练习数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", ShiroSessionUtil.getLoginUser().getLoginName());
			map.put("item_typeid", 4);
			exercised = itemExerciseService.getExercisedCount(map);
			//获取正确和错误率
			Map<String, Integer> tof = itemExerciseService.getExercisedTOFEc(map);
			msg.addResult("trueCount", tof.get("trueCount"));
			msg.addResult("falseCount", tof.get("falseCount"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("判断题相关统计数据获取异常");
		}
		msg.addResult("surplusCount", surplusCount);
		msg.addResult("exercised", exercised);
		return msg;
	}
}
