package com.cplusexam.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplusexam.bean.system.Grade;
import com.cplusexam.bean.util.UpLoadFile;
import com.cplusexam.service.system.IGradeService;
import com.cplusexam.service.util.IUpLoadFileService;
import com.cplusexam.util.Grid;
import com.cplusexam.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RequestMapping("/adminGrade")
@Controller
public class AdminGradeController {

	private static Logger logger = Logger.getLogger(AdminGradeController.class);
	
	@Autowired
	private IGradeService gradeService;
	
	@Autowired
	private IUpLoadFileService upLoadFileService;
	
	/***
	 * 
	 * @Title: getGradeList   
	 * @Description: 获取班级信息维护页面的表格数据 
	 * @param: @return      
	 * @return: Grid      
	 * @throws
	 */
	@RequestMapping("/getGradeList")
	@ResponseBody
	public Grid getGradeList(@RequestParam("page") int page,@RequestParam("limit")int limit) {
		//使用mybatis的分页插件PageHelper完成分页查询
		//在查询前调用PageHelper
		PageHelper.startPage(page, limit);
		//startPage后紧跟的查询  就是一个分页查询
		List<Map<String, Object>> list = gradeService.getGradeListAndCountUser();
		//查询完成后  使用pageInfo包装查询结果  只需要将pageInfo交给页面就行 里面封装了详细的分页信息
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		Grid grid = new Grid(pageInfo.getTotal(), pageInfo.getList());
		return grid;
	}
	
	
	/***
	 * 
	 * @Title: addGradePage   
	 * @Description: 跳转班级添加页面
	 * @param: @param endPage
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/addGradePage")
	public String addGradePage(@RequestParam("endPage")int endPage,Model model) {
		model.addAttribute("pageNum", endPage);
		return "admin/msgManage/grade/addGrade";
	}
	
	
	/***
	 * 
	 * @Title: gradeIdOnaly   
	 * @Description: 判断用户输入的班级id是否唯一
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/gradeIdOnaly")
	@ResponseBody
	public Msg gradeIdOnaly(@RequestParam("gradeId")String gradeId) {
		Msg msg = new Msg();
		msg.setSuccess(gradeService.gradeIdOnaly(gradeId));
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: addGrade   
	 * @Description: 班级信息的添加操作
	 * @param: @param grade
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/addGrade")
	@ResponseBody
	public Msg addGrade(Grade grade) {
		Msg msg = new Msg();
		msg.setSuccess(gradeService.addGrade(grade));
		if(msg.isSuccess()) {
			msg.setMsg("添加班级信息成功！");
		}else {
			msg.setMsg("添加班级信息失败！");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: editGradePage   
	 * @Description: 班级信息修改页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/editGradePage")
	public String editGradePage(@RequestParam("now_page")int now_page,@RequestParam("gradeId") String gradeId,Model model){
		model.addAttribute("now_page", now_page);
		model.addAttribute("gradeId", gradeId);
		return "admin/msgManage/grade/editGrade";
	}
	
	
	/***
	 * 
	 * @Title: getGradeById   
	 * @Description: 根据班级id  获取班级信息
	 * @param: @param gradeId
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getGradeById")
	@ResponseBody
	public Msg getGradeById(@RequestParam("gradeId") String gradeId) {
		Msg msg = new Msg();
		msg.addResult("grade", gradeService.getGradeById(gradeId));
		return msg;
	}
	
	
	@RequestMapping("/updateGrade")
	@ResponseBody
	public Msg updateGrade(Grade grade) {
		Msg msg = new Msg();
		msg.setSuccess(gradeService.updateGradeById(grade));
		if(msg.isSuccess()) {
			msg.setMsg("添加班级修改成功！");
		}else {
			msg.setMsg("添加班级修改失败！");
		}
		return msg;
	}
	
	
	@RequestMapping("/deleteGrade")
	@ResponseBody
	public Msg deleteGrade(@RequestParam("id")String ids) {
		Msg msg = new Msg();
		String [] idstr = ids.split(",");
		List<String> listGradeId = new ArrayList<>();
		for (String temp : idstr) {
			listGradeId.add(temp);
		}
		//先查询要删除的班级信息中 是否已经有用户在使用
		String names = gradeService.getUsedGradeById(listGradeId);
		if(names.length()>0) {
			msg.setMsg("班级编号：【<font color='#FF5722'>"+names.substring(0, names.length()-1)+"</font>】已被使用，不能删除！");
		} else {
			gradeService.deleteGradeBuIds(listGradeId);
			msg.setMsg("成功删除 <font color = '#01AAED'>"+listGradeId.size()+"</font> 条数据");
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: improtGrade   
	 * @Description: 加载模版文件  并添加到数据库中
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/improtGrade")
	@ResponseBody
	public Msg improtGrade(@RequestParam("fileId")int fileId) {
		Msg msg = new Msg();
		//根据文件id  读取文件信息
		UpLoadFile file = upLoadFileService.getFileById(fileId);
		logger.info("读取文件："+file.getFileUrl()+file.getFileUUName()+file.getFilTemp());
		try {
			logger.info("文件导入操作");
			InputStream inputStream =new FileInputStream(new File(file.getFileUrl()+file.getFileUUName()+file.getFilTemp()));
			//更改文件使用状态
			Map<String, Object> map = new HashMap<>();
			map.put("used", 1);
			map.put("fileId", fileId);
			upLoadFileService.updateFileUsedById(map);
			POIFSFileSystem system = new POIFSFileSystem(inputStream);
			Workbook workbook = new HSSFWorkbook(system);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			//进行模版合法性的判断
			String [] modelStr = {"班级编号","班级名称","所属年级","所属专业","备注说明"};
			logger.info("进行模版正确性判断");
			for(int i=0;i<5;i++) {
				Cell cell = row.getCell(i);
				if(!modelStr[i].equals(cell.getStringCellValue())) {
					logger.error("模本有问题");
					msg.setMsg("模版格式存在问题，请下载最新模版！");
					return msg;
				}
			}
			//模版没有问题  导出模版数据 进行数据的添加
			try {
				msg.setMsg(gradeService.excleImportGradeMsg(sheet));
			}catch (Exception e) {
				msg.setMsg("模版数据导入失败，请下载最新模版，或检查数据格式！");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("文件导入操作失败");
			msg.setSuccess(false);
		}
		return msg;
	}
	
	
	/***
	 * 
	 * @Title: getGradeSelect   
	 * @Description: 获取班级下拉树 
	 * @param: @return      
	 * @return: Msg      
	 * @throws
	 */
	@RequestMapping("/getGradeSelect")
	@ResponseBody
	public Msg getGradeSelect() {
		Msg msg = new Msg();
		msg.addResult("select", gradeService.getGradeSelect());
		return msg;
	}
}
