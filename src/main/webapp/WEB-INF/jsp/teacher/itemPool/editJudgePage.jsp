<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.r{position:fixed; bottom:0; background-color: #eee;border-bottom : 1px solid #eee; width: 100%; padding : 0px; margin : 0px; }
</style>
<title>判断题修改页面</title>
</head>
<body>
<div class="layui-card">
	 <div class="layui-card-header">考题编号：<font color="#FFB800">${id }</font>&nbsp;&nbsp;录入时间：<font color="#FFB800" id="create_time"></font></div>
   <form class="layui-form layui-form-pane" style="margin-top: 20px;" id="edit_form" lay-filter="edit_form">
	 <div class="layui-card-body" style="margin-left: 50px;margin-right: 50px; margin-bottom : 50px;">
  		<div class="layui-form-item layui-form-text" >
	    <label class="layui-form-label">考题内容</label>
	    <div class="layui-input-block">
	      <textarea name="item_name" required  lay-verify="required" placeholder="请输入内容" class="layui-textarea"></textarea>
	    </div>
	 </div>
	 
	 
	 <div class="layui-form-item">
		<div class="layui-upload" id="img_div" hidden="true">
		 	<img id="img_id" alt="图片缺失" src="" width="300px;" height="300px;" style="border:solid 1px black;">
	   	    <button type="button" class="layui-btn" id="upload"><i class="layui-icon">&#xe67c;</i>更换图片</button>
		</div>
		<input value="-1" type="hidden" name="item_imageId" id="imageId">
	 </div>
	 
	  
	  <div class="layui-form-item" pane >
	    <label class="layui-form-label">正确答案：</label>
	    <div class="layui-input-block" >
	    	<input type="radio"  name="item_option" value="true" title="✔">
	      	<input type="radio"  name="item_option" value="false" title="✖">
	    </div>
	  </div>
	  
	 
  
  </div>
	  <div class="r">
	  	<div style="margin-left: 600px;">
	      <button class="layui-btn" lay-submit lay-filter="subForm">修改</button>
	      <button id="radio_resetBtn" class="layui-btn layui-btn-primary" onclick="form_reset()">恢复</button>
	    </div>
	  </div>
  
   	  </form>
</div>
</body>
<script type="text/javascript">

layui.use(['form','upload','layer'],function(){
	var form = layui.form;
	var upload = layui.upload;
	var layer = layui.layer;


	//填充form表单
	form_full(form);
	

	var uploadInst = upload.render({
	    elem: '#upload'
    	,url: '${path }/upload/imageUpLoad' //上传接口
		,accept : 'images'
		,acceptMime: 'image/*'
	    ,done: function(res){
	      //上传完成  返回该文件id
	      $("#imageId").val(res.msg);  //文件id
	      $("#img_id").attr("src","/CPlusExamImage/"+res.data.src)
	      layer.msg("文件上传成功！");
	    }
	    ,error: function(){
	    	layer.msg("上传接口异常");
	    }
	  });



	form.on('submit(subForm)', function(data){
  	  	$.ajax({
		    url : "${path }/teacherItemList/updateJudgeItem/"+"${id }",
		    method : "post",
		    data : $('#edit_form').serialize(),
		    dataType : "json",
		    success : function(res){
				//关闭当前弹出层
				layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
					layer.close(index);
					var index=parent.layer.getFrameIndex(window.name);
				    parent.layer.close(index);
			    }); 
			}
		  });
		return false;
	  });

	
});

/***
 * 填充form表单
 */
function form_full(form){
	$.post("${path }/teacherItemList/getJudgeItemById",{id : "${id }"},function(res){
		var judge = res.extend.judge;
		$("#create_time").text(judge.create_time);
		if(judge.item_imageId==-1)
			$("#img_div").remove();
		else{
			$("#img_div").show();
			$("#img_id").attr("src","/CPlusExamImage/"+judge.upLoadFile.fileUUName+judge.upLoadFile.filTemp);	
		}
		//移除多余的选项  并进行正确答案的初始化
		form.val("edit_form", {
			  "item_name": judge.item_name
			  ,"item_imageId": judge.item_imageId
			  ,"item_option": judge.item_option
		}); 
		form.render('radio','edit_form');
	},"json")
};



//点击恢复按钮
function form_reset(){
	layui.use('form',function(){
		var form = layui.form;	
		form_full(form);   //进行form表单的重新初始化
	});
}

</script>

</html>