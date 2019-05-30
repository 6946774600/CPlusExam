<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>填空题练习页面</title>
</head>
<body class="layui-container">
<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 40px;">填空题练习</blockquote>
<div class="layui-row layui-col-space20" style="margin-top: 20px;">
	<div class="layui-col-xs9 layui-col-sm9 layui-col-md9">
		
		 		
	<form class="layui-form" id="gap_from" lay-filter="check_filter">
    	<!-- 填空题内容  动态填充 -->
    	<fieldset class='layui-elem-field' id="gap_div">
    	
    		
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
	  gap_full(form);
		//考题剩余数量填充
	  gapCountFull();
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
	    	var index = layer.load();
		    $.ajax({
			    url : "${path }/studentItem/addGapExercise",
			    method : "post",
			    data : $('#gap_from').serialize(),
			    dataType : "json",
			    success : function(res){
					$("#sub_btn").hide();
					$("#tof_span").text("");
					$("#userO_span").text("");
					$("#itemO_span").text("");
					$("#tof_span").append($(res.extend.tof));
					$("#userO_span").append($("<font color='#01AAED'><br>"+res.extend.userOption+"</font>"));
					$("#itemO_span").append($("<font color='#01AAED'><br>"+res.extend.checkOption+"</font>")); 
					layer.close(index);
				}
			});  
		    return false;
	  }); 
	  
	  
	});

function gap_full(form){
	$.post("${path }/studentItem/getGapItemOne",{},function(res){
			var gap = res.extend.gap;
			$("#gap_div").html("");
			if(gap == null ){
				//没有题目
				var dom = $("<legend>题库没有练习题了呦</legend>");
				$("#gap_div").append(dom);
				$("#sub_btn").hide();
			}else{
				$("#item_id").val(gap.uuid);
				var dom = $("<legend>考题编号：<font color='#FFB800'>"+gap.uuid+"</font>&nbsp;&nbsp;录入教师：<font color='#FFB800'>"+gap.name+"</font>"
						+"&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+gap.create_time+"</font></legend>"
						+"<div class='layui-field-box' style='margin:20px;'>"
						+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
						+"<div style='margin-left:40px;'>问题："+gap.item_name+"</div>"
						+"<div style='margin-top: 25px; margin-left : 0px;'>"
						+gap_optionFull()
						+"<div>"
						+ gap_imgStr(gap.item_imageId,gap.fileName)
						+"</div></div>"
						);
				$("#gap_div").append(dom);
			}
	},"json");
}

function gap_optionFull(){
    return '<div class="layui-form-item"><label class="layui-form-label">正确答案</label><div class="layui-input-block">'
      	 	+'<input type="text" name="user_option" required  lay-verify="required" placeholder="多个答案之间请用&nbsp;-&nbsp;隔开" autocomplete="off" class="layui-input"></div></div>';
}

function gap_imgStr(imageId,fileMsg){
	if(imageId == -1)
		return "";
	else{
		return "<img class='layui-col-xs3 layui-col-sm3 layui-col-md3' alt='图片文件缺失' src='/CPlusExamImage/"+fileMsg+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
}

	//填充剩余的单选题个数
	function gapCountFull(){
		$.post("${path }/studentItem/getGapSurplusCount",{},function(res){
			$("#sum_span").text("");
			$("#sum_span").append($("<font color='#2F4056'>"+res.extend.count+"</font>"));
		},"json");
	}



	function netxItem(){
		layui.use(['form','layer'],function(){
			var form = layui.form;
			var layer = layui.layer;
			var index = layer.load();
			gap_full(form)
			gapCountFull();
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