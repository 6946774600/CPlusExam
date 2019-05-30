<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考试时间维护页面</title>
<style type="text/css">
/* body {
overflow-y: auto;
overflow-x: hidden;
} */
</style>
</head>
<body class="layui-fluid">

<blockquote style="margin-top: 20px;" class="layui-elem-quote layui-anim layui-anim-up">
本页面进行正式考试时间信息的维护
</blockquote>

<fieldset style="margin-top: 20px;" class="layui-elem-field layui-field-title layui-anim layui-anim-scale">
  <legend>考试时间表</legend>
</fieldset>
<table class="layui-table" id="tab" lay-filter="tab_filter"></table>
<!-- 自定义工具栏 -->
<script type="text/heml" id="toolbar_btn">
				<div  class="layui-btn-container">
				<button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="addExamTime">
				<i class="layui-icon layui-icon-add-1" ></i>添加
				</button>
				<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="editExamTime">
				<i class="layui-icon layui-icon-edit" ></i>编辑
				</button>
				<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="deleteExamTime">
				<i class="layui-icon layui-icon-delete" ></i>删除
				</button>
				</div>
</script>
</body>
<script type="text/html" id="switchTpl">
  <input disabled type="checkbox" name="show_notice" value="{{d.show_notice}}" lay-skin="switch" lay-text="发布|未发布" lay-filter="show_noticeDemo" {{ d.show_notice == 1 ? 'checked' : '' }}>
</script>
<script type="text/javascript">
var now_page;

layui.use(['table','layer'], function(){
	
	 var table = layui.table;
	 var layer = layui.layer;
	
	table.render({
		id : 'lay_table',
	    elem: '#tab' ,
	    toolbar : "#toolbar_btn",
	    defaultToolbar: ['print', 'exports'],
	    url: '${path}/adminExamTime/getExamTimeList', //数据接口
	    loading : true,
	    page : true,
	    limits : [10,30,50],
	     cols: [[ //表头
	      {type : 'checkbox', },
	      {field: 'uuid', hide : true , },
	      {field: 'name', title : '试卷名称' , align : 'center' , },
		  {type : 'numbers',title : '序号' ,rowspan : '2' , align : 'center' ,},
		  {field: 'start_time', title : '考试开始时间' , align : 'center' ,templet: function(d){
		    return formatDate(new Date(d.start_time));
	      }},
		  {field: 'end_time', title : '考试结束时间' , align : 'center'  , templet: function(d){
		        return formatDate(new Date(d.end_time));
	      }},
		  {field: 'insert_time', title : '创建时间' , align : 'center' ,},
		  {field: 'exam_name', title : '使用试题信息' , align : 'center'   ,templet: function(d){
			  
		        return '<span style="cursor: pointer;" class="layui-badge layui-bg-blue" onclick="javascript:showExamModel('+d.exam_modelid+')">'+d.exam_name+'</span>';
	      }},
		  {field: 'show_notice', title : '是否发布通告' , align : 'center' ,templet: '#switchTpl' , },
		  {field: 'notice_title', title : '通告标题' , align : 'center' },
		  {field: 'notice_msg', title : '通告详情' , align : 'center' },
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
	
	//为自定义的工具栏绑定事件
	table.on('toolbar(tab_filter)', function(obj){ 
		switch(obj.event){
		    case 'addExamTime':
		    	addExamTime(now_page+1);
		    break;
		    case 'editExamTime':
			    editExamTime(now_page);
			break;
		    case 'deleteExamTime':
		    	deleteExamTime(now_page);
		    break;
		  };
	});
	
});


function addExamTime(endPage){
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.open({
			  title : '考试时间添加',
			  type: 2, 
			  area : ['500px','515px'],
			  shadeClose : true,
			  content: '${path }/adminExamTime/addExamTimePage?endPage='+endPage , 
			  btn : ['提交','重置'],
			  yes : function(index, layero){
				 var body = layer.getChildFrame('body', index);
				 body.find('#subForm').click();
			  },
			  btn2 : function(index, layero){
				 var body = layer.getChildFrame('body', index);
				  body.find('#resetForm').click();
				  return false;
			  },
		  });
	}); 
} 


 function editExamTime(now_page){
	 alert("修改");
	 /* layui.use(['table','layer'], function(){
	    var table = layui.table;
	    var layer = layui.layer;
		var checkStatus = table.checkStatus('lay_table');
		if(checkStatus.data.length==0){
			layer.msg('你都没选，我编辑啥！',{
				icon: 5,
					time: 2000,
			});
			return;
		} else if(checkStatus.data.length == 1){
			layer.open({
				  title : '班级信息编辑',
				  type: 2, 
				  area : ['500px','450px'],
				  shadeClose : true,
				  content: '${path }/adminExamTime/editExamTimePage?ExamTimeId='+checkStatus.data[0].ExamTimeId+'&now_page='+now_page, 
				  btn : ['提交','恢复'],
				  yes : function(index, layero){
					  var body = layer.getChildFrame('body', index);
					  body.find('#subForm').click();
				  },
				  btn2 : function(index, layero){
					  var body = layer.getChildFrame('body', index);
					  body.find('#recoverForm').click();
					  return false;
				  },
			  });
		} else {
			layer.msg('只能编辑一条数据呦！',{
				icon: 0,
					time: 2000,
			});
			return;
		}
	});	  */
}


 function deleteExamTime(now_page){
	layui.use(['table','layer'], function(){
	    var table = layui.table;
	    var layer = layui.layer;
		var checkStatus = table.checkStatus('lay_table');
		if(checkStatus.data.length==0){
			layer.msg('你都没选，我删啥',{
				icon: 0,
					time: 2000,
			});
			return;
		}else{
			/* var ids = [];
			var names =[];
			for(var i=0;i<checkStatus.data.length;i++){
				console.log(checkStatus);
				ids.push(checkStatus.data[i].uuid);
			}
			var id = ids.join(',');
			var name = names.join('】，【');
			layer.confirm('您确定要删除 这&nbsp;&nbsp;<font color="red">'+checkStatus.data.length+'</font>&nbsp;&nbsp;项数据吗?', {icon: 3, title:'提示'}, function(index){
				var url = '${path }/adminExamTime/deleteExamTime';
				$.post(url,{id : id},function (res){
					layer.alert(res.msg,{title : '系统提示'},function(index){
						layui.table.reload('lay_table',{page: {curr: now_page}});
						layer.close(index);
				    }); 
				},"json");  
				layer.close(index);
			});  */
			alert("未实现");
			
		}
		
	});	
} 
 
//显示考卷详情信息按钮
function showExamModel(id){
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
	
	
</script>
</html>