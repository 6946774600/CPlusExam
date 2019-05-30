package com.cplusexam.controller.system;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.service.exam.IExamModelService;
import com.cplusexam.service.system.IAdminMenuService;
import com.cplusexam.util.Msg;

@RequestMapping("/adminMenu")
@Controller
public class AdminMenuController {

	private Logger logger = Logger.getLogger(AdminMenuController.class);
	
	@Autowired
	private IAdminMenuService adminMenuService;
	
	@Autowired
	private IExamModelService examModelService;
	/***
	 * 
	 * @Title: getTree   
	 * @Description: 获取菜单树 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getMenuTree")
	@ResponseBody
	public Msg getTree() {
		Msg msg = new Msg();
		msg.addResult("tree", adminMenuService.getMenuTree(-1));
		return msg;
	}
	
	/***
	 * 
	 * @Title: adminHome   
	 * @Description: 返回admin用户的首页jsp
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/adminHome")
	public String adminHome() {
		return "admin/home";
	}
	
	/***
	 * 
	 * @Title: toStudentMsg   
	 * @Description: 返回学生信息维护页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/studentMsg")
	public String toStudentMsg() {
		return "admin/msgManage/student/studentList";
	}
	
	/***
	 * 
	 * @Title: toShiroMsg   
	 * @Description: 跳转权限管理界面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/shiroMsg")
	public String toShiroMsg() {
		return "admin/msgManage/shiro/shiroList";
	}
	
	/***
	 * 
	 * @Title: toClassMsg   
	 * @Description: 跳转班级信息管理界面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/gradeMsg")
	public String toGradeMsg() {
		return "admin/msgManage/grade/gradeList";
	}
	
	
	/***
	 * 
	 * @Title: toTeacherMsg   
	 * @Description: 跳转教师信息维护页面  
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/teacherMsg")
	public String toTeacherMsg() {
		return "admin/msgManage/teacher/teacherList";
	}
	
	
	/***
	 * 
	 * @Title: toItemTypeMsg   
	 * @Description: 跳转考题类型维护界面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/itemTypeMsg")
	public String toItemTypeMsg() {
		return "admin/itemManage/itemType/itemTypeList";
	}
	
	
	/***
	 * 
	 * @Title: toItemListAll   
	 * @Description: 跳转题库页面 显示所有考题信息
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/itemListAll")
	public String toItemListAll() {
		return "admin/itemManage/itemList/itemListAll";
	}
	
	
	/***
	 * 
	 * @Title: examItemType   
	 * @Description: 跳转考试题型管理页面  进入该页面要返回已经定义的考卷信息  对表格信息进行填充
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/examItemType")
	public String toExamItemType(Model model) {
		try {
			logger.info("获取已经定义的试卷信息");
			model.addAttribute("exams", examModelService.getExamModelPageMsg());
		} catch (Exception e) {
			logger.error("获取已经定义的试卷信息接口异常");
		}
		return "admin/examManage/examItemType/itemTypeList";
	}
	
	
	/***
	 * 
	 * @Title: toExamTime   
	 * @Description: 返回考试时间管理界面  
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/examTime")
	public String toExamTime() {
		return "admin/examManage/examTime/examTimeList";
	}
	
}
