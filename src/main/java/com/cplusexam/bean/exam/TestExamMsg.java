package com.cplusexam.bean.exam;


/***
 * 
 * @ClassName:  TestExamMsg   
 * @Description:  模拟考试基本信息对应实体bean
 * @author: FanD
 * @date:   2019年3月18日 上午11:06:05
 */
public class TestExamMsg {

	
	private int uuid;
	private String create_user;
	private String exam_name;
	private String exam_time;
	private String create_time;
	private int count_score;
	private int exam_score;
	private int examtest_type;
	
	
	public TestExamMsg() {
		super();
	}



	public TestExamMsg(String create_user, String exam_name, String exam_time, String create_time, int count_score,
			int exam_score, int examtest_type) {
		super();
		this.create_user = create_user;
		this.exam_name = exam_name;
		this.exam_time = exam_time;
		this.create_time = create_time;
		this.count_score = count_score;
		this.exam_score = exam_score;
		this.examtest_type = examtest_type;
	}


	public TestExamMsg(int uuid, String create_user, String exam_name, String exam_time, String create_time,
			int count_score, int exam_score, int examtest_type) {
		super();
		this.uuid = uuid;
		this.create_user = create_user;
		this.exam_name = exam_name;
		this.exam_time = exam_time;
		this.create_time = create_time;
		this.count_score = count_score;
		this.exam_score = exam_score;
		this.examtest_type = examtest_type;
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


	public String getExam_time() {
		return exam_time;
	}


	public void setExam_time(String exam_time) {
		this.exam_time = exam_time;
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


	public int getExam_score() {
		return exam_score;
	}


	public void setExam_score(int exam_score) {
		this.exam_score = exam_score;
	}


	public int getExamtest_type() {
		return examtest_type;
	}


	public void setExamtest_type(int examtest_type) {
		this.examtest_type = examtest_type;
	}



	public String getCreate_user() {
		return create_user;
	}



	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}



	@Override
	public String toString() {
		return "TestExamMsg [uuid=" + uuid + ", create_user=" + create_user + ", exam_name=" + exam_name
				+ ", exam_time=" + exam_time + ", create_time=" + create_time + ", count_score=" + count_score
				+ ", exam_score=" + exam_score + ", examtest_type=" + examtest_type + "]";
	}

	
}
