<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>题库--所有</title>
</head>
<body class="layui-fluid">
<blockquote style="margin-top: 20px;" class="layui-elem-quote layui-anim layui-anim-up">查看所有已录入题库的选题</blockquote>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>考题列表</legend>
</fieldset>
<div class="layui-tab layui-tab-brief " lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">单选题&nbsp;&nbsp;<span class="layui-badge layui-bg-blue" id="radio_count">00</span></li>
    <li>多选题&nbsp;&nbsp;<span class="layui-badge layui-bg-blue" id="check_count">00</span></li>
    <li>填空题&nbsp;&nbsp;<span class="layui-badge layui-bg-blue" id="gap_count">00</span></li>
    <li>判断题&nbsp;&nbsp;<span class="layui-badge layui-bg-blue" id="judge_count">00</span></li>
  </ul>
  <div class="layui-tab-content">
  	<div class="layui-tab-item layui-show">
  	
  		<div style="margin-left: 30%;" id="radio_page"></div>
	    <form class="layui-form" id="radio_form" lay-filter="radio_filter">
	    	<!-- 单选题内容  动态填充 -->
		</form>
		
	</div>
  	
    <div class="layui-tab-item">
    
    	<div style="margin-left: 30%;" id="check_page"></div>
	    <form class="layui-form" id="check_form" lay-filter="check_filter">
	    	<!-- 多选题内容  动态填充 -->
		</form>
		
    </div>
    <div class="layui-tab-item">
    
    	<div style="margin-left: 30%;" id="gap_page"></div>
    	<form class="layui-form" id="gap_form" lay-filter="gap_filter">
    		<!-- 填空题内容  动态填充 -->
		</form>
    
    </div>
    <div class="layui-tab-item">
    
    	<div style="margin-left: 30%;" id="judge_page"></div>
    	<form class="layui-form" id="judge_form" lay-filter="judge_filter">
    		<!-- 选择题动态填充 -->
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
	  
	  $.post("${path }/adminItemList/getItemCountForAll",{},function(res){
		  $("#radio_count").text(res.extend.count.radioCount);
		  $("#check_count").text(res.extend.count.checkCount);
		  $("#gap_count").text(res.extend.count.gapCount);
		  $("#judge_count").text(res.extend.count.judgeCount);
 
		//单选题分页
		 laypage.render({
		    elem: 'radio_page' 
		    ,count: res.extend.count.radioCount
		    ,limit : 5
		    ,limits : [5,10,20]
		    ,groups : 3
		    ,layout: [ 'prev', 'page', 'next', 'count', 'limit', 'refresh', 'skip']
		    ,jump: function(obj, first){
		        radio_full(obj.limit,obj.curr,form);
		    }
		  });
		  //多选题分页
		  laypage.render({
		    elem: 'check_page' 
		    ,count: res.extend.count.checkCount
		    ,limit : 5
		    ,limits : [5,10,20]
		    ,groups : 3
		    ,layout: [ 'prev', 'page', 'next', 'count', 'limit', 'refresh', 'skip']
		    ,jump: function(obj, first){
		        check_full(obj.limit,obj.curr,form);
		    }
		  }); 
		//填空题分页
		 laypage.render({
			    elem: 'gap_page' 
			    ,count: res.extend.count.gapCount
			    ,limit : 5
			    ,limits : [5,10,20]
			    ,groups : 3
			    ,layout: [ 'prev', 'page', 'next', 'count', 'limit', 'refresh', 'skip']
			    ,jump: function(obj, first){
			        gap_full(obj.limit,obj.curr,form);
			    }
			  });
		//选择题分页
		  laypage.render({
			    elem: 'judge_page' 
			    ,count: res.extend.count.judgeCount
			    ,limit : 5
			    ,limits : [5,10,20]
			    ,groups : 3
			    ,layout: [ 'prev', 'page', 'next', 'count', 'limit', 'refresh', 'skip']
			    ,jump: function(obj, first){
			    	judge_full(obj.limit,obj.curr,form);
			    }
			  }); 
		
  },"json");
	  
	  
});



