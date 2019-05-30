package com.cplusexam.controller.student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.bean.Item.CheckItem;
import com.cplusexam.bean.Item.GapItem;
import com.cplusexam.bean.Item.JudgeItem;
import com.cplusexam.bean.Item.RadioItem;
import com.cplusexam.bean.exam.TestExamItemMsg;
import com.cplusexam.bean.exam.TestExamMsg;
import com.cplusexam.bean.exam.TestExamPaper;
import com.cplusexam.bean.system.User;
import com.cplusexam.service.Item.ICheckItemService;
import com.cplusexam.service.Item.IGapItemService;
import com.cplusexam.service.Item.IItemTypeService;
import com.cplusexam.service.Item.IJudgeItemService;
import com.cplusexam.service.Item.IRadioItemService;
import com.cplusexam.service.exam.ITestExamItemMsgService;
import com.cplusexam.service.exam.ITestExamMsgService;
import com.cplusexam.service.exam.ITestExamPaperService;
import com.cplusexam.util.Grid;
import com.cplusexam.util.Msg;
import com.cplusexam.util.ShiroSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 * 
 * @ClassName:  StudentTestExamController   
 * @Description: 处理学生模拟考试页面的所有请求
 * @author: FanD
 * @date:   2019年3月18日 上午11:30:09
 */
@RequestMapping("/studentTestExam")
@Controller
public class StudentTestExamController {

	private Logger logger = Logger.getLogger(StudentTestExamController.class);
	
	
	@Autowired
	private ITestExamMsgService testExamMsgService;
	
	@Autowired
	private IItemTypeService itemTypeService;
	
	@Autowired
	private ITestExamItemMsgService testExamItemMsgService;
	
