package com.cplusexam.service.impl.system;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.system.Grade;
import com.cplusexam.bean.system.User;
import com.cplusexam.dao.system.GradeMapper;
import com.cplusexam.dao.system.UserMapper;
import com.cplusexam.service.system.IUserService;
import com.cplusexam.util.StringUtil;

import sun.util.logging.resources.logging;

@Service
public class UserServiceImpl implements IUserService {

	private static Logger logging = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private GradeMapper gradeMapper;
	
	
	@Override
	public User getUserById(String loginName) {
		return userMapper.getUserById(loginName);
	}

	@Override
	public Map<String, Object> getUserAllById(String loginName) {
		return userMapper.getUserAllById(loginName);
	}

	@Override
	public String getUserShiroEnNameById(String loginName) {
		return userMapper.getUserShiroEnNameById(loginName);
	}

	@Override
	public List<Map<String, Object>> getStrudentMsg(User user) {
		return userMapper.getStrudentMsg(user);
	}

	@Override
	public int studentIdOnaly(String loginName) {
		return userMapper.studentIdOnaly(loginName);
	}

	@Override
	public boolean addUser(User user) {
		return userMapper.addUser(user)!=0;
	}

	@Override
	public boolean updateUser(User user) {
		return userMapper.updateUser(user)!=0;
	}

	@Override
	public boolean deleteUserBuId(List<String> loginNames) {
		return userMapper.deleteUserBuId(loginNames)!=0;
	}

