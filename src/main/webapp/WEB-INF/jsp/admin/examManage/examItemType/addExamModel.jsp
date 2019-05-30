<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="/public/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考卷题型添加页面</title>
</head>
<body class="layui-container">


	<div class="layui-card" style="margin-top: 20px;">
	  <div class="layui-card-header">已定义考题总分：<font id="counts" color="#FFB800">0</font></div>
	  <div class="layui-card-body">
	  
	  	 <fieldset class="layui-elem-field">
			  <legend>题库状态</legend>
			  <div class="layui-field-box">
				  <div style="margin-left: 150px;">
				  	<c:forEach items="${itemType }" var="type">
				  		<font size="4">${type.itemType_name }：共<font color="#01AAED"> ${type.itemCount }  </font> 题，状态： 
				  		<c:if test="${type.show_index == 1 }">
				  			<font color="#01AAED">可用</font>
				  			&nbsp;&nbsp;&nbsp;&nbsp;<button id="button${type.item_id }" onclick="addItem(${type.item_id },${type.def_score },${type.itemCount },'${type.itemType_name }')" class="layui-btn layui-btn-radius layui-btn-xs">&nbsp;<i class="layui-icon layui-icon-add-circle"></i>&nbsp;</button>
				  		</c:if>
				  		<c:if test="${type.show_index == 0 }">
				  			<font color="#01AAED">禁用</font>
				  			&nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-xs layui-btn-disabled">&nbsp;<i class="layui-icon layui-icon-add-circle"></i>&nbsp;</button>
				  		</c:if>
				  		</font><br>
				  	</c:forEach>
				  </div>
			  </div>
		</fieldset>
	  
		<form class="layui-form layui-form-pane" id="addForm">
			<div class="layui-form-item">
			    <label class="layui-form-label">试卷名称</label>
			    <div class="layui-input-block">
			      <input type="text" id="exam_name" name="exam_name" required  lay-verify="required" placeholder="请输入试卷名称" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  
			  <fieldset class="layui-elem-field">
				  <legend>考卷题型定义</legend>
				  <div class="layui-field-box" id="examModel">
				  	<!-- 动态填充 -->
				  </div>
				</fieldset> 
			  
			  <div class="layui-form-item" hidden="true">
			    <div class="layui-input-block">
			      <button class="layui-btn" id="subForm" lay-submit lay-filter="subForm">立即提交</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			  </div>
			  
		</form>
	    
	  </div>
	</div>

</body>
<script type="text/javascript">


layui.use(['form'],function(){
	var form = layui.form;
	
	
	
	form.on('submit(subForm)', function(data){
		var formData=$("#addForm").serializeArray();
		var jsonData1 = {};
		// 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
          $(formData).each(function () {
        	  if(this.name!="exam_name"){  //只封装考题类型数据 其它属性过滤掉
	              if (jsonData1[this.name]) {
	                  if ($.isArray(jsonData1[this.name])) {
	                      jsonData1[this.name].push(this.value);
	                  } else {
	                      jsonData1[this.name] = [jsonData1[this.name], this.value];
	                  }
	              } else {
	                  jsonData1[this.name] = this.value;
	              }
        	  }
          });
		// 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
        var vCount = 0;
        // 计算json内部的数组最大长度
        for(var item in jsonData1){
            var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
            vCount = (tmp > vCount) ? tmp : vCount;
        }
        var json;
        if(vCount > 1) {
	        var jsonData2 = new Array();
            for(var i = 0; i < vCount; i++){
                var jsonObj = {};
                for(var item in jsonData1) {
	                jsonObj[item] = jsonData1[item][i];
                }
                jsonData2.push(jsonObj);
            }
            json = JSON.stringify(jsonData2);
        }else{
        	json = "[" + JSON.stringify(jsonData1) + "]";
        }
        if(vCount==0){
        	layer.msg("请添加相关考题信息");
        }else{
        	$.ajax({
    			url : '${path }/adminExamModel/addExamModel/'+$("#exam_name").val(),
    			method : 'post',
    			data : json ,
    			dataType : 'json',
    			contentType : 'application/json;charset=utf-8',
    			success : function (res){
    				layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
    					layer.close(index);
    					var index=parent.layer.getFrameIndex(window.name);
    				    parent.layer.close(index);
    				    parent.location.reload();
    			    });  
    			}
    		});
        }  
		 
	    return false;
	});
});


// 动态添加考题选项
function addItem(id,score,count,name){
	$("#button"+id).addClass("layui-btn-disabled");
	var dom =$("<blockquote class='layui-elem-quote layui-quote-nm'> <fieldset class='layui-elem-field layui-field-title'> <legend>"+name+"</legend> </fieldset>"
			+"<div class='layui-form-item'>"
			+"<div class='layui-inline'> <label class='layui-form-label'>考题数量</label> <div class='layui-input-inline' style='width: 60px;'>"
			+"<input type='text' required  lay-verify='required' name='item_count' id='item_count"+id+"'  autocomplete='off' class='layui-input'><input type='hidden' name='item_typeid' value='"+id+"'></div>"
			+"<div class='layui-form-mid layui-word-aux'>考题有多少道</div> </div>"
			+"<div class='layui-inline'><label class='layui-form-label'>考题分数</label><div class='layui-input-inline' style='width: 60px;'>"
			+"<input type='text' required  lay-verify='required' id='item_score"+id+"' name='item_score' value='"+score+"' autocomplete='off' class='layui-input'></div><div class='layui-form-mid layui-word-aux'>考题的分值（每题）</div></div></div>"
			+"<div class='layui-form-item'><div class='layui-inline'><label class='layui-form-label'>展示顺序</label><div class='layui-input-inline' style='width: 60px;'>"
			+"<input type='text' required  lay-verify='required' name='show_index' autocomplete='off' class='layui-input'></div><div class='layui-form-mid layui-word-aux'>题型出现顺序</div></div>"
			+"<div class='layui-inline'><label class='layui-form-label'>总分</label><div class='layui-input-inline' style='width: 60px;'>"
			+"<input type='text' required  lay-verify='required' id='item_counts"+id+"' name='item_counts' autocomplete='off' class='layui-input layui-disabled' disabled value='0'></div>"
			+"<div class='layui-form-mid layui-word-aux'>该题型的总分</div></div></div></blockquote>");
	$("#examModel").append(dom);
	$("#item_count"+id).on('change',function(){
		if($("#item_count"+id).val()>count){
			layui.use(['layer'],function(){
				var layer = layui.layer;
				layer.msg("选题数量已超过题库数量！");
				$("#item_count"+id).val(count);
			});
		}
		$("#item_counts"+id).val($("#item_count"+id).val()*$("#item_score"+id).val());
		var inputArr=document.getElementsByName("item_counts");
		var sum=0;
		for(var i=0;i<inputArr.length;i++){
			sum += parseInt(inputArr[i].value);
		}
		$("#counts").text("");
		$("#counts").text(sum);
	});
	
	$("#item_score"+id).on('change',function(){
		$("#item_counts"+id).val($("#item_count"+id).val()*$("#item_score"+id).val());
		var inputArr=document.getElementsByName("item_counts");
		var sum=0;
		for(var i=0;i<inputArr.length;i++){
			sum += parseInt(inputArr[i].value);
		}
		$("#counts").text("");
		$("#counts").text(sum);
	});
};

    

	
  
  
    
  
  



</script>
</html>