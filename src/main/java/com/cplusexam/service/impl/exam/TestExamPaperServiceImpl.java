package com.cplusexam.service.impl.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplusexam.bean.exam.TestExamItemMsg;
import com.cplusexam.bean.exam.TestExamPaper;
import com.cplusexam.dao.Item.CheckItemMapper;
import com.cplusexam.dao.Item.GapItemMapper;
import com.cplusexam.dao.Item.JudgeItemMapper;
import com.cplusexam.dao.Item.RadioItemMapper;
import com.cplusexam.dao.exam.TestExamMsgMapper;
import com.cplusexam.dao.exam.TestExamPaperMapper;
import com.cplusexam.service.exam.ITestExamPaperService;
import com.cplusexam.util.ShiroSessionUtil;

@Service
public class TestExamPaperServiceImpl implements ITestExamPaperService {

	@Autowired
	private TestExamPaperMapper testExamPaperMapper;
	
	@Autowired
	private RadioItemMapper radioItemMapper;
	
	@Autowired
	private CheckItemMapper checkItemMapper;

	@Autowired
	private GapItemMapper gapItemMapper;
	
	@Autowired
	private JudgeItemMapper judgeItemMapper;
	
	@Autowired
	private TestExamMsgMapper testExamMsgMapper;
	
	@Override
	public int addTestExamPaper(List<TestExamPaper> lists) throws Exception {
		return testExamPaperMapper.addTestExamPaper(lists);
	}

	@Override
	public void create_testExamPaper(int examTestID,List<TestExamItemMsg> testExamItemMsgs) throws Exception {
		// 获取当前模拟考卷试题的相关定义信息
		String userid = ShiroSessionUtil.getLoginUser().getLoginName();
		for (TestExamItemMsg testExamItemMsg : testExamItemMsgs) {
			int itemType = testExamItemMsg.getItem_type();
			//随机在题库中  获取当前题型的  习题编号  不允许重复
			List<Integer> itemIds = new ArrayList<Integer>();
			switch (itemType) {
			case 1:  //单选题
				{
					itemIds = radioItemMapper.getRadioItemRandom(testExamItemMsg.getItem_count());
				}
				break;
			case 2:  //多选题
				{
					itemIds = checkItemMapper.getCheckItemRandom(testExamItemMsg.getItem_count());
				}
				break;
			case 3:  //填空题
				{
					itemIds = gapItemMapper.getGapItemRandom(testExamItemMsg.getItem_count());
				}
				break;
			case 4:  //判断题
				{
					itemIds = judgeItemMapper.getJudgeItemRandom(testExamItemMsg.getItem_count());
				}
				break;
			}
			//对id进行遍历  生成模拟考题bean  并批量添加
			List<TestExamPaper> param = new ArrayList<TestExamPaper>();
			for (Integer itemid : itemIds) {
				TestExamPaper testExamPaper = new TestExamPaper(examTestID, userid, itemType, itemid);
				param.add(testExamPaper);
			}
			testExamPaperMapper.addTestExamPaper(param);
		}
		//进行当前试卷状态的修改
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", 1); //考试中状态
		map.put("uuid", examTestID); 
		testExamMsgMapper.updateTestExamType(map);
	}

	@Override
	public List<Integer> getTestExamPaperIds(Map<String, Object> map) throws Exception {
		return testExamPaperMapper.getTestExamPaperIds(map);
	}

	@Override
	public String getUserOptionOne(Map<String, Object> map) throws Exception {
		return testExamPaperMapper.getUserOptionOne(map);
	}

	@Override
	public void updateTestExamPaperUesrOption(TestExamPaper testExamPaper) throws Exception {
		testExamPaperMapper.updateTestExamPaperUesrOption(testExamPaper);
	}

	@Override
	public List<Map<String, Object>> getTestExamPaperOptionType(Map<String, Object> map) throws Exception {
		return testExamPaperMapper.getTestExamPaperOptionType(map);
	}

	@Override
	public List<Map<String, Object>> getOveredTestExamItemMsg(Map<String, Object> map) throws Exception {
		return testExamPaperMapper.getOveredTestExamItemMsg(map);
	}
	
	
	
}
