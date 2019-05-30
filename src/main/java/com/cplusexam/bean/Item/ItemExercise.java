package com.cplusexam.bean.Item;


/***
 * 
 * @ClassName:  ItemExercise   
 * @Description:题库练习表 对应实体bean
 * @author: FanD
 * @date:   2019年3月13日 上午9:44:46
 */
public class ItemExercise {

	private int uuid;
	private String user_id;
	private int item_type;
	private int item_id;
	private String user_option;
	private int option_tof;   //答案是否正确(0-错误  1 正确)
	private String create_time;
	
	
	
	public ItemExercise() {
		super();
	}



	public ItemExercise(int uuid, String user_id, int item_type, int item_id, String user_option, int option_tof,
			String create_time) {
		super();
		this.uuid = uuid;
		this.user_id = user_id;
		this.item_type = item_type;
		this.item_id = item_id;
		this.user_option = user_option;
		this.option_tof = option_tof;
		this.create_time = create_time;
	}



	public ItemExercise(String user_id, int item_type, int item_id, String user_option, int option_tof,
			String create_time) {
		super();
		this.user_id = user_id;
		this.item_type = item_type;
		this.item_id = item_id;
		this.user_option = user_option;
		this.option_tof = option_tof;
		this.create_time = create_time;
	}



	public int getUuid() {
		return uuid;
	}



	public void setUuid(int uuid) {
		this.uuid = uuid;
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



	public String getCreate_time() {
		return create_time;
	}



	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	@Override
	public String toString() {
		return "ItemExercise [uuid=" + uuid + ", user_id=" + user_id + ", item_type=" + item_type + ", item_id="
				+ item_id + ", user_option=" + user_option + ", option_tof=" + option_tof + ", create_time="
				+ create_time + "]";
	}
	
	
	
}
