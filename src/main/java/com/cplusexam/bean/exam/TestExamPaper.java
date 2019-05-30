package com.cplusexam.bean.exam;

/***
 * 
 * @ClassName:  TestExamPaper   
 * @Description:模拟考试试卷表
 * @author: FanD
 * @date:   2019年3月18日 上午11:21:33
 */
public class TestExamPaper {

	
	private int uuid;
	private int item_testid;
	private String user_id;
	private int item_type;
	private int item_id;
	private String user_option;
	private int option_tof;
	
	
	public TestExamPaper() {
		super();
	}


	public TestExamPaper(int uuid, int item_testid, String user_id, int item_type, int item_id, String user_option,
			int option_tof) {
		super();
		this.uuid = uuid;
		this.item_testid = item_testid;
		this.user_id = user_id;
		this.item_type = item_type;
		this.item_id = item_id;
		this.user_option = user_option;
		this.option_tof = option_tof;
	}


	public TestExamPaper(int item_testid, String user_id, int item_type, int item_id, String user_option,
			int option_tof) {
		super();
		this.item_testid = item_testid;
		this.user_id = user_id;
		this.item_type = item_type;
		this.item_id = item_id;
		this.user_option = user_option;
		this.option_tof = option_tof;
	}


	public TestExamPaper(int item_testid, String user_id, int item_type, int item_id) {
		super();
		this.item_testid = item_testid;
		this.user_id = user_id;
		this.item_type = item_type;
		this.item_id = item_id;
	}


	public int getUuid() {
		return uuid;
	}


	public void setUuid(int uuid) {
		this.uuid = uuid;
	}


	public int getItem_testid() {
		return item_testid;
	}


	public void setItem_testid(int item_testid) {
		this.item_testid = item_testid;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public int getItem_type() {
		return item_type;
	}


	public void setItem_type(int item_type) {
		this.item_type = item_type;
	}


	public int getItem_id() {
		return item_id;
	}


	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}


	public String getUser_option() {
		return user_option;
	}


	public void setUser_option(String user_option) {
		this.user_option = user_option;
	}


	public int getOption_tof() {
		return option_tof;
	}


	public void setOption_tof(int option_tof) {
		this.option_tof = option_tof;
	}


	@Override
	public String toString() {
		return "TestExamPaper [uuid=" + uuid + ", item_testid=" + item_testid + ", user_id=" + user_id + ", item_type="
				+ item_type + ", item_id=" + item_id + ", user_option=" + user_option + ", option_tof=" + option_tof
				+ "]";
	}
	
	
}
