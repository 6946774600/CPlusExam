package com.cplusexam.bean.system;

/***
 * 
 * @ClassName:  User   
 * @Description:user表对应的实体bean
 * @author: FanD
 * @date:   2019年1月21日 上午10:58:23
 */
public class User {

	private String loginName;
	private String password;
	private String name;
	private int sex; //学生性别(0-男  1-女)
	private String toSchool;
	private int shiroId; //所属角色id
	private String gradeId;  //所属班级id
	private String phone;//电话
	private String email; //邮箱
	
	public User() {
		super();
	}


	public User(String loginName, String password, String name, int sex, String toSchool, int shiroId, String gradeId,
			String phone, String email) {
		super();
		this.loginName = loginName;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.toSchool = toSchool;
		this.shiroId = shiroId;
		this.gradeId = gradeId;
		this.phone = phone;
		this.email = email;
	}


	public User(String password, String name, int sex, String toSchool, int shiroId, String gradeId, String phone,
			String email) {
		super();
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.toSchool = toSchool;
		this.shiroId = shiroId;
		this.gradeId = gradeId;
		this.phone = phone;
		this.email = email;
	}

	
	
	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


	public String getToSchool() {
		return toSchool;
	}


	public void setToSchool(String toSchool) {
		this.toSchool = toSchool;
	}


	public int getShiroId() {
		return shiroId;
	}


	public void setShiroId(int shiroId) {
		this.shiroId = shiroId;
	}


	public String getGradeId() {
		return gradeId;
	}


	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "User [loginName=" + loginName + ", password=" + password + ", name=" + name + ", sex=" + sex
				+ ", toSchool=" + toSchool + ", shiroId=" + shiroId + ", gradeId=" + gradeId + ", phone=" + phone
				+ ", email=" + email + "]";
	}

	
	
}
