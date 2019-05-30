package com.cplusexam.controller.system;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.service.system.IShiroService;
import com.cplusexam.util.Grid;

/***
 * 
 * @ClassName:  AdminShiroController   
 * @Description:处理系统角色信息管理页面的所有数据请求 
 * @author: FanD
 * @date:   2019年1月22日 下午5:31:06
 */
@RequestMapping("/adminShiro")
@Controller
public class AdminShiroController {

	private static Logger logger = Logger.getLogger(AdminShiroController.class);
	
	@Autowired
	private IShiroService shiroService;
	
	@RequestMapping("/getShiroList")
	@ResponseBody
	public Grid getShiroList() {
		Grid grid = new Grid();
		logger.info("获取系统权限管理信息表数据");
		grid.setdata(shiroService.getShiroList());
		return grid;
	}
	
	/***
	 * 
	 * @Title: shiroUserList   
	 * @Description: 根据权限id  跳转该权用户页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/shiroUserList")
	public String shiroUserList(@RequestParam("id") int shiroId,Model model) {
		model.addAttribute("shiro", shiroService.getShiroById(shiroId));
		return "admin/msgManage/shiro/shiroUserList";
	}
	
	
	/***
	 * 
	 * @Title: getShiroUserList   
	 * @Description: 获取权限id下 所有的用户信息
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getShiroUserList")
	@ResponseBody
	public Grid getShiroUserList(@RequestParam("page") int page,@RequestParam("limit")int limit,@RequestParam("shiroId")int shiroId) {
		Grid grid = new Grid();
		
		return grid;
	}
	
}
