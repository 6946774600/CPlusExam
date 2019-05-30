<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="/public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户模拟考试页面</title>
</head>
<body class="layui-container">

<blockquote style="margin-top: 40px;" class="layui-elem-quote layui-anim layui-anim-up">模拟考试</blockquote>

<fieldset class="layui-elem-field layui-field-title">
  <legend id="time_span"></legend>
  <div class="layui-field-box">
  </div>
</fieldset>

<div class="layui-tab layui-tab-brief">
  <ul class="layui-tab-title" id="tab_title">
   <c:if test="${radioC != null }">
	    <li>单选题<span class="layui-badge layui-bg-orange">${radioC }</span></li>
   </c:if>
   <c:if test="${checkC != null }">
    <li>多选题<span class="layui-badge layui-bg-orange">${checkC }</span></li>
   </c:if>
   <c:if test="${gapC != null }">
    <li>填空题<span class="layui-badge layui-bg-orange">${gapC }</span></li>
   </c:if>
   <c:if test="${judgeC != null }">
    <li>判断题<span class="layui-badge layui-bg-orange">${judgeC }</span></li>
   </c:if>
  </ul>
  <div class="layui-tab-content" style="height: 100px;" id="tab_body">
   <c:if test="${radioC != null }">
      <div class="layui-tab-item">
         <div class="layui-row layui-col-space20">
         
         
    	<!-- 单选题页面 -->
	    	<div class="layui-col-xs9 layui-col-sm9 layui-col-md9">
				<form class="layui-form" id="radio_form" lay-filter="radio_filter">
			    	<!-- 单选题内容  动态填充 -->
			    	<fieldset class='layui-elem-field' id="radio_div">
			    		
			    	</fieldset>
			    	<div class="layui-form-item" hidden="true">
				    <div class="layui-input-block">
				      <input type="hidden" id="itemRadio_id" name="item_id">
				    </div>
				  </div>
			    	
				</form>
			
			<div>
				<button class="layui-btn " id="lastRadio_btn" onclick="lastRadioItem()">上一题</button>
				<button class="layui-btn " id="radio_sub" onclick="subRadioItem()">确定</button>
				<button class="layui-btn " id="nextRadio_btn" onclick="nextRadioItem()">下一题</button>
			</div>
				
			</div>
			<div class="layui-col-xs3 layui-col-sm3 layui-col-md3">
				
					<fieldset class="layui-elem-field">
					  <legend>单选题考题状态</legend>
					  <div class="layui-field-box">
					    	<table id="radio_table" lay-filter="radio_table"></table>
					  </div>
					</fieldset>
				
			</div>
    	</div>
     </div>
    </c:if>
    
    <c:if test="${checkC != null }">
    <div class="layui-tab-item">
    	
    	<div class="layui-row layui-col-space20">
    	
    		<div class="layui-col-xs9 layui-col-sm9 layui-col-md9">
		
		 		
				<form class="layui-form" id="check_form" lay-filter="check_filter">
			    	<!-- 多选题内容  动态填充 -->
			    	<fieldset class='layui-elem-field' id="check_div">
			    	
			    		
			    	</fieldset>
			    	<div class="layui-form-item" hidden="true">
				    <div class="layui-input-block">
				      <input type="hidden" id="itemCheck_id" name="item_id">
				    </div>
				  </div>
			    	
				</form>
				
				<div>
					<button class="layui-btn " id="lastCheck_btn" onclick="lastCheckItem()">上一题</button>
					<button class="layui-btn " id="check_sub" onclick="subCheckItem()">确定</button>
					<button class="layui-btn " id="nextCheck_btn" onclick="nextCkeckItem()">下一题</button>
				</div>
					
			</div>
			
			<div class="layui-col-xs3 layui-col-sm3 layui-col-md3">
				
					<fieldset class="layui-elem-field">
					  <legend>多选题考题状态</legend>
					  <div class="layui-field-box">
					    	<table id="check_table" lay-filter="check_table"></table>
					  </div>
					</fieldset>
				
			</div>
    	
    	</div>
    
    </div>
    </c:if>
    
    
    <c:if test="${gapC != null }">
    <div class="layui-tab-item">
    		
    		<div class="layui-row layui-col-space20">
    	
	    		<div class="layui-col-xs9 layui-col-sm9 layui-col-md9">
			
			 		
					<form class="layui-form" id="gap_form" lay-filter="gap_form">
				    	<!-- 填空题内容  动态填充 -->
				    	<fieldset class='layui-elem-field' id="gap_div">
				    	
				    		
				    	</fieldset>
				    	<div class="layui-form-item" hidden="true">
					    <div class="layui-input-block">
					      <input type="hidden" id="itemGap_id" name="item_id">
					    </div>
					  </div>
				    	
					</form>
					
					<div>
						<button class="layui-btn " id="lastGap_btn" onclick="lastGapItem()">上一题</button>
						<button class="layui-btn " id="gap_sub" onclick="subGapItem()">确定</button>
						<button class="layui-btn " id="nextGap_btn" onclick="nextGapItem()">下一题</button>
					</div>
						
				</div>
				
				<div class="layui-col-xs3 layui-col-sm3 layui-col-md3">
					
						<fieldset class="layui-elem-field">
						  <legend>填空题考题状态</legend>
						  <div class="layui-field-box">
						    	<table id="gap_table" lay-filter="gap_table"></table>
						  </div>
						</fieldset>
					
				</div>
	    	
	    	</div>
    
    </div>
    </c:if>
    
    
    <c:if test="${judgeC != null }">
    <div class="layui-tab-item">
    	
    	<div class="layui-row layui-col-space20">
    	
	    		<div class="layui-col-xs9 layui-col-sm9 layui-col-md9">
			
			 		
					<form class="layui-form" id="judge_form" lay-filter="judge_form">
				    	<!-- 填空题内容  动态填充 -->
				    	<fieldset class='layui-elem-field' id="judge_div">
				    	
				    		
				    	</fieldset>
				    	<div class="layui-form-item" hidden="true">
					    <div class="layui-input-block">
					      <input type="hidden" id="itemJudge_id" name="item_id">
					    </div>
					  </div>
				    	
					</form>
					
					<div>
						<button class="layui-btn " id="lastJudge_btn" onclick="lastJudgeItem()">上一题</button>
						<button class="layui-btn " id="judge_sub" onclick="subJudgeItem()">确定</button>
						<button class="layui-btn " id="nextJudge_btn" onclick="nextJudgeItem()">下一题</button>
					</div>
						
				</div>
				
				<div class="layui-col-xs3 layui-col-sm3 layui-col-md3">
					
						<fieldset class="layui-elem-field">
						  <legend>判断题考题状态</legend>
						  <div class="layui-field-box">
						    	<table id="judge_table" lay-filter="judge_table"></table>
						  </div>
						</fieldset>
					
				</div>
	    	
	    	</div>
    
    </div>
    </c:if>
    
    
  </div>
