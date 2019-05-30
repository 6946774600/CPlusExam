package com.cplusexam.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
import com.cplusexam.service.system.ITeacherService;
import com.cplusexam.service.system.IUserService;
import com.cplusexam.service.util.IUpLoadFileService;
import com.cplusexam.util.Grid;
import com.cplusexam.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 * 
 * @ClassName:  AdminTeacherController   
 * @Description  处理老师信息的所有请求
 * @author: FanD
 * @date:   2019年1月25日 上午11:45:44
 */

@RequestMapping("/adminTeacher")
@Controller
public class AdminTeacherController {

	
	private static Logger logger = Logger.getLogger(AdminTeacherController.class);
	
	@Autowired
	private ITeacherService teacherService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGradeService gradeService;
	
	@Autowired
	private IUpLoadFileService upLoadFileService;
	
	@RequestMapping("/getTeacherSelect")
	@ResponseBody
	public Msg getTeacherSelect() {
		Msg msg = new Msg();
		msg.addResult("select", teacherService.getTeacherSelect());
		return msg;
	}
	
	
	@RequestMapping("/getTeacherMsg")
	@ResponseBody
	public Grid getTeacherMsg(@RequestParam("page") int page,@RequestParam("limit")int limit,User user) {
		PageHelper.startPage(page, limit);
		List<Map<String, Object>> list = teacherService.getTeacherMsg(user);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	/***
	 * 
	 * @Title: addTeacherPage   
	 * @Description: 返回添加教师页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/addTeacherPage")
	public String addTeacherPage(@RequestParam("endPage")int endPage) {
		return "admin/msgManage/teacher/addTeacher";
	}
	
	/***
	 * 
	 * @Title: addTeacher   
	 * @Description: 教职工信息的添加
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addTeacher")
	@ResponseBody
	public Msg addTeacher(User user) {
		logger.info("教职工信息添加操作");
		Msg msg = new Msg();
		user.setShiroId(2);
		user.setPassword(new Md5Hash(user.getLoginName(),user.getLoginName()).toString());
		System.out.println(user);
		msg.setSuccess(userService.addUser(user));
		if(msg.isSuccess()){
			msg.setMsg("教师信息添加成功！");
		}else {
			msg.setMsg("教师信息添加失败！");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: editTeacherPage   
	 * @Description: 返回教师信息修改页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/editTeacherPage")
	public String editTeacherPage(@RequestParam("loginName") String loginName,@RequestParam("now_page") int now_page,Model model) {
		model.addAttribute("loginName", loginName);
		model.addAttribute("now_page", now_page);
		return "admin/msgManage/teacher/editTeacher";
	}
	
	
	/***
	 * 
	 * @Title: getTeacherById   
	 * @Description: 根据id  获取教师信息
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getTeacherById")
	@ResponseBody
	public Msg getTeacherById(@RequestParam("loginName")String loginName) {
		Msg msg = new Msg();
		User user = userService.getUserById(loginName);
		user.setPassword("");
		msg.addResult("teacher", user);
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: updateTeacher   
	 * @Description: 学生信息的修改方法
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/updateTeacher")
	@ResponseBody
	public Msg updateTeacher(User user) {
		Msg msg = new Msg();
		msg.setSuccess(userService.updateUser(user));
		if(msg.isSuccess()){
			msg.setMsg("教师信息修改成功！");
		}else {
			msg.setMsg("教师信息修改失败！");
		}
		return msg;
	}
	
	
	
	@RequestMapping("/deleteTeacher")
	@ResponseBody
	public Msg deleteTeacher(@RequestParam("id")String loginNames) {
		Msg msg = new Msg();
		String [] idstr = loginNames.split(",");
		List<String> listTeacherId = new ArrayList<>();
		for (String temp : idstr) {
			listTeacherId.add(temp);
		}
		//判断当前教师是否已经有代课班级
		String name = gradeService.getTeacherUsedById(listTeacherId);
		if(name.length()>0) {
			msg.setMsg("教师编号：【<font color='#FF5722'>"+name.substring(0, name.length()-1)+"</font>】已有任课班级，不能删除！");
		}else {
			msg.setSuccess(userService.deleteUserBuId(listTeacherId));
			if(msg.isSuccess()) {
				msg.setMsg("成功删除 <font color = '#01AAED'>"+listTeacherId.size()+"</font> 条数据");
			}else {
				msg.setMsg("删除失败");
			}
		}
		return msg;
	}
	
	
	/**
	 * 
	 * @Title: modelFileDownLoad   
	 * @Description: 下载execl模版  
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/modelFileDownLoad")
	public void modelFileDownLoad(HttpServletResponse response) {
		//Workbook workbook = new HSSFWorkbook();
		Workbook workbook = new XSSFWorkbook();
		
		Sheet sheet = userService.modelTeacherFile(workbook);
		
		try {
			response.setHeader("Content-Disposition", "attachment;filename="+new String("teacherFileModel.xlsx".getBytes("utf-8"),"iso8859-1"));
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
	 * @Title: improtTeacher   
	 * @Description: 使用模版进行老师数据的导入 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/improtTeacher")
	@ResponseBody
	public Msg improtTeacher(@RequestParam("fileId") int fileId) {
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
			/*POIFSFileSystem system = new POIFSFileSystem(inputStream);
			Workbook workbook = new HSSFWorkbook(system);*/
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			msg.setMsg(userService.improtTeacherFile(sheet));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			msg.setMsg("信息模版导入异常，请下载最新模版。");
		}
		
		return msg;
	}
}
