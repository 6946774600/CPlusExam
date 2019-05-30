<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>模拟考试页面</title>
</head>
<body class="layui-container">

<blockquote style="margin-top: 40px;" class="layui-elem-quote layui-anim layui-anim-up">模拟考试页面</blockquote>

<div class="layui-row" style="margin-top: 20px;" >
	<div class="layui-col-md-offset4 layui-col-xs4 layui-col-sm4 layui-col-md4">
		<button class="layui-btn layui-btn-fluid " onclick="addTestExam()">点击按钮，创建模拟考卷。</button>
	</div>
</div >

<table style="margin-top: 20px;" id="testExam" lay-filter="testExam"></table>
</body>
<script type="text/javascript">
var now_page=1;


layui.use(['table'], function(){
	  var table = layui.table;
	  
	  //第一个实例
	  table.render({
	    elem: '#testExam' ,
	    url: '${path }/studentTestExam/getTestExamTabMsg' , //数据接口
	    page: true , //开启分页
	    limits : [10,20,30] ,
	    loading : true,
	    cols: [[ //表头
	      {type : 'numbers' , title: '序号', align : 'center'},
	      {field: 'exam_name', title: '模拟考试名称', align : 'center'},
	      {field: 'create_time', title: '创建时间', align : 'center'},
	      {field: 'exam_time', title: '剩余考试时长', align : 'center'},
	      {field: 'uuid', title: '试卷信息', align : 'center' , templet: function(d){
	          return '<span style="cursor: pointer;" class="layui-badge layui-bg-blue" onclick="javascript:showExamModel('+d.uuid+',\''+d.exam_name+'\','+d.count_score+')">试卷详情定义</span>';
	      }},
	      {field: 'count_score', title: '试题总分', align : 'center' ,},
	      {field: 'examtest_type', title: '考卷状态', align : 'center' , templet: function(d){
	    	  if(d.examtest_type==0)
		    	  return '<span class="layui-badge layui-bg-red">未开始</span>';
	    	  else if(d.examtest_type==1)
		    	  return '<span class="layui-badge layui-bg-green">未完成</span>';
	    	  else if(d.examtest_type==2)
	    		  return '<span class="layui-badge layui-bg-blue">已完成</span>';
	    	  else
	    		  return '无法解析当前状态';
	      }},
	      {field: 'exam_score', title: '考试分数', align : 'center' , templet: function(d){
	    	  if(d.examtest_type==2){
	    		  //考完了
	    		  return d.exam_score;
	    	  }else{
	    		  return "--";
	    	  }
	      }},
	      { title: '考试详情', align : 'center' ,templet: function(d){
	    	  if(d.examtest_type==0)
		    	  return '<span class="layui-badge layui-bg-red">考卷未作答</span>';
	    	  else if(d.examtest_type==1)
		    	  return '<span class="layui-badge layui-bg-green">考卷未提交</span>';
	    	  else if(d.examtest_type==2)
	    		  return '<span style="cursor: pointer;" onclick="showTestExamPaperMsg('+d.uuid+')" class="layui-badge layui-bg-blue">考试详情</span>';
	    	  else
	    		  return '无法解析当前状态';
	      }},
	      {field: 'uuid', title: '操作', align : 'center' , templet: function(d){
	    	  if(d.examtest_type==0)
		    	  return '<a href="${path }/studentTestExam/toTestExamPage/'+d.uuid+'"><span style="cursor: pointer;" class="layui-badge layui-bg-red">进入考试</span></a>';
	    	  else if(d.examtest_type==1)
		    	  return '<a href="${path }/studentTestExam/toTestExamPage/'+d.uuid+'"><span style="cursor: pointer;" class="layui-badge layui-bg-orange">继续考试</span></a>';
	    	  else if(d.examtest_type==2)
	    		  return ' <span class="layui-badge layui-bg-blue">考试已完成</span> ';
	    	  else
	    		  return '无法解析当前状态';
	      }},
	    ]],
	    parseData : function(res){
            return {
               code : res.code,
               msg: res.msg ,
               count:res.count, //数据总数
               data : res.data,
           };
       }, 
       done: function(res, curr, count){
			  now_page =curr; 
		}
	  });
});

function addTestExam(){
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.open({
			  title : '模拟考试信息定义',
			  type: 2, 
			  closeBtn : 2,
			  area : ['500px','350px'],
			  shadeClose : false,
			  content: '${path }/studentTestExam/addTestExamPage?endPage='+(now_page+1) , //这里content是一个普通的String
			  cancel : function(index, layero){ 
				  var body = layer.getChildFrame('body', index);
				  body.find('#delTestExam').click();
			  }    
		  });
	});     
}


//显示考卷详情信息按钮
function showExamModel(id,name,score){
	layui.use(['layer'],function(){
		var layer = layui.layer;
		$.post("${path }/studentTestExam/getTestExamItemMsg/"+id , {}, function(res){
			var examItems = res.extend.examItems;
			var itemDom =$("<div></div>");
			examItems.forEach(function(item,index){
				dom = $("<hr/><div>考试题型： <font color='#FFB800'>"+item.itemType_name+"</font>，考题数量：<font color='#FFB800'>"+item.item_count+"</font>，考题总分：<font color='#FFB800'>"+item.item_counts+"</font>；</div>");
				dom.appendTo(itemDom);
			});
			layer.open({
		        type: 1
		        ,title: '试卷定义信息' //不显示标题栏
		        ,closeBtn: false
		        ,area: ['400px', '355px']
		        ,shade: 0.1
		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		        ,btn: ['关闭']
		        ,btnAlign: 'c'
		        ,moveType: 1 //拖拽模式，0或者1
		        ,move: false
		        ,content: '<div style="padding: 20px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">'
		        +'<hr/><div id="itemdom">模拟试卷【 <font color="#FFB800">'+name+'</font>】题型如下：总分：<font color="#FFB800">'+score+'</font></div>'
		        +'</div>'
		        ,yes: function(index, layero){
		            layer.close(index);
		        }
		        ,success : function(layero, index){
		        	itemDom.appendTo($("#itemdom"));
		        }
		      });
			
		},"json");
	})
}

/***
 * 显示本次模拟考卷的详情信息
 */
function showTestExamPaperMsg(testItemId){
	window.location.href = "${path }/studentTestExam/showTestExamPaperMsg/"+testItemId;
}
</script>
</html>