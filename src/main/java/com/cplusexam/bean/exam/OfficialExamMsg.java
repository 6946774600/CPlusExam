package com.cplusexam.bean.exam;


/***
 * 
 * @ClassName:  OfficialExamMsg   
 * @Description:正式考试对应的考试基本信息bean  
 * @author: FanD
 * @date:   2019年3月28日 上午10:45:26
 */
public class OfficialExamMsg{

	
	private int uuid;
	private int item_timeid;
	private String user_id;
	private int exam_counts;
	private int exam_type;
	
	public OfficialExamMsg() {
		super();
	}

	public OfficialExamMsg(int uuid, int item_timeid, String user_id, int exam_counts, int exam_type) {
		super();
		this.uuid = uuid;
		this.item_timeid = item_timeid;
		this.user_id = user_id;
		this.exam_counts = exam_counts;
		this.exam_type = exam_type;
	}

	public OfficialExamMsg(int item_timeid, String user_id, int exam_counts, int exam_type) {
		super();
		this.item_timeid = item_timeid;
		this.user_id = user_id;
		this.exam_counts = exam_counts;
		this.exam_type = exam_type;
	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public int getItem_timeid() {
		return item_timeid;
	}

	public void setItem_timeid(int item_timeid) {
		this.item_timeid = item_timeid;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getExam_counts() {
		return exam_counts;
	}

	public void setExam_counts(int exam_counts) {
		this.exam_counts = exam_counts;
	}

	public int getExam_type() {
		return exam_type;
	}

	public void setExam_type(int exam_type) {
		this.exam_type = exam_type;
	}

	@Override
	public String toString() {
		return "OfficialExamMsg [uuid=" + uuid + ", item_timeid=" + item_timeid + ", user_id=" + user_id
				+ ", exam_counts=" + exam_counts + ", exam_type=" + exam_type + "]";
	}
	
}
