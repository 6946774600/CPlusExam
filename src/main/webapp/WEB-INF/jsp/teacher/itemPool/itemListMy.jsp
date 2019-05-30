<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>题库--我的录入</title>
</head>
<body class="layui-container">

	<blockquote style="margin-top: 40px;" class="layui-elem-quote">本页面可以进行已录入考题的维护</blockquote>

<div class="layui-collapse" lay-accordion style="margin-top: 20px;">
  <div class="layui-colla-item">
    <h2 class="layui-colla-title">单选题&nbsp;&nbsp;<span class="layui-badge layui-bg-blue" id="radio_count">00</span></h2>
    <div class="layui-colla-content layui-show" id="radio_div" >
    <div style="margin-left: 25%;" id="radio_page"></div>
    <form class="layui-form" id="radio_form" lay-filter="radio_filter">
    	<!-- 单选题内容  动态填充 -->
	</form>
    </div>
  </div>
  <div class="layui-colla-item">
    <h2 class="layui-colla-title">多选题&nbsp;&nbsp;<span class="layui-badge layui-bg-blue" id="check_count">00</span></h2>
    <div class="layui-colla-content">
    
    <div style="margin-left: 25%;" id="check_page"></div>
    <form class="layui-form" id="check_form" lay-filter="check_filter">
    	<!-- 多选题内容  动态填充 -->
	</form>
    
    </div>
  </div>
  <div class="layui-colla-item">
    <h2 class="layui-colla-title">填空题&nbsp;&nbsp;<span class="layui-badge layui-bg-blue" id="gap_count">00</span></h2>
    <div class="layui-colla-content">
    	
    	<div style="margin-left: 25%;" id="gap_page"></div>
    	<form class="layui-form" id="gap_form" lay-filter="gap_filter">
    	<!-- 填空题内容  动态填充 -->
		</form>
    
    </div>
  </div>
  <div class="layui-colla-item">
    <h2 class="layui-colla-title">判断题&nbsp;&nbsp;<span class="layui-badge layui-bg-blue" id="judge_count">00</span></h2>
    <div class="layui-colla-content">
    	
		<div style="margin-left: 25%;" id="judge_page"></div>
    	<form class="layui-form" id="judge_form" lay-filter="judge_filter">
    		<!-- 选择题动态填充-->
    	</form>
    	
    </div>
  </div>
</div>
</body>
<script>
layui.use(['element','form','layer','laypage'], function(){
  var element = layui.element;
  var form = layui.form;
  var layer = layui.layer;
  var laypage = layui.laypage;
  

  //进行题型数量的获取
  $.post("${path }/teacherItemList/getItemCountForMy",{},function(res){
		  $("#radio_count").text(res.extend.count.radioCount);
		  $("#check_count").text(res.extend.count.checkCount);
		  $("#gap_count").text(res.extend.count.gapCount);
		  $("#judge_count").text(res.extend.count.judgeCount);

		//单选题分页
		laypage.render({
		    elem: 'radio_page' 
		    ,count: res.extend.count.radioCount
		    ,limit : 5
		    ,groups : 3
		    ,layout: [ 'prev', 'page', 'next', 'count', 'refresh', 'skip']
		    ,jump: function(obj, first){
		        radio_full(obj.limit,obj.curr,form);
		    }
		  });
		 //多选题分页
		 laypage.render({
		    elem: 'check_page' 
		    ,count: res.extend.count.checkCount
		    ,limit : 5
		    ,groups : 3
		    ,layout: [ 'prev', 'page', 'next', 'count', 'refresh', 'skip']
		    ,jump: function(obj, first){
		        check_full(obj.limit,obj.curr,form);
		    }
		  });
		  //填空题分页
		 laypage.render({
			    elem: 'gap_page' 
			    ,count: res.extend.count.gapCount
			    ,limit : 5
			    ,groups : 3
			    ,layout: [ 'prev', 'page', 'next', 'count', 'refresh', 'skip']
			    ,jump: function(obj, first){
			        gap_full(obj.limit,obj.curr,form);
			    }
			  });
		//选择题分页
		  laypage.render({
			    elem: 'judge_page' 
			    ,count: res.extend.count.judgeCount
			    ,limit : 5
			    ,groups : 3
			    ,layout: [ 'prev', 'page', 'next', 'count', 'refresh', 'skip']
			    ,jump: function(obj, first){
			    	judge_full(obj.limit,obj.curr,form);
			    }
			  });
		
  },"json");

});

