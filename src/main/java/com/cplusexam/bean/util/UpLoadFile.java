package com.cplusexam.bean.util;

/***
 * 
 * @ClassName:  UpLoadFile   
 * @Description:文件上传工具bean
 * @author: FanD
 * @date:   2019年1月24日 上午10:59:42
 */
public class UpLoadFile {

	private int fileId;
	private String fileName;
	private String fileUUName;
	private String filTemp;
	private int used;  //是否被使用  0-未使用  1-已使用
	private String fileUrl;
	
	public UpLoadFile() {
		super();
	}

	public UpLoadFile(int fileId, String fileName, String fileUUName, String filTemp, int used, String fileUrl) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileUUName = fileUUName;
		this.filTemp = filTemp;
		this.used = used;
		this.fileUrl = fileUrl;
	}

	public UpLoadFile(String fileName, String fileUUName, String filTemp, int used, String fileUrl) {
		super();
		this.fileName = fileName;
		this.fileUUName = fileUUName;
		this.filTemp = filTemp;
		this.used = used;
		this.fileUrl = fileUrl;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUUName() {
		return fileUUName;
	}

	public void setFileUUName(String fileUUName) {
		this.fileUUName = fileUUName;
	}

	public String getFilTemp() {
		return filTemp;
	}

	public void setFilTemp(String filTemp) {
		this.filTemp = filTemp;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Override
	public String toString() {
		return "UpLoadFile [fileId=" + fileId + ", fileName=" + fileName + ", fileUUName=" + fileUUName + ", filTemp="
				+ filTemp + ", used=" + used + ", fileUrl=" + fileUrl + "]";
	}
	
	
	
}
