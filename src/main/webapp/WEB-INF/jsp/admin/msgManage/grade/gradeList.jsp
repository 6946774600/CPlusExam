<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>班级信息管理界面</title>
</head>
<body class="layui-fluid">

	<blockquote style="margin-top: 20px;" class="layui-elem-quote layui-anim layui-anim-up">本页面可以进行班级信息的维护</blockquote>


	<fieldset style="margin-top: 20px;" class="layui-elem-field  layui-anim layui-anim-scale">
	  <legend>班级信息表</legend>
	  <div class="layui-field-box">
	  		<table id="gradeTab" lay-filter="gradeTab_filter"></table>
	  		<!-- 自定义工具栏 -->
			<script type="text/heml" id="toolbar_btn">
				<div  class="layui-btn-container">
				<button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="addGrade">
				<i class="layui-icon layui-icon-add-1" ></i>添加
				</button>
				<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="editGrade">
				<i class="layui-icon layui-icon-edit" ></i>编辑
				</button>
				<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="deleteGrade">
				<i class="layui-icon layui-icon-delete" ></i>删除
				</button>
				<button class="layui-btn layui-btn-sm " lay-event="importGrade">
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
   		<button class="layui-btn " onclick="downloadFile()" lay-event="importGrade"><i class="layui-icon layui-icon-template" ></i>点击进行模版下载</button>
  </div>
</fieldset>
<fieldset class="layui-elem-field" style="margin-top: 20px;">
  <legend>数据上传</legend>
  <div class="layui-field-box" align="center">
  	<button type="button" class="layui-btn" id="uploadFile">
	  <i class="layui-icon">&#xe67c;</i>添加班级信息文件
	</button>
  </div>
</fieldset>
	
</div>

<script type="text/javascript">

var fileId ;
var now_page=1;
var modelFile = -1;  //是否添加了上传的文件

layui.use(['upload','table','layer'], function(){
	  var table = layui.table;
	  var upload = layui.upload;
	  var layer = layui.layer;
	  
	  
	  table.render({
			id : 'lay_table',
		    elem: '#gradeTab' ,
		    toolbar : "#toolbar_btn",
		    defaultToolbar: ['print', 'exports'],
		    url: '${path}/adminGrade/getGradeList', //数据接口
		    loading : true,
		    page : true,
		    limits : [10,30,50],
		     cols: [[ //表头
		      {type : 'checkbox',rowspan : '2' , width:80},
			  {type : 'numbers',title : '序号' ,rowspan : '2' , align : 'center' , width:80},
		      {title : '班级信息' ,rowspan : '1',colspan : '6' , align : 'center'},
		      {title : '统计信息' ,rowspan : '1',colspan : '3' , align : 'center'},
		    ],
		    [	{field: 'gradeId', title : '班级编号', rowspan : '1',  align : 'center' ,width:120 },
		    	{field: 'gradeName', title : '班级名称', rowspan : '1',  align : 'center' , width:160},
		    	{field: 'gradeTerm',title : '所属年级', rowspan : '1',  align : 'center' , },
		    	{field: 'gradeMajor',title : '所属专业', rowspan : '1',  align : 'center' , width:120},
		    	{field: 'teacherName',title : '任课教师', rowspan : '1',  align : 'center' , },
		    	{field: 'gradeRemark',title : '备注说明', rowspan : '1',  align : 'center', },
		    	{field: 'countNum',title : '班级人数', rowspan : '1',  align : 'center' ,},
		    	{field: 'boyNum',title : '男生人数', rowspan : '1',  align : 'center' ,},
		    	{field: 'grilNum',title : '女生人数', rowspan : '1',  align : 'center' , },
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
		table.on('toolbar(gradeTab_filter)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			switch(obj.event){
			    case 'addGrade':
			    	addGrade(now_page+1);
			    break;
			    case 'editGrade':
				    editGrade(now_page);
				break;
			    case 'deleteGrade':
			    	deleteGrade(now_page);
			    break;
			    case 'importGrade' :
			    	importGrade();
			    break;
			  };
		});



		//文件上传相关设置
		var uploadInst = upload.render({
		    elem: '#uploadFile' //绑定元素
		    ,url: '${path }/upload/execlUpLoad' //上传接口 
		    ,accept :'file'
			,exts : 'xls' 
		    /* ,acceptMime : 'image/jpg, image/png' */  //文件选择时   显示出来的文件信息
			,auto : true    //添加文件  就进行文件的上传操作
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



function addGrade(endPage){
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.open({
			  title : '班级信息添加',
			  type: 2, 
			  area : ['500px','500px'],
			  shadeClose : true,
			  content: '${path }/adminGrade/addGradePage?endPage='+endPage , //这里content是一个普通的String
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


 function editGrade(now_page){
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
				  title : '班级信息编辑',
				  type: 2, 
				  area : ['500px','450px'],
				  shadeClose : true,
				  content: '${path }/adminGrade/editGradePage?gradeId='+checkStatus.data[0].gradeId+'&now_page='+now_page, 
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


 function deleteGrade(now_page){
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
				ids.push(checkStatus.data[i].gradeId);
				names.push(checkStatus.data[i].gradeName);
			}
			var id = ids.join(',');
			var name = names.join('】，【');
			layer.confirm('您确定要删除 【'+name+'】 这&nbsp;&nbsp;<font color="red">'+checkStatus.data.length+'</font>&nbsp;&nbsp;项数据吗?', {icon: 3, title:'提示'}, function(index){
				var url = '${path }/adminGrade/deleteGrade';
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

function importGrade(){
	$("#importDom").show();
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.open({
			  title : '使用模版进行班级信息的批量导入',
			  type: 1, 
			  area : ['450px','430px'],
			  shadeClose : true,
			  content: $('#importDom') , 
			  btn : ['导入'],
			  yes : function(index, layero){
				  if(modelFile==-1){
					  layer.msg("请添加要导入的文件！");					  
				  }else{
					  $.post("${path }/adminGrade/improtGrade",{fileId : fileId},function(res){
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
	window.location.href="${path}/public/modelFile/GradeModel.xls";
	/* 
		文件下载 可以通过直接请求文件地址  获取使用servlet进行处理 将文件out出来实现
		通过超链接下载文件时，如果浏览器可以识别该文件格式，浏览器就会直接打开。只有浏览器不能识别该文件格式的时候，才会实现下载。
		比如图片  浏览器就会直接打开图片  而不是下载
	 */
}
 

</script>
</html>