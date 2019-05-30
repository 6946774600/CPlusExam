package com.cplusexam.controller.exam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.bean.exam.ExamModel;
import com.cplusexam.bean.exam.ExamModelItem;
import com.cplusexam.service.Item.IItemTypeService;
import com.cplusexam.service.exam.IExamModelItemSerivce;
import com.cplusexam.service.exam.IExamModelService;
import com.cplusexam.service.exam.IExamTimeService;
import com.cplusexam.util.Msg;

/***
 * 
 * @ClassName: AdminExamModelController
 * @Description:处理管理员用户 关于考卷题型管理页面的请求
 * @author: FanD
 * @date: 2019年3月6日 下午3:33:17
 */
@Controller
@RequestMapping("/adminExamModel")
public class AdminExamModelController {

	private Logger logger = Logger.getLogger(AdminExamModelController.class);

	@Autowired
	private IExamModelService examModelService;

	@Autowired
	private IExamModelItemSerivce iExamModelItemSerivce;

	@Autowired
	private IItemTypeService iItemTypeService;
	
	@Autowired
	private IExamTimeService examTimeService;

	/***
	 * 
	 * @Title: addExamModelPage @Description: 跳转考卷题型添加界面 @param: @return @return:
	 *         String @throws
	 */
	@RequestMapping("/addExamModelPage")
	public String addExamModelPage(Model model) {
		try {
			model.addAttribute("itemType", iItemTypeService.getItemTypeList());
		} catch (Exception e) {
		}
		return "admin/examManage/examItemType/addExamModel";
	}


	/***
	 * 
	 * @Title: addExamModel   
	 * @Description: 进行考卷信息的添加操作
	 * @param: @param examModelItems
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addExamModel/{name}")
	@ResponseBody
	public Msg addExamModel(@RequestBody List<ExamModelItem> examModelItems,@PathVariable("name")String exam_name) {
		Msg msg = new Msg();
		System.out.println(exam_name);
		//进行考题信息的录入 
		//因为前台传值问题  这里要重新计算出  总分数  和每种类型题述的总分数
		ExamModel examModel = new ExamModel();
		examModel.setExam_name(exam_name);
		int count_score=0;
		for (ExamModelItem examModelItem : examModelItems) {
			examModelItem.setItem_counts(examModelItem.getItem_count()*examModelItem.getItem_score());
			count_score += examModelItem.getItem_counts();
		}
		examModel.setCount_score(count_score);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		examModel.setCreate_time(sdf.format(new Date()));
		//添加考卷基本信息  并返回考卷信息的id
		try {
			logger.info("进行考卷基本信息添加");
			examModelService.addExamModel(examModel);
			//将返回的id信息  添加到题型信息中
			for (ExamModelItem examModelItem : examModelItems) {
				examModelItem.setExam_id(examModel.getUuid());
			}
			//添加考题选题信息
			logger.info("进行考卷题型信息添加");
			iExamModelItemSerivce.addExamModelItems(examModelItems);
			msg.setMsg("添加成功");
		} catch (Exception e) {
			logger.error("考卷基本信息添加异常");
			msg.setMsg("添加接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: deleteExamModel   
	 * @Description: 根据 id  删除当前定义的考卷信息
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/deleteExamModel/{id}")
	@ResponseBody
	public Msg deleteExamModel(@PathVariable("id")int id) {
		Msg msg = new Msg();
		try {
			//先判断当前试卷是否已经被考试时间定义中被使用
			if(examTimeService.getExamTimeByExamModelId(id)) {
				logger.info("当前考卷信息已经被使用");
				msg.setMsg("当前考卷信息已经被使用，不能删除！");
			}else {
				logger.info("当前考卷信息没有被使用");
				//进行该考卷信息的删除   需要删除该考卷的基本信息和定义的题型信息
				logger.info("删除当前考卷的所有信息");
				examModelService.deleteExamAllMsg(id);
				msg.setMsg("成功删除当前试卷信息");
			}
		} catch (Exception e) {
			msg.setMsg("试题删除接口异常");
		}
		return msg;
	}

}
