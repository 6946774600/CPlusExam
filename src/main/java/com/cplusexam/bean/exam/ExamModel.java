package com.cplusexam.bean.exam;

/***
 * 
 * @ClassName:  ExamModelMsg   
 * @Description:考卷题型基本信息表
 * @author: FanD
 * @date:   2019年3月6日 下午3:15:44
 */
public class ExamModel {

	private int uuid;
	private String exam_name;
	private String create_time;
	private int count_score;
	
	
	public ExamModel() {
		super();
	}


	public ExamModel(int uuid, String exam_name, String create_time, int count_score) {
		super();
		this.uuid = uuid;
		this.exam_name = exam_name;
		this.create_time = create_time;
		this.count_score = count_score;
	}


	public ExamModel(String exam_name, String create_time, int count_score) {
		super();
		this.exam_name = exam_name;
		this.create_time = create_time;
		this.count_score = count_score;
	}


	public int getUuid() {
		return uuid;
	}


	public void setUuid(int uuid) {
		this.uuid = uuid;
	}


	public String getExam_name() {
		return exam_name;
	}


	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}


	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	public int getCount_score() {
		return count_score;
	}


	public void setCount_score(int count_score) {
		this.count_score = count_score;
	}


	@Override
	public String toString() {
		return "ExamMsg [uuid=" + uuid + ", exam_name=" + exam_name + ", create_time=" + create_time + ", count_score="
				+ count_score + "]";
	}
	
	
}
