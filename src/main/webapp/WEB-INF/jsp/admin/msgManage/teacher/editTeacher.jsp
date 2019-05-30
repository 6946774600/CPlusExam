<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教师信息修改页面</title>
</head>
<body class="layui-fluid">

<form class="layui-form" id="editForm" lay-filter="editForm" style="padding: 10px;">
  
	  
	  <div class="layui-form-item" >
	    <label class="layui-form-label">教师姓名：</label>
	    <div class="layui-input-block" >
	      <input style="width: 300px;"  type="text" name="name" id="name" required  lay-verify="required" placeholder="请输入教师姓名" autocomplete="off" class="layui-input" />
	    </div>
	  </div>
  
	  <div class="layui-form-item ">
	    <label class="layui-form-label">教师性别：</label>
	    <div class="layui-input-block">
	      <input type="radio" name="sex" value="0" title="男" checked>
	      <input type="radio" name="sex" value="1" title="女" >
	    </div>
	  </div>
	  
	  <div class="layui-form-item ">
	    <label class="layui-form-label">入教日期：</label>
	    <div class="layui-input-block" >
	    	<input style="width: 300px;"  type="text" class="layui-input" id="toSchool" name="toSchool">
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
	  
	  


	//为form表单赋值
 		actionFrom(form,loginName);


	
		
	  //表单提交
	  form.on('submit(subForm)', function(data){
		  $.ajax({
			    url : "${path }/adminTeacher/updateTeacher",
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
		var url = "${path }/adminTeacher/getTeacherById";
		$.post(url,{loginName : loginName},function(res){
			var teacher = res.extend.teacher;
			form.val("editForm", {
				  "name": teacher.name
				  ,"sex": $("input[name=sex][value=0]").attr("checked", teacher.sex == 0 ? true : false)
				  ,"sex": $("input[name=sex][value=1]").attr("checked", teacher.sex == 1 ? true : false)
				  ,"toSchool": teacher.toSchool
				  ,"phone" : teacher.phone
				  ,"email" : teacher.email
			}); 
			form.render('radio');
		},"json");

	}

</script>

</html>