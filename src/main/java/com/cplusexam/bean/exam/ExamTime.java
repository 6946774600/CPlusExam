package com.cplusexam.bean.exam;

/***
 * 
 * @ClassName:  ExamTime   
 * @Description:考试时间表  对应实体bean
 * @author: FanD
 * @date:   2019年3月11日 上午11:34:36
 */
public class ExamTime {

	
	private int uuid;
	private String name;
	private String start_time;
	private String end_time;
	private int exam_modelid;
	private String insert_time;
	private int show_notice;
	private String notice_title;
	private String notice_msg;
	
	
	public ExamTime() {
		super();
	}

	
	public ExamTime(String name, String start_time, String end_time, int exam_modelid, String insert_time,
			int show_notice, String notice_title, String notice_msg) {
		super();
		this.name = name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.exam_modelid = exam_modelid;
		this.insert_time = insert_time;
		this.show_notice = show_notice;
		this.notice_title = notice_title;
		this.notice_msg = notice_msg;
	}




	public ExamTime(int uuid, String name, String start_time, String end_time, int exam_modelid,
			String insert_time, int show_notice, String notice_title, String notice_msg) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.exam_modelid = exam_modelid;
		this.insert_time = insert_time;
		this.show_notice = show_notice;
		this.notice_title = notice_title;
		this.notice_msg = notice_msg;
	}




	public int getUuid() {
		return uuid;
	}


	public void setUuid(int uuid) {
		this.uuid = uuid;
	}


	public String getname() {
		return name;
	}


	public void setname(String name) {
		this.name = name;
	}


	public String getStart_time() {
		return start_time;
	}


	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}


	public String getEnd_time() {
		return end_time;
	}


	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}


	public int getExam_modelid() {
		return exam_modelid;
	}


	public void setExam_modelid(int exam_modelid) {
		this.exam_modelid = exam_modelid;
	}


	public String getInsert_time() {
		return insert_time;
	}


	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}


	public int getShow_notice() {
		return show_notice;
	}


	public void setShow_notice(int show_notice) {
		this.show_notice = show_notice;
	}


	public String getNotice_msg() {
		return notice_msg;
	}


	public void setNotice_msg(String notice_msg) {
		this.notice_msg = notice_msg;
	}


	public String getNotice_title() {
		return notice_title;
	}


	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}


	@Override
	public String toString() {
		return "ExamTime [uuid=" + uuid + ", start_time=" + start_time + ", end_time=" + end_time + ", exam_modelid="
				+ exam_modelid + ", insert_time=" + insert_time + ", show_notice=" + show_notice + ", notice_title="
				+ notice_title + ", notice_msg=" + notice_msg + "]";
	}
	
	
}