	@Override
	public Sheet modelTeacherFile(Workbook workbook) {
		Sheet sheet = workbook.createSheet("教职工信息录入页");
		
		DataFormat fmt = workbook.createDataFormat();
		Row row;
		Cell cell;
		CellStyle cellStyle;  //单元格样式类
		//第一行第一列
		row = sheet.createRow(0);
		row.setHeight((short) 500);
		sheet.setColumnWidth((short) 0, (short) 3000);
		sheet.setColumnWidth((short) 1, (short) 3000);
		sheet.setColumnWidth((short) 2, (short) 3000);
		sheet.setColumnWidth((short) 3, (short) 3000);
		sheet.setColumnWidth((short) 4, (short) 3000);
		sheet.setColumnWidth((short) 5, (short) 3000);
		
		cell = row.createCell(0);
		cell.setCellValue("教职工编号");
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
		cell.setCellStyle(cellStyle);
		
		//第一行第二列
		cell = row.createCell(1);
		cell.setCellValue("教职工姓名");
		cell.setCellStyle(cellStyle);
		
		
		//第三列  性别下拉框
		cell = row.createCell(2);
		cell.setCellValue("性别");
		cell.setCellStyle(cellStyle);
		
		//第4列  时间
		cell = row.createCell(3);
		cell.setCellValue("入教时间");
		cell.setCellStyle(cellStyle);
		
		//第5列  联系方式
		cell = row.createCell(4);
		cell.setCellValue("联系方式");
		cell.setCellStyle(cellStyle);
		
		//第6列  邮箱
		cell = row.createCell(5);
		cell.setCellValue("邮箱");
		cell.setCellStyle(cellStyle);
		
		//生成下拉框内容  第三列  
		String list[] = {"男","女"};
		//设置下拉框数据
		DataValidationHelper helper = sheet.getDataValidationHelper();   
        //CellRangeAddressList(firstRow, lastRow, firstCol, lastCol)设置行列范围  
        CellRangeAddressList addressList = new CellRangeAddressList(1,51,2,2);  
        //设置下拉框数据  
        DataValidationConstraint constraint = helper.createExplicitListConstraint(list);   
        DataValidation dataValidation = helper.createValidation(constraint, addressList);  
        sheet.addValidationData(dataValidation);  
		
        
		//设置后51行样式
		for(int i =1; i<=51;i++) {
			row = sheet.createRow(i);
			row.setHeight((short) 300);
			//第一列   文本格式
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
			cell = row.createCell(0);
			cellStyle.setDataFormat(fmt.getFormat("@"));
			cell.setCellStyle(cellStyle);
			
			//第二列  文本格式
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
			cell = row.createCell(1);
			cellStyle.setDataFormat(fmt.getFormat("@"));
			cell.setCellStyle(cellStyle);
			
			//第四列  时间格式
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
			cell = row.createCell(3);
			cellStyle.setDataFormat(fmt.getFormat("yyyy/mm/dd"));
			cell.setCellStyle(cellStyle);
			
			//第五列  文本格式
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
			cell = row.createCell(4);
			cellStyle.setDataFormat(fmt.getFormat("@"));
			cell.setCellStyle(cellStyle);
			
			//第六列  文本格式
			cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
			cell = row.createCell(5);
			cellStyle.setDataFormat(fmt.getFormat("@"));
			cell.setCellStyle(cellStyle);
			
		}
		
		
		return sheet;
	}

	
	public String improtTeacherFile(Sheet sheet) {
		int successNum=0;
		int defeatedNum=0;
		int rowLastNum = sheet.getLastRowNum();
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=1;i<=rowLastNum;i++) {
			Row row = sheet.getRow(i);
			if(row!=null) {
				if(StringUtil.isEmpt(row.getCell(0).getStringCellValue()) && StringUtil.isEmpt(row.getCell(1).getStringCellValue()) 
						&& row.getCell(2)==null &&StringUtil.isEmpt(row.getCell(3).getStringCellValue()) 
						&& StringUtil.isEmpt(row.getCell(4).getStringCellValue()) && StringUtil.isEmpt(row.getCell(5).getStringCellValue())){
							logging.info("空列");
							continue;
						}
				if(StringUtil.isEmpt(row.getCell(0).getStringCellValue())) {
					defeatedNum++;
					sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：教职工编号不能为空<br>");
					continue;
				}
				User user;
				try {
					user = new User(row.getCell(0).getStringCellValue(), 
							new Md5Hash(row.getCell(0).getStringCellValue(), row.getCell(0).getStringCellValue()).toString(), 
							row.getCell(1)==null?null:row.getCell(1).getStringCellValue(), 
							row.getCell(2).getStringCellValue().equals("男")?0:1, 
							row.getCell(3)==null?null:sdf.format(row.getCell(3).getDateCellValue()), 
							2, 
							null, 
							row.getCell(4)==null?null:row.getCell(4).getStringCellValue(), 
							row.getCell(5)==null?null:row.getCell(5).getStringCellValue());
				} catch (Exception e) {
					defeatedNum++;
					sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：该行数据读取失败，请检查数据格式是否按照模版要求填写，是否含有未填写信息。<br>");
					continue;
				}
				//判断教职工编号是否唯一
				if(userMapper.studentIdOnaly(user.getLoginName())>0) {
					defeatedNum++;
					sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：该教职工编号已被使用<br>");
					continue;
				}
				try {
					userMapper.addUser(user);
					successNum++;
				} catch (Exception e) {
					defeatedNum++;
					sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：该行数据导入数据库失败，请检查数据格式<br>");
					continue;
				}
			}else {
				defeatedNum++;
				continue;
			}
			
		}
		return "本次导入操作共导入【<font color='#009688'>"+(successNum+defeatedNum)+"</font>】条数据。<br>成功【<font color='#01AAED'>"+successNum+"</font>】条数据，失败【<font color='#FF5722'>"+defeatedNum+"</font>】条数据。<br>"+sb.toString();
	}

	@Override
	public Sheet modelStudentFile(Workbook workbook) {
Sheet sheet = workbook.createSheet("学生信息录入页");
		
		DataFormat fmt = workbook.createDataFormat();
		Row row;
		Cell cell;
		CellStyle cellStyle;  //单元格样式类
		//第一行第一列
		row = sheet.createRow(0);
		row.setHeight((short) 500);
		sheet.setColumnWidth((short) 0, (short) 3000);
		sheet.setColumnWidth((short) 1, (short) 3000);
		sheet.setColumnWidth((short) 2, (short) 3000);
		sheet.setColumnWidth((short) 3, (short) 3000);
		sheet.setColumnWidth((short) 4, (short) 3000);
		sheet.setColumnWidth((short) 5, (short) 3000);
		sheet.setColumnWidth((short) 6, (short) 4000);
		
		cell = row.createCell(0);
		cell.setCellValue("学生学号");
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
		cell.setCellStyle(cellStyle);
		
		//第一行第二列
		cell = row.createCell(1);
		cell.setCellValue("学生姓名");
		cell.setCellStyle(cellStyle);
		
		
		//第三列  性别下拉框
		cell = row.createCell(2);
		cell.setCellValue("性别");
		cell.setCellStyle(cellStyle);
		
		//生成下拉框内容  第三列  
		String list[] = {"男","女"};
		//设置下拉框数据
		DataValidationHelper helper = sheet.getDataValidationHelper();   
        //CellRangeAddressList(firstRow, lastRow, firstCol, lastCol)设置行列范围  
        CellRangeAddressList addressList = new CellRangeAddressList(1,51,2,2);  
        //设置下拉框数据  
        DataValidationConstraint constraint = helper.createExplicitListConstraint(list);   
        DataValidation dataValidation = helper.createValidation(constraint, addressList);  
        sheet.addValidationData(dataValidation);  
		
		
		//第4列  时间
		cell = row.createCell(3);
		cell.setCellValue("入学时间");
		cell.setCellStyle(cellStyle);
		
		//第5列  联系方式
		cell = row.createCell(4);
		cell.setCellValue("联系方式");
		cell.setCellStyle(cellStyle);
		
		//第6列  邮箱
		cell = row.createCell(5);
		cell.setCellValue("邮箱");
		cell.setCellStyle(cellStyle);
		
		
		
        //第7列  学生班级
		cell = row.createCell(6);
		cell.setCellValue("学生班级");
		cell.setCellStyle(cellStyle);
		//生成班级下拉选择框
		List<Grade> gradeList = gradeMapper.getGradeList();
		String [] gradeStr = new String[gradeList.size()];
		for(int i=0;i<gradeStr.length;i++) {
			gradeStr[i] = gradeList.get(i).getGradeId()+"-"+gradeList.get(i).getGradeName();
		} 
		//设置下拉框数据
		DataValidationHelper helper2 = sheet.getDataValidationHelper();   
        //CellRangeAddressList(firstRow, lastRow, firstCol, lastCol)设置行列范围  
        CellRangeAddressList addressList2 = new CellRangeAddressList(1,51,6,6);  
        //设置下拉框数据  
        DataValidationConstraint constraint2 = helper2.createExplicitListConstraint(gradeStr);   
        DataValidation dataValidation2 = helper2.createValidation(constraint2, addressList2);  
        sheet.addValidationData(dataValidation2);  
        
        
      //设置后51行样式
  		for(int i =1; i<=51;i++) {
  			row = sheet.createRow(i);
  			row.setHeight((short) 300);
  			//第一列   文本格式
  			cellStyle = workbook.createCellStyle();
  			cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
  			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
  			cell = row.createCell(0);
  			cellStyle.setDataFormat(fmt.getFormat("@"));
  			cell.setCellStyle(cellStyle);
  			
  			//第二列  文本格式
  			cellStyle = workbook.createCellStyle();
  			cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
  			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
  			cell = row.createCell(1);
  			cellStyle.setDataFormat(fmt.getFormat("@"));
  			cell.setCellStyle(cellStyle);
  			
  			//第四列  时间格式
  			cellStyle = workbook.createCellStyle();
  			cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
  			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
  			cell = row.createCell(3);
  			cellStyle.setDataFormat(fmt.getFormat("yyyy/mm/dd"));
  			cell.setCellStyle(cellStyle);
  			
  			//第五列  文本格式
  			cellStyle = workbook.createCellStyle();
  			cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
  			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
  			cell = row.createCell(4);
  			cellStyle.setDataFormat(fmt.getFormat("@"));
  			cell.setCellStyle(cellStyle);
  			
  			//第六列  文本格式
  			cellStyle = workbook.createCellStyle();
  			cellStyle.setAlignment(HorizontalAlignment.CENTER);     //设置单元格水平对其方式
  			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);    //设置单元格垂直对其方式
  			cell = row.createCell(5);
  			cellStyle.setDataFormat(fmt.getFormat("@"));
  			cell.setCellStyle(cellStyle);
  		}
		return sheet;
	}

	@Override
	public String improtStudentFile(Sheet sheet) {
		int successNum=0;
		int defeatedNum=0;
		int rowLastNum = sheet.getLastRowNum();
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=1;i<=rowLastNum;i++) {
			Row row = sheet.getRow(i);
			if(row!=null) {
				if(StringUtil.isEmpt(row.getCell(0).getStringCellValue()) && StringUtil.isEmpt(row.getCell(1).getStringCellValue()) 
						&& row.getCell(2)==null &&StringUtil.isEmpt(row.getCell(3).getStringCellValue()) 
						&& StringUtil.isEmpt(row.getCell(4).getStringCellValue()) && StringUtil.isEmpt(row.getCell(5).getStringCellValue())
						&& row.getCell(6)==null){
							logging.info("空列");
							continue;
						}
				if(StringUtil.isEmpt(row.getCell(0).getStringCellValue())) {
					defeatedNum++;
					sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：学生学号不能为空<br>");
					continue;
				}
				User user;
				try {
					user = new User(row.getCell(0).getStringCellValue(), 
							new Md5Hash(row.getCell(0).getStringCellValue(), row.getCell(0).getStringCellValue()).toString(), 
							row.getCell(1)==null?null:row.getCell(1).getStringCellValue(), 
							row.getCell(2).getStringCellValue().equals("男")?0:1, 
							row.getCell(3)==null?null:sdf.format(row.getCell(3).getDateCellValue()), 
							3, 
							row.getCell(6)==null?null:row.getCell(6).getStringCellValue().substring(0, row.getCell(6).getStringCellValue().indexOf("-")), 
							row.getCell(4)==null?null:row.getCell(4).getStringCellValue(), 
							row.getCell(5)==null?null:row.getCell(5).getStringCellValue());
				} catch (Exception e) {
					defeatedNum++;
					sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：该行数据读取失败，请检查数据格式是否按照模版要求填写，是否含有未填写信息。<br>");
					continue;
				}
				//判断教职工编号是否唯一
				if(userMapper.studentIdOnaly(user.getLoginName())>0) {
					defeatedNum++;
					sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：该学生学号已被使用<br>");
					continue;
				}
				try {
					userMapper.addUser(user);
					successNum++;
				} catch (Exception e) {
					defeatedNum++;
					sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：该行数据导入数据库失败，请检查数据格式<br>");
					continue;
				}
			}else {
				defeatedNum++;
				continue;
			}
			
		}
		return "本次导入操作共导入【<font color='#009688'>"+(successNum+defeatedNum)+"</font>】条数据。<br>成功【<font color='#01AAED'>"+successNum+"</font>】条数据，失败【<font color='#FF5722'>"+defeatedNum+"</font>】条数据。<br>"+sb.toString();
	}

	@Override
	public boolean updatePassword(Map<String, Object> map) {
		return userMapper.updatePassword(map)!=0;
	}

	@Override
	public boolean updateUserByHome(User user) {
		return userMapper.updateUserByHome(user)!=0;
	}
	
}