</div>

</body>
<script type="text/javascript">

//各类型的所有考题ids  这里全部存储  点击下一个上一个 在这里寻找
var radioIdsData;
var radio_index;
var checkIdsData;
var check_index;
var gapIdsData;
var gap_index;
var judgeIdsData;
var judge_index;
var radioC = "${radioC }";
var checkC = "${checkC }";
var gapC = "${gapC }";
var judgeC = "${judgeC }";
var testExamId = ${testExamId };

//将时分秒转化为时间戳
function time_to_sec(time) {
    var s = '';
    var hour = time.split(':')[0];
    var min = time.split(':')[1];
    var sec = time.split(':')[2];
    s = Number(hour*3600000) + Number(min*60000) + Number(sec*1000);
    return s;
};


layui.use(['form','layer','util','element','table'], function(){
  var util = layui.util;
  var form = layui.form;
  var layer = layui.layer;
  var element = layui.element;
  var table = layui.table;
  
  //选项卡  第一个div 默认选中
  $("#tab_body div:first-child").addClass("layui-show");
  $("#tab_title li:first-child").addClass("layui-this");
  //底部固定块
  util.fixbar({
	    bar1: '&#xe681;',
	    bar2: '&#xe65c;',
	    click: function(type){
    	  if(type === 'bar1'){
    		  layer.confirm('确定提交考卷吗？', {icon: 3, title:'系统提示'}, function(index){
  		    	layer.close(index);
    			  $.post("${path }/studentTestExam/commitTestExam",{testExamId : "${testExamId }"},function(res){
      		    	layer.alert('试卷提交完成！',{closeBtn : false,}, function(index){
  	    		    	window.history.go(-1);
      		    	}); 
      		      },"json");
    		   });
	      }
	      if(type === 'bar2'){
	    	  window.history.go(-1);
	      }
	    }
  }); 
  
  
  var examTime = "${examTime }"
  //考试开始时间： 
  var serverTime = new Date().getTime();
  //考试结束时间  开始时间+考试时间
  var endTime = new Date().getTime()+time_to_sec(examTime);
  
  util.countdown(endTime, serverTime, function(date, serverTime, timer){
    var str = date[0] + '天' + date[1] + '时' +  date[2] + '分' + date[3] + '秒';
    if(date[0]+date[1]+date[2]+date[3]==0){
    	//时间已经用玩了
    	//给出提示  ，  修改当前考卷状态  并退出该页面
    	layui.$('#time_span').html('考试剩余时间：<font color="#FF5722">'+ str + '</font>');
    	layer.open({
    		  title: '系统提示'
    		  ,content: '<font color="#FF5722">考试时间已截止！</font>'
    		  ,closeBtn : false
    		  ,shade : 0.5
    		  ,anim :6
    		  ,yes : function(index, layero){
    		    layer.close(index);
    		    var index2 = layer.open({
    	    		  title: ''
    	    		  ,content: '<i class="layui-icon layui-icon-loading layui-anim layui-anim-rotate layui-anim-loop"></i>&nbsp;&nbsp;试卷提交中！'
    	    		  ,closeBtn : false
    	    		  ,shade : 0.5
    	    		  ,anim :5
    	    		  ,btn : ''
    	    	});
    		    //提交当前试卷
    		    $.post("${path }/studentTestExam/commitTestExam",{testExamId : "${testExamId }"},function(res){
    		    	layer.close(index2);
    		    	layer.alert('试卷提交完成！',{closeBtn : false,}, function(index){
	    		    	window.history.go(-1);
    		    	}); 
    		    },"json");
    		  }
    	}); 
    	
    }else{
    	//倒计时  每一秒都向服务期发送当前剩余时间	
	    layui.$('#time_span').html('考试剩余时间：<font color="#FF5722">'+ str + '</font>');
    	endExamTime = date[1] + ":" + date[2] + ":" + date[3];
    	$.post("${path }/studentTestExam/updateTestExamTime",{testExamId : "${testExamId }", endExamTime : endExamTime},function(res){
    		
    	},"json");
    }
  });
  
  
  //获取所有题型的考题id
  $.post("${path }/studentTestExam/getTestExamItemIds",{testExamId : "${testExamId }"},function(res){
		radioIdsData = res.extend.radioIdList;
		checkIdsData = res.extend.checkIdList;
		gapIdsData = res.extend.gapIdList;
		judgeIdsData = res.extend.judgeIdList; 
		//进行各类型考题的填充
		if(radioIdsData.length!=0){
			full_radio(0);
		}
		if(checkIdsData.length!=0){
			full_check(0);
		}
		if(gapIdsData.length!=0){
			full_gap(0);
		}
		if(judgeIdsData.length!=0){
			full_judge(0);
		}
  },"json");
  
  
  //填充单选题的考题状态表格
	table.render({
		elem: '#radio_table',
		url: '${path }/studentTestExam/getTestExamRadioType/'+testExamId,  //获取当前考卷单选题的状态
		page: {
			groups : 1,
			layout : ['prev', 'page', 'next','limit'],
		} ,//开启分页
		limit : 5,
		limits : [5,10,15],
		loading : true,
		cols: [[ //表头
		  {type : 'numbers' , title: '序号', width : 60}
		  ,{field: 'item_id', title: '编号', width : 60 ,align : 'center'}
		  ,{field: 'option_type', title: '状态' ,minWidth:120 ,  align : 'center' ,  templet: function(d){
			  if(d.option_type == 0){
				  //未答题
				  return '<span style="cursor: pointer;" onclick="gotoRadioItem('+(d.LAY_INDEX-1)+')" class="layui-badge layui-bg-orange">未答题</span>';
			  }else if(d.option_type == 1){
				  //已答题
				  return '<span style="cursor: pointer;" onclick="gotoRadioItem('+(d.LAY_INDEX-1)+')" class="layui-badge layui-bg-blue">已答题</span>';
			  }else{
				  //无法解析的状态
			  }
		  }}
		]],
		parseData : function(res){
            return {
               code : res.code,
               msg: res.msg ,
               count:res.count, //数据总数
               data : res.data,
           };
       }, 
	});
  
  //多选题考题状态填充
	table.render({
		elem: '#check_table',
		url: '${path }/studentTestExam/getTestExamCheckType/'+testExamId,  //获取当前考卷单选题的状态
		page: {
			groups : 1,
			layout : ['prev', 'page', 'next','limit'],
		} ,//开启分页
		limit : 5,
		limits : [5,10,15],
		loading : true,
		cols: [[ //表头
		  {type : 'numbers' , title: '序号', width : 60}
		  ,{field: 'item_id', title: '编号', align : 'center' , width : 60}
		  ,{field: 'option_type', title: '状态' , minWidth:120, align : 'center' , templet: function(d){
			  if(d.option_type == 0){
				  //未答题
				  return '<span style="cursor: pointer;" onclick="gotoCheckItem('+(d.LAY_INDEX-1)+')" class="layui-badge layui-bg-orange">未答题</span>';
			  }else if(d.option_type == 1){
				  //已答题
				  return '<span style="cursor: pointer;" onclick="gotoCheckItem('+(d.LAY_INDEX-1)+')" class="layui-badge layui-bg-blue">已答题</span>';
			  }else{
				  //无法解析的状态
			  }
		  }}
		]],
		parseData : function(res){
            return {
               code : res.code,
               msg: res.msg ,
               count:res.count, //数据总数
               data : res.data,
           };
       }, 
	});
  
  //填空题状态表格填充
	table.render({
		elem: '#gap_table',
		url: '${path }/studentTestExam/getTestExamGapType/'+testExamId,  //获取当前考卷单选题的状态
		page: {
			groups : 1,
			layout : ['prev', 'page', 'next','limit'],
		} ,//开启分页
		limit : 5,
		limits : [5,10,15],
		loading : true,
		cols: [[ //表头
		  {type : 'numbers' , title: '序号', width : 60}
		  ,{field: 'item_id', title: '编号', align : 'center' , width : 60}
		  ,{field: 'option_type', title: '状态' , minWidth:120, align : 'center' , templet: function(d){
			  if(d.option_type == 0){
				  //未答题
				  return '<span style="cursor: pointer;" onclick="gotoGapItem('+(d.LAY_INDEX-1)+')" class="layui-badge layui-bg-orange">未答题</span>';
			  }else if(d.option_type == 1){
				  //已答题
				  return '<span style="cursor: pointer;" onclick="gotoGapItem('+(d.LAY_INDEX-1)+')" class="layui-badge layui-bg-blue">已答题</span>';
			  }else{
				  //无法解析的状态
			  }
		  }}
		]],
		parseData : function(res){
            return {
               code : res.code,
               msg: res.msg ,
               count:res.count, //数据总数
               data : res.data,
           };
       }, 
	});
  
  //判断题考题状态填充
	table.render({
		elem: '#judge_table',
		url: '${path }/studentTestExam/getTestExamJudgeType/'+testExamId,  //获取当前考卷单选题的状态
		page: {
			groups : 1,
			layout : ['prev', 'page', 'next','limit'],
		} ,//开启分页
		limit : 5,
		limits : [5,10,15],
		loading : true,
		cols: [[ //表头
		  {type : 'numbers' , title: '序号', width : 60}
		  ,{field: 'item_id', title: '编号', align : 'center' ,  width : 60}
		  ,{field: 'option_type', title: '状态' , minWidth:120, align : 'center' , templet: function(d){
			  if(d.option_type == 0){
				  //未答题
				  return '<span style="cursor: pointer;" onclick="gotoJudgeItem('+(d.LAY_INDEX-1)+')" class="layui-badge layui-bg-orange">未答题</span>';
			  }else if(d.option_type == 1){
				  //已答题
				  return '<span style="cursor: pointer;" onclick="gotoJudgeItem('+(d.LAY_INDEX-1)+')" class="layui-badge layui-bg-blue">已答题</span>';
			  }else{
				  //无法解析的状态
			  }
		  }}
		]],
		parseData : function(res){
            return {
               code : res.code,
               msg: res.msg ,
               count:res.count, //数据总数
               data : res.data,
           };
       }, 
	});
  
  
})

