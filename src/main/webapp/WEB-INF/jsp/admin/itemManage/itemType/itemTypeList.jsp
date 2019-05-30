<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考题类型维护界面</title>
</head>
<body class="layui-fluid">

	<blockquote style="margin-top: 20px;" class="layui-elem-quote layui-anim layui-anim-up">本页面可以进行考题类型信息的维护。
	<font color="red">注：考题类型只支持启用和禁用功能，被禁用的考题类型，不会在定义试卷题型时出现。</font>
	<hr/><font color="#1E9FFF">&nbsp;&nbsp;--&nbsp;&nbsp;若要拓展考题类型，请致函开发者邮箱。</font></blockquote>


	<fieldset style="margin-top: 20px;" class="layui-elem-field  layui-anim layui-anim-fadein">
	  <legend>系统考题类型信息</legend>
	  <div class="layui-field-box">
	  		<table id="itemTypeTab" lay-filter="itemTypeTab_filter"></table>
	  		<script type="text/html" id="switchTpl">
 				 <input type="checkbox" id="showDemo" name="showDemo" value="{{d.item_id}}" lay-skin="switch" lay-text="启用|禁用" lay-filter="showDemo" {{ d.show_index == 1 ? 'checked' : '' }}>
			</script>
	  </div>
	</fieldset>
</body>

<script type="text/javascript">

layui.use(['table','form','layer'], function(){
	  var table = layui.table;
	  var form = layui.form;
	  var layer = layui.layer;
	  
	  table.render({
			id : 'lay_table',
		    elem: '#itemTypeTab' ,
		    url: '${path}/adminItem/getItemTypeList', //数据接口
		    loading : true,
		    even: true,
		    cols: [[ //表头
			  {type : 'numbers',title : '序号'},
		      {field: 'item_id', title: 'id',  hide : true,},
		      {field: 'itemType_name', title: '考题类型名称', width:160 , align : 'center'},
		      {field:'show_index', title:'是否启用',  templet: '#switchTpl', width:100 ,},
		      {field: 'def_score', title: '默认分值（点击修改）', width:170 , align : 'center' 
			      ,templet: function(d){
		         	 return "<span class='layui-badge layui-bg-green' style='cursor: pointer;' onclick='changeDefScore("+d.item_id+")'> "+d.def_score+"</span>"
		      	  }
		      },	
		      {field: 'itemCount', title: '考题数量', width:160 , align : 'center' 
			      ,templet: function(d){
		         	 return "<span class='layui-badge layui-bg-blue'> "+d.itemCount+"</span>"
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
	//监听修改是否启用操作
	  form.on('switch(showDemo)', function(obj){
	    var id = this.value;
	    var checked = obj.elem.checked==true?1:0;
	    //发送ajax请求  进行是否启用和进行的修改操作
	    var index = layer.load();
	    $.post("${path }/adminItem/updateItemShow",{id : id , show_index : checked} , function(res){
	    	layer.close(index); 
	    	if(res.success){
		    	if(obj.elem.checked)
			    	layer.msg('考题已修改为启用状态',{
			    		  icon: 1,
			    		  time: 1000 
			    		});
		    	else
		    		layer.msg('考题已修改禁用状态',{
			    		  icon: 0,
			    		  time: 1000 
			    		});
		    }else
	    		layer.msg('修改数据接口异常');
		},"json");
	  });
});


function changeDefScore(id){
	layui.use(['layer'],function(){
		var layer = layui.layer;

		layer.prompt({
			title: '请输入默认分值',
			},function(value, index, elem){
			  //var reg = /^[0-2]\d?$/;
			  var reg = /^(([1-9])|(1[0-9])|(2[0-9])|(3[0]))$/;
			  if (!reg.test(value)) {
				  layer.msg('请输入大于0小于30的正整数');
			  }else{
				  $.post("${path }/adminItem/updateDefScore",{id : id , def_score : value},function(res){
	  				  layer.close(index);
				      if(res.success){
					      layui.table.reload('lay_table');
					      layer.msg('数据修改成功');
					  }else
				    	  layer.msg('修改数据接口异常');
				  },"json");
			  }
		});
		
	});
}

</script>
</html>