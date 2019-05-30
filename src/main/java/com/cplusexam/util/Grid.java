package com.cplusexam.util;

/***
 * 
* @ClassName: Grid
* @Description: layui表格的返回参数
* @author 韩帆
* @data 2018年11月16日
*
 */
public class Grid {

	private int code;
	private String msg;
	private Long count;
	private Object data;
	
	
	
	
	
	
	public Grid(Long count, Object data) {
		super();
		this.code =0;
		this.msg="";
		this.count = count;
		this.data = data;
	}
	public Grid() {
		super();
		this.code =0;
		this.msg="";
		// TODO Auto-generated constructor stub
	}
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Object getdata() {
		return data;
	}
	public void setdata(Object data) {
		this.data = data;
	}
	
	
}
