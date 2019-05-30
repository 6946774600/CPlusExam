package com.cplusexam.util;

import java.util.HashMap;
import java.util.Map;

public class Msg {
	private boolean success;
	
	private String msg;
	
	private Map<String, Object> extend = new HashMap<String, Object>();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void addResult(String key,Object value) {
		this.extend.put(key, value);
	}
	
	
}