//单选题页面填充函数
function radio_full(limit,curr,form){
	$.post("${path }/teacherItemList/getRadioItemListForMy",{limit : limit , curr : curr},function(res){
			var radios = res.extend.radios;
			$("#radio_form").html("");
			radios.forEach(function(item,index){
				var dom = $("<fieldset class='layui-elem-field' style='margin: 30px;'>"
						+"<legend>考题编号：<font color='#FFB800'>"+item.uuid+"</font>&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+item.create_time+"</font>"
						+"&nbsp;&nbsp;<span class='layui-badge layui-bg-green' style='cursor: pointer;' onclick ='javascript:radio_edit("+item.uuid+");'><i class='layui-icon layui-icon-edit'></i></span>"
						+"&nbsp;&nbsp;<span class='layui-badge'><i class='layui-icon layui-icon-delete'></i></span></legend>"
						+"<div class='layui-field-box' style='margin:20px;'>"
						+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
						+"<div>"+item.item_name+"</div><form class='layui-form'>"
						+ radio_radioStr("option1",item.option1,item.item_option)
						+ radio_radioStr("option2",item.option2,item.item_option)
						+ radio_radioStr("option3",item.option3,item.item_option)
						+ radio_radioStr("option4",item.option4,item.item_option)
						+ radio_radioStr("option5",item.option5,item.item_option)
						+ radio_radioStr("option6",item.option6,item.item_option)
						+"</div></form>"
						+ radio_imgStr(item.item_imageId,item.upLoadFile)
						+"</div></div></fieldset>"
						);
				$("#radio_form").append(dom);
			});
			form.render('radio','radio_filter');
	},"json");
}

