<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>非考试时间段提示</title>
</head>
<body>
</body>
<script type="text/javascript">
layui.use('layer', function(){
	var layer = layui.layer;
	layer.alert("非考试时间段！",{icon: 0,closeBtn : 0 , title : '系统提示'},function(index){
		 window.history.go(-1);
	});
}); 
</script>
</html>