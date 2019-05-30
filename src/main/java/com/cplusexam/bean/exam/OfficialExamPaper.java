package com.cplusexam.bean.exam;

/***
 * 
 * @ClassName:  OfficialExamPaper   
 * @Description:正式考试考题详细信息对应实体bean
 * @author: FanD
 * @date:   2019年3月28日 上午10:53:00
 */
public class OfficialExamPaper {

	private int uuid;
	private int examofficial_id;
	private int item_type;
	private int item_id;
	private String user_option;
	private int option_tof;
	
	public OfficialExamPaper() {
		super();
	}

	public OfficialExamPaper(int uuid, int examofficial_id, int item_type, int item_id, String user_option,
			int option_tof) {
		super();
		this.uuid = uuid;
		this.examofficial_id = examofficial_id;
		this.item_type = item_type;
		this.item_id = item_id;
		this.user_option = user_option;
		this.option_tof = option_tof;
	}

	public OfficialExamPaper(int examofficial_id, int item_type, int item_id, String user_option, int option_tof) {
		super();
		this.examofficial_id = examofficial_id;
		this.item_type = item_type;
		this.item_id = item_id;
		this.user_option = user_option;
		this.option_tof = option_tof;
	}
	

	public OfficialExamPaper(int examofficial_id, int item_type, int item_id) {
		super();
		this.examofficial_id = examofficial_id;
		this.item_type = item_type;
		this.item_id = item_id;
	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public int getExamofficial_id() {
		return examofficial_id;
	}

	public void setExamofficial_id(int examofficial_id) {
		this.examofficial_id = examofficial_id;
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
		return "OfficialExamPaper [uuid=" + uuid + ", examofficial_id=" + examofficial_id + ", item_type=" + item_type
				+ ", item_id=" + item_id + ", user_option=" + user_option + ", option_tof=" + option_tof + "]";
	}
	
	
}
