package com.cplusexam.bean.system;

/***
 * 
 * @ClassName:  AdminMenu   
 * @Description:管理员菜单表
 * @author: FanD
 * @date:   2019年1月21日 下午5:00:51
 */
public class AdminMenu {

	private int menuId;
	private int pid;
	private String menuName;
	private String menuIcon;
	private String menuUrl;
	private int showIndex;
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public int getShowIndex() {
		return showIndex;
	}
	public void setShowIndex(int showIndex) {
		this.showIndex = showIndex;
	}
	public AdminMenu() {
		super();
	}
	public AdminMenu(int menuId, int pid, String menuName, String menuIcon, String menuUrl, int showIndex) {
		super();
		this.menuId = menuId;
		this.pid = pid;
		this.menuName = menuName;
		this.menuIcon = menuIcon;
		this.menuUrl = menuUrl;
		this.showIndex = showIndex;
	}
	public AdminMenu(int pid, String menuName, String menuIcon, String menuUrl, int showIndex) {
		super();
		this.pid = pid;
		this.menuName = menuName;
		this.menuIcon = menuIcon;
		this.menuUrl = menuUrl;
		this.showIndex = showIndex;
	}
	@Override
	public String toString() {
		return "AdminMenu [menuId=" + menuId + ", pid=" + pid + ", menuName=" + menuName + ", menuIcon=" + menuIcon
				+ ", menuUrl=" + menuUrl + ", showIndex=" + showIndex + "]";
	}
	
	
	
}
