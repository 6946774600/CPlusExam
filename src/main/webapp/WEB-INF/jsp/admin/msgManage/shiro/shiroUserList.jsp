<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限用户页面</title>
</head>
<body class="layui-fluid">
<blockquote style="margin-top: 20px;" class="layui-elem-quote" id="shiroText"></blockquote>
<fieldset class="layui-elem-field">
	  <legend>用户列表</legend>
	  <div class="layui-field-box">
	  		<table id="userTab" lay-filter="userTab_filter"></table>
	  </div>
	</fieldset>
</body>
<script type="text/javascript">
layui.use('table', function(){
	  var table = layui.table;

	  $("#shiroText").text("权限为【"+"${shiro.shiroCnName }"+"】，用户信息统计。");
	  
	  table.render({
			id : 'lay_table',
		    elem: '#userTab' ,
		    defaultToolbar: ['print', 'exports'],
		    url: '${path}/adminShiro/getShiroUserList', //数据接口
		    loading : true,
		    cols: [[ //表头
			  {type : 'numbers',title : '序号'},
		      {field: 'shiroId', title: 'id',  hide : true,},
		      {field: 'shiroEnName', title: '角色编码', width:160 , align : 'center'},
		      {field: 'shiroCnName', title: '角色名称', width:160 , align : 'center'},
		      {field: 'userNum', title: '用户数量', width:160 , align : 'center' 
			      ,templet: function(d){
		         	 return "<span class='layui-badge layui-bg-blue' style='cursor:pointer' onclick='UserList("+d.shiroId+")'> "+d.userNum+"</span>"
		      	  }
		      },
		      {field: 'remark', title: '备注说明'},
		    ]],
	        parseData : function(res){
	             return {
	                code : res.code,
	                msg: res.msg ,
	                count:res.count, //数据总数
	                data : res.data,
	            };
	        },
		  }); 
	  
});



</script>
</html>