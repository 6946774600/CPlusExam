package com.cplusexam.service.impl.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.exam.OfficialExamPaper;
import com.cplusexam.dao.Item.CheckItemMapper;
import com.cplusexam.dao.Item.GapItemMapper;
import com.cplusexam.dao.Item.JudgeItemMapper;
import com.cplusexam.dao.Item.RadioItemMapper;
import com.cplusexam.dao.exam.OfficialExamMsgMapper;
import com.cplusexam.dao.exam.OfficialExamPaperMapper;
import com.cplusexam.dao.exam.StudentOfficialExamMapper;
import com.cplusexam.service.exam.IStudentOfficialExamService;
import com.cplusexam.util.ShiroSessionUtil;

@Service
public class StudentOfficialExamServiceImpl implements IStudentOfficialExamService {

	@Autowired
	private StudentOfficialExamMapper studentOfficialExamMapper;

	@Autowired
	private OfficialExamPaperMapper officialExamPaperMapper;
	
	@Autowired
	private OfficialExamMsgMapper officialExamMsgMapper;
	
	@Autowired
	private RadioItemMapper radioItemMapper;
	
	@Autowired
	private CheckItemMapper checkItemMapper;
	
	@Autowired
	private GapItemMapper gapItemMapper;
	
	@Autowired
	private JudgeItemMapper judgeItemMapper;
	
	@Override
	public List<Map<String, Object>> getOfficialExamTableMsg(String userId) throws Exception {
		return studentOfficialExamMapper.getOfficialExamTableMsg(userId);
	}

	@Override
	public List<Map<String, Object>> getOfficialExamUserTableMsg(String userId) throws Exception{
		return studentOfficialExamMapper.getOfficialExamUserTableMsg(userId);
	}

	@Override
	public void createOfficialExamPaper(int examId, List<Map<String, Object>> examItems) throws Exception {
		for (Map<String, Object> examItem : examItems) {
			int itemType = Integer.parseInt(examItem.get("item_typeid").toString());  //当前考题类型
			//随机在题库中  获取当前题型的  习题编号  不允许重复
			List<Integer> itemIds = new ArrayList<Integer>();
			int item_count = Integer.parseInt(examItem.get("item_count").toString());  //当前类型考题数量
			//进行考试生成
			switch (itemType) {
			case 1:  //单选题
				{
					itemIds = radioItemMapper.getRadioItemRandom(item_count);
				}
				break;
			case 2:  //多选题
				{
					itemIds = checkItemMapper.getCheckItemRandom(item_count);
				}
				break;
			case 3:  //填空题
				{
					itemIds = gapItemMapper.getGapItemRandom(item_count);
				}
				break;
			case 4:  //判断题
				{
					itemIds = judgeItemMapper.getJudgeItemRandom(item_count);
				}
				break;
			}
			List<OfficialExamPaper> parm = new ArrayList<OfficialExamPaper>();
			for (Integer itemId : itemIds) {
				OfficialExamPaper officialExamPaper = new OfficialExamPaper(examId, itemType, itemId);
				parm.add(officialExamPaper);
			}
			//进行考题信息的添加
			officialExamPaperMapper.addOfficialExamPapers(parm);
		}
		//修改当前考卷的状态
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", 1); //考试中状态
		map.put("id", examId); 
		officialExamMsgMapper.updateExamType(map);
	}

	@Override
	public List<Integer> getOfficialExamItemids(Map<String, Object> parm) throws Exception {
		return studentOfficialExamMapper.getOfficialExamItemids(parm);
	}
	
}
