package com.cplusexam.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.bean.system.User;
import com.cplusexam.bean.util.UpLoadFile;
import com.cplusexam.service.system.IGradeService;
import com.cplusexam.service.system.IUserService;
import com.cplusexam.service.util.IUpLoadFileService;
import com.cplusexam.util.Grid;
import com.cplusexam.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 * 
 * @ClassName:  AdminStudentController   
 * @Description:处理学生信息维护页面的所有请求
 * @author: FanD
 * @date:   2019年1月24日 下午4:45:48
 */
@RequestMapping("/adminStudent")
@Controller
public class AdminStudentController {

	private static Logger logger = Logger.getLogger(AdminStudentController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGradeService gradeService;
	
	@Autowired
	private IUpLoadFileService upLoadFileService;
	/***
	 * 
	 * @Title: getStudentList   
	 * @Description: 返回学生信息维护页面的学生数据   涵盖查询功能
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getStudentList")
	@ResponseBody
	public Grid getStudentList(@RequestParam("page") int page,@RequestParam("limit")int limit,User user) {
		PageHelper.startPage(page, limit);
		List<Map<String, Object>> list = userService.getStrudentMsg(user);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	
	
	/***
	 * 
	 * @Title: addStudentPage   
	 * @Description: 返回添加学生页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/addStudentPage")
	public String addStudentPage(@RequestParam("endPage")int endPage) {
		return "admin/msgManage/student/addStudent";
	}
	
	
	/***
	 * 
	 * @Title: studentIdOnaly   
	 * @Description: 判断学生学号是否唯一
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/studentIdOnaly")
	@ResponseBody
	public Msg studentIdOnaly(@RequestParam("loginName") String loginName) {
		Msg msg = new Msg();
		msg.setSuccess(userService.studentIdOnaly(loginName)==0?true:false);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: addStudent   
	 * @Description: 学生信息的添加
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addStudent")
	@ResponseBody
	public Msg addStudent(User user) {
		logger.info("学生信息添加操作");
		Msg msg = new Msg();
		user.setShiroId(3);
		user.setPassword(new Md5Hash(user.getLoginName(),user.getLoginName()).toString());
		System.out.println(user);
		msg.setSuccess(userService.addUser(user));
		if(msg.isSuccess()){
			msg.setMsg("学生信息添加成功！");
		}else {
			msg.setMsg("学生信息添加失败！");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: editStudentPage   
	 * @Description: 返回学生信息修改页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/editStudentPage")
	public String editStudentPage(@RequestParam("loginName") String loginName,@RequestParam("now_page") int now_page,Model model) {
		model.addAttribute("loginName", loginName);
		model.addAttribute("now_page", now_page);
		return "admin/msgManage/student/editStudent";
	}
	
	
	/***
	 * 
	 * @Title: getStudentById   
	 * @Description: 根据id  获取学生信息
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
		msg.addResult("student", user);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateStudent   
	 * @Description: 学生信息的修改方法
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateStudent")
	@ResponseBody
	public Msg updateStudent(User user) {
		Msg msg = new Msg();
		msg.setSuccess(userService.updateUser(user));
		if(msg.isSuccess()){
			msg.setMsg("学生信息修改成功！");
		}else {
			msg.setMsg("学生信息修改失败！");
		}
		return msg;
	}
	
	
	@RequestMapping("/deleteStudent")
	@ResponseBody
	public Msg deleteStudent(@RequestParam("id")String loginNames) {
		Msg msg = new Msg();
		String [] idstr = loginNames.split(",");
		List<String> listStudentId = new ArrayList<>();
		for (String temp : idstr) {
			listStudentId.add(temp);
		}
		msg.setSuccess(userService.deleteUserBuId(listStudentId));
		if(msg.isSuccess()) {
			msg.setMsg("成功删除 <font color = '#01AAED'>"+listStudentId.size()+"</font> 条数据");
		}else {
			msg.setMsg("删除失败");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: modelFileDownLoad   
	 * @Description: 下载学生信息模版
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/modelFileDownLoad")
	public void modelFileDownLoad(HttpServletResponse response) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = userService.modelStudentFile(workbook);
		
		try {
			response.setHeader("Content-Disposition", "attachment;filename="+new String("studentFileModel.xlsx".getBytes("utf-8"),"iso8859-1"));
			response.setContentType("application/ynd.ms-excel;charset=UTF-8");
			OutputStream out=response.getOutputStream();
			workbook.write(out); 
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("模版加载异常");
		}
	}
	
	
	/***
	 * 
	 * @Title: improtStudent   
	 * @Description: 进行学生信息的模版批量导入
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/improtStudent")
	@ResponseBody
	public Msg improtStudent(@RequestParam("fileId") int fileId) {
		Msg msg = new Msg();
		//根据文件id  读取文件信息
		UpLoadFile file = upLoadFileService.getFileById(fileId);
		logger.info("读取文件："+file.getFileUrl()+file.getFileUUName()+file.getFilTemp());
		//更改文件使用状态
		Map<String, Object> map = new HashMap<>();
		map.put("used", 1);
		map.put("fileId", fileId);
		upLoadFileService.updateFileUsedById(map);
		try {
			//获取文件  进行数据导入
			InputStream inputStream =new FileInputStream(new File(file.getFileUrl()+file.getFileUUName()+file.getFilTemp()));
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			msg.setMsg(userService.improtStudentFile(sheet));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			msg.setMsg("信息模版导入异常，请下载最新模版。");
		}
		
		return msg;
	}
	
}
