<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>模拟考卷添加页面</title>
</head>
<body>

<div style="margin-top: 20px;" id="testExamDiv">
	<form class="layui-form" id="testExamForm">
	  <div class="layui-form-item">
	    <label class="layui-form-label">考试名称：</label>
	    <div class="layui-input-block" style="width: 320px;">
	      <input type="text" name="exam_name" required  lay-verify="required" placeholder="请输入模拟考试名称" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">考试时长：</label>
	    <div class="layui-input-block" style="width: 320px;">
	    	<input type="text" name="exam_time" class="layui-input" id="exam_time" autocomplete="off" placeholder="请选择考试时长">
	    </div>
	  </div>
	  
	  <div class="layui-form-item" style="margin-top: 20px;">
	    <div class="layui-input-block">
		  <input id="testExamId" type="hidden" name="uuid" value="-1" >
	      <button style="margin-left:240px;" class="layui-btn"  lay-submit lay-filter="testExamSub">下一步</button>
	    </div>
	  </div>
	  
	</form>

</div> 


<div style="margin-top: 20px;" id="testExamItemDiv" hidden="true">

	<button  class="layui-btn layui-btn-fluid"  onclick="returnPage()">上一步</button>
	<button hidden="true" id="delTestExam" onclick="deleteTestExam()">点击关闭当前弹出层按钮  ， 要进行已录入的模拟考试基本信息的删除操作</button>

	<form class="layui-form" id="testExamItemForm">
		
		<fieldset class="layui-elem-field layui-field-title">
		  <legend>单选题&nbsp;&nbsp;考题分数：<font color='#FFB800'>${radio_defS }</font>&nbsp;&nbsp;题库数量：<font color='#FFB800'>${radio_itemC }</font></legend>
		  <div class="layui-field-box">
		  
		    	<div class="layui-form-item">
			    <label class="layui-form-label">是否选用：</label>
			    <div class="layui-input-block">
			      <input type="hidden" name ="item_score" value="${radio_defS }">
			      <input type="hidden" name ="item_type" value="1">
			      <input type="checkbox" id="radio_switch" lay-skin="switch" lay-filter="radio_switch" value="1" lay-text="选用|不选用" checked>
			    </div>
			    </div>
			    
			    
			    <div class="layui-form-item" id="radio_div">
 
			  
				  <div class="layui-inline">
				    <label class="layui-form-label">考题数量</label>
				    <div class="layui-input-inline" style="width: 100px;">
				      <input type="text" id="radio_count" name="item_count" lay-verify="number" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-inline">
				    <label class="layui-form-label">考题顺序</label>
				    <div class="layui-input-inline" style="width: 100px;">
				      <input type="text" id="radio_show" name="show_index" lay-verify="number" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				</div>
			  
		  </div>
		</fieldset>
		
		<fieldset class="layui-elem-field layui-field-title">
		  <legend>多选题&nbsp;&nbsp;考题分数：<font color='#FFB800'>${check_defS }</font>&nbsp;&nbsp;题库数量：<font color='#FFB800'>${check_itemC }</font></legend>
		  <div class="layui-field-box">
		  
		    	<div class="layui-form-item">
			    <label class="layui-form-label">是否选用：</label>
			    <div class="layui-input-block">
			      <input type="hidden" name ="item_score" value="${check_defS }">
			      <input type="hidden" name ="item_type" value="2">
			      <input type="checkbox" id="check_switch" lay-skin="switch" lay-filter="check_switch" value="1" lay-text="选用|不选用" checked>
			    </div>
			    </div>
			    
			    
			    <div class="layui-form-item" id="check_div">
 
			  
				  <div class="layui-inline">
				    <label class="layui-form-label">考题数量</label>
				    <div class="layui-input-inline" style="width: 100px;">
				      <input type="text" id="check_count" name="item_count" lay-verify="number" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-inline">
				    <label class="layui-form-label">考题顺序</label>
				    <div class="layui-input-inline" style="width: 100px;">
				      <input type="text" id="check_show" name="show_index"  lay-verify="number" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				</div>
			  
		  </div>
		</fieldset>
		
		<fieldset class="layui-elem-field layui-field-title">
		  <legend>填空题&nbsp;&nbsp;考题分数：<font color='#FFB800'>${gap_defS }</font>&nbsp;&nbsp;题库数量：<font color='#FFB800'>${gap_itemC }</font></legend>
		  <div class="layui-field-box">
		  
		    	<div class="layui-form-item">
			    <label class="layui-form-label">是否选用：</label>
			    <div class="layui-input-block">
			      <input type="hidden" name ="item_score" value="${gap_defS }">
			      <input type="hidden" name ="item_type" value="3">
			      <input type="checkbox" id="gap_switch"  lay-skin="switch" lay-filter="gap_switch" value="1" lay-text="选用|不选用" checked>
			    </div>
			    </div>
			    
			    
			    <div class="layui-form-item" id="gap_div">
 
			  
				  <div class="layui-inline">
				    <label class="layui-form-label">考题数量</label>
				    <div class="layui-input-inline" style="width: 100px;">
				      <input type="text" id="gap_count" name="item_count"  lay-verify="number" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-inline">
				    <label class="layui-form-label">考题顺序</label>
				    <div class="layui-input-inline" style="width: 100px;">
				      <input type="text" id="gap_show" name="show_index"  lay-verify="number" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				</div>
			  
		  </div>
		</fieldset>
		
		<fieldset class="layui-elem-field layui-field-title">
		  <legend>判断题&nbsp;&nbsp;考题分数：<font color='#FFB800'>${judge_defS }</font>&nbsp;&nbsp;题库数量：<font color='#FFB800'>${judge_itemC }</font></legend>
		  <div class="layui-field-box">
		  
		    	<div class="layui-form-item">
			    <label class="layui-form-label">是否选用：</label>
			    <div class="layui-input-block">
			      <input type="hidden" name ="item_score" value="${judge_defS }">
			      <input type="hidden" name ="item_type" value="4">
			      <input type="checkbox" id="judge_switch" lay-skin="switch" lay-filter="judge_switch" value="1" lay-text="选用|不选用" checked>
			    </div>
			    </div>
			    
			    
			    <div class="layui-form-item" id="judge_div">
 
			  
				  <div class="layui-inline">
				    <label class="layui-form-label">考题数量</label>
				    <div class="layui-input-inline" style="width: 100px;">
				      <input type="text" id="judge_count" name="item_count" lay-verify="number" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-inline">
				    <label class="layui-form-label">考题顺序</label>
				    <div class="layui-input-inline" style="width: 100px;">
				      <input type="text" id="judge_show" name="show_index" lay-verify="number" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				</div>
			  
		  </div>
		</fieldset>
	
		<div class="layui-form-item" style="margin-top: 20px;">
	    <div class="layui-input-block">
	      <button style="margin-left:240px;" class="layui-btn" lay-submit lay-filter="testExamItemSub">提交</button>
	    </div>
	  </div>
	
	</form>

