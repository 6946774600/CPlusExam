package com.cplusexam.bean.Item;

import com.cplusexam.bean.util.UpLoadFile;

/***
 * 
 * @ClassName:  GapItem   
 * @Description:填空题题库表对应bean
 * @author: FanD
 * @date:   2019年2月26日 下午5:19:15
 */
public class GapItem {

	private int uuid;
	private String item_name;
	private int item_imageId ;
	private String item_option;
	private String create_time ;
	private String create_teacherId;
	
	private UpLoadFile upLoadFile;
	
	public GapItem() {
		super();
	}
	public GapItem(int uuid, String item_name, int item_imageId, String item_option, String create_time,
			String create_teacherId) {
		super();
		this.uuid = uuid;
		this.item_name = item_name;
		this.item_imageId = item_imageId;
		this.item_option = item_option;
		this.create_time = create_time;
		this.create_teacherId = create_teacherId;
	}
	public GapItem(String item_name, int item_imageId, String item_option, String create_time,
			String create_teacherId) {
		super();
		this.item_name = item_name;
		this.item_imageId = item_imageId;
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
		return "GapItem [uuid=" + uuid + ", item_name=" + item_name + ", item_imageId=" + item_imageId
				+ ", item_option=" + item_option + ", create_time=" + create_time + ", create_teacherId="
				+ create_teacherId + ", upLoadFile=" + upLoadFile + "]";
	}

	
}
