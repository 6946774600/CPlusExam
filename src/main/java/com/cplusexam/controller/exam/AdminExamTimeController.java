package com.cplusexam.controller.exam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.cplusexam.bean.exam.ExamModel;
import com.cplusexam.bean.exam.ExamTime;
import com.cplusexam.service.exam.IExamModelItemSerivce;
import com.cplusexam.service.exam.IExamModelService;
import com.cplusexam.service.exam.IExamTimeService;
import com.cplusexam.util.Grid;
import com.cplusexam.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/***
 * 
 * @ClassName:  AdminExamTimeController   
 * @Description:处理考试时间管理所有页面的请求
 * @author: FanD
 * @date:   2019年3月11日 上午11:41:08
 */
@RequestMapping("/adminExamTime")
@Controller
public class AdminExamTimeController {

	private Logger logger = Logger.getLogger(AdminExamTimeController.class);
	
	@Autowired
	private IExamTimeService examTimeService;
	
	@Autowired
	private IExamModelService examModelService;
	
	@Autowired
	private IExamModelItemSerivce examModelItemSerivce;
	
	
	@RequestMapping("/getExamTimeList")
	@ResponseBody
	public Grid getExamTimeList(@RequestParam("page") int page,@RequestParam("limit")int limit) {
		PageHelper.startPage(page, limit);
		List<Map<String, Object>> list;
		Grid grid=null;
		try {
			logger.info("进行考试时间信息的查询");
			list = examTimeService.getExamTimeList();
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
			grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		} catch (Exception e) {
			logger.error("考试时间信息查询接口异常");
		}
		return grid;
	}
	
	
	@RequestMapping("/getExamModelMsg/{id}")
	@ResponseBody
	public Msg getExamModelMsg(@PathVariable("id") int id) {
		Msg msg = new Msg();
		try {
			logger.info("获取试卷定义相关信息");
			ExamModel examModel = examModelService.getExamModelById(id);
			msg.addResult("examModel", examModel);
			List<Map<String, Object>> examModelItem = examModelItemSerivce.getExamModelItemByModelId(id);
			msg.addResult("examModelItems", examModelItem);
		} catch (Exception e) {
			logger.error("试卷定义相关信息获取接口异常");
		}  
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: addExamTimePage   
	 * @Description: 跳转考试时间添加页面
	 * @param: @param endPage
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("addExamTimePage")
	public String addExamTimePage(@RequestParam("endPage")int endPage,Model model) {
		model.addAttribute("endPage", endPage);
		return "admin/examManage/examTime/addExamTime";
	}
	
	
	/***
	 * 
	 * @Title: getItemModelTree   
	 * @Description: 获取考题类型树
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getItemModelTree")
	@ResponseBody
	public Msg getItemModelTree() {
		Msg msg = new Msg();
		try {
			logger.info("获取试题信息树");
			msg.addResult("tree", examModelService.getExamModelTree());
		} catch (Exception e) {
			logger.error("获取试题信息树异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: addExamTime   
	 * @Description: 进行考试时间的录入
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addExamTime")
	@ResponseBody
	public Msg addExamTime(ExamTime examTime) {
		Msg msg = new Msg();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		examTime.setInsert_time(sdf.format(new Date()));
		try {
			logger.info("进行考试时间的添加");
			msg.setSuccess(examTimeService.addExamItem(examTime));
			if(msg.isSuccess()) {
				msg.setMsg("考试时间添加成功");
			}else {
				msg.setMsg("考试时间添加接口异常");
			}
		} catch (Exception e) {
			logger.error("考试时间添加接口异常");
			msg.setMsg("考试时间添加接口异常");
		}
		return msg;
	}
	
	
	/*
	 * @RequestMapping("/deleteExamTime")
	 * 
	 * @ResponseBody public Msg deleteExamTime(@RequestParam("id")String ids) { Msg
	 * msg = new Msg(); String [] idstr = ids.split(","); List<Integer>
	 * listStudentId = new ArrayList<>(); for (String temp : idstr) {
	 * listStudentId.add(Integer.parseInt(temp)); }
	 * System.out.println("要删除的考试时间id"+ids); return msg; }
	 */
}
