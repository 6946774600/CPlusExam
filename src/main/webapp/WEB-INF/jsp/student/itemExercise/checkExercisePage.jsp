<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>多选题练习页面</title>
</head>
<body class="layui-container">
<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 40px;">多选题练习</blockquote>
<div class="layui-row layui-col-space20" style="margin-top: 20px;">
	<div class="layui-col-xs9 layui-col-sm9 layui-col-md9">
		
		 		
	<form class="layui-form" id="check_form" lay-filter="check_filter">
    	<!-- 填空题内容  动态填充 -->
    	<fieldset class='layui-elem-field' id="check_div">
    	
    		
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
	  check_full(form);
		//考题剩余数量填充
	  checkCountFull();
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
		    var temp = $('input:checkbox:checked').length;
		    if(temp==0){
		    	layer.msg("请选择正确答案");
		    }else if (temp==1){
		    	layer.msg("请选择至少两个或以上正确答案");
		    }else{
		    	var index = layer.load();
		    	$.ajax({
				    url : "${path }/studentItem/addCheckExercise",
				    method : "post",
				    data : $('#check_form').serialize(),
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
		    }
		    return false;
	  }); 
	  
	  
	});

function check_full(form){
	$.post("${path }/studentItem/getCheckItemOne",{},function(res){
			var check = res.extend.check;
			$("#check_div").html("");
			if(check == null ){
				//没有题目
				var dom = $("<legend>题库没有练习题了呦</legend>");
				$("#check_div").append(dom);
				$("#sub_btn").hide();
			}else{
				$("#item_id").val(check.uuid);
				var dom = $("<legend>考题编号：<font color='#FFB800'>"+check.uuid+"</font>&nbsp;&nbsp;录入教师：<font color='#FFB800'>"+check.name+"</font>"
						+"&nbsp;&nbsp;录入时间：<font color='#FFB800'>"+check.create_time+"</font></legend>"
						+"<div class='layui-field-box' style='margin:20px;'>"
						+"<div class='layui-row'><div class='layui-col-xs9 layui-col-sm9 layui-col-md9'>"
						+"<div>"+check.item_name+"</div>"
						+ check_radioStr("option1",check.option1)
						+ check_radioStr("option2",check.option2)
						+ check_radioStr("option3",check.option3)
						+ check_radioStr("option4",check.option4)
						+ check_radioStr("option5",check.option5)
						+ check_radioStr("option6",check.option6)
						+"</div>"
						+ check_imgStr(check.item_imageId,check.fileName)
						+"</div></div>"
						);
				$("#check_div").append(dom);
				form.render('checkbox','check_filter');
			}
	},"json");
}

//进行多选题选项的封装  返回组装好的dom节点
function check_radioStr(index,option){
	if(option==null || option.length==0){
		return "";	
	}else{
		return "<div style='margin-top:20px;'></div><input type='checkbox' style='margin-top:20px;' lay-skin='primary' name='user_option' value='"+index+"' title='"+option+"'><br>";
	}
}

//进行多选题图片节点判断  并返回封装好的dom节点
function check_imgStr(imageId,fileMsg){
	if(imageId == -1)
		return "";
	else{
		return "<img class='layui-col-xs3 layui-col-sm3 layui-col-md3' alt='图片文件缺失' src='/CPlusExamImage/"+fileMsg+"' width='280' height='280' style='border:solid 1px black;'>";	
	}
} 

	//填充剩余的单选题个数
	function checkCountFull(){
		$.post("${path }/studentItem/getCheckSurplusCount",{},function(res){
			$("#sum_span").text("");
			$("#sum_span").append($("<font color='#2F4056'>"+res.extend.count+"</font>"));
		},"json");
	}



	function netxItem(){
		layui.use(['form','layer'],function(){
			var form = layui.form;
			var layer = layui.layer;
			var index = layer.load();
			check_full(form)
			checkCountFull();
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