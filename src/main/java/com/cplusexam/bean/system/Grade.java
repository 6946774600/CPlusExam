package com.cplusexam.bean.system;

/***
 * 
 * @ClassName:  Grade   
 * @Description:班级信息表
 * @author: FanD
 * @date:   2019年1月23日 上午10:35:08
 */
public class Grade {

	private String gradeId;
	private String gradeName;
	private String gradeTerm;
	private String gradeMajor;
	private String teacherId;
	private String gradeRemark;
	
	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Grade(String gradeId, String gradeName, String gradeTerm, String gradeMajor, String teacherId,
			String gradeRemark) {
		super();
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.gradeTerm = gradeTerm;
		this.gradeMajor = gradeMajor;
		this.teacherId = teacherId;
		this.gradeRemark = gradeRemark;
	}


	

	public Grade(String gradeName, String gradeTerm, String gradeMajor, String teacherId, String gradeRemark) {
		super();
		this.gradeName = gradeName;
		this.gradeTerm = gradeTerm;
		this.gradeMajor = gradeMajor;
		this.teacherId = teacherId;
		this.gradeRemark = gradeRemark;
	}



	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeTerm() {
		return gradeTerm;
	}

	public void setGradeTerm(String gradeTerm) {
		this.gradeTerm = gradeTerm;
	}

	public String getGradeMajor() {
		return gradeMajor;
	}

	public void setGradeMajor(String gradeMajor) {
		this.gradeMajor = gradeMajor;
	}

	public String getGradeRemark() {
		return gradeRemark;
	}

	public void setGradeRemark(String gradeRemark) {
		this.gradeRemark = gradeRemark;
	}

	
	
	public String getTeacherId() {
		return teacherId;
	}



	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}



	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", gradeName=" + gradeName + ", gradeTerm=" + gradeTerm + ", gradeMajor="
				+ gradeMajor + ", teacherId=" + teacherId + ", gradeRemark=" + gradeRemark + "]";
	}

	
	
}
