<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>班级信息修改页面</title>
</head>
<body class="layui-fluid">

<form class="layui-form" id="editForm" lay-filter="editForm" style="padding: 10px;">
  
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
  
  
  <div class="layui-form-item">
    <label class="layui-form-label">所属专业：</label>
    <div class="layui-input-block" >
      <input style="width: 300px;" type="text" name="gradeMajor" id="gradeMajor" required  lay-verify="required" placeholder="示例：软件工程专业" autocomplete="off" class="layui-input" />
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
    <label class="layui-form-label">备注说明：</label>
    <div class="layui-input-block" >
      <textarea style="width: 300px; "  style="resize:none"  name="gradeRemark" id="gradeRemark" placeholder="请输入班级说明信息" class="layui-textarea"></textarea>
    </div>
  </div>
  <div hidden="true" class="layui-form-item">
    <div class="layui-input-block">
      <input type="text" name="gradeId" id="gradeId" value="${gradeId }" class="layui-input" />
      <button class="layui-btn" id="subForm" lay-filter="subForm" lay-submit="">提交</button>
      <button id="recoverForm" type="button" onclick="recoverFormClick()" class="layui-btn layui-btn-primary">恢复</button>
    </div>
  </div>
</form>

</body>
<script type="text/javascript">
layui.use(['form','layer'], function(){
	  var form = layui.form;
	  var layer = layui.layer;
	  var gradeId = "${gradeId }";
	  
	  
	  //进行任课教师下拉框的初始化
	  $.post("${path }/adminTeacher/getTeacherSelect",{},function(res){
		  var select = res.extend.select;
			select.forEach(function(item,index){
				var option	= $("<option value="+item.id+">"+item.NAME+"</option>");
				option.appendTo($("#teacher"));
			})
			form.render('select');
			//为form表单赋值
			actionFrom(form,gradeId);
	  },"json");

	  //表单提交
	  form.on('submit(subForm)', function(data){
		  $.ajax({
			    url : "${path }/adminGrade/updateGrade",
			    method : "post",
			    data : $('#editForm').serialize(),
			    dataType : "json",
			    success : function(res){
					//关闭当前弹出层
					layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
						layer.close(index);
						var index=parent.layer.getFrameIndex(window.name);
					    parent.layer.close(index);
					    layui.use('table', function(){
						    parent.layui.table.reload('lay_table',{page: {curr: "${now_page}"}});
						});
				    }); 
				}
			  }); 
		  return false;
	  });


})


	function recoverFormClick(){
	layui.use('form', function(){
		  var form = layui.form;
		  var gradeId = "${gradeId }";
		  actionFrom(form,gradeId);	
	});
	}
	//进行form表单的初始化
	function actionFrom(form,gradeId){
		var url = "${path }/adminGrade/getGradeById";
		$.post(url,{gradeId : gradeId},function(res){
			var grade = res.extend.grade;
			form.val("editForm", {
				  "gradeName": grade.gradeName
				  ,"gradeTerm": grade.gradeTerm
				  ,"gradeMajor": grade.gradeMajor
				  ,"teacherId" : grade.teacherId
				  ,"gradeRemark": grade.gradeRemark
			});
		},"json");

	}

</script>

</html>