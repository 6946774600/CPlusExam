<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色信息维护</title>
</head>
<body class="layui-fluid">

	<blockquote style="margin-top: 20px;" class="layui-elem-quote layui-anim layui-anim-up">系统角色信息为系统默认，不提供新增与相关字段修改操作。</blockquote>


	<fieldset style="margin-top: 20px;" class="layui-elem-field  layui-anim layui-anim-scale">
	  <legend>系统角色信息</legend>
	  <div class="layui-field-box">
	  		<table id="shiroTab" lay-filter="shiroTab_filter"></table>
	  </div>
	</fieldset>
</body>
<script type="text/javascript">
layui.use('table', function(){
	  var table = layui.table;
	  table.render({
			//title : "菜单类型表",
			id : 'lay_table',
		    elem: '#shiroTab' ,
		    defaultToolbar: ['print', 'exports'],
		    url: '${path}/adminShiro/getShiroList', //数据接口
		    loading : true,
		    cols: [[ //表头
			  {type : 'numbers',title : '序号'},
		      {field: 'shiroId', title: 'id',  hide : true,},
		      {field: 'shiroEnName', title: '角色编码', width:160 , align : 'center'},
		      {field: 'shiroCnName', title: '角色名称', width:160 , align : 'center'},
		      {field: 'userNum', title: '用户数量', width:160 , align : 'center' 
			      ,templet: function(d){
		         	 return "<span class='layui-badge layui-bg-blue'> "+d.userNum+"</span>"
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