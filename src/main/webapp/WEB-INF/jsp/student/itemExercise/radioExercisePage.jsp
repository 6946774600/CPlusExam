<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>单选题练习界面</title>
</head>
<body class="layui-container" >
<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 40px;">单选题练习</blockquote>
<div class="layui-row layui-col-space20" style="margin-top: 20px;">
	<div class="layui-col-xs9 layui-col-sm9 layui-col-md9">
		
		 		
	<form class="layui-form" id="radio_form" lay-filter="radio_filter">
    	<!-- 单选题内容  动态填充 -->
    	<fieldset class='layui-elem-field' id="radio_div">
    	
    		
    	</fieldset>
    	<div class="layui-form-item" hidden="true">
	    <div class="layui-input-block">
	      <button id="subForm" class="layui-btn" lay-submit lay-filter="formDemo">确定</button>
	      <input type="hidden" id="item_id" name="item_id">
	    </div>
	  </div>
    	
	</form>
	
	<div>
		<button class="layui-btn " id="sub_btn" onclick="javascript:$('#subForm').click();">提交</button>
		<button class="layui-btn " onclick="netxItem()">下一题</button>
	</div>
		
	</div>
	<div class="layui-col-xs3 layui-col-sm3 layui-col-md3">
		
		<fieldset class="layui-elem-field">
		  <legend>提示信息</legend>
		  <div class="layui-field-box">
		    	<div>习题剩余数量：<span id="sum_span"></span></div>
		    	<hr class="layui-bg-green">
		    	<div>答题结果：<span id="tof_span">--</span></div>
		    	<hr class="layui-bg-green">
		    	<div>输入答案：<span id="userO_span">--</span></div>
		    	<hr class="layui-bg-green">
		    	<div>正确答案：<span id="itemO_span">--</span></div>
		  </div>
		</fieldset>
		
	</div>
</div>
</body>
<script type="text/javascript">

layui.use(['form','layer','util'], function(){
  var util = layui.util;
  var form = layui.form;
  var layer = layui.layer;
  
  
	//考题填充
  radio_full(form);
	//考题剩余数量填充
  radioCountFull();
  //执行
  util.fixbar({
    bar1: '&#xe65c;',
    //css: {left: 60, top: 0} ,
    click: function(type){
      if(type === 'bar1'){
    	  window.history.go(-1);
      }
    }
  }); 
  
  
  form.on('submit(formDemo)', function(data){
	    var temp = $('input:radio:checked').length;
	    console.log(temp);
	    if(temp!=1){
	    	layer.msg("请选择正确答案");
	    }else{
	    	var index = layer.load();
	    	$.ajax({
			    url : "${path }/studentItem/addRadioExercise",
			    method : "post",
			    data : $('#radio_form').serialize(),
			    dataType : "json",
			    success : function(res){
					$("#sub_btn").hide();
					$("#tof_span").text("");
					$("#userO_span").text("");
					$("#itemO_span").text("");
					$("#tof_span").append($(res.extend.tof));
					$("#userO_span").append($("<font color='#01AAED'>"+res.extend.userOption+"</font>"));
					$("#itemO_span").append($("<font color='#01AAED'>"+res.extend.radioOption+"</font>"));
					layer.close(index);
				}
			}); 
	    }
	    return false;
  });
  
  
});

function radio_full(form){
	$.post("${path }/studentItem/getRadioItemOne",{},function(res){
		var radio = res.extend.radio;
		$("#radio_div").html("");
		if(radio == null ){
			//没有题目
			var dom = $("<legend>题库没有练习题了呦</legend>");
			$("#radio_div").append(dom);
			$("#sub_btn").hide();
		}else{
			$("#item_id").val(radio.uuid);
			var dom = $("<legend>考题编号：<font color='#FFB800'>"+radio.uuid+"</font>&nbsp;&nbsp;录入教师：<font color='#FFB800'>"+radio.name+"</font>"
					+"&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+radio.create_time+"</font></legend>"
					+"<div class='layui-field-box' style='margin:20px;'>"
					+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
					+"<div>"+radio.item_name+"</div>"
					+ radio_radioStr("option1",radio.option1)
					+ radio_radioStr("option2",radio.option2)
					+ radio_radioStr("option3",radio.option3)
					+ radio_radioStr("option4",radio.option4)
					+ radio_radioStr("option5",radio.option5)
					+ radio_radioStr("option6",radio.option6)
					+"</div>"
					+ radio_imgStr(radio.item_imageId,radio.fileName)
					+"</div></div>"
					); 
			$("#radio_div").append(dom);
			form.render('radio'); 
		}
	},"json");
}

//进行单选题选项的封装  返回组装好的dom节点
function radio_radioStr(index,option){
	if(option==null || option.length==0){
		return "";	
	}else{
		return "<input type='radio' name='user_option' value='"+index+"' title='"+option+"'><br>";
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

//填充剩余的单选题个数
function radioCountFull(){
	$.post("${path }/studentItem/getRadioSurplusCount",{},function(res){
		$("#sum_span").text("");
		$("#sum_span").append($("<font color='#2F4056'>"+res.extend.count+"</font>"));
	},"json");
}



function netxItem(){
	layui.use(['form','layer'],function(){
		var form = layui.form;
		var layer = layui.layer;
		var index = layer.load();
		radio_full(form)
		radioCountFull();
		$("#sub_btn").show();
		//相关提示信息置空
		$("#tof_span").text("--");
		$("#userO_span").text("--");
		$("#itemO_span").text("--");
		layer.close(index);
	});
}
</script>
</html>