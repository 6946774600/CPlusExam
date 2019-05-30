<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>正式考试页面</title>
</head>
<body  class="layui-container">

<blockquote style="margin-top: 40px;" class="layui-elem-quote layui-anim layui-anim-up">已发布考试信息</blockquote>

<!-- 考试时间表 动态填充 -->
<table id="exam_timeTab" lay-filter="exam_timeTabF">

</table>


<blockquote style="margin-top: 20px;" class="layui-elem-quote layui-anim layui-anim-up">已报名考试信息</blockquote>

<!-- 考试表 动态填充 -->
<table id="exam_officialTab" lay-filter="exam_officialTab">
	
</table>

</body>
<script type="text/javascript">

var exam_timePageNow =1;
var exam_officialPageNow = 1;

layui.use('table', function(){
	var table = layui.table;
	
	//第一个实例
	table.render({
		id : 'exam_time',
		elem: '#exam_timeTab' ,
		url: '${path }/studentOfficialExam/getExamTimeMsg', //数据接口
		page: true , //开启分页
		limit : 5,
		limits : [5,10,20],
		loading : true,
		cols: [[ //表头
		  { type : 'numbers' , title: '序号',}
		  ,{field: 'name', title: '试卷名称', align : 'center',}
		  ,{field: 'insert_time',  align : 'center', title: '创建时间', } 
		  ,{field: 'start_time', title: '考试开始时间' , align : 'center', width : 160, templet: function(d){
		        return formatDate(new Date(d.start_time));
	      }}
		  ,{field: 'end_time', title: '考试结束时间', align : 'center', width : 160,  templet: function(d){
		        return formatDate(new Date(d.end_time));
	      }} 
		  ,{field: 'uuid', title: '试卷题型详情', align : 'center', templet: function(d){
  			  return '<span style="cursor: pointer;" class="layui-badge layui-bg-blue" onclick="javascript:showExamModel('+d.exam_modelid+')">详情定义</span>';
	      }}
		  ,{field: 'count_score',  align : 'center', title: '试卷总分', }
		  ,{field: 'uuid',  align : 'center', title: '查看通告', templet: function(d){
  			  return '<span style="cursor: pointer;" class="layui-badge layui-bg-blue" onclick="javascript:showExamNotice(\''+d.notice_title+'\',\''+d.notice_msg+'\')">查看通告</span>';
	      }}
		  ,{field: 'bmzt', title: '操作', align : 'center', templet: function(d){
			  if(d.overdue==0){
				  //当前开始已经过期
				  if(d.bmzt == 0){
					  return '<span class="layui-badge layui-bg-red">已过期--未报名</span>';
				  }else{
					  return '<span class="layui-badge layui-bg-red">已过期--已报名</span>';
				  }
			  }else{
				  if(d.bmzt == 0){  //0为未报名  1为已报名
					  //还没有报名考试  
		  			  return '<span style="cursor: pointer;" class="layui-badge layui-bg-orange" onclick="javascript:insertOfficialExam('+d.uuid+')">点击报名</span>';
				  }else{
					  //显示报名按钮
					  return '<span class="layui-badge layui-bg-blue">已报名</span>';
				  }
			  }
	      }}
		]] ,
		parseData : function(res){
            return {
               code : res.code,
               msg: res.msg ,
               count:res.count, //数据总数
               data : res.data,
           };
        }, 
        done: function(res, curr, count){
        	exam_timePageNow = curr; 
		}
	});
	
	
	//该用户考试表填充
	table.render({
		id : 'exam_official',
	    elem: '#exam_officialTab' ,
	    url: '${path }/studentOfficialExam/getOfficialExamTabMsg' , //数据接口
	    page: true , //开启分页
	    limits : [10,20,30] ,
	    loading : true,
	    cols: [[ //表头
	      {type : 'numbers' , title: '序号', align : 'center'},
	      {field: 'name', title: '试卷名称', align : 'center'},
	      {field: 'start_time', title: '考试开始时间', align : 'center', width : 160, templet: function(d){
		        return formatDate(new Date(d.start_time));
	      }},
	      {field: 'end_time', title: '考试结束时间', align : 'center', width : 160, templet: function(d){
		        return formatDate(new Date(d.end_time));
	      }},
	      { title: '试卷信息', align : 'center' , width : 100, templet: function(d){
	          return '<span style="cursor: pointer;" class="layui-badge layui-bg-blue" onclick="javascript:showExamModel('+d.exam_modelid+')">试卷定义</span>';
	      }},
	      {field: 'count_score', title: '试题总分',   align : 'center' , width : 90,},
	      {field: 'exam_type', title: '考卷状态',   align : 'center' , width : 90, templet: function(d){
	    	  if(d.exam_type==0)
		    	  return '<span class="layui-badge layui-bg-red">未开始</span>';
	    	  else if(d.exam_type==1)
		    	  return '<span class="layui-badge layui-bg-green">未完成</span>';
	    	  else if(d.exam_type==2)
	    		  return '<span class="layui-badge layui-bg-blue">已完成</span>';
	    	  else
	    		  return '无法解析当前状态';
	      }},
	      {field: 'exam_counts', title: '考试分数',  align : 'center' , width : 90, templet: function(d){
	    	  if(d.exam_type==2){
	    		  //考完了
	    		  return d.exam_counts;
	    	  }else{
	    		  return "--";
	    	  }
	      }},
	      { title: '考试详情', align : 'center' , width : 110, templet: function(d){
	    	  if(d.exam_type==0)
		    	  return '<span class="layui-badge layui-bg-red">考卷未作答</span>';
	    	  else if(d.exam_type==1)
		    	  return '<span class="layui-badge layui-bg-green">考卷未提交</span>';
	    	  else if(d.exam_type==2)
	    		  return '<span style="cursor: pointer;" onclick="showTextExamPaperMsg('+d.uuid+')" class="layui-badge layui-bg-blue">考试详情</span>';
	    	  else
	    		  return '无法解析当前状态';
	      }},
	      {field: 'uuid', title: '操作', align : 'center' , width : 110, templet: function(d){
	    	  if(d.exam_type==0)
		    	  return '<a href="${path }/studentOfficialExam/toOfficialExamPage/'+d.uuid+'"><span style="cursor: pointer;" class="layui-badge layui-bg-red">进入考试</span></a>';
	    	  else if(d.exam_type==1)
		    	  return '<a href="${path }/studentOfficialExam/toOfficialExamPage/'+d.uuid+'"><span style="cursor: pointer;" class="layui-badge layui-bg-orange">继续考试</span></a>';
	    	  else if(d.exam_type==2)
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
    	   exam_officialPageNow =curr; 
		}
	  });
	  
});
//显示试卷详情定义信息
function showExamModel(examModelId){
	layui.use(['layer'],function(){
		var layer = layui.layer;
		
		$.post("${path }/studentOfficialExam/getExamModelMsg/"+examModelId , {}, function(res){
			var examModel = res.extend.examModel;
			var examModelItems = res.extend.examModelItems;
			var itemDom =$("<div></div>");
			examModelItems.forEach(function(item,index){
				dom = $("<hr/><div>考试题型： <font color='#FFB800'>"+item.itemType_name+"</font>，考题数量：<font color='#FFB800'>"+item.item_count+"</font>，考题总分：<font color='#FFB800'>"+item.item_counts+"</font>；</div>");
				dom.appendTo(itemDom);
			});
			layer.open({
		        type: 1
		        ,title: '试卷定义信息' //不显示标题栏
		        ,closeBtn: false
		        ,area: ['400px', '400px']
		        ,shade: 0.1
		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		        ,btn: ['关闭']
		        ,btnAlign: 'c'
		        ,moveType: 1 //拖拽模式，0或者1
		        ,move: false
		        ,content: '<div style="padding: 20px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">'
		        +'<hr/><div>试卷名称：<font color="#FFB800">'+examModel.exam_name+'</font><br>创建时间：<font color="#FFB800">'+examModel.create_time+'</font><br>试卷总分：<font color="#FFB800">'+examModel.count_score+'分</font></div>'
		        +'<hr/><div id="itemdom">试卷题型如下：</div>'
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

//时间格式化函数
var formatDate = function (date) {  
	    var y = date.getFullYear();  
	    var m = date.getMonth() + 1;  
	    m = m < 10 ? ('0' + m) : m;  
	    var d = date.getDate();  
	    d = d < 10 ? ('0' + d) : d;  
	    var h = date.getHours();  
	    h = h < 10 ? ('0' + h) : h;  
	    var minute = date.getMinutes();  
	    minute = minute < 10 ? ('0' + minute) : minute; 
	    var second= date.getSeconds();  
	    second = second < 10 ? ('0' + second) : second;  
	    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+ second;  
}; 

function insertOfficialExam(id){
	//进行考试报名操作  然后刷新当前发布的考试信息表格与下方的自己的考试信息表格
	$.post("${path }/studentOfficialExam/addOfficialExam",{examId : id },function(res){
		layui.use('table',function(){
			var table = layui.table;
			table.reload('exam_time',{page: {curr: exam_timePageNow}});
			table.reload('exam_official',{page: {curr: exam_officialPageNow}});
		})
	},"json");
}

//显示考试详情按钮
function showExamNotice(title,msg){
	layui.use(['layer'],function(){
		var layer = layui.layer;
		
		layer.open({
			type: 1
	        ,title: '<font color="#009688">'+title+'</font>' //不显示标题栏
	        ,closeBtn: false
	        ,area: '400px'
	        ,shade: 0.1
	        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
	        ,btn: ['关闭']
	        ,btnAlign: 'c'
	        ,moveType: 1 //拖拽模式，0或者1
	        ,move: false
	        ,content: '<div style="padding: 20px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">'
	        + msg + '</div>'
	        ,yes: function(index, layero){
	            layer.close(index);
	        }
		});
	})
}


function showTextExamPaperMsg(uuid){
	alert("开发中");
}


</script>
</html>