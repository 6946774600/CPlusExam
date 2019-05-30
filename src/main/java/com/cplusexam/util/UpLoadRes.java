package com.cplusexam.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 
* @ClassName: ImageUpLoad
* @Description: 图片上传后  要返回该类的json格式给前台
* @author 韩帆
* @date 2018年12月24日
*
 */
public class UpLoadRes {

	private int code;
	private String msg;
	private Map<String, Object> data = new HashMap<>();
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setSuccess(String src,String title) {
		this.code = 0;
		this.msg = "存储成功";
		this.data.put("src", src);
		//this.data.put("title", title);
	}
	
	public void setFalse() {
		this.code = 1;
		this.msg = "图片上传失败！";
	}

	@Override
	public String toString() {
		return "ImageUpLoad [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
