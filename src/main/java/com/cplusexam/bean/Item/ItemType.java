package com.cplusexam.bean.Item;


/***
 * 
 * @ClassName:  ItemType   
 * @Description:考题类型实体bean
 * @author: FanD
 * @date:   2019年2月20日 下午4:39:50
 */
public class ItemType {

	private int item_id;
	private String itemType_name;
	private int show_index;  //是否启用该题型
	private int def_score;  // 默认分值
	private String remark;
	
	
	
	
	public ItemType(String itemType_name, int show_index, int def_score, String remark) {
		super();
		this.itemType_name = itemType_name;
		this.show_index = show_index;
		this.def_score = def_score;
		this.remark = remark;
	}
	public ItemType(int item_id, String itemType_name, int show_index, int def_score, String remark) {
		super();
		this.item_id = item_id;
		this.itemType_name = itemType_name;
		this.show_index = show_index;
		this.def_score = def_score;
		this.remark = remark;
	}
	public ItemType() {
		super();
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItemType_name() {
		return itemType_name;
	}
	public void setItemType_name(String itemType_name) {
		this.itemType_name = itemType_name;
	}
	public int getShow_index() {
		return show_index;
	}
	public void setShow_index(int show_index) {
		this.show_index = show_index;
	}
	public int getDef_score() {
		return def_score;
	}
	public void setDef_score(int def_score) {
		this.def_score = def_score;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ItemType [item_id=" + item_id + ", itemType_name=" + itemType_name + ", show_index=" + show_index
				+ ", def_score=" + def_score + ", remark=" + remark + "]";
	}
	
}
