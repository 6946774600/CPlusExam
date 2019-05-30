<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="/public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>模拟考试--考试详情页面</title>
</head>
<body class="layui-container">

<blockquote style="margin-top: 40px;" class="layui-elem-quote layui-anim layui-anim-up">模拟考试试卷名称：<font color="#FF5722">${examName }</font>； 总分：<font color="#FF5722">${countS }</font>；考试分数：<font color="#FF5722">${examS }</font></blockquote>


<c:if test="${radioC != null }">

	<fieldset class="layui-elem-field layui-field-title">
	  <legend>单选题&nbsp;&nbsp;<font color="#FFB800">${radioC }道</font>&nbsp;&nbsp;总分&nbsp;&nbsp;<font color="#FFB800">${radioSC }分</font></legend>
	  <div class="layui-field-box">
	   		<form class="layui-form" id="radio_form" lay-filter="radio_filter">
	    		<!-- 单选题内容  动态填充 -->
			</form>
	  </div>
	</fieldset>

</c:if>



<c:if test="${checkC != null }">

	<fieldset class="layui-elem-field layui-field-title">
	  <legend>多选题&nbsp;&nbsp;<font color="#FFB800">${checkC }道</font>&nbsp;&nbsp;总分&nbsp;&nbsp;<font color="#FFB800">${checkSC }分</font></legend>
	  <div class="layui-field-box">
	    内容区域
	  </div>
	</fieldset>
	
</c:if>

<c:if test="${gapC != null }">

	<fieldset class="layui-elem-field layui-field-title">
	  <legend>填空题&nbsp;&nbsp;<font color="#FFB800">${gapC }道</font>&nbsp;&nbsp;总分&nbsp;&nbsp;<font color="#FFB800">${gapSC }分</font></legend>
	  <div class="layui-field-box">
	    内容区域
	  </div>
	</fieldset>
	
</c:if>

<c:if test="${judgeC != null }">

	<fieldset class="layui-elem-field layui-field-title">
	  <legend>判断题&nbsp;&nbsp;<font color="#FFB800">${judgeC }道</font>&nbsp;&nbsp;总分&nbsp;&nbsp;<font color="#FFB800">${judgeSC }分</font></legend>
	  <div class="layui-field-box">
	    内容区域
	  </div>
	</fieldset>
	
</c:if>

</body>

<script type="text/javascript">
var testExamId = "${testItemId }";


layui.use(['form','layer','util','element'], function(){
	  var util = layui.util;
	  var form = layui.form;
	  var layer = layui.layer;
	  var element = layui.element;
	  
	  
	  util.fixbar({
		    bar1: '&#xe65c;',
		    click: function(type){
	    	  if(type === 'bar1'){
	    		  window.history.go(-1);
		      }
		    }
	  }); 
	  
	  //考题信息填充
	  radio_full(form);
	  
	  
})

//单选题页面填充函数
function radio_full(form){
	$.post("${path }/studentTestExam/getTextExamRadioItemList",{testExamId : testExamId},function(res){
			var radios = res.extend.radios;
			radios.forEach(function(item,index){
				var dom = $("<fieldset class='layui-elem-field' style='margin: 30px;'>"
						+"<legend>序号：<font color='#FFB800'>"+index+"</font>"
						+"&nbsp;&nbsp;考题编号：<font color='#FFB800'>"+item.uuid+"</font></legend>"
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
		return "<img class='layui-col-xs3 layui-col-sm3 layui-col-md3' alt='图片文件缺失' src='/CPlusExamImage/"+fileMsg+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
}


</script>

</html>