package com.cplusexam.bean.exam;

/***
 * 
 * @ClassName:  ExamItemMsg   
 * @Description:考卷题型 考题信息表 
 * @author: FanD
 * @date:   2019年3月6日 下午3:17:45
 */
public class ExamModelItem {

	
	private int uuid;
	private int exam_id;
	private int item_typeid;
	private int item_count;
	private int item_score;
	private int item_counts;
	private int show_index;
	
	
	public ExamModelItem() {
		super();
	}


	public ExamModelItem(int uuid, int exam_id, int item_typeid, int item_count, int item_score, int item_counts,
			int show_index) {
		super();
		this.uuid = uuid;
		this.exam_id = exam_id;
		this.item_typeid = item_typeid;
		this.item_count = item_count;
		this.item_score = item_score;
		this.item_counts = item_counts;
		this.show_index = show_index;
	}


	public ExamModelItem(int exam_id, int item_typeid, int item_count, int item_score, int item_counts, int show_index) {
		super();
		this.exam_id = exam_id;
		this.item_typeid = item_typeid;
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


	public int getExam_id() {
		return exam_id;
	}


	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}


	public int getItem_typeid() {
		return item_typeid;
	}


	public void setItem_typeid(int item_typeid) {
		this.item_typeid = item_typeid;
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
		return "ExamItemMsg [uuid=" + uuid + ", exam_id=" + exam_id + ", item_typeid=" + item_typeid + ", item_count="
				+ item_count + ", item_score=" + item_score + ", item_counts=" + item_counts + ", show_index="
				+ show_index + "]";
	}
	
	
	
}
