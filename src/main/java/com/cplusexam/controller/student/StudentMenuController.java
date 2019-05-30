package com.cplusexam.controller.student;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.service.student.IStudentMenuService;
import com.cplusexam.util.Msg;

/***
 * 
 * @ClassName:  StudentMenuController   
 * @Description:处理学生的菜单请求
 * @author: FanD
 * @date:   2019年3月11日 下午10:33:20
 */
@RequestMapping("/studentMenu")
@Controller
public class StudentMenuController {

	private Logger logger = Logger.getLogger(StudentMenuController.class);
	
	@Autowired
	private IStudentMenuService studentMenuService;
	
	
	/***
	 * 
	 * @Title: getMenuTree   
	 * @Description: 获取学生菜单树 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getMenuTree")
	@ResponseBody
	public Msg getMenuTree() {
		Msg msg = new Msg();
		try {
			logger.info("获取学生菜单树");
			msg.addResult("tree", studentMenuService.getMenuTree(0));
		} catch (Exception e) {
			logger.error("获取学生菜单树接口异常");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: studentHome   
	 * @Description:返回学生信息首页
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/studentHome")
	public String studentHome() {
		return "student/home";
	}
	
	/***
	 * 
	 * @Title: studentItem   
	 * @Description: 返回学生题库练习界面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/studentItem")
	public String studentItem() {
		return "student/itemExercise/itemHome";
	}
	
	
	/***
	 * 
	 * @Title: studentOffExam   
	 * @Description:跳转学生  正式考试页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/studentOffExam")
	public String studentOffExam() {
		return "student/myExam/officicalExam/officialExam";
	}
	
	
	/***
	 * 
	 * @Title: studentTestExam   
	 * @Description: 跳转学生  模拟考试页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/studentTestExam")
	public String studentTestExam() {
		return "student/myExam/testExam/testExam";
	}
}
