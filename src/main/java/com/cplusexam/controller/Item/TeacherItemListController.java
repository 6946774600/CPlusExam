package com.cplusexam.controller.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/***
 * 
 * @ClassName:  TeacherItemListController   
 * @Description:处理教师查看题库页面所有请求
 * @author: FanD
 * @date:   2019年2月27日 上午11:05:58
 */
@RequestMapping("/teacherItemList")
@Controller
public class TeacherItemListController {

	private Logger logger = Logger.getLogger(TeacherItemListController.class);
	
	@Autowired
	private IRadioItemService radioItemService;

	@Autowired
	private ICheckItemService checkItemService;

	@Autowired
	private IGapItemService gapItemService;
	
	@Autowired
	private IJudgeItemService judgeItemService;
	
	@Autowired
	private IUpLoadFileService uploadFileService;
	
	/***
	 * 
	 * @Title: getItemCountForMy   
	 * @Description: 获取当前教师，录入的选题的数量
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getItemCountForMy")
	@ResponseBody
	public Msg getItemCountByTeacherId() {
		Msg msg = new Msg();
		logger.info("获取当前教师录入的选题数量信息");
		Map<String, Object> res = new HashMap<>();
		String userId = ShiroSessionUtil.getLoginUser().getLoginName();
		try {
			res.put("radioCount", radioItemService.getCountByUserId(userId));
			res.put("checkCount", checkItemService.getCountByUserId(userId));
			res.put("gapCount", gapItemService.getCountByUserId(userId));
			res.put("judgeCount", judgeItemService.getCountByUserId(userId));
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
	 * @Title: getItemListForMy   
	 * @Description: 获取当前用户录入的单选题
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getRadioItemListForMy")
	@ResponseBody
	public Msg getItemListForMy(@RequestParam("limit")int limit,@RequestParam("curr") int curr) {
		Msg msg = new Msg();
		logger.info("获取当前用户录入的单选题");
		try {
			//进行分页
			PageHelper.startPage(curr, limit);
			List<RadioItem> radios = radioItemService.getItemListForMy(ShiroSessionUtil.getLoginUser().getLoginName());
			PageInfo<RadioItem> pageInfo = new PageInfo<>(radios);
			msg.addResult("radios", pageInfo.getList());
		} catch (Exception e) {
			
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
	 * @Title: getCheckItemListForMy   
	 * @Description: 获取当前用户录入的多选题
	 * @param: @param limit
	 * @param: @param curr
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getCheckItemListForMy")
	@ResponseBody
	public Msg getCheckItemListForMy(@RequestParam("limit")int limit,@RequestParam("curr") int curr) {
		Msg msg = new Msg();
		logger.info("获取当前用户录入的多选题");
		try {
			//进行分页
			PageHelper.startPage(curr, limit);
			List<CheckItem> radios = checkItemService.getItemListForMy(ShiroSessionUtil.getLoginUser().getLoginName());
			PageInfo<CheckItem> pageInfo = new PageInfo<>(radios);
			msg.addResult("checks", pageInfo.getList());
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
	 * @Title: getGapItemListForMy   
	 * @Description: 获取当前用户录入的填空题
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getGapItemListForMy")
	@ResponseBody
	public Msg getGapItemListForMy(@RequestParam("limit")int limit,@RequestParam("curr") int curr) {
		Msg msg = new Msg();
		logger.info("获取当前用户录入的填空题");
		try {
			//进行分页
			PageHelper.startPage(curr, limit);
			List<GapItem> radios = gapItemService.getItemListForMy(ShiroSessionUtil.getLoginUser().getLoginName());
			PageInfo<GapItem> pageInfo = new PageInfo<>(radios);
			msg.addResult("gaps", pageInfo.getList());
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
	 * @Title: getRadioItemListForMy   
	 * @Description: 获取当前用户录入的判断题
	 * @param: @param limit
	 * @param: @param curr
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getJudgeItemListForMy")
	@ResponseBody
	public Msg getJudgeItemListForMy(@RequestParam("limit")int limit,@RequestParam("curr") int curr) {
		Msg msg = new Msg();
		logger.info("获取当前用户录入的判断题");
		try {
			//进行分页
			PageHelper.startPage(curr, limit);
			List<JudgeItem> radios = judgeItemService.getItemListForMy(ShiroSessionUtil.getLoginUser().getLoginName());
			PageInfo<JudgeItem> pageInfo = new PageInfo<>(radios);
			msg.addResult("judges", pageInfo.getList());
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
	
	
	/***
	 * 
	 * @Title: editRadioPage   
	 * @Description: 跳转单选题修改页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/editRadioPage/{id}")
	public String toEditRadioPage(@PathVariable("id")int id,Model model) {
		model.addAttribute("id", id);
		return "teacher/itemPool/editRadioPage";
	}
	
	
	/***
	 * 
	 * @Title: getRadioItemById   
	 * @Description: 根据考题id  获取当前id对应的单选题信息 
	 * @param: @param id
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getRadioItemById")
	@ResponseBody
	public Msg getRadioItemById(@RequestParam("id")int id) {
		 Msg msg = new Msg();
		 logger.info("获取单选题，考题id为"+id);
		 try {
			msg.addResult("radio", radioItemService.getItemById(id));
		} catch (Exception e) {
			logger.error("单选题获取接口异常");
		}
		 return msg;
	} 
	
	
	/***
	 * 
	 * @Title: updateRadioItem   
	 * @Description: 进行单选题的修改操作
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateRadioItem/{id}")
	@ResponseBody
	public Msg updateRadioItem(@PathVariable("id") int id , RadioItem radioItem) {
		Msg msg = new Msg();
		radioItem.setUuid(id);
		logger.info("单选题的修改操作");
		try {
			msg.setSuccess(radioItemService.updateItem(radioItem));
			if(msg.isSuccess()) {
				msg.setMsg("数据修改成功");
				//进行文件状态的修改
				if(radioItem.getItem_imageId()!=-1) {
					//上传了文件
					logger.info("修改文件使用状态操作");
					Map<String, Object> parm = new HashMap<>();
					parm.put("fileId", radioItem.getItem_imageId());
					parm.put("used", 1);
					uploadFileService.updateFileUsedById(parm);
				}
			}else {
				msg.setMsg("数据修改失败");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("数据修改接口异常");
			logger.error("单选题修改操作接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: editCheckPage   
	 * @Description: 跳转多选题编辑页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/editCheckPage/{id}")
	public String editCheckPage(@PathVariable("id")int id,Model model) {
		model.addAttribute("id", id);
		return "teacher/itemPool/editCheckPage";
	}
	
	
	/***
	 * 
	 * @Title: getCheckItemById   
	 * @Description: 根据 id  获取当前多选题的信息   
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getCheckItemById")
	@ResponseBody
	public Msg getCheckItemById(@RequestParam("id")int id) {
		 Msg msg = new Msg();
		 logger.info("获取多选题，考题id为"+id);
		 try {
			msg.addResult("check", checkItemService.getItemById(id));
		} catch (Exception e) {
			logger.error("多选题获取接口异常");
		}
		 return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateCheckItem   
	 * @Description:多选题的修改操作
	 * @param: @param id
	 * @param: @param checkItem
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateCheckItem/{id}")
	@ResponseBody
	public Msg updateCheckItem(@PathVariable("id")int id,CheckItem checkItem) {
		Msg msg = new Msg();
		checkItem.setUuid(id);
		logger.info("单选题的修改操作");
		try {
			msg.setSuccess(checkItemService.updateItem(checkItem));
			if(msg.isSuccess()) {
				msg.setMsg("数据修改成功");
				//进行文件状态的修改
				if(checkItem.getItem_imageId()!=-1) {
					//上传了文件
					logger.info("修改文件使用状态操作");
					Map<String, Object> parm = new HashMap<>();
					parm.put("fileId", checkItem.getItem_imageId());
					parm.put("used", 1);
					uploadFileService.updateFileUsedById(parm);
				}
			}else {
				msg.setMsg("数据修改失败");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("数据修改接口异常");
			logger.error("单选题修改操作接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: editGapPage   
	 * @Description: 跳转填空题修改页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/editGapPage/{id}")
	public String editGapPage(@PathVariable("id")int id,Model model) {
		model.addAttribute("id", id);
		return "teacher/itemPool/editGapPage";
	}
	
	
	/***
	 * 
	 * @Title: getGapItemById   
	 * @Description: 根据id 获取当前id的填空题信息 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getGapItemById")
	@ResponseBody
	public Msg getGapItemById(@RequestParam("id")int id) {
		Msg msg = new Msg();
		logger.info("获取填空题，考题id为"+id);
		try {
			msg.addResult("gap", gapItemService.getItemById(id));
		} catch (Exception e) {
			logger.error("填空题获取接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateGapItem   
	 * @Description: 修改填空题操作
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateGapItem/{id}")
	@ResponseBody
	public Msg updateGapItem(@PathVariable("id")int id,GapItem gapItem) {
		Msg msg = new Msg();
		gapItem.setUuid(id);
		logger.info("填空题的修改操作");
		try {
			msg.setSuccess(gapItemService.updateItem(gapItem));
			if(msg.isSuccess()) {
				msg.setMsg("数据修改成功");
				//进行文件状态的修改
				if(gapItem.getItem_imageId()!=-1) {
					//上传了文件
					logger.info("修改文件使用状态操作");
					Map<String, Object> parm = new HashMap<>();
					parm.put("fileId", gapItem.getItem_imageId());
					parm.put("used", 1);
					uploadFileService.updateFileUsedById(parm);
				}
			}else {
				msg.setMsg("数据修改失败");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("数据修改接口异常");
			logger.error("填空题修改操作接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: editJudgePage   
	 * @Description: 跳转判断题修改页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/editJudgePage/{id}")
	public String editJudgePage(@PathVariable("id")int id,Model model) {
		model.addAttribute("id", id);
		return "teacher/itemPool/editJudgePage";
		
	}
	
	
	/***
	 * 
	 * @Title: getJudgeItemById   
	 * @Description: 根据id  获取当前id对应的判断题信息
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getJudgeItemById")
	@ResponseBody
	public Msg getJudgeItemById(@RequestParam("id")int id) {
		 Msg msg = new Msg();
		 logger.info("获取判断题，考题id为"+id);
		 try {
			msg.addResult("judge", judgeItemService.getItemById(id));
		} catch (Exception e) {
			logger.error("判断题获取接口异常");
		}
		return msg;
		
	}
	
	
	/***
	 * 
	 * @Title: updateJudgeItem   
	 * @Description: 修改判断题操作
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateJudgeItem/{id}")
	@ResponseBody
	public Msg updateJudgeItem(@PathVariable("id")int id,JudgeItem judgeItem) {
		Msg msg = new Msg();
		judgeItem.setUuid(id);
		logger.info("判断题的修改操作");
		try {
			msg.setSuccess(judgeItemService.updateItem(judgeItem));
			if(msg.isSuccess()) {
				msg.setMsg("数据修改成功");
				//进行文件状态的修改
				if(judgeItem.getItem_imageId()!=-1) {
					//上传了文件
					logger.info("修改文件使用状态操作");
					Map<String, Object> parm = new HashMap<>();
					parm.put("fileId", judgeItem.getItem_imageId());
					parm.put("used", 1);
					uploadFileService.updateFileUsedById(parm);
				}
			}else {
				msg.setMsg("数据修改失败");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("数据修改接口异常");
			logger.error("判断题修改操作接口异常");
		}
		return msg;
	}
	
}
