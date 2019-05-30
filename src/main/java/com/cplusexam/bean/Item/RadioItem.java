package com.cplusexam.bean.Item;

import com.cplusexam.bean.util.UpLoadFile;

/***
 * 
 * @ClassName:  RadioItem   
 * @Description:单选题题库表
 * @author: FanD
 * @date:   2019年2月25日 下午3:00:47
 */
public class RadioItem {

	private int uuid;
	private String item_name;
	private int item_imageId;    //考题的图片信息id
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	private String option6;
	private String item_option;  //正确答案
	private String create_time;   //考题创建时间
	private String create_teacherId;  //创建的教室id
	
	private UpLoadFile upLoadFile;  //上传的文件
	
	public RadioItem() {
		super();
	}


	public RadioItem(int uuid, String item_name, int item_imageId, String option1, String option2, String option3,
			String option4, String option5, String option6, String item_option, String create_time,
			String create_teacherId) {
		super();
		this.uuid = uuid;
		this.item_name = item_name;
		this.item_imageId = item_imageId;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.option5 = option5;
		this.option6 = option6;
		this.item_option = item_option;
		this.create_time = create_time;
		this.create_teacherId = create_teacherId;
	}


	public RadioItem(String item_name, int item_imageId, String option1, String option2, String option3, String option4,
			String option5, String option6, String item_option, String create_time, String create_teacherId) {
		super();
		this.item_name = item_name;
		this.item_imageId = item_imageId;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.option5 = option5;
		this.option6 = option6;
		this.item_option = item_option;
		this.create_time = create_time;
		this.create_teacherId = create_teacherId;
	}


	public int getUuid() {
		return uuid;
	}


	public void setUuid(int uuid) {
		this.uuid = uuid;
	}


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public int getItem_imageId() {
		return item_imageId;
	}


	public void setItem_imageId(int item_imageId) {
		this.item_imageId = item_imageId;
	}


	public String getOption1() {
		return option1;
	}


	public void setOption1(String option1) {
		this.option1 = option1;
	}


	public String getOption2() {
		return option2;
	}


	public void setOption2(String option2) {
		this.option2 = option2;
	}


	public String getOption3() {
		return option3;
	}


	public void setOption3(String option3) {
		this.option3 = option3;
	}


	public String getOption4() {
		return option4;
	}


	public void setOption4(String option4) {
		this.option4 = option4;
	}


	public String getOption5() {
		return option5;
	}


	public void setOption5(String option5) {
		this.option5 = option5;
	}


	public String getOption6() {
		return option6;
	}


	public void setOption6(String option6) {
		this.option6 = option6;
	}


	public String getItem_option() {
		return item_option;
	}


	public void setItem_option(String item_option) {
		this.item_option = item_option;
	}


	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	public String getCreate_teacherId() {
		return create_teacherId;
	}


	public void setCreate_teacherId(String create_teacherId) {
		this.create_teacherId = create_teacherId;
	}

	
	

	public UpLoadFile getUpLoadFile() {
		return upLoadFile;
	}


	public void setUpLoadFile(UpLoadFile upLoadFile) {
		this.upLoadFile = upLoadFile;
	}


	@Override
	public String toString() {
		return "RadioItem [uuid=" + uuid + ", item_name=" + item_name + ", item_imageId=" + item_imageId + ", option1="
				+ option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + ", option5="
				+ option5 + ", option6=" + option6 + ", item_option=" + item_option + ", create_time=" + create_time
				+ ", create_teacherId=" + create_teacherId + ", upLoadFile=" + upLoadFile + "]";
	}

	
}
