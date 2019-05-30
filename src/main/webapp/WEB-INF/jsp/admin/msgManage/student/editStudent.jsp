<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息修改页面</title>
</head>
<body class="layui-fluid">

<form class="layui-form" id="editForm" lay-filter="editForm" style="padding: 10px;">
  
	  
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
	      <input type="text" name="loginName" id="loginName" value="${loginName }" class="layui-input" />
	      <button class="layui-btn" id="subForm" lay-filter="subForm" lay-submit="">提交</button>
	      <button id="recoverForm" type="button" onclick="recoverFormClick()" class="layui-btn layui-btn-primary">恢复</button>
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
	  var loginName = "${loginName }";
	  
	  


	//进行班级下拉框的初始化
	  $.post("${path}/adminGrade/getGradeSelect",{},function(res){
			var select = res.extend.select;
			select.forEach(function(item,index){
				var option	= $("<option value="+item.id+">"+item.name+"</option>");
				option.appendTo($("#grade"));
			})
			//为form表单赋值
	  		actionFrom(form,loginName);
			form.render('select');
	  },"json");


	
		
	  //表单提交
	  form.on('submit(subForm)', function(data){
		  $.ajax({
			    url : "${path }/adminStudent/updateStudent",
			    method : "post",
			    data : $('#editForm').serialize(),
			    dataType : "json",
			    success : function(res){
					//关闭当前弹出层
					layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
						layer.close(index);
						var index=parent.layer.getFrameIndex(window.name);
					    parent.layer.close(index);
					    layui.use('table', function(){
						    parent.layui.table.reload('lay_table',{page: {curr: "${now_page}"}});
						});
				    }); 
				}
			  });  
		  return false;
	  });


})


	function recoverFormClick(){
	layui.use('form', function(){
		  var form = layui.form;
		  var loginName = "${loginName }";
		  actionFrom(form,loginName);	
	});
	}
	//进行form表单的初始化
	function actionFrom(form,loginName){
		var url = "${path }/adminStudent/getStudentById";
		$.post(url,{loginName : loginName},function(res){
			var student = res.extend.student;
			form.val("editForm", {
				  "name": student.name
				  ,"sex": $("input[name=sex][value=0]").attr("checked", student.sex == 0 ? true : false)
				  ,"sex": $("input[name=sex][value=1]").attr("checked", student.sex == 1 ? true : false)
				  ,"toSchool": student.toSchool
				  ,"gradeId": student.gradeId
				  ,"phone" : student.phone
				  ,"email" : student.email
			}); 
			form.render('radio');
		},"json");

	}

</script>

</html>