<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>班级信息添加页面</title>
</head>
<body class="layui-fluid">

<form class="layui-form" id="addForm" style="padding: 10px;">
  <div class="layui-form-item">
    <label class="layui-form-label">班级编号：</label>
    <div class="layui-input-block" >
      <input style="width: 300px;" type="text" name="gradeId" id="gradeId" required  lay-verify="required" placeholder="示例：20180001" autocomplete="off" class="layui-input" />
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">班级名称：</label>
    <div class="layui-input-block" >
      <input style="width: 300px;" type="text" name="gradeName" id="gradeName" required  lay-verify="required" placeholder="示例：2018级软件1班" autocomplete="off" class="layui-input" />
    </div>
  </div>
  
  
  <div class="layui-form-item">
    <label class="layui-form-label">所属年级：</label>
    <div class="layui-input-block" >
      <input style="width: 300px;" type="text" name="gradeTerm" id="gradeTerm" required  lay-verify="required" placeholder="示例：2018级" autocomplete="off" class="layui-input" />
    </div>
  </div>
  
  <div class="layui-form-item " > 
		    <label class="layui-form-label">任课教师：</label>
		    	<div class="layui-input-block" style="width: 300px;">
			      <select name="teacherId" id="teacher">
			      </select>
			    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">所属专业：</label>
    <div class="layui-input-block" >
      <input style="width: 300px;" type="text" name="gradeMajor" id="gradeMajor" required  lay-verify="required" placeholder="示例：软件工程专业" autocomplete="off" class="layui-input" />
    </div>
  </div>
  
  
  <div class="layui-form-item">
    <label class="layui-form-label">备注说明：</label>
    <div class="layui-input-block" >
      <textarea style="width: 300px; "  style="resize:none"  name="gradeRemark" id="gradeRemark" placeholder="请输入班级说明信息" class="layui-textarea"></textarea>
    </div>
  </div>
  <div hidden="true" class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" id="subForm" lay-filter="subForm" lay-submit="">提交</button>
      <button id="resetForm" type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>

</body>
<script type="text/javascript">
layui.use(['form','layer'], function(){
	  var form = layui.form;
	  var layer = layui.layer;
	  var uuid=-1;   //用来标识用户输入的班级编号是否唯一   -1标识不唯一


	  //进行任课教师下拉框的初始化
	  $.post("${path }/adminTeacher/getTeacherSelect",{},function(res){
		  var select = res.extend.select;
			select.forEach(function(item,index){
				var option	= $("<option value="+item.id+">"+item.NAME+"</option>");
				option.appendTo($("#teacher"));
			})
			form.render('select');
	  },"json");
	  
	  //表单提交
	  form.on('submit(subForm)', function(data){
		  if(uuid==-1){
			  layer.msg("该班级班号已存在，请重新输入");
		  }else{
			  $.ajax({
			    url : "${path }/adminGrade/addGrade",
			    method : "post",
			    data : $('#addForm').serialize(),
			    dataType : "json",
			    success : function(res){
					layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
						layer.close(index);
						var index=parent.layer.getFrameIndex(window.name);
					    parent.layer.close(index);
					    layui.use('table', function(){
						    parent.layui.table.reload('lay_table',{page: {curr: "${endPage}"}});
						});
				    }); 
				}
			  });  
		  }
		    
		  return false;
	  });


	$("#gradeId").on('change',function(){
		$.post("${path}/adminGrade/gradeIdOnaly",{gradeId:$("#gradeId").val()},function(res){
			if(res.success)
				uuid = 0;
			else{
				uuid  = -1;
				layer.msg("该班级班号已存在，请重新输入",{time: 2000});
			}
		},"json");
	})
	
})

</script>

</html>