function full_radio(item_index){
	layui.use(['form','layer','element'],function(){
		var form = layui.form;
		var layer = layui.layer;
		var element = layui.element;
		
		//加载层
		var load_index = layer.load();
		
		//获取当前的考题id
		var itemId = radioIdsData[item_index];
		//查看当前索引是否有上一个或下一个考题
		radio_index = item_index;
		if(item_index == 0){
			$("#lastRadio_btn").hide();
		}else{
			$("#lastRadio_btn").show();		
		}
		if(item_index == (radioC-1)){
			$("#nextRadio_btn").hide();
		}else{
			$("#nextRadio_btn").show();		
		}
		//进行题目的填充
		$.post("${path }/studentTestExam/getRadioItemExam",{ item_id : itemId , testExamId : testExamId},function(res){
			var radio = res.extend.radio;
			var userOption = res.extend.userOption;
			$("#radio_div").html("");
			$("#itemRadio_id").val(radio.uuid);
			var dom = $("<legend>考题序号：<font color='#FFB800'>"+(radio_index+1)+"</font>"
					+"&nbsp;&nbsp;考题编号：<font color='#FFB800'>"+radio.uuid+"</font></legend>"
					+"<div class='layui-field-box' style='margin:20px;'>"
					+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
					+"<div>"+radio.item_name+"</div>"
					+ radio_radioStr("option1",radio.option1,userOption)
					+ radio_radioStr("option2",radio.option2,userOption)
					+ radio_radioStr("option3",radio.option3,userOption)
					+ radio_radioStr("option4",radio.option4,userOption)
					+ radio_radioStr("option5",radio.option5,userOption)
					+ radio_radioStr("option6",radio.option6,userOption)
					+"</div>"
					+ radio_imgStr(radio.item_imageId,radio.upLoadFile)
					+"</div></div>"
					); 
			$("#radio_div").append(dom);
			form.render('radio');
			element.render('tab');
			layer.close(load_index); 
		},"json");
	});
}

