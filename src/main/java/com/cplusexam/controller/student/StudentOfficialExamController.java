package com.cplusexam.controller.student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.cplusexam.bean.exam.ExamModel;
import com.cplusexam.bean.exam.ExamTime;
import com.cplusexam.bean.exam.OfficialExamMsg;
import com.cplusexam.bean.exam.OfficialExamPaper;
import com.cplusexam.bean.exam.TestExamPaper;
import com.cplusexam.service.Item.ICheckItemService;
import com.cplusexam.service.Item.IGapItemService;
import com.cplusexam.service.Item.IJudgeItemService;
import com.cplusexam.service.Item.IRadioItemService;
import com.cplusexam.service.exam.IExamModelItemSerivce;
import com.cplusexam.service.exam.IExamModelService;
import com.cplusexam.service.exam.IExamTimeService;
import com.cplusexam.service.exam.IOfficialExamMsgService;
import com.cplusexam.service.exam.IOfficialExamPaperService;
import com.cplusexam.service.exam.IStudentOfficialExamService;
import com.cplusexam.util.Grid;
import com.cplusexam.util.Msg;
import com.cplusexam.util.ShiroSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 * 
 * @ClassName:  StudentOfficialExamController   
 * @Description:处理正式考试页面的所有请求
 * @author: FanD
 * @date:   2019年3月28日 上午10:06:03
 */
@RequestMapping("/studentOfficialExam")
@Controller
public class StudentOfficialExamController {

