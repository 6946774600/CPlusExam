<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link href="${path }/static/css/login/style.css" rel="stylesheet" type="text/css" />
<meta charset="UTF-8">
<title>在线考试系统</title>
</head>
<body class="layui-fluid" >
<div id="web">
<p style="height:120px;"></p>
<p align="center"><img src="${path }/static/image/login/login.png" /></p>
<p style="height:50px;"></p>
<div class="login">
   <div class="banner">
   <iframe id="frame_banner" src="${path }/banner.jsp" height="218" width="100%"  allowtransparency="true" title="test"  scrolling="no" frameborder="0"></iframe>
   </div>
   <div class="logmain">
     <h1>&nbsp;</h1>
     <form id="login_form" class="layui-form layui-form-pane">
      <div class="logdv" >
         <span class="logzi">账 号：</span>
        <input class="ipt" type="text" name="loginName" id="loginName" required  lay-verify="required" placeholder="请输入学号" autocomplete="off">
      </div>
      <div class="logdv">
      <span class="logzi">密 码：</span>
        <input class="ipt" type="password" name="password" id="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off">
      </div>
      <div class="logdv">
        <p class="logzi">&nbsp;</p>
      </div>
      <div class="logdv" style="height:40px;">
        <p class="logzi">&nbsp;</p>    
        <button class="btnbg layui-btn layui-btn-normal" lay-submit lay-filter="formSub">点击登录</button>
      </div>
      <div>
        <a onclick="findPsd()" class="more">忘记密码</a>
      </div>
      </form>
   </div>
</div>
<p style="height:100px;"></p>
<p align="center">在线考试系统</p>
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
				      }else{
					      layer.msg(res.msg);
					  }
					  layer.close(index);
				  }
			   });
			  return false;
		  }); 
		   
		   
		  window.findPsd = function(){
			  layer.msg("暂未开发，敬请期待");
		  }
		   
	}); 
</script>
</html>