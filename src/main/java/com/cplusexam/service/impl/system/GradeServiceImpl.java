package com.cplusexam.service.impl.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.Array;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.system.Grade;
import com.cplusexam.dao.system.GradeMapper;
import com.cplusexam.service.system.IGradeService;
import com.cplusexam.service.util.IUpLoadFileService;
import com.cplusexam.util.StringUtil;


@Service
public class GradeServiceImpl implements IGradeService {
	
	
	@Autowired
	private GradeMapper gradeMapper;
	
	
	
	@Override
	public List<Grade> getGradeList() {
		return gradeMapper.getGradeList();
	}

	@Override
	public List<Map<String, Object>> getGradeListAndCountUser() {
		return gradeMapper.getGradeListAndCountUser();
	}

	@Override
	public boolean gradeIdOnaly(String gradeId) {
		if(gradeMapper.gradeIdOnaly(gradeId)==0)
			return true;
		else
			return false;
	}

	@Override
	public boolean addGrade(Grade grade) {
		return gradeMapper.addGrade(grade)!=0;
	}

	@Override
	public Grade getGradeById(String gradeId) {
		return gradeMapper.getGradeById(gradeId);
	}

	@Override
	public boolean updateGradeById(Grade grade) {
		return gradeMapper.updateGradeById(grade)!=0;
	}

	@Override
	public String getUsedGradeById(List<String> gradeIds) {
		StringBuffer sb = new StringBuffer();
		for (String gradeId : gradeIds) {
			int temp = gradeMapper.getUsedGradeById(gradeId);
			if(temp>0) {
				sb.append(gradeId+",");
			}
		}
		return sb.toString();
	}

	@Override
	public boolean deleteGradeBuIds(List<String> gradeIds) {
		return gradeMapper.deleteGradeBuIds(gradeIds)!=0;
	}

	@Override
	public String excleImportGradeMsg(Sheet sheet) {
		int successNum=0;
		int defeatedNum=0;
		int rowLastNum = sheet.getLastRowNum();
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<=rowLastNum;i++) {
			Row row = sheet.getRow(i);
			if(row==null) {
				defeatedNum++;
				continue;
			}
			//获取班级编号 判断班级编号的唯一性
			String gradeId=null;
			try {
				gradeId = row.getCell(0)!=null?row.getCell(0).getStringCellValue():null;
			}catch (Exception e) {
				sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：模版数据获取异常，请下载最新模版<br>");
				continue;
			}
			
			if(StringUtil.isEmpt(gradeId)) {
				defeatedNum++;
				sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：班级编号不能为空<br>");
				continue;
			}
			if(gradeMapper.gradeIdOnaly(gradeId)!=0) {  //id已经存在
				defeatedNum++;
				sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因：该班级编号已经存在<br>");
				continue;
			} else {
				try {
					Grade grade = new Grade(row.getCell(0)!=null?row.getCell(0).getStringCellValue():null, 
							row.getCell(1)!=null?row.getCell(0).getStringCellValue():null, 
							row.getCell(2)!=null?row.getCell(0).getStringCellValue():null, 
							row.getCell(3)!=null?row.getCell(0).getStringCellValue():null, 
							null,
							row.getCell(4)!=null?row.getCell(0).getStringCellValue():null);
					gradeMapper.addGrade(grade);
					successNum++;
				} catch (Exception e) {
					defeatedNum++;
					sb.append("第【<font color='red'>"+i+"</font>】行数据插入失败，原因："+e.getMessage()+"<br>");
				}
			}
		}
		return "本次导入操作共导入【<font color='#009688'>"+rowLastNum+"</font>】条数据。<br>成功【<font color='#01AAED'>"+successNum+"</font>】条数据，失败【<font color='#FF5722'>"+defeatedNum+"</font>】条数据。<br>"+sb.toString();
	}

	@Override
	public List<Map<String, Object>> getGradeSelect() {
		List<Map<String, Object>> resList = new ArrayList<>();
		List<Grade> rsList = gradeMapper.getGradeList();
		for (Grade grade : rsList) {
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("id", grade.getGradeId());
			resMap.put("name", grade.getGradeName());
			resList.add(resMap);
		}
		return resList;
	}

	@Override
	public String getTeacherUsedById(List<String> teacherIds) {
		StringBuffer sb = new StringBuffer();
		for (String teacherId : teacherIds) {
			int temp = gradeMapper.getTeacherUsedById(teacherId);
			if(temp>0) {
				sb.append(teacherId+",");
			}
		}
		return sb.toString();
	}

}