</div>
</body>
<script type="text/javascript">
layui.use(['laydate','form','layer'], function(){
	  var laydate = layui.laydate;
	  var form = layui.form;
	  var layer = layui.layer;
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#exam_time' , //指定元素
	    type : 'time',
	    min: '00:30:00',
	    max: '02:30:00',
	    btns: ['clear', 'confirm']
	  });
	  
	  
	  
	//监听模拟考卷基本信息提交
	  form.on('submit(testExamSub)', function(data){
		  $("#testExamDiv").hide();
		  $("#testExamItemDiv").show();
		  if($("#testExamId").val()==-1){
			  //说明第一次点击下一步  进行基本信息添加
			  $.ajax({
				  url : '${path }/studentTestExam/addTestExamMsg',
				  method : "post",
				  data : $("#testExamForm").serialize(),
				  dataType : "json",
				  success : function(res){
					  $("#testExamId").val(res.extend.uuid);
				  }
			  });
		  }else{
			  //不是第一次点击下一步  进行基本信息修改
			  $.ajax({
				  url : '${path }/studentTestExam/updateTestExamMsg',
				  method : "post",
				  data : $("#testExamForm").serialize(),
				  dataType : "json",
				  success : function(res){
				  }
			  });
		  }
	      return false;
	  });
	  //监听模拟考试考题信息定义页面提交按钮
	  form.on('submit(testExamItemSub)', function(data){
		  //将表单内容进行json数组式的格式化   传入到后台  list数组中
		  var formData=$("#testExamItemForm").serializeArray();
			var jsonData1 = {};
			// 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
	          $(formData).each(function () {
	              if (jsonData1[this.name]) {
	                  if ($.isArray(jsonData1[this.name])) {
	                      jsonData1[this.name].push(this.value);
	                  } else {
	                      jsonData1[this.name] = [jsonData1[this.name], this.value];
	                  }
	              } else {
	                  jsonData1[this.name] = this.value;
	              }
	          });
			// 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
	        var vCount = 0;
	        // 计算json内部的数组最大长度
	        for(var item in jsonData1){
	            var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
	            vCount = (tmp > vCount) ? tmp : vCount;
	        }
	        var json;
	        if(vCount > 1) {
		        var jsonData2 = new Array();
	            for(var i = 0; i < vCount; i++){
	                var jsonObj = {};
	                for(var item in jsonData1) {
		                jsonObj[item] = jsonData1[item][i];
	                }
	                jsonData2.push(jsonObj);
	            }
	            json = JSON.stringify(jsonData2);
	        }else{
	        	json = "[" + JSON.stringify(jsonData1) + "]";
	        }
	        if($("#radio_count").val() == 0 && $("#check_count").val() == 0 && $("#gap_count").val() == 0 && $("#judge_count").val() == 0 ){
	        	layer.msg("你这样选择，考啥啊！");
	        }else{
	        	$.ajax({
	    			url : '${path }/studentTestExam/addTestExamItems/'+$("#testExamId").val(),
	    			method : 'post',
	    			data : json ,
	    			dataType : 'json',
	    			contentType : 'application/json;charset=utf-8',
	    			success : function (res){
	    				$("#testExamId").val("-1"); //添加成功之后，点击关闭按钮  就不用在删除基本信息了  这是的标识改为-1
	    				layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
	    					layer.close(index);
	    					var index=parent.layer.getFrameIndex(window.name);
						    parent.layer.close(index);
						    layui.use('table', function(){
							    parent.layui.table.reload('testExam',{page: {curr: "${endPage}"}});
							}); 
	    			    }); 
	    			}
	    		}); 
	        }
	        return false;
        	
	      
	  });
	
	
	  form.on('switch(radio_switch)', function(data){
	  	 if(data.value==0){
	  		 $("#radio_switch").val("1");
	  		 $("#radio_div").show();
	  		 $("#radio_count").attr("lay-verify","number");
	  		 $("#radio_show").attr("lay-verify","number");
	  	 }else{
	  		$("#radio_switch").val("0");
	  		//隐藏按钮下方的input   并且取消输入框校验
	  		$("#radio_div").hide();
	  		 $("#radio_count").attr("lay-verify","");
	  		 $("#radio_show").attr("lay-verify","");
	  		 //将输入框里面的内容置空 这里置为零
	  		 $("#radio_count").val("0");
	  		 $("#radio_show").val("0");
	  	 }
	  }); 
	  form.on('switch(check_switch)', function(data){
		  if(data.value==0){
			 $("#check_switch").val("1");
			 $("#check_div").show();
			 $("#check_count").attr("lay-verify","number");
	  		 $("#check_show").attr("lay-verify","number");
		  }else{
		  	 $("#check_switch").val("0");
		  	 $("#check_div").hide();
		  	$("#check_count").attr("lay-verify","");
	  		 $("#check_show").attr("lay-verify","");
	  		$("#check_count").val("0");
	  		 $("#check_show").val("0");
		  }
	  });
	  form.on('switch(gap_switch)', function(data){
		  if(data.value==0){
			 $("#gap_switch").val("1");
			 $("#gap_div").show();
			 $("#gap_count").attr("lay-verify","number");
	  		 $("#gap_show").attr("lay-verify","number");
		  }else{
			 $("#gap_switch").val("0");
			 $("#gap_div").hide();
			 $("#gap_count").attr("lay-verify","");
	  		 $("#gap_show").attr("lay-verify","");
	  		 $("#gap_count").val("0");
	  		 $("#gap_show").val("0");
		  }
	  }); 
	  form.on('switch(judge_switch)', function(data){
		  if(data.value==0){
			 $("#judge_switch").val("1");
			 $("#judge_div").show();
			 $("#judge_count").attr("lay-verify","number");
	  		 $("#judge_show").attr("lay-verify","number");
		  }else{
			 $("#judge_switch").val("0");
			 $("#judge_div").hide();
			 $("#judge_count").attr("lay-verify","");
	  		 $("#judge_show").attr("lay-verify","");
	  		$("#judge_count").val("0");
	  		 $("#judge_show").val("0");
		  }
	  }); 
	  
	  //习题数量监听
	  $("#radio_count").change(function(){
		  var count = ${radio_itemC };
		  if($("#radio_count").val()>count){
			  layer.msg("考题数量大于题库数量啦！");
			  $("#radio_count").val("");
		  }
	  });
	  $("#check_count").change(function(){
		  var count = ${check_itemC };
		  if($("#check_count").val()>count){
			  layer.msg("考题数量大于题库数量啦！");
			  $("#check_count").val("");
		  }
	  });
	  $("#gap_count").change(function(){
		  var count = ${gap_itemC };
		  if($("#gap_count").val()>count){
			  layer.msg("考题数量大于题库数量啦！");
			  $("#gap_count").val("");
		  }
	  });
	  $("#judge_count").change(function(){
		  var count = ${judge_itemC };
		  if($("#judge_count").val()>count){
			  layer.msg("考题数量大于题库数量啦！");
			  $("#judge_count").val("");
		  }
	  });
	  
});

//点击上一步按钮
function returnPage(){
	$("#testExamDiv").show();
	$("#testExamItemDiv").hide();
}
//点击删除按钮
function deleteTestExam(){
	if($("#testExamId").val()==-1){
		//不用删除
	}else{
		//进行删除操作
		$.ajax({
			  url : '${path }/studentTestExam/deleteTestExam',
			  method : "post",
			  data : 'id=' + $("#testExamId").val() ,
			  dataType : "json",
			  success : function(res){
			  }
		  });
	}
}




</script>
</html>