	private Logger logger = Logger.getLogger(StudentOfficialExamController.class);
	
	
	@Autowired
	private IStudentOfficialExamService studentOfficialExamService;
	
	
	@Autowired
	private IExamModelService examModelService;
	
	
	@Autowired
	private IExamModelItemSerivce examModelItemSerivce;
	
	
	@Autowired
	private IOfficialExamMsgService officialExamMsgService;
	
	
	@Autowired
	private IOfficialExamPaperService officialExamPaperService;
	
	
	@Autowired
	private IExamTimeService examTimeService;
	
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
	 * @Title: getExamTimeMsg   
	 * @Description: 获取考试时间基本信息  （所有发布的考试时间，与当前用户是否报名参与信息） 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getExamTimeMsg")
	@ResponseBody
	public Grid getExamTimeMsg(@RequestParam("page") int page,@RequestParam("limit")int limit) {
		List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
		try {
			logger.info("正式考试-->获取管理员发布的考试信息");
			PageHelper.startPage(page, limit);
			map = studentOfficialExamService.getOfficialExamTableMsg(ShiroSessionUtil.getLoginUser().getLoginName());
		} catch (Exception e) {
			logger.error("正式考试接口异常-->获取管理员发布的考试信息");
		};
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String,Object>>(map);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	/***
	 * 
	 * @Title: getExamModelMsg   
	 * @Description: 获取当前正式考试  试卷相关信息
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getExamModelMsg/{id}")
	@ResponseBody
	public Msg getExamModelMsg(@PathVariable("id") int id) {
		Msg msg = new Msg();
		try {
			logger.info("正式考试-->获取试卷定义相关信息");
			ExamModel examModel = examModelService.getExamModelById(id);
			msg.addResult("examModel", examModel);
			List<Map<String, Object>> examModelItem = examModelItemSerivce.getExamModelItemByModelId(id);
			msg.addResult("examModelItems", examModelItem);
		} catch (Exception e) {
			logger.error("正式考试-->试卷定义相关信息获取接口异常");
		}  
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: addOfficialExam   
	 * @Description: 当前登录用户  报名一个正式考试  向正式考试基本信息表里面添加一条记录  呦
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addOfficialExam")
	@ResponseBody
	public Msg addOfficialExam(@RequestParam("examId")int examId /*正式考试时间id*/) {
		Msg msg = new Msg();
		OfficialExamMsg officialExamMsg = new OfficialExamMsg();
		officialExamMsg.setItem_timeid(examId);
		officialExamMsg.setUser_id(ShiroSessionUtil.getLoginUser().getLoginName());
		officialExamMsg.setExam_type(0);  //考卷  未考试状态
		try {
			logger.info("正式考试-->报名操作");
			officialExamMsgService.addOfficialExamMsg(officialExamMsg);
		} catch (Exception e) {
			logger.error("正式考试接口异常-->报名操作");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getOfficialExamTabMsg   
	 * @Description:  获取当前登录用户
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getOfficialExamTabMsg")
	@ResponseBody
	public Grid getOfficialExamTabMsg(@RequestParam("page") int page,@RequestParam("limit")int limit) {
		List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
		try {
			logger.info("正式考试-->获取当前用户的考试信息");
			PageHelper.startPage(page, limit);
			map = studentOfficialExamService.getOfficialExamUserTableMsg(ShiroSessionUtil.getLoginUser().getLoginName());
		} catch (Exception e) {
			logger.error("正式考试接口异常-->获取当前用户的考试信息");
		};
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String,Object>>(map);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	/***
	 * 
	 * @Title: toOfficialExamPage   
	 * @Description: 返回正式考试页面  
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/toOfficialExamPage/{id}")
	public String toOfficialExamPage(@PathVariable("id")int examId,Model model) {
		String path ="";
		model.addAttribute("examId", examId);      //考卷id
		//判断该考卷是否已经生成考题信息    考卷类型为0  这里根据考题信息  生成具体考卷   类型不为0   说明已经生成了
		OfficialExamMsg officialExamMsg = new OfficialExamMsg();
		try {
			logger.info("正式考试页面-->根据id，获取当前正式考试基本信息");
			officialExamMsg = officialExamMsgService.getOfficialExamMsgById(examId);
		} catch (Exception e) {
			logger.error("正式考试页面异常-->根据id，获取当前正式考试基本信息");
		}
		//获取当前考试时间信息
		ExamTime examTime = new ExamTime();
		try {
			logger.info("正式考试页面-->根据id，获取当前正式考试时间基本信息");
			examTime = examTimeService.getExamTimeById(officialExamMsg.getItem_timeid());
		} catch (Exception e) {
			logger.error("正式考试页面-->根据id，获取当前正式考试时间基本信息");
		}
		//考试时间信息：
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("serverTime", sdf.format(new Date()));
		model.addAttribute("endTime", examTime.getEnd_time());
		
		
		//先判断当前考试是否已经到考试时间了
		String startTimestr =  examTime.getStart_time();
		String endTimestr = examTime.getEnd_time();
		Date startDate;
		Date endDate;
		try {
			endDate = sdf.parse(endTimestr);
			startDate = sdf.parse(startTimestr);
			Date nowTime = new Date();
			if((nowTime.getTime()>=startDate.getTime())&& (nowTime.getTime()<=endDate.getTime())) {
				//考试时间段
				//根据考试时间信息   获取该考试相关题型数量等信息
				List<Map<String, Object>> examItems = new ArrayList<Map<String,Object>>();
				try {
					logger.info("正式考试-->获取当前定义的正式考卷的习题相关信息");
					examItems = examModelItemSerivce.getExamModelItemByModelId(examTime.getExam_modelid());
					if(!examItems.isEmpty()) {
						for (Map<String, Object> examItem : examItems) {
							switch (examItem.get("item_typeid").toString()) {
							case "1":  //单选题
								{
									model.addAttribute("radioC", examItem.get("item_count").toString());  //题型数量
								}
								break;
							case "2":  //多选题
								{
									model.addAttribute("checkC", examItem.get("item_count").toString());  //题型数量
								}
								break;
							case "3":  //填空题
								{
									model.addAttribute("gapC", examItem.get("item_count").toString());  //题型数量
								}
								break;
							case "4":  //判断题
								{
									model.addAttribute("judgeC", examItem.get("item_count").toString());  //题型数量
								}
								break;
							}
						}
					}
				} catch (Exception e) {
					logger.error("正式考试--异常-->获取当前定义的正式考卷的习题相关信息");
				}
				if(officialExamMsg.getExam_type()==0) {
					//说明当前用户的当前考卷  为未开始状态   进行考卷习题的随机生成  然后改变试卷状态
					try {
						studentOfficialExamService.createOfficialExamPaper(examId,examItems);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				path =  "student/myExam/officicalExam/userOfficialExam";
			}else {
				//不是考试时间段
				path = "student/myExam/officicalExam/notExamTimeAlert";
			}
		} catch (ParseException e1) {
			
		}
		return path;
	}
	
	
	/***
	 * 
	 * @Title: getOfficialExamItemIds   
	 * @Description:  根据当前正式考卷id  获取当前正式考题的所有考题id
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getOfficialExamItemIds")
	@ResponseBody
	public Msg getOfficialExamItemIds(@RequestParam("examId") int examId) {
		Msg msg = new Msg();
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("examId", examId);
		parm.put("type", 1);
		try {
			logger.info("获取正式考卷单选题考题ids");
			msg.addResult("radioIdList", studentOfficialExamService.getOfficialExamItemids(parm));
		} catch (Exception e) {
			logger.error("正式考卷单选题考题ids获取异常");
		}
		parm.put("type", 2);
		try {
			logger.info("获取正式考卷多选题考题ids");
			msg.addResult("checkIdList", studentOfficialExamService.getOfficialExamItemids(parm));
		} catch (Exception e) {
			logger.error("正式考卷多选题考题ids获取异常");
		}
		parm.put("type", 3);
		try {
			logger.info("获取正式考卷填空题考题ids");
			msg.addResult("gapIdList", studentOfficialExamService.getOfficialExamItemids(parm));
		} catch (Exception e) {
			logger.error("正式考卷填空题考题ids获取异常");
		}
		parm.put("type", 4);
		try {
			logger.info("获取正式考卷判断题考题ids");
			msg.addResult("judgeIdList", studentOfficialExamService.getOfficialExamItemids(parm));
		} catch (Exception e) {
			logger.error("正式考卷判断题考题ids获取异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getRadioItemExam   
	 * @Description: 获取当前考卷中   具体的一道单选题
	 * @param: @param itemId
	 * @param: @param examId
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getRadioItemExam")
	@ResponseBody
	public Msg getRadioItemExam(@RequestParam("item_id") int itemId , @RequestParam("examId") int examId) {
		Msg msg = new Msg();
		RadioItem radioItem = new RadioItem();   //考题信息
		String userOption=null;  //用户答案
		try {
			logger.info("正式考试-->获取当前考题的基本信息与用户输入答案");
			radioItem = radioItemService.getItemById(itemId);
			radioItem.setItem_option("");
			//获取用户输入的答案
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("item_id", itemId);
			map.put("examId", examId);
			map.put("item_type", 1);   //单选题
			userOption = officialExamPaperService.getUserOptionOne(map);
		} catch (Exception e) {
			logger.info("正式考试-->当前考题的基本信息与用户输入答案获取异常");
		}
		msg.addResult("radio", radioItem);
		msg.addResult("userOption", userOption);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateRadioUserOption   
	 * @Description: 点击页面提交按钮  进行单选题答案的录入以及判断
	 * @param: @param examId
	 * @param: @param officialExamPaper
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateRadioUserOption/{examId}")
	@ResponseBody
	public Msg updateRadioUserOption(@PathVariable("examId")int examId,OfficialExamPaper officialExamPaper) {
		Msg msg = new Msg();
		officialExamPaper.setExamofficial_id(examId);
		officialExamPaper.setItem_type(1);  //单选题
		//获取该考题的正确答案 进行判断
		try {
			logger.info("正式考试-->单选题用户答案与答案判断");
			RadioItem radioItem = radioItemService.getItemById(officialExamPaper.getItem_id());
			if(radioItem.getItem_option().equals(officialExamPaper.getUser_option())) {
				officialExamPaper.setOption_tof(1);
			}else {
				officialExamPaper.setOption_tof(0);
			}
			//进行用户输入答案的修改  以及正确答案的修改
			officialExamPaperService.updateOfficialExamPaperUesrOption(officialExamPaper);
		} catch (Exception e) {
			logger.error("正式考试-->单选题用户答案与答案判断接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getOfficialExamRadioType   
	 * @Description: 获取当前考卷  单选题完成状态   
	 * @param: @param examId
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getOfficialExamRadioType/{examId}")
	@ResponseBody
	public Grid getOfficialExamRadioType(@PathVariable("examId") int examId,
			@RequestParam("page") int page,@RequestParam("limit")int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examId", examId);
		map.put("item_type", 1);
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try {
			logger.info("正式考试-->获取当前考卷中单选题的状态信息");
			PageHelper.startPage(page, limit);
			res = officialExamPaperService.getOfficialExamPaperOptionType(map);
		} catch (Exception e) {
			logger.info("正式考试-->获取当前考卷中单选题的状态信息接口异常");
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	/***
	 * 
	 * @Title: getCheckItemExam   
	 * @Description: 获取当前考卷中   具体的一道多选题
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getCheckItemExam")
	@ResponseBody
	public Msg getCheckItemExam(@RequestParam("item_id") int itemId , @RequestParam("examId") int examId) {
		Msg msg = new Msg();
		CheckItem checkItem = new CheckItem();   //考题信息
		String userOption=null;  //用户答案
		try {
			logger.info("正式考试-->获取当前多选题的基本信息与用户输入答案");
			checkItem = checkItemService.getItemById(itemId);
			checkItem.setItem_option("");
			//获取用户输入的答案
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("item_id", itemId);
			map.put("examId", examId);
			map.put("item_type", 2);   //多选题
			userOption = officialExamPaperService.getUserOptionOne(map);
		} catch (Exception e) {
			logger.info("正式考试-->当前多选题的基本信息与用户输入答案获取异常");
		}
		msg.addResult("check", checkItem);
		msg.addResult("userOption", userOption);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateCheckUserOption   
	 * @Description: 点击页面提交按钮  进行多选题答案的录入以及判断
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateCheckUserOption/{examId}")
	@ResponseBody
	public Msg updateCheckUserOption(@PathVariable("examId")int examId,OfficialExamPaper officialExamPaper) {
		Msg msg = new Msg();
		officialExamPaper.setExamofficial_id(examId);
		officialExamPaper.setItem_type(2);  //多选题
		//获取该考题的正确答案 进行判断
		try {
			logger.info("正式考试-->多选题用户答案与答案判断");
			CheckItem checkItem = checkItemService.getItemById(officialExamPaper.getItem_id());
			if(checkItem.getItem_option().equals(officialExamPaper.getUser_option())) {
				officialExamPaper.setOption_tof(1);
			}else {
				officialExamPaper.setOption_tof(0);
			}
			//进行用户输入答案的修改  以及正确答案的修改
			officialExamPaperService.updateOfficialExamPaperUesrOption(officialExamPaper);
		} catch (Exception e) {
			logger.error("正式考试-->多选题用户答案与答案判断接口异常");
		}
		return msg;
	}
	
	
	
	/***
	 * 
	 * @Title: getTestExamCheckType   
	 * @Description: 获取当前考卷  多选题完成状态   
	 * @param: @param examId
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getTestExamCheckType/{examId}")
	@ResponseBody
	public Grid getTestExamCheckType(@PathVariable("examId") int examId,
			@RequestParam("page") int page,@RequestParam("limit")int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examId", examId);
		map.put("item_type", 2);
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try {
			logger.info("正式考试-->获取当前考卷中 多选题的状态信息");
			PageHelper.startPage(page, limit);
			res = officialExamPaperService.getOfficialExamPaperOptionType(map);
		} catch (Exception e) {
			logger.info("正式考试-->获取当前考卷中 多选题的状态信息接口异常");
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	/***
	 * 
	 * @Title: getGapItemExam   
	 * @Description: 获取当前考卷中   具体的一道填空题
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getGapItemExam")
	@ResponseBody
	public Msg getGapItemExam(@RequestParam("item_id") int itemId , @RequestParam("examId") int examId) {
		Msg msg = new Msg();
		GapItem gapItem = new GapItem();   //考题信息
		String userOption=null;  //用户答案
		try {
			logger.info("正式考试-->获取当前填空题的基本信息与用户输入答案");
			gapItem = gapItemService.getItemById(itemId);
			gapItem.setItem_option("");
			//获取用户输入的答案
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("item_id", itemId);
			map.put("examId", examId);
			map.put("item_type", 3);   //填空题
			userOption = officialExamPaperService.getUserOptionOne(map);
		} catch (Exception e) {
			logger.info("正式考试-->当前填空题的基本信息与用户输入答案获取异常");
		}
		msg.addResult("gap", gapItem);
		msg.addResult("userOption", userOption);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateGapUserOption   
	 * @Description: 点击页面提交按钮  进行填空题答案的录入以及判断
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateGapUserOption/{examId}")
	@ResponseBody
	public Msg updateGapUserOption(@PathVariable("examId")int examId,OfficialExamPaper officialExamPaper) {
		Msg msg = new Msg();
		officialExamPaper.setExamofficial_id(examId);
		officialExamPaper.setItem_type(3);  //填空题
		//获取该考题的正确答案 进行判断
		try {
			logger.info("正式考试-->填空题用户答案与答案判断");
			GapItem gapItem = gapItemService.getItemById(officialExamPaper.getItem_id());
			if(gapItem.getItem_option().equals(officialExamPaper.getUser_option())) {
				officialExamPaper.setOption_tof(1);
			}else {
				officialExamPaper.setOption_tof(0);
			}
			//进行用户输入答案的修改  以及正确答案的修改
			officialExamPaperService.updateOfficialExamPaperUesrOption(officialExamPaper);
		} catch (Exception e) {
			logger.error("正式考试-->填空题用户答案与答案判断接口异常");
		}
		return msg;
	}
	
	
	
	/***
	 * 
	 * @Title: getTestExamGapType   
	 * @Description: 获取当前考卷  填空题完成状态   
	 * @param: @param examId
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getTestExamGapType/{examId}")
	@ResponseBody
	public Grid getTestExamGapType(@PathVariable("examId") int examId,
			@RequestParam("page") int page,@RequestParam("limit")int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examId", examId);
		map.put("item_type", 3);
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try {
			logger.info("正式考试-->获取当前考卷中填空题的状态信息");
			PageHelper.startPage(page, limit);
			res = officialExamPaperService.getOfficialExamPaperOptionType(map);
		} catch (Exception e) {
			logger.info("正式考试-->获取当前考卷中 填空题的状态信息接口异常");
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	
	/***
	 * 
	 * @Title: getJudgeItemExam   
	 * @Description: 获取当前考卷中   具体的一道判断题
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getJudgeItemExam")
	@ResponseBody
	public Msg getJudgeItemExam(@RequestParam("item_id") int itemId , @RequestParam("examId") int examId) {
		Msg msg = new Msg();
		JudgeItem judgeItem = new JudgeItem();   //考题信息
		String userOption=null;  //用户答案
		try {
			logger.info("正式考试-->获取当前判断题的基本信息与用户输入答案");
			judgeItem = judgeItemService.getItemById(itemId);
			judgeItem.setItem_option("");
			//获取用户输入的答案
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("item_id", itemId);
			map.put("examId", examId);
			map.put("item_type", 4);   //判断题
			userOption = officialExamPaperService.getUserOptionOne(map);
		} catch (Exception e) {
			logger.info("正式考试-->当前判断题的基本信息与用户输入答案获取异常");
		}
		msg.addResult("judge", judgeItem);
		msg.addResult("userOption", userOption);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateJudgeUserOption   
	 * @Description: 点击页面提交按钮  进行填空题答案的录入以及判断
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateJudgeUserOption/{examId}")
	@ResponseBody
	public Msg updateJudgeUserOption(@PathVariable("examId")int examId,OfficialExamPaper officialExamPaper) {
		Msg msg = new Msg();
		officialExamPaper.setExamofficial_id(examId);
		officialExamPaper.setItem_type(4);  //判断题
		//获取该考题的正确答案 进行判断
		try {
			logger.info("正式考试-->判断题用户答案与答案判断");
			JudgeItem judgeItem = judgeItemService.getItemById(officialExamPaper.getItem_id());
			if(judgeItem.getItem_option().equals(officialExamPaper.getUser_option())) {
				officialExamPaper.setOption_tof(1);
			}else {
				officialExamPaper.setOption_tof(0);
			}
			//进行用户输入答案的修改  以及正确答案的修改
			officialExamPaperService.updateOfficialExamPaperUesrOption(officialExamPaper);
		} catch (Exception e) {
			logger.error("正式考试-->判断题用户答案与答案判断接口异常");
		}
		return msg;
	}
	
	
	
	/***
	 * 
	 * @Title: getTestExamJudgeType   
	 * @Description: 获取当前考卷  判断题完成状态   
	 * @param: @param examId
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getTestExamJudgeType/{examId}")
	@ResponseBody
	public Grid getTestExamJudgeType(@PathVariable("examId") int examId,
			@RequestParam("page") int page,@RequestParam("limit")int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examId", examId);
		map.put("item_type", 4);
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try {
			logger.info("正式考试-->获取当前考卷中判断题的状态信息");
			PageHelper.startPage(page, limit);
			res = officialExamPaperService.getOfficialExamPaperOptionType(map);
		} catch (Exception e) {
			logger.info("正式考试-->获取当前考卷中判断题的状态信息接口异常");
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	/***
	 * 
	 * @Title: commitOfficialExam   
	 * @Description: 提交当前考卷
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/commitOfficialExam")
	@ResponseBody
	public Msg commitOfficialExam(@RequestParam("examId") int examId) {
		Msg msg = new Msg();
		int countC = 0;
		try {
			logger.info("正式考试-->考卷提交操作");
			//获取当前试卷  题型的总分数
			countC = officialExamPaperService.getOffifialExamscoreCByExamId(examId);
			//修改当前考卷的总分数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("exam_counts", countC);
			map.put("examId", examId);
			officialExamMsgService.updateExamScoreC(map);
			//修改当前考卷的状态
			map.put("type", 2);
			map.put("id", examId);
			officialExamMsgService.updateExamType(map);
		} catch (Exception e) {
			logger.error("正式考试--异常-->考卷提交操作");
		}
		return msg;
	}
}
