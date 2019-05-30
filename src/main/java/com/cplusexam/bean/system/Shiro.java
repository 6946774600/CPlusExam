package com.cplusexam.bean.system;

/***
 * 
 * @ClassName:  Shiro   
 * @Description:角色权限表对应的实体bean
 * @author: FanD
 * @date:   2019年1月22日 下午5:32:57
 */
public class Shiro {

	private int shiroId;
	private String shiroEnName;
	private String shiroCnName;
	private String remark;
	
	public Shiro() {
		super();
	}

	public Shiro(int shiroId, String shiroEnName, String shiroCnName, String remark) {
		super();
		this.shiroId = shiroId;
		this.shiroEnName = shiroEnName;
		this.shiroCnName = shiroCnName;
		this.remark = remark;
	}

	public Shiro(String shiroEnName, String shiroCnName, String remark) {
		super();
		this.shiroEnName = shiroEnName;
		this.shiroCnName = shiroCnName;
		this.remark = remark;
	}

	public int getShiroId() {
		return shiroId;
	}

	public void setShiroId(int shiroId) {
		this.shiroId = shiroId;
	}

	public String getShiroEnName() {
		return shiroEnName;
	}

	public void setShiroEnName(String shiroEnName) {
		this.shiroEnName = shiroEnName;
	}

	public String getShiroCnName() {
		return shiroCnName;
	}

	public void setShiroCnName(String shiroCnName) {
		this.shiroCnName = shiroCnName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Shiro [shiroId=" + shiroId + ", shiroEnName=" + shiroEnName + ", shiroCnName=" + shiroCnName
				+ ", remark=" + remark + "]";
	}
	
	
}
