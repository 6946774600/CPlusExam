package com.cplusexam.dao.util;

import java.util.Map;

import com.cplusexam.bean.util.UpLoadFile;

/***
 * 
 * @ClassName:  UpLoadFileMapper   
 * @Description:文件上传dao层 
 * @author: FanD
 * @date:   2019年1月24日 上午11:02:01
 */
public interface UpLoadFileMapper {

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
	 * @return: int      
	 * @throws
	 */
	public int updateFileUsedById(Map<String, Object> parm);
	
}