//进行单选题选项的封装  返回组装好的dom节点
function radio_radioStr(index,option,userOption){
	if(option==null || option.length==0){
		return "";	
	}else{
		if(index == userOption){
			return "<input type='radio' name='user_option' value='"+index+"' title='"+option+"' checked><br>";		
		}else{
			return "<input type='radio' name='user_option' value='"+index+"' title='"+option+"'><br>";
		}
	}
}

//进行单选题图片节点判断  并返回封装好的dom节点
function radio_imgStr(imageId,fileMsg){
	if(imageId == -1)
		return "";
	else{
		return "<img class='layui-col-xs3 layui-col-sm3 layui-col-md3' alt='图片文件缺失' src='/CPlusExamImage/"+(fileMsg.fileUUName+fileMsg.filTemp)+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
}

//上一题
function lastRadioItem(){
	full_radio(radio_index-1);
}

//提交该题按钮
function subRadioItem(){
	//提交当前考题的用户答案
	if($("#radio_form").find("input:checked").length == 0){
		layui.use('layer',function(){
			var layer = layui.layer;
			layer.msg("还木有选择正确答案");
		});
	}else{
		//发送ajax请求  进行该考题的用户答案录入
		$.ajax({
			url : "${path }/studentTestExam/updateRadioUserOption/"+testExamId,
		    method : "post",
		    data : $('#radio_form').serialize(),
		    dataType : "json",
		    success : function(res){
		    	nextRadioItem();
		    	//重新加载 单选题的考题状态表格
		    	layui.use('table',function(){
					var table = layui.table;	    		
			    	table.reload("radio_table");
		    	})
		    }
		});
	}
	
}

//下一题
function nextRadioItem(){
	full_radio((radio_index+1)>(radioC-1)?(radioC-1):(radio_index+1));
}

//跳转到具体的单选题
function gotoRadioItem(index){
	full_radio(index);
}



//多选题相关操作
function full_check(item_index){
	layui.use(['form','layer','element'],function(){
		var form = layui.form;
		var layer = layui.layer;
		var element = layui.element;
		
		//加载层
		var load_index = layer.load();
		
		//获取当前的考题id
		var itemId = checkIdsData[item_index];
		//查看当前索引是否有上一个或下一个考题
		check_index = item_index;
		if(item_index == 0){
			$("#lastCheck_btn").hide();
		}else{
			$("#lastCheck_btn").show();		
		}
		if(item_index == (checkC-1)){
			$("#nextCheck_btn").hide();
		}else{
			$("#nextCheck_btn").show();		
		}
		//进行考题填充
		$.post("${path }/studentTestExam/getCheckItemExam",{ item_id : itemId , testExamId : testExamId},function(res){
			var check = res.extend.check;
			var userOption = res.extend.userOption;
			$("#check_div").html("");
			$("#itemCheck_id").val(check.uuid);
			var dom = $("<legend>考题序号：<font color='#FFB800'>"+(check_index+1)+"</font>"
					+"&nbsp;&nbsp;考题编号：<font color='#FFB800'>"+check.uuid+"</font></legend>"
					+"<div class='layui-field-box' style='margin:20px;'>"
					+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
					+"<div>"+check.item_name+"</div>"
					+ check_radioStr("option1",check.option1,userOption)
					+ check_radioStr("option2",check.option2,userOption)
					+ check_radioStr("option3",check.option3,userOption)
					+ check_radioStr("option4",check.option4,userOption)
					+ check_radioStr("option5",check.option5,userOption)
					+ check_radioStr("option6",check.option6,userOption)
					+"</div>"
					+ check_imgStr(check.item_imageId,check.upLoadFile)
					+"</div></div>"
					);
			$("#check_div").append(dom);
			form.render('checkbox','check_filter');
			layer.close(load_index); 
		},"json");
	})
}
//进行多选题选项的封装  返回组装好的dom节点
function check_radioStr(index,option,user_option){
	var temp="";
	if(user_option==null || user_option.lenth ==0){
		
	}else{
		temp = user_option.split(",");
	}
	if(option==null || option.length==0){
		return "";	
	}else{
		if($.inArray(index, temp) != -1){
			return "<div style='margin-top:20px;'></div><input type='checkbox' checked style='margin-top:20px;' lay-skin='primary' name='user_option' value='"+index+"' title='"+option+"'><br>";
		}else{
			return "<div style='margin-top:20px;'></div><input type='checkbox' style='margin-top:20px;' lay-skin='primary' name='user_option' value='"+index+"' title='"+option+"'><br>";
		}
	}
}

//进行多选题图片节点判断  并返回封装好的dom节点
function check_imgStr(imageId,fileMsg){
	if(imageId == -1)
		return "";
	else{
		return "<img class='layui-col-xs3 layui-col-sm3 layui-col-md3' alt='图片文件缺失' src='/CPlusExamImage/"+(fileMsg.fileUUName+fileMsg.filTemp)+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
} 


//上一题
function lastCheckItem(){
	full_check(check_index-1);
}
//下一题
function nextCkeckItem(){
	full_check((check_index+1)>(checkC-1)?(checkC-1):(check_index+1));
}
//多选题提交按钮
function subCheckItem(){
	//提交当前考题的用户答案
	if($("#check_form").find("input:checked").length < 2){
		layui.use('layer',function(){
			var layer = layui.layer;
			layer.msg("请至少选择两个或以上答案");
		});
	}else{
		//发送ajax请求  进行该考题的用户答案录入
		$.ajax({
			url : "${path }/studentTestExam/updateCheckUserOption/"+testExamId,
		    method : "post",
		    data : $('#check_form').serialize(),
		    dataType : "json",
		    success : function(res){
		    	nextCkeckItem();
		    	//重新加载 单选题的考题状态表格
		    	layui.use('table',function(){
					var table = layui.table;	    		
			    	table.reload("check_table");
		    	})
		    }
		});
	}
}

//跳转按钮
function gotoCheckItem(item_index){
	full_check(item_index);
}



//--------------------填空题填充
function full_gap(item_index){
	layui.use(['form','layer','element'],function(){
		var form = layui.form;
		var layer = layui.layer;
		var element = layui.element;
		
		//加载层
		var load_index = layer.load();
		
		//获取当前的考题id
		var itemId = gapIdsData[item_index];
		//查看当前索引是否有上一个或下一个考题
		gap_index = item_index;
		if(item_index == 0){
			$("#lastGap_btn").hide();
		}else{
			$("#lastGap_btn").show();		
		}
		if(item_index == (gapC-1)){
			$("#nextGap_btn").hide();
		}else{
			$("#nextGap_btn").show();		
		}
		//进行考题填充
		$.post("${path }/studentTestExam/getGapItemExam",{ item_id : itemId , testExamId : testExamId},function(res){
			var gap = res.extend.gap;
			var userOption = res.extend.userOption;
			$("#gap_div").html("");
			$("#itemGap_id").val(gap.uuid);
			var dom = $("<legend>考题序号：<font color='#FFB800'>"+(gap_index+1)+"</font>"
					+"&nbsp;&nbsp;考题编号：<font color='#FFB800'>"+gap.uuid+"</font></legend>"
					+"<div class='layui-field-box' style='margin:20px;'>"
					+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
					+"<div style='margin-left:40px;'>问题："+gap.item_name+"</div>"
					+"<div style='margin-top: 25px; margin-left : 0px;'>"
					+gap_optionFull(userOption)
					+"<div>"
					+ gap_imgStr(gap.item_imageId,gap.upLoadFile)
					+"</div></div>"
					);
			$("#gap_div").append(dom);
			layer.close(load_index); 
		},"json");
	})
}

function gap_optionFull(userOption){
	if(userOption==null || userOption.length==0){
	    return '<div class="layui-form-item"><label class="layui-form-label">正确答案</label><div class="layui-input-block">'
      	 	+'<input type="text" name="user_option" required  lay-verify="required" placeholder="多个答案之间请用&nbsp;-&nbsp;隔开" autocomplete="off" class="layui-input"></div></div>';
	}else{
		return '<div class="layui-form-item"><label class="layui-form-label">正确答案</label><div class="layui-input-block">'
  	 	+'<input type="text" value="'+userOption+'" name="user_option" required  lay-verify="required" placeholder="多个答案之间请用&nbsp;-&nbsp;隔开" autocomplete="off" class="layui-input"></div></div>';
	}
}

function gap_imgStr(imageId,fileMsg){
	if(imageId == -1)
		return "";
	else{
		return "<img class='layui-col-xs3 layui-col-sm3 layui-col-md3' alt='图片文件缺失' src='/CPlusExamImage/"+(fileMsg.fileUUName+fileMsg.filTemp)+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
}
//上一题
function lastGapItem(){
	full_gap(gap_index-1);
}

//下一题
function nextGapItem(){
	full_gap((gap_index+1)>(gapC-1)?(gapC-1):(gap_index+1));
}

//填空题跳转按钮
function gotoGapItem(item_index){
	full_gap(item_index);
}

//提交按钮
function subGapItem(){
	//提交当前考题的用户答案
	var temp =$("#gap_form").find("input[name=user_option]").val();
	temp = temp.replace(/\s*/g,"");
	if(temp == null || temp.length == 0){
		layui.use('layer',function(){
			var layer = layui.layer;
			layer.msg("请填写答案");
		});
	}else{
		//发送ajax请求  进行该考题的用户答案录入
		$.ajax({
			url : "${path }/studentTestExam/updateGapUserOption/"+testExamId,
		    method : "post",
		    data : $('#gap_form').serialize(),
		    dataType : "json",
		    success : function(res){
		    	nextGapItem();
		    	//重新加载 单选题的考题状态表格
		    	layui.use('table',function(){
					var table = layui.table;	    		
			    	table.reload("gap_table");
		    	})
		    }
		});
	}
}


//-------------------判断题填充
function full_judge(item_index){
	layui.use(['form','layer','element'],function(){
		var form = layui.form;
		var layer = layui.layer;
		var element = layui.element;
		
		//加载层
		var load_index = layer.load();
		
		//获取当前的考题id
		var itemId = judgeIdsData[item_index];
		//查看当前索引是否有上一个或下一个考题
		judge_index = item_index;
		if(item_index == 0){
			$("#lastJudge_btn").hide();
		}else{
			$("#lastJudge_btn").show();		
		}
		if(item_index == (judgeC-1)){
			$("#nextJudge_btn").hide();
		}else{
			$("#nextJudge_btn").show();		
		}
		//进行考题填充
		$.post("${path }/studentTestExam/getJudgeItemExam",{ item_id : itemId , testExamId : testExamId},function(res){
			var judge = res.extend.judge;
			var userOption = res.extend.userOption;
			$("#judge_div").html("");
			$("#itemJudge_id").val(judge.uuid);
			var dom = $("<legend>考题编号：<font color='#FFB800'>"+(judge_index+1)+"</font>"
					+"&nbsp;&nbsp;考题编号：<font color='#FFB800'>"+judge.uuid+"</font></legend>"
					+"<div class='layui-field-box' style='margin:20px;'>"
					+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
					+"<div >问题："+judge.item_name+"</div>"
					+ judge_radioStr("true",userOption)
					+ judge_radioStr("false",userOption)
					+"</div>"
					+ judge_imgStr(judge.item_imageId,judge.upLoadFile)
					+"</div></div>"
					);
			$("#judge_div").append(dom);
			form.render('radio','judge_form');
			layer.close(load_index); 
		},"json");
	})
}


function judge_radioStr(index,userOption){
	if(userOption==null || userOption.length ==0){
		if(index == "true"){
			return "<input type='radio' name='user_option' value='true' title='✔'><br>";
		}else{
			return "<input type='radio' name='user_option' value='false' title='✖'><br>";		
		}
	}else{
		if(index == "true"){
			if(userOption=="true"){
				return "<input type='radio' name='user_option' value='true' title='✔' checked ><br>";
			}else{
				return "<input type='radio' name='user_option' value='true' title='✔' ><br>";
			}
		}else{
			if(userOption=="false"){
				return "<input type='radio' name='user_option' value='false' title='✖' checked ><br>";
			}else{
				return "<input type='radio' name='user_option' value='false' title='✖' ><br>";
			}
		}
	}
	
}

function judge_imgStr(imageId,fileMsg){
	if(imageId == -1)
		return "";
	else{
		return "<img class='layui-col-xs3 layui-col-sm3 layui-col-md3' alt='图片文件缺失' src='/CPlusExamImage/"+(fileMsg.fileUUName+fileMsg.filTemp)+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
}

//上一题
function lastJudgeItem(){
	full_judge(judge_index-1);
}

//下一题
function nextJudgeItem(){
	//是否是最后一个题  
	full_judge((judge_index+1)>(judgeC-1)?(judgeC-1):(judge_index+1));
}

//判断题提交按钮
function subJudgeItem(){
	if($("#judge_form").find("input:checked").length == 0){
		layui.use('layer',function(){
			var layer = layui.layer;
			layer.msg("还木有选择正确答案");
		});
	}else{
		//发送ajax请求  进行该考题的用户答案录入
		$.ajax({
			url : "${path }/studentTestExam/updateJudgeUserOption/"+testExamId,
		    method : "post",
		    data : $('#judge_form').serialize(),
		    dataType : "json",
		    success : function(res){
		    	nextJudgeItem();
		    	//重新加载 单选题的考题状态表格
		    	layui.use('table',function(){
					var table = layui.table;	    		
			    	table.reload("judge_table");
		    	})
		    }
		});
	}
}

//判断题考题跳转页面
function gotoJudgeItem(item_index){
	full_judge(item_index);
}

</script>

</html>