//单选题页面填充函数
function radio_full(limit,curr,form){
	$.post("${path }/adminItemList/getRadioItemListForAll",{limit : limit , curr : curr},function(res){
			var radios = res.extend.radios;
			$("#radio_form").html("");
			radios.forEach(function(item,index){
				var dom = $("<fieldset class='layui-elem-field' style='margin: 30px;'>"
						+"<legend>考题编号：<font color='#FFB800'>"+item.uuid+"</font>&nbsp;&nbsp;录入教师：<font color='#FFB800'>"+item.name+"</font>"
						+"&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+item.create_time+"</font>"
						+"&nbsp;&nbsp;<span class='layui-badge'><i class='layui-icon layui-icon-delete'></i></span></legend>"
						+"<div class='layui-field-box' style='margin:20px;'>"
						+"<div class='layui-row'><div class='layui-col-xs10 layui-col-sm10 layui-col-md10'>"
						+"<div>"+item.item_name+"</div><form class='layui-form'>"
						+ radio_radioStr("option1",item.option1,item.item_option)
						+ radio_radioStr("option2",item.option2,item.item_option)
						+ radio_radioStr("option3",item.option3,item.item_option)
						+ radio_radioStr("option4",item.option4,item.item_option)
						+ radio_radioStr("option5",item.option5,item.item_option)
						+ radio_radioStr("option6",item.option6,item.item_option)
						+"</div></form>"
						+ radio_imgStr(item.item_imageId,item.fileName)
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
		return "<img class='layui-col-xs2 layui-col-sm2 layui-col-md2' alt='图片文件缺失' src='/CPlusExamImage/"+fileMsg+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
}

//***********单选题填充完成

//多选题页面填充函数
function check_full(limit,curr,form){
	$.post("${path }/adminItemList/getCheckItemListForAll",{limit : limit , curr : curr},function(res){
			var checks = res.extend.checks;
			$("#check_form").html("");
			checks.forEach(function(item,index){
				var dom = $("<fieldset class='layui-elem-field' style='margin: 30px;'>"
						+"<legend>考题编号：<font color='#FFB800'>"+item.uuid+"</font>&nbsp;&nbsp;录入教师：<font color='#FFB800'>"+item.name+"</font>"
						+"&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+item.create_time+"</font>"
						+"&nbsp;&nbsp;<span class='layui-badge'><i class='layui-icon layui-icon-delete'></i></span></legend>"
						+"<div class='layui-field-box' style='margin:20px;'>"
						+"<div class='layui-row'><div class='layui-col-xs10 layui-col-sm10 layui-col-md10'>"
						+"<div>"+item.item_name+"</div>"
						+ check_radioStr("option1",item.option1,item.item_option)
						+ check_radioStr("option2",item.option2,item.item_option)
						+ check_radioStr("option3",item.option3,item.item_option)
						+ check_radioStr("option4",item.option4,item.item_option)
						+ check_radioStr("option5",item.option5,item.item_option)
						+ check_radioStr("option6",item.option6,item.item_option)
						+"</div>"
						+ check_imgStr(item.item_imageId,item.fileName)
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
		return "<img class='layui-col-xs2 layui-col-sm2 layui-col-md2' alt='图片文件缺失' src='/CPlusExamImage/"+fileMsg+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
} 

//***************多选题页面填充完成



//******填空题页面填充

function gap_full(limit,curr,form){
	$.post("${path }/adminItemList/getGapItemListForALL",{limit : limit , curr : curr},function(res){
			var gaps = res.extend.gaps;
			$("#gap_form").html("");
			gaps.forEach(function(item,index){
				var dom = $("<fieldset class='layui-elem-field' style='margin: 30px;'>"
						+"<legend>考题编号：<font color='#FFB800'>"+item.uuid+"</font>&nbsp;&nbsp;录入教师：<font color='#FFB800'>"+item.name+"</font>"
						+"&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+item.create_time+"</font>"
						+"&nbsp;&nbsp;<span class='layui-badge'><i class='layui-icon layui-icon-delete'></i></span></legend>"
						+"<div class='layui-field-box' style='margin:20px;'>"
						+"<div class='layui-row'><div class='layui-col-xs10 layui-col-sm10 layui-col-md10'>"
						+"<div>"+item.item_name+"</div><form class='layui-form'>"
						+"<div style='padding-top: 20px;'><font size='3'>正确答案:</font>"
						+gap_optionFull(item.item_option)
						+"<div></div></form>"
						+ gap_imgStr(item.item_imageId,item.fileName)
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
		return "<img class='layui-col-xs2 layui-col-sm2 layui-col-md2' alt='图片文件缺失' src='/CPlusExamImage/"+fileMsg+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
}
//******************** 填空题填充完成


//***************判断题填充
function judge_full(limit,curr,form){
	$.post("${path }/adminItemList/getJudgeItemListForALL",{limit : limit , curr : curr},function(res){
			var judges = res.extend.judges;
			$("#judge_form").html("");
			judges.forEach(function(item,index){
				var dom = $("<fieldset class='layui-elem-field' style='margin: 30px;'>"
						+"<legend>考题编号：<font color='#FFB800'>"+item.uuid+"</font>&nbsp;&nbsp;录入教师：<font color='#FFB800'>"+item.name+"</font>"
						+"&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+item.create_time+"</font>"
						+"&nbsp;&nbsp;<span class='layui-badge'><i class='layui-icon layui-icon-delete'></i></span></legend>"
						+"<div class='layui-field-box' style='margin:20px;'>"
						+"<div class='layui-row'><div class='layui-col-xs10 layui-col-sm10 layui-col-md10'>"
						+"<div>"+item.item_name+"</div><form class='layui-form' style='padding-top:20px;'>"
						+ judge_radioStr("true",item.item_option)
						+ judge_radioStr("false",item.item_option)
						+"</div></form>"
						+ judge_imgStr(item.item_imageId,item.fileName)
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
		return "<img class='layui-col-xs2 layui-col-sm2 layui-col-md2' alt='图片文件缺失' src='/CPlusExamImage/"+fileMsg+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
}
//**************判断题填充完成
</script>
</html>