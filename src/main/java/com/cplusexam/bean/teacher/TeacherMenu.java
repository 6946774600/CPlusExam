package com.cplusexam.bean.teacher;

/***
 * 
 * @ClassName:  TeacherMenu   
 * @Description:老师的菜单实体bean
 * @author: FanD
 * @date:   2019年2月21日 上午11:54:58
 */
public class TeacherMenu {

	private int menuId;
	private int pid;
	private String menuName;
	private String menuIcon;
	private String menuUrl;
	private int showIndex;
	
	
	public TeacherMenu(int menuId, int pid, String menuName, String menuIcon, String menuUrl, int showIndex) {
		super();
		this.menuId = menuId;
		this.pid = pid;
		this.menuName = menuName;
		this.menuIcon = menuIcon;
		this.menuUrl = menuUrl;
		this.showIndex = showIndex;
	}


	public TeacherMenu(int pid, String menuName, String menuIcon, String menuUrl, int showIndex) {
		super();
		this.pid = pid;
		this.menuName = menuName;
		this.menuIcon = menuIcon;
		this.menuUrl = menuUrl;
		this.showIndex = showIndex;
	}


	public TeacherMenu() {
		super();
		// TODO Auto-generated constructor stub
	}


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


	@Override
	public String toString() {
		return "TeacherMenu [menuId=" + menuId + ", pid=" + pid + ", menuName=" + menuName + ", menuIcon=" + menuIcon
				+ ", menuUrl=" + menuUrl + ", showIndex=" + showIndex + "]";
	}
	
	
	
	
}
