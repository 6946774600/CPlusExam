<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息添加页面</title>
</head>
<body class="layui-container">

<form class="layui-form" id="addForm" style="padding: 10px; margin-top: 15px;">

	  <div class="layui-form-item">
	    <label class="layui-form-label">学生学号：</label>
	    <div class="layui-input-block" >
	      <input style="width: 300px;"  type="text" name="loginName" id="loginName" required  lay-verify="required" placeholder="示例：201912010101" autocomplete="off" class="layui-input" />
	    </div>
	  </div>
	  
	  <div class="layui-form-item" >
	    <label class="layui-form-label">学生姓名：</label>
	    <div class="layui-input-block" >
	      <input style="width: 300px;"  type="text" name="name" id="name" required  lay-verify="required" placeholder="请输入学生姓名" autocomplete="off" class="layui-input" />
	    </div>
	  </div>
  
	  <div class="layui-form-item ">
	    <label class="layui-form-label">学生性别：</label>
	    <div class="layui-input-block">
	      <input type="radio" name="sex" value="0" title="男" checked>
	      <input type="radio" name="sex" value="1" title="女" >
	    </div>
	  </div>
	  
	  <div class="layui-form-item ">
	    <label class="layui-form-label">入学日期：</label>
	    <div class="layui-input-block" >
	    	<input style="width: 300px;"  type="text" class="layui-input" id="toSchool" name="toSchool">
	    </div>
	  </div>
  
  
	  <div class="layui-form-item">
	    <label class="layui-form-label">所属班级：</label>
	    <div class="layui-input-block" style="width: 300px;">
	    	<select  name="gradeId" id="grade">
			</select>
	    </div>
	  </div>
  
  
	  <div class="layui-form-item ">
	    <label class="layui-form-label">联系方式：</label>
	    <div class="layui-input-block" >
	      <input style="width: 300px;"  type="text" name="phone" id="pnohe" required  lay-verify="phone" placeholder="请输入联系方式" autocomplete="off" class="layui-input" />
	    </div>
	  </div>
	  
	  <div class="layui-form-item " >
	    <label class="layui-form-label">邮箱地址：</label>
	    <div class="layui-input-block" >
	      <input style="width: 300px;"  type="text" name="email" id="email" required  lay-verify="email" placeholder="请输入邮箱地址" autocomplete="off" class="layui-input" />
	    </div>
	  </div>
  
  <div hidden="true" class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" id="subForm" lay-filter="subForm" lay-submit="">提交</button>
      <button id="resetForm" type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>

</body>
<script type="text/javascript">
//日期选择框初始化   
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  laydate.render({
	    elem: '#toSchool' //指定元素
	  });


});


layui.use(['form','layer'], function(){
	  var form = layui.form;
	  var layer = layui.layer;
	  var uuid=-1;   //用来标识用户输入的学生级编号是否唯一   -1标识不唯一

	//进行班级下拉框的初始化
	  $.post("${path}/adminGrade/getGradeSelect",{},function(res){
			var select = res.extend.select;
			select.forEach(function(item,index){
				var option	= $("<option value="+item.id+">"+item.name+"</option>");
				option.appendTo($("#grade"));
			})
			form.render('select');
	  },"json");
	  
	  //表单提交
	  form.on('submit(subForm)', function(data){
		  if(uuid==-1){
			  layer.msg("该学生学号已存在，请重新输入");
		  }else{
			  $.ajax({
			    url : "${path }/adminStudent/addStudent",
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


	$("#loginName").on('change',function(){
		$.post("${path}/adminStudent/studentIdOnaly",{loginName:$("#loginName").val()},function(res){
			if(res.success)
				uuid = 0;
			else{
				uuid  = -1;
				layer.msg("该学生学号已存在，请重新输入",{time: 2000});
			}
		},"json");
	})
	
})

</script>

</html>