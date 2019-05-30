package com.cplusexam.controller.student;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.bean.system.User;
import com.cplusexam.service.system.IUserService;
import com.cplusexam.util.Msg;
import com.cplusexam.util.ShiroSessionUtil;

@RequestMapping("/studentHome")
@Controller
public class StudentHomeController {

	
	private Logger logger = Logger.getLogger(StudentHomeController.class);
	
	@Autowired
	private IUserService userService;
	
	
	
	/***
	 * 
	 * @Title: getStudentById   
	 * @Description: 根据id  获取用户信息
	 * @param: @param loginName
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getStudentById")
	@ResponseBody
	public Msg getStudentById(@RequestParam("loginName")String loginName) {
		Msg msg = new Msg();
		User user = userService.getUserById(loginName);
		user.setPassword("");
		msg.addResult("user", user);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateStudent   
	 * @Description: 用户信息修改操作
	 * @param: @param user
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateStudent")
	@ResponseBody
	public Msg updateStudent(User user) {
		Msg msg = new Msg();
		msg.setSuccess(userService.updateUserByHome(user));
		if(msg.isSuccess()){
			msg.setMsg("信息修改成功！");
		}else {
			msg.setMsg("信息修改失败！");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updatePassword   
	 * @Description: 修改密码操作
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Msg updatePassword(@RequestParam("password")String password,@RequestParam("loginName")String loginName) {
		Msg msg = new Msg();
		Map<String, Object> map = new HashMap<>();
		map.put("password", new Md5Hash(password, loginName).toString());
		map.put("loginName", loginName);
		msg.setSuccess(userService.updatePassword(map));
		if(msg.isSuccess()){
			msg.setMsg("密码修改成功！");
		}else {
			msg.setMsg("密码修改失败！");
		}
		return msg;
	}
	
	
	
	/***
	 * 
	 * @Title: confimPasd   
	 * @Description: 判断用户输入的密码是否正确
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/confimPasd")
	@ResponseBody
	public Msg confimPasd(@RequestParam("password") String password, @RequestParam("loginName") String loginName) {
		Msg msg = new Msg();
		User user = ShiroSessionUtil.getLoginUser();
		if(user.getLoginName().equals(loginName) && user.getPassword().equals(new Md5Hash(password, loginName).toString())) {
			msg.setSuccess(true);
		}else {
			msg.setSuccess(false);
		}
		return msg;
	}
}
