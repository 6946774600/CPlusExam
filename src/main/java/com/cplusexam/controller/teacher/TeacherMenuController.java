package com.cplusexam.controller.teacher;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.service.teacher.ITeacherMenuService;
import com.cplusexam.util.Msg;

@RequestMapping("/teacherMenu")
@Controller
public class TeacherMenuController {

	private Logger logger = Logger.getLogger(TeacherMenuController.class);
	
	@Autowired
	private ITeacherMenuService teacherMenuService;
	
	
	@RequestMapping("/getMenuTree")
	@ResponseBody
	public Msg getMenuTree() {
		Msg msg = new Msg();
		logger.info("获取教师菜单树");
		msg.addResult("tree", teacherMenuService.getMenuTree(0));
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: teacherHome   
	 * @Description: 返回教师页面home页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/teacherHome")
	public String teacherHome() {
		return "teacher/home";
	}
	
	/***
	 * 
	 * @Title: toItemInptPage   
	 * @Description: 返回考题录入页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/itemInpt")
	public String toItemInptPage() {
		return "teacher/item/itemInput";
	}
	
	
	/***
	 * 
	 * @Title: toItemListMy   
	 * @Description: 返回题库录入--我的页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/itemListMy")
	public String toItemListMy() {
		return "teacher/itemPool/itemListMy";
	}
	
	
	/***
	 * 
	 * @Title: toItemListAll   
	 * @Description: 返回题库录入--所有考题页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/itemListAll")
	public String toItemListAll() {
		return "teacher/itemPool/itemListAll";
	}
}
