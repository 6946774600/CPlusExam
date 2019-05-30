<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息列表</title>
</head>
<body class="layui-fluid">

	<blockquote style="margin-top: 20px;" class="layui-elem-quote  layui-anim layui-anim-up">本页面可以进行学生信息的维护</blockquote>


	<fieldset style="margin-top: 20px;" class="layui-elem-field  layui-anim layui-anim-scale">
	  <legend>学生信息表</legend>
	  <blockquote class="layui-elem-quote layui-quote-nm layui-container">
	  <form class="layui-form layui-form-pane ">
	  	<div class="layui-row">
		  <div class="layui-form-item layui-col-xs4 layui-col-sm4 layui-col-md4" style="clear : none;">
		    <label class="layui-form-label">学号</label>
		    <div class="layui-input-inline">
		      <input type="text" name="loginName" id="loginName" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item layui-col-xs4 layui-col-sm4 layui-col-md4" style="clear : none;">
		    <label class="layui-form-label">姓名</label>
		    <div class="layui-input-inline">
		      <input type="text" name="name" id="name" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item layui-col-xs4 layui-col-sm4 layui-col-md4" style="clear : none;">
		    <label class="layui-form-label">所属班级</label>
		    	<div class="layui-input-block">
			      <select name="gradeId" id="grade">
			        <option value=""></option>
			      </select>
			    </div>
		  </div>
		  </div>
		</form>
		<div class="layui-row">
		  	<button class="layui-btn layui-col-md-offset5 layui-col-xs-offset5 layui-col-sm-offset5" onclick="seachBtn()">搜索</button>
		  	<button class="layui-btn layui-btn-primary" onclick="resetBtn()">重置</button>
		  </div>
	  </blockquote>
	  <div class="layui-field-box">
	  		<table id="studentTab" lay-filter="studentTab_filter"></table>
	  		<!-- 自定义工具栏 -->
			<script type="text/heml" id="toolbar_btn">
				<div  class="layui-btn-container">
				<button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="addStudent">
				<i class="layui-icon layui-icon-add-1" ></i>添加
				</button>
				<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="editStudent">
				<i class="layui-icon layui-icon-edit" ></i>编辑
				</button>
				<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="deleteStudent">
				<i class="layui-icon layui-icon-delete" ></i>删除
				</button>
				<button class="layui-btn layui-btn-sm " lay-event="importStudent">
				<i class="layui-icon layui-icon-template" ></i>使用模版批量导入
				</button>
				</div>
			</script>
	  </div>
	</fieldset>
</body>
<div hidden="true" id="importDom" class="layui-fluid">
	<blockquote style="margin-top: 15px;" class="layui-elem-quote layui-quote-nm">
		1. 下载模版文件并进行相关信息的录入；<br>
		2. 上传模版文件；<br>
		3. 点击导入按钮，进行信息导入。
	</blockquote>
<fieldset class="layui-elem-field" style="margin-top: 5px;">
  <legend>模版下载</legend>
  <div class="layui-field-box" align="center">
   		<button class="layui-btn " onclick="downloadFile()" lay-event="importStudent"><i class="layui-icon layui-icon-template" ></i>点击进行模版下载</button>
  </div>
</fieldset>
<fieldset class="layui-elem-field" style="margin-top: 20px;">
  <legend>数据上传</legend>
  <div class="layui-field-box" align="center">
  	<button type="button" class="layui-btn" id="uploadFile">
	  <i class="layui-icon">&#xe67c;</i>添加学生信息文件
	</button>
  </div>
</fieldset>
	
</div>

<script type="text/javascript">

var fileId ;
var now_page=1;
var modelFile = -1;  //是否添加了上传的文件