	@Autowired
	private ITestExamPaperService testExamPaperService;
	
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
	 * @Title: toAddTestExamPage   
	 * @Description: 跳转模拟考卷信息定义页面   返回考试题型的相关信息 
	 * @param: @param endPage
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/addTestExamPage")
	public String toAddTestExamPage(@RequestParam("endPage")int endPage,Model model) {
		model.addAttribute("endPage", endPage);
		try {
			//获取各个题型的默认分数与习题数量
   			List<Map<String, Object>> lists = itemTypeService.getItemTypeList();
   			for (Map<String, Object> map : lists) {
   				switch ((Integer) map.get("item_id")) {
				case 1: {model.addAttribute("radio_defS", map.get("def_score"));model.addAttribute("radio_itemC", map.get("itemCount"));} //单选题
					break;
				case 2: {model.addAttribute("check_defS", map.get("def_score"));model.addAttribute("check_itemC", map.get("itemCount"));} //多选题
					break;
				case 3: {model.addAttribute("gap_defS", map.get("def_score"));model.addAttribute("gap_itemC", map.get("itemCount"));}  //填空题
					break;
				case 4: {model.addAttribute("judge_defS", map.get("def_score"));model.addAttribute("judge_itemC", map.get("itemCount"));}  //判断题
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
		}
		return "student/myExam/testExam/addTestExam";
	}
	
	
	/***
	 * 
	 * @Title: addTestExamMsg   
	 * @Description: 进行模拟考试试题基本信息的添加  并返回id值
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addTestExamMsg")
	@ResponseBody
	public Msg addTestExamMsg(TestExamMsg testExamMsg) {
		Msg msg = new Msg();
		testExamMsg.setCreate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
		testExamMsg.setExamtest_type(0); //考题为未开始状态
		testExamMsg.setCreate_user(ShiroSessionUtil.getLoginUser().getLoginName());
		try {
			logger.info("进行模拟考试试题基本信息的添加");
			testExamMsgService.addTestExamMsgRetId(testExamMsg);
			msg.addResult("uuid", testExamMsg.getUuid());
		} catch (Exception e) {
			logger.error("进行模拟考试试题基本信息的添加接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateTestExamMsg   
	 * @Description: 进行模拟考试基本信息的修改
	 * @param: @param testExamMsg
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateTestExamMsg")
	@ResponseBody
	public Msg updateTestExamMsg(TestExamMsg testExamMsg) {
		Msg msg = new Msg();
		try {
			logger.info("进行模拟考试试题基本信息的修改");
			testExamMsgService.updateTestExam4AddPage(testExamMsg);
		} catch (Exception e) {
			logger.error("进行模拟考试试题基本信息的修改接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: deleteTestExam   
	 * @Description: 进行考卷基本信息的删除操作
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/deleteTestExam")
	@ResponseBody
	public Msg deleteTestExam(@RequestParam("id") int id) {
		Msg msg = new Msg();
		logger.info("进行模拟考卷基本信息的删除操作");
		try {
			testExamMsgService.deleteTestExamById(id);
		} catch (Exception e) {
			logger.error("模拟考卷基本信息的删除接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: addTestExamItems   
	 * @Description: 添加模拟考试的考试基本信息
	 * @param: @param testExamItemMsgs
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addTestExamItems/{id}")
	@ResponseBody
	public Msg addTestExamItems(@RequestBody List<TestExamItemMsg> testExamItemMsgs,@PathVariable("id")int testExamMsgId) {
		Msg msg = new Msg();
		List<TestExamItemMsg> list = new ArrayList<TestExamItemMsg>();
		int score=0;
		for (TestExamItemMsg testExamItemMsg : testExamItemMsgs) {
			if(testExamItemMsg.getItem_count()!=0) {
				testExamItemMsg.setExamtest_id(testExamMsgId);
				testExamItemMsg.setItem_counts(testExamItemMsg.getItem_count()*testExamItemMsg.getItem_score());
				//计算试卷总分
				score+=testExamItemMsg.getItem_counts();
				list.add(testExamItemMsg);
			}
		}
		logger.info("进行模拟考试考题信息的添加操作");
		try {
			testExamItemMsgService.addTestExamTtems(list);
			msg.setMsg("模拟考卷信息添加成功");
			//修改模拟考试基本信息的总分数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("score",score);
			map.put("uuid",testExamMsgId);
			testExamMsgService.updateTestExamScore(map);
		} catch (Exception e) {
			logger.error("模拟考试考题信息的添加接口异常");
			msg.setMsg("模拟考卷信息添加接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getTestExamTabMsg   
	 * @Description: 获取模拟考试首页表格数据
	 * @param: @param page
	 * @param: @param limit
	 * @param: @param user
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getTestExamTabMsg")
	@ResponseBody
	public Grid getTestExamTabMsg(@RequestParam("page") int page,@RequestParam("limit")int limit,User user) {
		PageHelper.startPage(page, limit);
		List<TestExamMsg> list=null;
		try {
			logger.info("获取当前登录用户的模拟考卷信息");
			list = testExamMsgService.getTestExamMsgAll(ShiroSessionUtil.getLoginUser().getLoginName());
		} catch (Exception e) {
			logger.error("当前登录用户的模拟考卷信息获取异常");
		}
		PageInfo<TestExamMsg> pageInfo = new PageInfo<>(list);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	/***
	 * 
	 * @Title: getTestExamItemMsg   
	 * @Description: 获取模拟试卷题型的相关定义信息  
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getTestExamItemMsg/{id}")
	@ResponseBody
	public Msg getTestExamItemMsg(@PathVariable("id") int testExamId) {
		Msg msg = new Msg();
		try {
			logger.info("获取当前试卷的相关题型定义信息");
			msg.addResult("examItems", testExamMsgService.getTestExamItemMsg(testExamId));
		} catch (Exception e) {
			logger.info("当前试卷的相关题型定义信息获取接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: toTestExamPage   
	 * @Description: 跳转用户模拟考试页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/toTestExamPage/{id}")
	public String toTestExamPage(@PathVariable("id")int testExamId , Model model) {
		model.addAttribute("testExamId", testExamId);      //考卷id
		//判断该考卷是否已经生成考题信息    考卷类型为0  这里根据考题信息  生成具体考卷   类型不为0   说明已经生成了
		TestExamMsg testExamMsg = new TestExamMsg();
		try {
			logger.info("获取当前模拟考卷id下的具体信息");
			testExamMsg = testExamMsgService.getTestExamMsgById(testExamId);
		} catch (Exception e1) {
			logger.error("当前模拟考卷id下的具体信息获取异常");
		}
		model.addAttribute("examTime", testExamMsg.getExam_time());    //考试时间信息
		//根据模拟考卷的id  获取相关题型数量、考试时间等信息
		List<TestExamItemMsg> examItemMsgs = new ArrayList<TestExamItemMsg>();
		try {
			logger.info("获取该模拟考卷题型信息");
			examItemMsgs = testExamItemMsgService.getTestExamItemMsgAllByExamId(testExamId);
			for (TestExamItemMsg testExamItemMsg : examItemMsgs) {
				switch (testExamItemMsg.getItem_type()) {
				case 1:  //单选题
					{
						model.addAttribute("radioC", testExamItemMsg.getItem_count());  //题型数量
					}
					break;
				case 2:  //多选题
					{
						model.addAttribute("checkC", testExamItemMsg.getItem_count());  //题型数量
					}
					break;
				case 3:  //填空题
					{
						model.addAttribute("gapC", testExamItemMsg.getItem_count());  //题型数量
					}
					break;
				case 4:  //判断题
					{
						model.addAttribute("judgeC", testExamItemMsg.getItem_count());  //题型数量
					}
					break;
				}
			}
		} catch (Exception e) {
			logger.error("模拟考卷题型信息获取异常");
		}
		if(testExamMsg.getExamtest_type()==0) {
			//进行具体考卷的生成   并修改当前考卷的状态
			try {
				logger.info("进行模拟考卷考题的创建");
				testExamPaperService.create_testExamPaper(testExamId,examItemMsgs);
			} catch (Exception e) {
				logger.error("模拟考卷考题创建接口异常");
			}
		}
		return "student/myExam/testExam/userTestExamPage";
	}
	
	
	/***
	 * 
	 * @Title: getTestExamItemIds   
	 * @Description: 获取当前模拟试卷id下  所有的考题ids信息 
	 * @param: @param testExamId
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getTestExamItemIds")
	@ResponseBody
	public Msg getTestRadioItem(@RequestParam("testExamId") int testExamId) {
		Msg msg = new Msg();
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("item_testid", testExamId);
		parm.put("item_type", 1);
		try {
			logger.info("获取模拟考卷单选题考题ids");
			msg.addResult("radioIdList", testExamPaperService.getTestExamPaperIds(parm));
		} catch (Exception e) {
			logger.error("模拟考卷单选题考题ids获取异常");
		}
		parm.put("item_type", 2);
		try {
			logger.info("获取模拟考卷多选题考题ids");
			msg.addResult("checkIdList", testExamPaperService.getTestExamPaperIds(parm));
		} catch (Exception e) {
			logger.error("模拟考卷多选题考题ids获取异常");
		}
		parm.put("item_type", 3);
		try {
			logger.info("获取模拟考卷填空题考题ids");
			msg.addResult("gapIdList", testExamPaperService.getTestExamPaperIds(parm));
		} catch (Exception e) {
			logger.error("模拟考卷填空题考题ids获取异常");
		}
		parm.put("item_type", 4);
		try {
			logger.info("获取模拟考卷判断题考题ids");
			msg.addResult("judgeIdList", testExamPaperService.getTestExamPaperIds(parm));
		} catch (Exception e) {
			logger.error("模拟考卷判断题考题ids获取异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getRadioItemOne   
	 * @Description: 获取该id的单选题信息  以及用户输入的答案（有的话）
	 * @param: @param itemId
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getRadioItemExam")
	@ResponseBody
	public Msg getRadioItemOne(@RequestParam("item_id") int itemId , @RequestParam("testExamId") int testExamId) {
		Msg msg = new Msg();
		RadioItem radioItem = new RadioItem();   //考题信息
		String userOption=null;  //用户答案
		try {
			logger.info("模拟考试-->获取当前考题的基本信息与用户输入答案");
			radioItem = radioItemService.getItemById(itemId);
			radioItem.setItem_option("");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("item_id", itemId);
			map.put("testExamId", testExamId);
			map.put("item_type", 1);   //单选题
			userOption = testExamPaperService.getUserOptionOne(map);
		} catch (Exception e) {
			logger.info("模拟考试-->当前考题的基本信息与用户输入答案获取异常");
		}
		msg.addResult("radio", radioItem);
		msg.addResult("userOption", userOption);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateRadioUserOption   
	 * @Description: 修改单选题  
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateRadioUserOption/{testExamId}")
	@ResponseBody
	public Msg updateRadioUserOption(@PathVariable("testExamId")int testExamId,TestExamPaper testExamPaper) {
		Msg msg = new Msg();
		testExamPaper.setItem_testid(testExamId);
		testExamPaper.setUser_id(ShiroSessionUtil.getLoginUser().getLoginName());
		testExamPaper.setItem_type(1);  //单选题
		//获取该考题的正确答案 进行判断
		try {
			logger.info("模拟考试-->单选题用户答案与答案判断");
			RadioItem radioItem = radioItemService.getItemById(testExamPaper.getItem_id());
			if(radioItem.getItem_option().equals(testExamPaper.getUser_option())) {
				testExamPaper.setOption_tof(1);
			}else {
				testExamPaper.setOption_tof(0);
			}
			//进行用户输入答案的修改  以及正确答案的修改
			testExamPaperService.updateTestExamPaperUesrOption(testExamPaper);
		} catch (Exception e) {
			logger.error("模拟考试-->单选题用户答案与答案判断接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getTestExamRadioType   
	 * @Description: 获取当前模拟考卷的考题状态  是否作答   
	 * @param: @param testExamId
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getTestExamRadioType/{testExamId}")
	@ResponseBody
	public Grid getTestExamRadioType(@PathVariable("testExamId") int testExamId,
			@RequestParam("page") int page,@RequestParam("limit")int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("item_testid", testExamId);
		map.put("item_type", 1);
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try {
			logger.info("模拟考试-->获取当前考卷中试题的状态信息");
			PageHelper.startPage(page, limit);
			res = testExamPaperService.getTestExamPaperOptionType(map);
		} catch (Exception e) {
			logger.info("模拟考试-->获取当前考卷中试题的状态信息接口异常");
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	@RequestMapping("/getCheckItemExam")
	@ResponseBody
	public Msg getCheckItemExam(@RequestParam("item_id") int itemId , @RequestParam("testExamId") int testExamId) {
		Msg msg = new Msg();
		CheckItem checkItem = new CheckItem();  //考题信息
		String userOption=null;  //用户答案
		try {
			logger.info("模拟考试-->获取当前考题的基本信息与用户输入答案");
			checkItem = checkItemService.getItemById(itemId);
			checkItem.setItem_option("");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("item_id", itemId);
			map.put("testExamId", testExamId);
			map.put("item_type", 2);   //多选题
			userOption = testExamPaperService.getUserOptionOne(map);
		} catch (Exception e) {
			logger.info("模拟考试-->当前考题的基本信息与用户输入答案获取异常");
		}
		msg.addResult("check", checkItem);
		msg.addResult("userOption", userOption);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateCheckUserOption   
	 * @Description: 修改模拟考试多选题用户的答案  
	 * @param: @param testExamId
	 * @param: @param testExamPaper
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateCheckUserOption/{testExamId}")
	@ResponseBody
	public Msg updateCheckUserOption(@PathVariable("testExamId")int testExamId,TestExamPaper testExamPaper) {
			Msg msg = new Msg();
			testExamPaper.setItem_testid(testExamId);
			testExamPaper.setUser_id(ShiroSessionUtil.getLoginUser().getLoginName());
			testExamPaper.setItem_type(2);  //多选题
			//获取该考题的正确答案 进行判断
			try {
				logger.info("模拟考试-->多选题用户答案与答案判断");
				CheckItem checkItem = checkItemService.getItemById(testExamPaper.getItem_id());
				if(checkItem.getItem_option().equals(testExamPaper.getUser_option())) {
					testExamPaper.setOption_tof(1);
				}else {
					testExamPaper.setOption_tof(0);
				}
				//进行用户输入答案的修改  以及正确答案的修改
				testExamPaperService.updateTestExamPaperUesrOption(testExamPaper);
			} catch (Exception e) {
				logger.error("模拟考试-->多选题用户答案与答案判断接口异常");
			}
			return msg;
	}
	
	
	/***
	 * 
	 * @Title: getTestExamCheckType   
	 * @Description: 获取当前考卷多选题的完成状态 
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getTestExamCheckType/{testExamId}")
	@ResponseBody
	public Grid getTestExamCheckType(@PathVariable("testExamId") int testExamId,
			@RequestParam("page") int page,@RequestParam("limit")int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("item_testid", testExamId);
		map.put("item_type", 2);
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try {
			logger.info("模拟考试-->获取当前考卷中试题的状态信息--多选");
			PageHelper.startPage(page, limit);
			res = testExamPaperService.getTestExamPaperOptionType(map);
		} catch (Exception e) {
			logger.info("模拟考试-->获取当前考卷中试题的状态信息接口异常--多选");
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	/***
	 * 
	 * @Title: getGapItemExam   
	 * @Description: 根据试卷id  获取该试卷的填空题所有题目
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getGapItemExam")
	@ResponseBody
	public Msg getGapItemExam(@RequestParam("item_id") int itemId , @RequestParam("testExamId") int testExamId) {
		Msg msg = new Msg();
		GapItem gapItem = new GapItem();  //考题信息
		String userOption=null;  //用户答案
		try {
			logger.info("模拟考试-->获取当前考题的基本信息与用户输入答案");
			gapItem = gapItemService.getItemById(itemId);
			gapItem.setItem_option("");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("item_id", itemId);
			map.put("testExamId", testExamId);
			map.put("item_type", 3);   //填空题
			userOption = testExamPaperService.getUserOptionOne(map);
		} catch (Exception e) {
			logger.info("模拟考试-->当前考题的基本信息与用户输入答案获取异常");
		}
		msg.addResult("gap", gapItem);
		msg.addResult("userOption", userOption);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateGapUserOption   
	 * @Description: 修改当前模拟考卷 用户输入的填空题答案信息 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateGapUserOption/{testExamId}")
	@ResponseBody
	public Msg updateGapUserOption(@PathVariable("testExamId")int testExamId,TestExamPaper testExamPaper) {
		Msg msg = new Msg();
		testExamPaper.setItem_testid(testExamId);
		testExamPaper.setUser_id(ShiroSessionUtil.getLoginUser().getLoginName());
		testExamPaper.setItem_type(3);  //填空题
		//获取该考题的正确答案 进行判断
		try {
			logger.info("模拟考试-->填空题用户答案与答案判断");
			GapItem gapItem = gapItemService.getItemById(testExamPaper.getItem_id());
			if(gapItem.getItem_option().equals(testExamPaper.getUser_option())) {
				testExamPaper.setOption_tof(1);
			}else {
				testExamPaper.setOption_tof(0);
			}
			//进行用户输入答案的修改  以及正确答案的修改
			testExamPaperService.updateTestExamPaperUesrOption(testExamPaper);
		} catch (Exception e) {
			logger.error("模拟考试-->填空题用户答案与答案判断接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getTestExamGapType   
	 * @Description:  获取当前考卷的填空题答案状态 
	 * @param: @return   
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getTestExamGapType/{testExamId}")
	@ResponseBody
	public Grid getTestExamGapType(@PathVariable("testExamId") int testExamId,
			@RequestParam("page") int page,@RequestParam("limit")int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("item_testid", testExamId);
		map.put("item_type", 3);
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try {
			logger.info("模拟考试-->获取当前考卷中试题的状态信息--填空");
			PageHelper.startPage(page, limit);
			res = testExamPaperService.getTestExamPaperOptionType(map);
		} catch (Exception e) {
			logger.info("模拟考试-->获取当前考卷中试题的状态信息接口异常--填空");
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	/***
	 * 
	 * @Title: getJudgeItemExam   
	 * @Description:根据模拟考卷id 获取当前考卷的判断题考题信息 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getJudgeItemExam")
	@ResponseBody
	public Msg getJudgeItemExam(@RequestParam("item_id") int itemId , @RequestParam("testExamId") int testExamId) {
		Msg msg = new Msg();
		JudgeItem judgeItem = new JudgeItem();  //考题信息
		String userOption=null;  //用户答案
		try {
			logger.info("模拟考试-->获取当前考题的基本信息与用户输入答案--判断题");
			judgeItem = judgeItemService.getItemById(itemId);
			judgeItem.setItem_option("");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("item_id", itemId);
			map.put("testExamId", testExamId);
			map.put("item_type", 4);   //判断题
			userOption = testExamPaperService.getUserOptionOne(map);
		} catch (Exception e) {
			logger.info("模拟考试-->当前考题的基本信息与用户输入答案获取异常--判断题");
		}
		msg.addResult("judge", judgeItem);
		msg.addResult("userOption", userOption);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateJudgeUserOption   
	 * @Description: 修改当前考卷，当前 判断题 用户答案信息
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateJudgeUserOption/{testExamId}")
	@ResponseBody
	public Msg updateJudgeUserOption(@PathVariable("testExamId")int testExamId,TestExamPaper testExamPaper) {
		Msg msg = new Msg();
		testExamPaper.setItem_testid(testExamId);
		testExamPaper.setUser_id(ShiroSessionUtil.getLoginUser().getLoginName());
		testExamPaper.setItem_type(4);  //判断题
		//获取该考题的正确答案 进行判断
		try {
			logger.info("模拟考试-->判断题用户答案与答案判断");
			JudgeItem judgeItem = judgeItemService.getItemById(testExamPaper.getItem_id());
			if(judgeItem.getItem_option().equals(testExamPaper.getUser_option())) {
				testExamPaper.setOption_tof(1);
			}else {
				testExamPaper.setOption_tof(0);
			}
			//进行用户输入答案的修改  以及正确答案的修改
			testExamPaperService.updateTestExamPaperUesrOption(testExamPaper);
		} catch (Exception e) {
			logger.error("模拟考试-->判断题用户答案与答案判断接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getTestExamJudgeType   
	 * @Description: 获取当前模拟考卷判断题答案状态
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getTestExamJudgeType/{testExamId}")
	@ResponseBody
	public Grid getTestExamJudgeType(@PathVariable("testExamId") int testExamId,
			@RequestParam("page") int page,@RequestParam("limit")int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("item_testid", testExamId);
		map.put("item_type", 4);
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try {
			logger.info("模拟考试-->获取当前考卷中试题的状态信息--判断");
			PageHelper.startPage(page, limit);
			res = testExamPaperService.getTestExamPaperOptionType(map);
		} catch (Exception e) {
			logger.info("模拟考试-->获取当前考卷中试题的状态信息接口异常--判断");
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	/***
	 * 
	 * @Title: updateTestExamTime   
	 * @Description: 修改 指定模拟考卷 的考试剩余时间  
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateTestExamTime")
	@ResponseBody
	public Msg updateTestExamTime(@RequestParam("testExamId")int testExamId,@RequestParam("endExamTime")String endExamTime) {
		Msg msg = new Msg();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endTime", endExamTime);
		map.put("testExamId", testExamId);
		try {
			logger.info("重置当前考卷剩余考试时间");
			testExamMsgService.updateTextExamTime(map);
		} catch (Exception e) {
			logger.error("重置当前考卷剩余考试时间接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: commitTestExam   
	 * @Description: 提交当前模拟考试试卷 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/commitTestExam")
	@ResponseBody
	public Msg commitTestExam(@RequestParam("testExamId")int testExamId) {
		Msg msg = new Msg();
		//计算当前考试总分数与每个题型的分数，  并修改数据库中得值
		try {
			logger.info("获取当前考卷的各个考题考试分数信息");
			int scortCount=0;//总分数
			List<Map<String, Object>> testExamScoreMsg = testExamMsgService.getTestExamScoreByType(testExamId);
			for (Map<String, Object> map : testExamScoreMsg) {
				scortCount = scortCount + Integer.parseInt(map.get("score").toString()) ;
			}
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("exam_score", scortCount);
			parm.put("testExamId", testExamId);
			logger.info("进行当前模拟考卷考试分数的修改操作");
			testExamMsgService.updateTextExamExam_Score(parm);
			msg.setSuccess(true);
		} catch (Exception e) {
			logger.error("获取当前考卷的各个考题考试分数信息接口异常");
			msg.setSuccess(false);
		}
		//修改当前考卷状态
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", testExamId);
		map.put("type", 2);     //考卷已完成状态
		try {
			logger.info("修改当前模拟考卷状态");
			testExamMsgService.updateTestExamType(map);
		} catch (Exception e) {
			logger.error("修改当前模拟考卷状态接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: showTestExamPaperMsg   
	 * @Description: 跳转模拟考试  考卷详情页面
	 * @param: @param testItemId
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/showTestExamPaperMsg/{testItemId}")
	public String showTestExamPaperMsg(@PathVariable("testItemId") int testItemId , Model model) {
		model.addAttribute("testItemId", testItemId);
		//模拟考试基本信息
		TestExamMsg testExamMsg = new TestExamMsg();
		try {
			logger.info("获取当前模拟考卷id下的具体信息");
			testExamMsg = testExamMsgService.getTestExamMsgById(testItemId);
		} catch (Exception e1) {
			logger.error("当前模拟考卷id下的具体信息获取异常");
		}
		model.addAttribute("examName", testExamMsg.getExam_name());
		model.addAttribute("countS",testExamMsg.getCount_score());
		model.addAttribute("examS", testExamMsg.getExam_score());
		//考题信息
		List<TestExamItemMsg> examItemMsgs = new ArrayList<TestExamItemMsg>();
		try {
			logger.info("获取该模拟考卷题型信息");
			examItemMsgs = testExamItemMsgService.getTestExamItemMsgAllByExamId(testItemId);
			for (TestExamItemMsg testExamItemMsg : examItemMsgs) {
				switch (testExamItemMsg.getItem_type()) {
				case 1:  //单选题
					{
						model.addAttribute("radioC", testExamItemMsg.getItem_count());  //题型数量
						model.addAttribute("radioSC", testExamItemMsg.getItem_counts());  //题型总分
					}
					break;
				case 2:  //多选题
					{
						model.addAttribute("checkC", testExamItemMsg.getItem_count());  //题型数量
						model.addAttribute("checkSC", testExamItemMsg.getItem_counts());  //题型总分
					}
					break;
				case 3:  //填空题
					{
						model.addAttribute("gapC", testExamItemMsg.getItem_count());  //题型数量
						model.addAttribute("gapSC", testExamItemMsg.getItem_counts());  //题型总分
					}
					break;
				case 4:  //判断题
					{
						model.addAttribute("judgeC", testExamItemMsg.getItem_count());  //题型数量
						model.addAttribute("judgeSC", testExamItemMsg.getItem_counts());  //题型总分
					}
					break;
				}
			}
		} catch (Exception e) {
			logger.error("模拟考卷题型信息获取异常");
		}
		return "student/myExam/testExam/showTestExamPaper";
	}
	
	
	/***
	 * 
	 * @Title: getTextExamRadioItemList   
	 * @Description: 获取当前模拟考试id下的单选题的答题状况
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getTextExamRadioItemList")
	@ResponseBody
	public Msg getTextExamRadioItemList(@RequestParam("testExamId") int testExamId) {
		Msg msg = new Msg();
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("testExamId", testExamId);
		parm.put("item_type", 1);  //单选题
		parm.put("table_name", "tb_item_radio");  //单选题对应的表
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		try {
			logger.info("模拟考试-考试详情-获取当前模拟考试的单选题答题信息");
			res = testExamPaperService.getOveredTestExamItemMsg(parm);
		} catch (Exception e) {
			logger.error("异常-->模拟考试-考试详情-获取当前模拟考试的单选题答题信息");
			e.printStackTrace();
		}
		msg.addResult("radios", res);
		return msg;
	}
}