//进行单选题选项的封装  返回组装好的dom节点
function radio_radioStr(index,option,item_option){
	if(option==null || option.length==0){
		return "";	
	}else{
		if(index == item_option){
			return "<input type='radio' name='a' title='"+option+"' checked disabled ><br>";
		}else{
			return "<input type='radio' name='a' title='"+option+"' disabled><br>";
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

//单选题修改事件
function radio_edit(radio_id){
	layui.use(['layer'], function(){
		var layer = layui.layer;

		parent.layer.open({
			  title : '单选题编辑',
			  type: 2, 
			  offset: '100px' ,
			  area : ['800px','550px'],
			  shadeClose : false,
			  fixed : true,
			  content: '${path }/teacherItemList/editRadioPage/'+radio_id, 
			  /*  该页面为B页面  这里在A页面弹出了该层  该层又为C页面   暂时无法进行C页面的操作  技术受限  暂时使用在C页面显示按钮直接进行数据操作
			  	  A页面为B页面的父级页面  为了保证弹出层的固定  只能这么弹出  但是出现了上述问题
			   btn : ['修改','恢复'], */
			  /* yes : function(index, layero){
				  var body = layer.getChildFrame('body', index);
				  var body = layero[0].getChildFrame('body', index).find('#radio_resetBtn').click(); 
				  var body = layero[0];
				  body = body.getChildFrame('body', index);
				  body.find('#radio_resetBtn').click(); 

				  console.log(body);
				  body.find('#subForm').click(); 
			  },
			  btn2 : function(index, layero){
				  var body = layer.getChildFrame('body', index);
				  body.find('#radio_resetBtn').click();
				  var iframeWin = window[layero.find('iframe')[0]['name']];
				  console.log(iframeWin);
				  return false;
			  }, */
			  end :  function(){
				  $(".layui-laypage-btn").click();
			  },
		  
		  });
		 
	});
}


//多选题页面填充函数
function check_full(limit,curr,form){
	$.post("${path }/teacherItemList/getCheckItemListForMy",{limit : limit , curr : curr},function(res){
			var checks = res.extend.checks;
			$("#check_form").html("");
			checks.forEach(function(item,index){
				var dom = $("<fieldset class='layui-elem-field' style='margin: 30px;'>"
						+"<legend>考题编号：<font color='#FFB800'>"+item.uuid+"</font>&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+item.create_time+"</font>"
						+"&nbsp;&nbsp;<span class='layui-badge layui-bg-green' style='cursor: pointer;' onclick ='javascript:check_edit("+item.uuid+");'><i class='layui-icon layui-icon-edit'></i></span>"
						+"&nbsp;&nbsp;<span class='layui-badge'><i class='layui-icon layui-icon-delete'></i></span></legend>"
						+"<div class='layui-field-box' style='margin:20px;'>"
						+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
						+"<div>"+item.item_name+"</div>"
						+ check_radioStr("option1",item.option1,item.item_option)
						+ check_radioStr("option2",item.option2,item.item_option)
						+ check_radioStr("option3",item.option3,item.item_option)
						+ check_radioStr("option4",item.option4,item.item_option)
						+ check_radioStr("option5",item.option5,item.item_option)
						+ check_radioStr("option6",item.option6,item.item_option)
						+"</div>"
						+ check_imgStr(item.item_imageId,item.upLoadFile)
						+"</div></div></fieldset>"
						);
				$("#check_form").append(dom);
			});
			form.render('checkbox','check_filter');
	},"json");
}

//进行多选题选项的封装  返回组装好的dom节点
function check_radioStr(index,option,item_option){
	var temp = item_option.split(",");
	if(option==null || option.length==0){
		return "";	
	}else{
		if($.inArray(index, temp) != -1){
			return "<div style='margin-top:20px;'></div><input type='checkbox'  lay-skin='primary' name='a' title='"+option+"' disabled checked><br>";
		}else{
			return "<div style='margin-top:20px;'></div><input type='checkbox' style='margin-top:20px;' lay-skin='primary' name='a' title='"+option+"' disabled><br>";
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


//多选题修改事件
function check_edit(check_id){
	layui.use(['layer'], function(){
		var layer = layui.layer;

		parent.layer.open({
			  title : '多选题编辑',
			  type: 2, 
			  offset: '100px' ,
			  area : ['800px','550px'],
			  shadeClose : false,
			  fixed : true,
			  content: '${path }/teacherItemList/editCheckPage/'+check_id, 
			  end :  function(){
				  $(".layui-laypage-btn").click();
			  },
		  
		  });
		 
	});
}


//***************多选题页面填充完成


//******填空题页面填充

function gap_full(limit,curr,form){
	$.post("${path }/teacherItemList/getGapItemListForMy",{limit : limit , curr : curr},function(res){
			var gaps = res.extend.gaps;
			$("#gap_form").html("");
			gaps.forEach(function(item,index){
				var dom = $("<fieldset class='layui-elem-field' style='margin: 30px;'>"
						+"<legend>考题编号：<font color='#FFB800'>"+item.uuid+"</font>&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+item.create_time+"</font>"
						+"&nbsp;&nbsp;<span class='layui-badge layui-bg-green' style='cursor: pointer;' onclick ='javascript:gap_edit("+item.uuid+");'><i class='layui-icon layui-icon-edit'></i></span>"
						+"&nbsp;&nbsp;<span class='layui-badge'><i class='layui-icon layui-icon-delete'></i></span></legend>"
						+"<div class='layui-field-box' style='margin:20px;'>"
						+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
						+"<div>"+item.item_name+"</div><form class='layui-form'>"
						+"<div style='padding-top: 20px;'><font size='3'>正确答案:</font>"
						+gap_optionFull(item.item_option)
						+"<div></div></form>"
						+ gap_imgStr(item.item_imageId,item.upLoadFile)
						+"</div></div></fieldset>"
						);
				$("#gap_form").append(dom);
			});
	},"json");
}

function gap_optionFull(item_option){
    var option = item_option.replace(/\-/g,"&nbsp;&nbsp;")
    return "<div style='margin : 20px;'>"+option+"</div>";
}

function gap_imgStr(imageId,fileMsg){
	if(imageId == -1)
		return "";
	else{
		return "<img class='layui-col-xs3 layui-col-sm3 layui-col-md3' alt='图片文件缺失' src='/CPlusExamImage/"+(fileMsg.fileUUName+fileMsg.filTemp)+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
}


//填空题修改事件
function gap_edit(gap_id){
	layui.use(['layer'], function(){
		var layer = layui.layer;

		parent.layer.open({
			  title : '填空题编辑',
			  type: 2, 
			  offset: '200px' ,
			  area : ['800px','390px'],
			  shadeClose : false,
			  fixed : true,
			  content: '${path }/teacherItemList/editGapPage/'+gap_id, 
			  end :  function(){
				  $(".layui-laypage-btn").click();
			  },
		  });
		 
	});
}

//******************** 填空题填充完成


//***************判断题填充
function judge_full(limit,curr,form){
	$.post("${path }/teacherItemList/getJudgeItemListForMy",{limit : limit , curr : curr},function(res){
			var judges = res.extend.judges;
			$("#judge_form").html("");
			judges.forEach(function(item,index){
				var dom = $("<fieldset class='layui-elem-field' style='margin: 30px;'>"
						+"<legend>考题编号：<font color='#FFB800'>"+item.uuid+"</font>&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+item.create_time+"</font>"
						+"&nbsp;&nbsp;<span class='layui-badge layui-bg-green' style='cursor: pointer;' onclick ='javascript:judge_edit("+item.uuid+");'><i class='layui-icon layui-icon-edit'></i></span>"
						+"&nbsp;&nbsp;<span class='layui-badge'><i class='layui-icon layui-icon-delete'></i></span></legend>"
						+"<div class='layui-field-box' style='margin:20px;'>"
						+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
						+"<div>"+item.item_name+"</div><form class='layui-form' style='padding-top:20px;'>"
						+ judge_radioStr("true",item.item_option)
						+ judge_radioStr("false",item.item_option)
						+"</div></form>"
						+ judge_imgStr(item.item_imageId,item.upLoadFile)
						+"</div></div></fieldset>"
						);
				$("#judge_form").append(dom);
			});
			form.render('radio','judge_filter');
	},"json");
}

function judge_radioStr(index,option){
	if(index == "true"){
		if(option=="true"){
			return "<input type='radio' name='b' title='✔' checked disabled ><br>";
		}else{
			return "<input type='radio' name='b' title='✔' disabled ><br>";
		}
	}else{
		if(option=="false"){
			return "<input type='radio' name='b' title='✖' checked disabled ><br>";
		}else{
			return "<input type='radio' name='b' title='✖' disabled ><br>";
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


//判断题修改事件
function judge_edit(judge_id){
	layui.use(['layer'], function(){
		var layer = layui.layer;

		parent.layer.open({
			  title : '判断题编辑',
			  type: 2, 
			  offset: '200px' ,
			  area : ['800px','390px'],
			  shadeClose : false,
			  fixed : true,
			  content: '${path }/teacherItemList/editJudgePage/'+judge_id, 
			  end :  function(){
				  $(".layui-laypage-btn").click();
			  },
		  });
		 
	});
}
//**************判断题填充完成
</script>
</html>