layui.use(['upload','table','layer','form'], function(){
	  var table = layui.table;
	  var upload = layui.upload;
	  var layer = layui.layer;
	  var form = layui.form;

	  //进行班级下拉框的初始化
	  $.post("${path}/adminGrade/getGradeSelect",{},function(res){
			var select = res.extend.select;
			select.forEach(function(item,index){
				var option	= $("<option value="+item.id+">"+item.name+"</option>");
				option.appendTo($("#grade"));
			})
			form.render('select');
	  },"json");
	  
	  table.render({
			id : 'lay_table',
		    elem: '#studentTab' ,
		    toolbar : "#toolbar_btn",
		    defaultToolbar: ['print', 'exports'],
		    url: '${path}/adminStudent/getStudentList', //数据接口
		    loading : true,
		    page : true,
		    limits : [10,30,50],
		     cols: [
		    [	{type : 'checkbox', width:60},
				{type : 'numbers',title : '序号' , align : 'center' , },
			    {field: 'loginName', title : '学号',   align : 'center' , width:140},
		    	{field: 'name', title : '姓名',   align : 'center' , },
		    	{field: 'sex',title : '性别',  align : 'center' 
		    		,templet: function(d){
		    	        return d.sex==0?'男':'女';
		    	      }},
		    	{field: 'toSchool',title : '入学日期',   align : 'center' ,},
		    	{field: 'gradeName',title : '班级信息',   align : 'center' , },
		    	{field: 'phone',title : '联系方式',   align : 'center' , },
		    	{field: 'shiroName',title : '系统权限',   align : 'center' , },
		    	{field: 'email',title : '邮箱',   align : 'center' },
			]
			], 
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
		table.on('toolbar(studentTab_filter)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			switch(obj.event){
			    case 'addStudent':
			    	addStudent(now_page+1);
			    break;
			    case 'editStudent':
				    editStudent(now_page);
				break;
			    case 'deleteStudent':
			    	deleteStudent(now_page);
			    break;
			    case 'importStudent' :
			    	importStudent();
			    break;
			  };
		});



		//文件上传相关设置
		var uploadInst = upload.render({
		    elem: '#uploadFile' //绑定元素
		    ,url: '${path }/upload/execlUpLoad' //上传接口 
		    ,accept :'file'
			,exts : 'xlsx' 
			,auto : true     //添加文件  就进行文件的上传操作
		    ,done: function(res){
			    fileId = res.msg;
			    modelFile = 0;
			    layer.msg("文件上传成功！");
		    }
		    ,error: function(){
		        //请求异常回调
		    	layer.msg("啊哦，上传出错啦！");
		    }
		  }); 
	  
});


function addStudent(endPage){
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.open({
			  title : '学生信息添加',
			  type: 2, 
			  area : ['500px','550px'],
			  shadeClose : true,
			  content: '${path }/adminStudent/addStudentPage?endPage='+endPage , //这里content是一个普通的String
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


 function editStudent(now_page){
	 layui.use(['table','layer'], function(){
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
				  title : '学生信息编辑',
				  type: 2, 
				  area : ['500px','450px'],
				  shadeClose : true,
				  content: '${path }/adminStudent/editStudentPage?loginName='+checkStatus.data[0].loginName+'&now_page='+now_page, 
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
	});	  
}


 function deleteStudent(now_page){
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
			var ids = [];
			var names =[];
			for(var i=0;i<checkStatus.data.length;i++){
				ids.push(checkStatus.data[i].loginName);
				names.push(checkStatus.data[i].name);
			}
			var id = ids.join(',');
			var name = names.join('】，【');
			layer.confirm('您确定要删除 【'+name+'】 这&nbsp;&nbsp;<font color="red">'+checkStatus.data.length+'</font>&nbsp;&nbsp;项数据吗?', {icon: 3, title:'提示'}, function(index){
				var url = '${path }/adminStudent/deleteStudent';
				$.post(url,{id : id},function (res){
					layer.alert(res.msg,{title : '系统提示'},function(index){
						layui.table.reload('lay_table',{page: {curr: now_page}});
						layer.close(index);
				    }); 
				},"json"); 
				layer.close(index);
			});
			
		}
		
	});	
} 

function importStudent(){
	$("#importDom").show();
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.open({
			  title : '使用模版进行学生信息的批量导入',
			  type: 1, 
			  area : ['450px','430px'],
			  shadeClose : true,
			  content: $('#importDom') , 
			  btn : ['导入'],
			  yes : function(index, layero){
				  if(modelFile==-1){
					  layer.msg("请添加要导入的文件！");					  
				  }else{
					 $.post("${path }/adminStudent/improtStudent",{fileId : fileId},function(res){
						  layer.alert(res.msg,{title : '系统提示'},function(index){
								layui.table.reload('lay_table',{page: {curr: now_page}});
								layer.close(index);
						  }); 	   
						  layer.close(index);
					  },"json"); 
				  }
			  }, 
			  end : function(){
				  $("#importDom").hide();
				  modelFile = -1;
			  }
		  });
	});     
};

//点击文件下载按钮
function downloadFile(){
	window.location.href = "${path }/adminStudent/modelFileDownLoad";
}


function seachBtn(){
	layui.use(['table'],function(){
		var table = layui.table;
		table.reload('lay_table', {
			  method:'post',
			  where: {loginName : $("#loginName").val(),name : $("#name").val(),gradeId : $("#grade option:selected").val()} //设定异步数据接口的额外参数
		}); 
	})
}

function resetBtn(){
	 $("#loginName").val("");
	 $("#name").val("");
	 var options =document.getElementById('grade').children;//$("#grade").children;
	 options[0].selected=true;
	 layui.use('form',function(){
		 layui.form.render('select');
	 });
	 
}

</script>

</html>