<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考试时间添加页面</title>
</head>
<body class="layui-fluid">

 	 <form class="layui-form" action="" style="margin-top: 20px;" id="addForm">
 	 
 	 	<div class="layui-form-item">
      <label class="layui-form-label">试卷名称：</label>
      <div class="layui-input-block" style="width: 300px;">
        <input type="text" name="name" required  lay-verify="required" placeholder="请输入试卷名称" autocomplete="off" class="layui-input">
      </div>
    	</div>
 	 
 	 
 	 	<div class="layui-form-item">
      <label class="layui-form-label">开始时间：</label>
      <div class="layui-input-block" style="width: 300px;">
        <input type="text" class="layui-input" required placeholder="请选择考试开始时间" autocomplete="off"  lay-verify="required" id="start_time" name="start_time">
      </div>
    </div>
    
    <div class="layui-form-item">
      <label class="layui-form-label">结束时间：</label>
      <div class="layui-input-block" style="width: 300px;">
        <input type="text" class="layui-input" id="end_time" autocomplete="off"  placeholder="请选择考试结束时间" name="end_time" required  lay-verify="required">
      </div>
    </div>
 	 
 	 	<div class="layui-form-item " > 
        <label class="layui-form-label">试卷选择：</label>
    	<div class="layui-input-block" style="width: 300px;">
	      <select name="exam_modelid" id="examModel">
	      </select>
	    </div>
		</div>
 	 
 	 
 	 	<div class="layui-form-item">
	    <label class="layui-form-label">发布通告：</label>
	    <div class="layui-input-block">
	      <input type="checkbox" id="show_notice" name="show_notice" value="1" lay-skin="switch" lay-text="发布|不发布" lay-filter="switch_filter" checked>
	    </div>
	  </div>
 	 
 	 	<div class="layui-form-item">
      <label class="layui-form-label">通告标题：</label>
      <div class="layui-input-block" style="width: 300px;">
        <input type="text" name="notice_title" required  lay-verify="required" placeholder="请输入通告标题" autocomplete="off" class="layui-input">
      </div>
    	</div>
 	 
 	 
 	 	<div class="layui-form-item layui-form-text">
    <label class="layui-form-label">通告内容：</label>
    <div class="layui-input-block" style="width: 300px;">
      <textarea name="notice_msg" required  lay-verify="required" placeholder="请输入通告内容" class="layui-textarea"></textarea>
    </div>
    
    
    <div hidden="true" class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" id="subForm" lay-filter="subForm" lay-submit="">提交</button>
	      <button id="resetForm" type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
    </div>
  </div>
 	 
</form>
	  

</body>
<script type="text/javascript">
layui.use(['form','laydate'],function(){
	var form = layui.form;
	var laydate = layui.laydate;
	
	
	
	laydate.render({
	    elem: '#start_time' , //指定元素
	    type : 'datetime',
	    format : 'yyyy-MM-dd HH:mm:ss' ,
	    theme: 'grid',
	    
	});
	
	
	laydate.render({
	    elem: '#end_time' , //指定元素
    	type : 'datetime',
	    format : 'yyyy-MM-dd HH:mm:ss' ,
	    theme: 'grid',
	});
	
	
	//初始化下拉选择框
	$.post("${path }/adminExamTime/getItemModelTree",{},function(res){
		var tree = res.extend.tree;
		tree.forEach(function(item,index){
			var option	= $("<option value="+item.id+">"+item.name+"</option>");
			option.appendTo($("#examModel"));
		});
		form.render('select');
	},"json")
	
	
	//表单提交
	  form.on('submit(subForm)', function(data){
		  
		  var start_time = $("#start_time").val();
		  var end_time = $("#end_time").val();
		  start_time = new Date(start_time.replace(/-/g, "/"));
		  end_time = new Date(end_time.replace(/-/g, "/"));
		  var usedTime = end_time - start_time;
		 if(usedTime<5400000 || usedTime>10800000){
			  layer.msg("考试时间必须在90分钟~270分钟之间");
		  }else{
			  $.ajax({
			    url : "${path }/adminExamTime/addExamTime",
			    method : "post",
			    data : $('#addForm').serialize(),
			    dataType : "json",
			    success : function(res){
					layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
						layer.close(index);
						var index=parent.layer.getFrameIndex(window.name);
					    parent.layer.close(index);
					    layui.use('table', function(){
						    parent.layui.table.reload('lay_table',{page: {curr: "${endPage}"}});
						});
				    }); 
				}
			  });  
		  }
		  return false;
	  });
	
	
	form.on('switch(switch_filter)', function(data){
		  if(data.value==1){
			  $("#show_notice").val("0");
		  }else{
			  $("#show_notice").val("1");
		  }
	}); 
	
});
</script>
</html>