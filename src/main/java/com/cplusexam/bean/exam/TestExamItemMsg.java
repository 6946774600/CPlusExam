package com.cplusexam.bean.exam;


/***
 * 
 * @ClassName:  TestExamItemMsg   
 * @Description:模拟考试题型表
 * @author: FanD
 * @date:   2019年3月18日 上午11:15:31
 */
public class TestExamItemMsg {

	
	private int uuid;
	private int examtest_id;
	private int item_type;
	private int item_count;
	private int item_score;
	private int item_counts;
	private int show_index;
	
	
	public TestExamItemMsg() {
		super();
	}


	public TestExamItemMsg(int uuid, int examtest_id, int item_type, int item_count, int item_score, int item_counts,
			int show_index) {
		super();
		this.uuid = uuid;
		this.examtest_id = examtest_id;
		this.item_type = item_type;
		this.item_count = item_count;
		this.item_score = item_score;
		this.item_counts = item_counts;
		this.show_index = show_index;
	}


	public TestExamItemMsg(int examtest_id, int item_type, int item_count, int item_score, int item_counts,
			int show_index) {
		super();
		this.examtest_id = examtest_id;
		this.item_type = item_type;
		this.item_count = item_count;
		this.item_score = item_score;
		this.item_counts = item_counts;
		this.show_index = show_index;
	}


	public int getUuid() {
		return uuid;
	}


	public void setUuid(int uuid) {
		this.uuid = uuid;
	}


	public int getExamtest_id() {
		return examtest_id;
	}


	public void setExamtest_id(int examtest_id) {
		this.examtest_id = examtest_id;
	}


	public int getItem_type() {
		return item_type;
	}


	public void setItem_type(int item_type) {
		this.item_type = item_type;
	}


	public int getItem_count() {
		return item_count;
	}


	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}


	public int getItem_score() {
		return item_score;
	}


	public void setItem_score(int item_score) {
		this.item_score = item_score;
	}


	public int getItem_counts() {
		return item_counts;
	}


	public void setItem_counts(int item_counts) {
		this.item_counts = item_counts;
	}


	public int getShow_index() {
		return show_index;
	}


	public void setShow_index(int show_index) {
		this.show_index = show_index;
	}


	@Override
	public String toString() {
		return "TestExamItemMsg [uuid=" + uuid + ", examtest_id=" + examtest_id + ", item_type=" + item_type
				+ ", item_count=" + item_count + ", item_score=" + item_score + ", item_counts=" + item_counts
				+ ", show_index=" + show_index + "]";
	}
	
	
}
