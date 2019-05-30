package com.cplusexam.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplusexam.bean.util.UpLoadFile;
import com.cplusexam.service.util.IUpLoadFileService;
import com.cplusexam.util.UpLoadRes;

/***
 * 
 * @ClassName:  FileUploadController   
 * @Description:文件上传处理请求 
 * @author: FanD
 * @date:   2019年1月24日 上午10:17:17
 */
@RequestMapping("/upload")
@Controller
public class FileUploadController {

	private static Logger logger = Logger.getLogger(FileUploadController.class);
	
	@Autowired
	private IUpLoadFileService upLoadFileService;
	
	/***
	 * 
	 * @Title: execlUpLoad   
	 * @Description: 进行execle文件的上传操作   并将文件信息记录到数据库中
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/execlUpLoad")
	@ResponseBody
	public UpLoadRes execlUpLoad(@Param("file") MultipartFile file) {
		UpLoadRes res = new UpLoadRes();
		logger.info("execl上传操作");
		String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.')); //文件名称
		String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));  //文件后缀
		String uuName = UUID.randomUUID().toString();
		uuName = uuName.replace("-", "");
		logger.info("文件名称："+fileName);
		logger.info("文件后缀："+fileSuffix);
		logger.info("文件存储："+uuName);
		Properties prop=new Properties();
		//配置不在本类包下的导入方式
		//InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("jdbc_mysql.properties");//读取文件
		//配置文件与本类在同一包下的导入方式
		//InputStream in = DBUtil.class.getResourceAsStream("jdbc_mysql.properties");//读取文件
		//配置文件在src路径下的导入方式
		InputStream in = FileUploadController.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			prop.load(in);
			logger.info("项目配置文件加载成功");
		} catch (IOException e) {
			logger.error("项目配置文件加载异常");
			e.printStackTrace();
		}//加载文件
		String src = prop.getProperty("ExeclUpLoad");
		logger.info("文件存储地址："+src);
		try {
			//把文件放入服务器磁盘中
			file.transferTo(new java.io.File(src, uuName+fileSuffix));
			logger.info("文件写入磁盘成功");
			//保存文件信息到数据库中
			logger.info("文件信息写入数据库操作");
			UpLoadFile upLoadFile = new UpLoadFile(fileName, uuName, fileSuffix, 0, src);
			upLoadFileService.addFileMsg(upLoadFile);
			res.setMsg(""+upLoadFile.getFileId());
		} catch (IllegalStateException | IOException e) {
			logger.info("文件写入磁盘失败");
			res.setFalse();
		}
		return res;
	}
	
	
	/***
	 * 
	 * @Title: execlUpLoad   
	 * @Description: 进行execle文件的上传操作   并将文件信息记录到数据库中
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/imageUpLoad")
	@ResponseBody
	public UpLoadRes imageUpLoad(@Param("file") MultipartFile file) {
		UpLoadRes res = new UpLoadRes();
		logger.info("图片上传操作");
		String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.')); //文件名称
		String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));  //文件后缀
		String uuName = UUID.randomUUID().toString();
		uuName = uuName.replace("-", "");
		logger.info("文件名称："+fileName);
		logger.info("文件后缀："+fileSuffix);
		logger.info("文件存储："+uuName);
		Properties prop=new Properties();
		//配置不在本类包下的导入方式
		//InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("jdbc_mysql.properties");//读取文件
		//配置文件与本类在同一包下的导入方式
		//InputStream in = DBUtil.class.getResourceAsStream("jdbc_mysql.properties");//读取文件
		//配置文件在src路径下的导入方式
		InputStream in = FileUploadController.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			prop.load(in);
			logger.info("项目配置文件加载成功");
		} catch (IOException e) {
			logger.error("项目配置文件加载异常");
			e.printStackTrace();
		}//加载文件
		String src = prop.getProperty("ImageUpLoad");
		logger.info("图片存储地址："+src);
		try {
			//把文件放入服务器磁盘中
			file.transferTo(new java.io.File(src, uuName+fileSuffix));
			logger.info("图片写入磁盘成功");
			//保存文件信息到数据库中
			logger.info("图片信息写入数据库操作");
			UpLoadFile upLoadFile = new UpLoadFile(fileName, uuName, fileSuffix, 0, src);
			upLoadFileService.addFileMsg(upLoadFile);
			//msg存储了文件id  因为之前只返回id  就这么写了
			res.setMsg(""+upLoadFile.getFileId());
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("id", upLoadFile.getFileId());
			resMap.put("src", upLoadFile.getFileUUName()+upLoadFile.getFilTemp());
			//data放数据  因为之前用的是msg放数据  就只能先这样了  以后用data进行返回数据的存放
			res.setData(resMap);
		} catch (IllegalStateException | IOException e) {
			logger.info("图片写入磁盘失败");
			res.setFalse();
		}
		return res;
	}
	
}
