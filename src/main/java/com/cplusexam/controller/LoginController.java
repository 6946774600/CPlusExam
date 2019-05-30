package com.cplusexam.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.bean.system.User;
import com.cplusexam.service.system.IUserService;
import com.cplusexam.util.Msg;
import com.cplusexam.util.ShiroSessionUtil;

@RequestMapping("/Login")
@Controller
public class LoginController {

	
	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private IUserService userService;
	
	/***
	 * 
	 * @Title: userLogin   
	 * @Description: 用户登录方法   使用shior  进行用户的登录验证
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	@ResponseBody
	public Msg userLogin(@RequestParam(value="loginName",required=true)String loginName
			,@RequestParam(value="password",required=true) String password) {
		Msg msg = new Msg();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, new Md5Hash(password,loginName).toString()); //密码进行md5加盐操作
		try {
			subject.login(token);
			msg.setSuccess(true);
			logger.info("身份认证通过");
			Session session = subject.getSession();
			User user = userService.getUserById(loginName);
			session.setAttribute("loginUser", user);  //将登陆用户存储到sessoin中
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("用户名或者密码错误！");
			logger.error("身份认证失败");
		}
		return msg;
	}
	
	/***
	 * 
	 * @Title: goIndex   
	 * @Description: 根据当前登录人的角色，跳转不同的页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/goIndex")
	public String goIndex(Model model) {
		String url ="";
		model.addAttribute("loginName", ShiroSessionUtil.getLoginUser().getLoginName());
		model.addAttribute("userName", ShiroSessionUtil.getLoginUser().getName());
		switch (ShiroSessionUtil.getLoginUser().getShiroId()) {
		case 1:    //管理员登录
			url = "admin/index";
			break;
		case 2:   //老师登录
			url = "teacher/index";
			break;
		case 3:   //学生登录
			url = "student/index";
			break;
		}
		return url;
	}
	
	/***
	 * 
	 * @Title: loginOut   
	 * @Description: 退出方法 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/loginOut")
	@ResponseBody
	public Msg loginOut() {
		Msg msg = new Msg();
		logger.info("用户退出");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return msg;
	}
	
}
