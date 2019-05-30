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
            var topWindow=window; // 定义最顶层页面  把当前页面赋值给topWindow
            if(self!=top){  // 判断当前页面是否是顶层页面
                while(topWindow.parent!=topWindow){  // 不断的循环 把当前页面的父页面与顶层页面对象比较 直到相同
                    topWindow=topWindow.parent;
                }
                topWindow.location.href="${path}/Login.jsp"; // 顶层页面跳转到 登录页面
            }
        });
    });
</script>
</html>