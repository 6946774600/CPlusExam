<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在线考试系统</title>
</head>
<body class="layui-fluid">
<div>
	<fieldset class="layui-elem-field " style="width: 380px; height: 250px; margin-left: 35%; margin-top : 50px;">
	  <legend>简单登录，请先将就</legend>
	  <div class="layui-field-box" align="center" style="margin-top: 20px;">
	    <form id="login_form" class="layui-form layui-form-pane" >
	    	<div class="layui-form-item" style="left:40%;">
		    <label class="layui-form-label">登录名</label>
		    <div class="layui-input-block">
		      <input value="student" type="text" name="loginName" id="loginName" required  lay-verify="required" placeholder="请输入学号" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">密码</label>
		    <div class="layui-input-block">
		      <input value="student" type="password" name="password" id="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn layui-btn-radius layui-btn-normal" lay-submit lay-filter="formSub"><i class="layui-icon layui-icon-face-smile-b"></i>&nbsp;登录</button>
		    </div>
		  </div>
		</form>
	  </div>
	</fieldset>
</div>
</body>
<script type="text/javascript">
	 layui.use(['form','layer'], function(){
		  var form = layui.form;
		  var layer = layui.layer;

		   form.on('submit(formSub)', function(data){
			  var index = layer.load();
			  $.ajax({
				  url : "${path }/Login/userLogin",
				  method : "post",
				  dataType : "json",
				  data : $('#login_form').serialize(),
				  success : function(res){
					  if(res.success){
						  window.location.href="${path}/Login/goIndex";
						  layer.close(index);
				      }else{
					      layer.msg(res.msg);
					  }
				  }
			   });
			  return false;
		  }); 
	}); 
</script>
</html>