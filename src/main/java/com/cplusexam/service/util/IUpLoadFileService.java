package com.cplusexam.service.util;

import java.util.Map;

import com.cplusexam.bean.util.UpLoadFile;

public interface IUpLoadFileService {

	/***
	 * 
	 * @Title: addFileMsg   
	 * @Description: 添加一条文件信息 并返回文件的主键信息
	 * @param: @return      
	 * @return: int     返回文件的主键信息
	 * @throws
	 */
	public int addFileMsg(UpLoadFile file);
	
	
	
	/***
	 * 
	 * @Title: getFileById   
	 * @Description: 根据id获取该id的文件信息  
	 * @param: @param id      
	 * @return: void      
	 * @throws
	 */
	public UpLoadFile getFileById(int id);
	
	
	/***
	 * 
	 * @Title: updateFileUsedById   
	 * @Description: 根据id  修改该id的文件使用信息字段  
	 * @param: @param parm
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateFileUsedById(Map<String, Object> parm);
	
}
