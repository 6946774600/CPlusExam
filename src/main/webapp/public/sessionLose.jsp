<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录超时提示页面</title>
</head>
<body>

</body>
<script type="text/javascript">
layui.use('layer', function(){
	var layer = layui.layer;
	layer.alert("登录超时，请重新登录！",{icon: 0,closeBtn : 0 , title : '系统提示'},function(index){
		window.location.href="${path}/Login.jsp";
	});
}); 
</script>
</html>