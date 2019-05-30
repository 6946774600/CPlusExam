package com.cplusexam.service.impl.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.util.UpLoadFile;
import com.cplusexam.dao.util.UpLoadFileMapper;
import com.cplusexam.service.util.IUpLoadFileService;

@Service
public class UpLoadFileServiceImpl implements IUpLoadFileService {

	@Autowired
	private UpLoadFileMapper upLoadFileMapper;
	
	
	@Override
	public int addFileMsg(UpLoadFile file) {
		return upLoadFileMapper.addFileMsg(file);
	}


	@Override
	public UpLoadFile getFileById(int id) {
		return upLoadFileMapper.getFileById(id);
	}


	@Override
	public boolean updateFileUsedById(Map<String, Object> parm) {
		return upLoadFileMapper.updateFileUsedById(parm)!=0;
	}

}
