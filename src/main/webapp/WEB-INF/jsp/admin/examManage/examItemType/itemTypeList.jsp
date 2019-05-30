<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>试卷题型管理页面</title>
</head>
<body class="layui-fluid">
<blockquote style="margin-top: 20px;" class="layui-elem-quote layui-anim layui-anim-up">试卷题型维护页面。本页面进行试卷题型、考题数量、考题分数的维护；定义的试卷题型，可用于正式考试试卷题型的选择。
<hr/>
<font color="red">注：1. 已经被禁用的考题类型，不能在定义考卷时被选取到。<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. 已录入的试卷模型不支持修改操作</font></blockquote>

<div class="layui-card">
  <div class="layui-card-header"><font color="#FF5722">*点击右下角的添加按钮，可以定义添加新的试卷题型。</font></div>
  <div class="layui-card-body">
	    <table class="layui-table">
		  <thead>
		    <tr align="center">
		      <th rowspan="2"  align="right">试卷名称</th>
		      <th rowspan="2"  align="center">创建时间</th>
		      <th rowspan="2"  align="center">总分数</th>
		      <th colspan="5"  align="center">试卷题型信息</th>
		      <th rowspan="2"  align="center" width="120px;">操作</th>
		    </tr> 
		    <tr>
		      <th  align="center">考试题型</th>
		      <th  align="center">考题数量</th>
		      <th  align="center">考题分数</th>
		      <th  align="center">该题型总分数</th>
		      <th  align="center">题型展示顺序</th>
		    </tr> 
		  </thead>
		  <tbody>
	    	<c:set var="count" value="0"></c:set>   <!-- 控制当前行  是否是重复行  是的话  合并的要合并的单元格就不用在<td><td>了  只需要出现一次  -->
	    	<c:set var="rowspan" value="0"></c:set>  <!-- 记录考题id相同的数量 -->
	    	<c:set var="temp" value="1"></c:set>    <!-- 临时变量  用于操作栏的生成  -->
		    <c:forEach items="${exams }" var="exam" varStatus="index">  <!-- 第一层循环  控制所有的表格数目 -->
		    	<tr>
		    	<c:if test="${index.count > count }">
			    	<c:set var="uuid" value="${exam.uuid }"></c:set>
			    	<c:forEach items="${exams }" var="exam2"> 
				    	<!-- 第二层循环  判断id相同的数据有多少个  这个id数  就是行要合并的数 -->
			    		<c:if test="${exam2.uuid == uuid }">
			    			<c:set var="count" value="${count+1 }"></c:set>
			    			<c:set var="rowspan" value="${rowspan+1 }"></c:set>
			    		</c:if>
			    	</c:forEach>
		    		<td rowspan="${rowspan }">${exam.exam_name }</td>
			        <td rowspan="${rowspan }">${exam.create_time }</td>
			        <td rowspan="${rowspan }">${exam.count_score }</td>
		    	</c:if>
			        <td >${exam.itemType_name }</td>
			        <td >${exam.item_score }</td>
			        <td >${exam.item_count }</td>
			        <td >${exam.item_counts }</td>
			        <td >${exam.show_index }</td>
		    	<c:if test="${temp == 1 }">
		    		<td rowspan="${rowspan }">
			    		<div class="layui-btn-group">
						  <button class="layui-btn layui-btn-sm" onclick="showExamMsg(${exam.uuid })">查看定义</button>
						  <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="deleteExam(${exam.uuid },'${exam.exam_name }')">删除</button>
						</div>
					</td>
		    		<c:set var="temp" value="0"></c:set>     <!-- 临时变量置为0    该栏只需要出现一次 -->
		    	</c:if>
		    	
		    	<c:if test="${index.count == count }">
		    		<c:set var="rowspan" value="0"></c:set>
		    		<c:set var="temp" value="1"></c:set>      <!-- 当单元格合并完成后 在置为1  -->
		    	</c:if>
		    	</tr>
		    </c:forEach>
		  </tbody>
		</table>
		
		
  </div>
</div>
</body>
<script type="text/javascript">
layui.use(['table','util','layer'], function(){
	  var util = layui.util;
	  var table = layui.table;
	  var layer = layui.layer;
	  
	  
	  //固定块
	  util.fixbar({
	    bar1: "&#xe654;"
	    ,click: function(type){
	      if(type === 'bar1'){
	        //弹出层 进行考卷题型的添加
	    	  layer.open({
				  title : '考卷题型的添加',
				  type: 2, 
				  anim: 2 ,
				  /* offset: '100px' , */
				  area : ['800px','550px'],
				  shadeClose : false,
				  fixed : true,
				  content: '${path }/adminExamModel/addExamModelPage', 
				  btn : ['提交','取消'],
				  yes : function(index, layero){
					  var body = layer.getChildFrame('body', index);
					  body.find('#subForm').click();
					  /* return false; */
				  },
				  btn2 : function(index, layero){
					  layer.close(index);
				  },
			  
			  });
	      }
	    }
	  });
	  
	  
	});

//查看试卷的信息
function showExamMsg(id){
	layui.use(['layer'],function(){
		var layer = layui.layer;
		
		$.post("${path }/adminExamTime/getExamModelMsg/"+id , {}, function(res){
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

//根据id  删除当前定义的考题信息
function deleteExam(id,name){
	layui.use(['layer'],function(){
		var layer = layui.layer;
		layer.confirm('您确定要删除 <font color="#FF5722">【'+name+'】</font>这项数据吗?', {icon: 3, title:'提示'}, function(index){
			$.post("${path }/adminExamModel/deleteExamModel/"+id , {}, function(res){
				layer.alert(res.msg,{title : '系统提示'},function(index){
					layer.close(index);
					location.reload();
			    }); 
			},"json"); 
			layer.close(index);
		});
		
	})
}
</script>


</html>