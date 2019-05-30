<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/public/base.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考题录入</title>
</head>
<body class="layui-container">
<fieldset class="layui-elem-field layui-anim layui-anim-fadein" style="margin-top:40px;">
  <legend id="input_title" style="color: #393D49; font-size: 5;">单选题录入说明</legend>
  <div id="input_remark" class="layui-field-box">
  	<div>	
	<font color="#FFB800" size="5">说明：</font><br>
	<div style="margin-top: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请按照录入格式要求，录入单选题，并在最后选择出正确答案，提交考题。</div><br>
	<font color="#FF5722" size="5">注意：</font><br>
	<div style="margin-top: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. 单选题必须有2~6个选项</div><br>
	<font color="#01AAED" size="5">示例：</font><br>
	<div style="margin-top: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至2019年，卢莹多大了？</div>
	<div style="margin-top: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	A:22岁&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	B:23岁&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	C:24岁&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	D:25岁</div>
	<div style="margin-top: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正确答案：B</div>
	</div>
  </div>
</fieldset>
<div class="layui-tab layui-tab-card layui-anim layui-anim-fadein" style="margin-top:40px;" lay-filter="tab_card">
  <ul class="layui-tab-title">
    <li class="layui-this">单选题</li>
    <li>多选题</li>
    <li>填空题</li>
    <li>判断题</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
	    <div class="layui-card">
		  <div class="layui-card-body" style="margin-left: 50px;margin-right: 50px;">
		  		<div><button class="layui-btn layui-btn-radius" onclick="radioAdd()"><i class="layui-icon layui-icon-add-circle"></i>添加选项</button></div>
			  <form class="layui-form layui-form-pane" style="margin-top: 20px;" id="radio_form" lay-filter="radio_filter">
	    		<div class="layui-form-item layui-form-text" >
				    <label class="layui-form-label">考题内容</label>
				    <div class="layui-input-block">
				      <textarea name="item_name" required  lay-verify="required" placeholder="请输入内容" class="layui-textarea"></textarea>
				    </div>
				 </div>
				 
				 <div class="layui-form-item">
				 	<button type="button" class="layui-btn" id="radio_upload">
					  <i class="layui-icon">&#xe67c;</i>上传相关图片
					</button>
					<input value="-1" type="hidden" name="item_imageId" id="radio_imageId">
				 </div>
				 
				  <div class="layui-form-item" id="option1">
				    <label class="layui-form-label">A：</label>
				    <div class="layui-input-block">
				      <input type="text" name="option1" required  lay-verify="required" placeholder="请输入选项内容" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-form-item" id="option2">
				    <label class="layui-form-label">B：</label>
				    <div class="layui-input-block" >
				      <input type="text" name="option2" required  lay-verify="required" placeholder="请输入选项内容" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-form-item" pane id="option_last">
				    <label class="layui-form-label">正确答案：</label>
				    <div class="layui-input-block" id="radio_item_option">
				      <input type="radio"  name="item_option" value="option1" title="A">
				      <input type="radio"  name="item_option" value="option2" title="B">
				    </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <div class="layui-input-block" style="margin-left: 35%;">
				      <button class="layui-btn" lay-submit lay-filter="radioSub">立即提交</button>
				      <button id="radio_resetBtn" type="reset" class="layui-btn layui-btn-primary" onclick="radio_reset()">重置</button>
				    </div>
				  </div>
	    	  </form>
		  
		  </div>
		</div>
    	
    </div>
    <div class="layui-tab-item">
    	<div class="layui-card">
		  <div class="layui-card-body" style="margin-left: 50px;margin-right: 50px;">
		  		<div><button class="layui-btn layui-btn-radius" onclick="checkAdd()"><i class="layui-icon layui-icon-add-circle"></i>添加选项</button></div>
			  <form class="layui-form layui-form-pane" style="margin-top: 20px;" id="check_form" lay-filter="check_filter">
	    		<div class="layui-form-item layui-form-text" >
				    <label class="layui-form-label">考题内容</label>
				    <div class="layui-input-block">
				      <textarea name="item_name" required  lay-verify="required" placeholder="请输入内容" class="layui-textarea"></textarea>
				    </div>
				 </div>
				 
				 <div class="layui-form-item">
				 	<button type="button" class="layui-btn" id="check_upload">
					  <i class="layui-icon">&#xe67c;</i>上传相关图片
					</button>
					<input value="-1" type="hidden" name="item_imageId" id="check_imageId">
				 </div>
				 
				  <div class="layui-form-item" id="check_option1">
				    <label class="layui-form-label">A：</label>
				    <div class="layui-input-block">
				      <input type="text" name="option1" required  lay-verify="required" placeholder="请输入选项内容" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-form-item" id="check_option2">
				    <label class="layui-form-label">B：</label>
				    <div class="layui-input-block" >
				      <input type="text" name="option2" required  lay-verify="required" placeholder="请输入选项内容" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-form-item" id="check_option3">
				    <label class="layui-form-label">C：</label>
				    <div class="layui-input-block" >
				      <input type="text" name="option3" required  lay-verify="required" placeholder="请输入选项内容" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-form-item" id="check_option4">
				    <label class="layui-form-label">D：</label>
				    <div class="layui-input-block" >
				      <input type="text" name="option4" required  lay-verify="required" placeholder="请输入选项内容" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-form-item" pane id="check_option_last">
				    <label class="layui-form-label">正确答案：</label>
				    <div class="layui-input-block" id="check_item_option">
				    	<input type="checkbox" value="option1" name="item_option" title="A" lay-skin="primary">
				    	<input type="checkbox" value="option2" name="item_option" title="B" lay-skin="primary">
				    	<input type="checkbox" value="option3" name="item_option" title="C" lay-skin="primary">
				    	<input type="checkbox" value="option4" name="item_option" title="D" lay-skin="primary">
				    </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <div class="layui-input-block" style="margin-left: 35%;">
				      <button class="layui-btn" lay-submit lay-filter="checkSub">立即提交</button>
				      <button id="check_resetBtn" type="reset" class="layui-btn layui-btn-primary" onclick="check_reset()">重置</button>
				    </div>
				  </div>
	    	  </form>
		  
		  </div>
		</div>
    
    </div>
    <div class="layui-tab-item">
    
    	<div class="layui-card">
		  <div class="layui-card-body" style="margin-left: 50px;margin-right: 50px;">
			  <form class="layui-form layui-form-pane" style="margin-top: 20px;" id="gap_form" lay-filter="gap_filter">
	    		<div class="layui-form-item layui-form-text" >
				    <label class="layui-form-label">考题内容</label>
				    <div class="layui-input-block">
				      <textarea name="item_name" required  lay-verify="required" placeholder="请输入内容" class="layui-textarea"></textarea>
				    </div>
				 </div>
				 
				 <div class="layui-form-item" hidden="true">
				 	<button type="button" class="layui-btn" id="gap_upload">
					  <i class="layui-icon">&#xe67c;</i>上传相关图片
					</button>
					<input value="-1" type="hidden" name="item_imageId" id="gap_imageId">
				 </div>
				 
				  
				  
				  <div class="layui-form-item">
				    <label class="layui-form-label">正确答案：</label>
				    <div class="layui-input-block" >
				      <input type="text" name="item_option" required  lay-verify="required" placeholder="请输入正确答案，多个答案之间，请用&nbsp;-&nbsp;隔开" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <div class="layui-input-block" style="margin-left: 35%;">
				      <button class="layui-btn" lay-submit lay-filter="gapSub">立即提交</button>
				      <button id="gap_resetBtn" type="reset" class="layui-btn layui-btn-primary" onclick="gap_reset()">重置</button>
				    </div>
				  </div>
	    	  </form>
		  
		  </div>
		</div>
    	
    </div>
    <div class="layui-tab-item">
    
    	<div class="layui-card">
		  <div class="layui-card-body" style="margin-left: 50px;margin-right: 50px;">
			  <form class="layui-form layui-form-pane" style="margin-top: 20px;" id="judge_form" lay-filter="judge_filter">
	    		<div class="layui-form-item layui-form-text" >
				    <label class="layui-form-label">考题内容</label>
				    <div class="layui-input-block">
				      <textarea name="item_name" required  lay-verify="required" placeholder="请输入内容" class="layui-textarea"></textarea>
				    </div>
				 </div>
				 
				 <div class="layui-form-item" hidden="true">
				 	<button type="button" class="layui-btn" id="judge_upload">
					  <i class="layui-icon">&#xe67c;</i>上传相关图片
					</button>
					<input value="-1" type="hidden" name="item_imageId" id="judge_imageId">
				 </div> 
				 
				  
				  
				  <div class="layui-form-item" pane>
				    <label class="layui-form-label">正确答案：</label>
				    <div class="layui-input-block" >
				    	<input type="radio"  name="item_option1" value="true" title="✔">
				      	<input type="radio"  name="item_option1" value="false" title="✖">
				    </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <div class="layui-input-block" style="margin-left: 35%;">
				      <button class="layui-btn" lay-submit lay-filter="judgeSub">立即提交</button>
				      <button id="judge_resetBtn" type="reset" class="layui-btn layui-btn-primary" onclick="judge_reset()">重置</button>
				    </div>
				  </div>
	    	  </form>
		  
		  </div>
		</div>
    
    </div>
  </div>
</div>
</body>
<script>
var input_title = ["单选题录入说明","多选题录入说明","填空题录入说明","判断题录入说明"];
var input_remark = [$("<div><font color='#FFB800' size='5'>说明：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请按照录入格式要求，录入单选题，并在最后选择出正确答案，提交考题。</div><br>"
	+"<font color='FF5722' size='5'>注意：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. 单选题必须有2~6个选项</div><br>"
	+"<font color='#01AAED' size='5'>示例：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至2019年，卢莹多大了？</div>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	+"A:22岁&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	+"B:23岁&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	+"C:24岁&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	+"D:25岁</div>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正确答案：B</div>"
	+"</div>"),
	$("<div><font color='#FFB800' size='5'>说明：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请按照录入格式要求，录入多选题，并在最后选择出正确答案，提交考题。</div><br>"
	+"<font color='FF5722' size='5'>注意：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. 多选题必须有4~6个选项<br>"
	+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. 多选题至少要有两个或以上的正确答案</div><br>"
	+"<font color='#01AAED' size='5'>示例：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卢莹喜欢吃什么？</div>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	+"A:凉皮肉夹馍配冰峰&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	+"B:串串火锅麻辣烫&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	+"C:汉堡可乐和薯条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	+"D:炒面炒饼路边摊</div>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正确答案：ABCD</div>"
	+"</div>"),
	$("<div><font color='#FFB800' size='5'>说明：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请按照录入格式要求，录入填空题，并在最后写入正确答案，提交考题。</div><br>"
	+"<font color='FF5722' size='5'>注意：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. 填空题允许有多个问题<br>"
	+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. 多个问题的答案输入时，请用&nbsp;-&nbsp;隔开</div><br>"
	+"<font color='#01AAED' size='5'>示例：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卢莹的姓是（），名是（）。</div>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正确答案：卢-莹"
	+"</div>"),
	$("<div><font color='#FFB800' size='5'>说明：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请按照录入格式要求，录入判断题，并在最后写入正确答案，提交考题。</div><br>"
	+"<font color='FF5722' size='5'>注意：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. 暂无注意事项<div><br>"
	+"<font color='#01AAED' size='5'>示例：</font><br>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卢莹是男生？</div>"
	+"<div style='margin-top: 15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正确答案：错"
	+"</div>")]

layui.use('element', function(){
  var element = layui.element;

	//监听选项卡切换操作
  element.on('tab(tab_card)', function(data){
	  var tab_index = data.index; //得到当前Tab的所在下标
	  /* $("#input_remark").remove(); */
	  $("#input_remark").html("");
	  $("#input_title").text(input_title[tab_index]);
	  $("#input_remark").append(input_remark[tab_index]);
  });
  
});



//  对单选题表单以及选项卡页面的相关操作

//因为除了AB之外  其余选项为动态填充  所以 在这里定义填充的内容
var radio_title = ["C","D","E","F"];
var radio_inputName = ["option3","option4","option5","option6"];
var radio_option = 2;  //选项个数  已经有AB两个
//添加选项按钮
function radioAdd(){
	radio_option++;
	if(radio_option<=6){
		var dom = $("<div class='layui-row' id='"+radio_inputName[radio_option-3]+"'><div class='layui-form-item layui-col-xs11 layui-col-sm11 layui-col-md11'>"
				+"<label class='layui-form-label'>"+radio_title[radio_option-3]+"：</label>"
				+"<div class='layui-input-block'>"
				+"<input type='text' id='option_input"+radio_option+"' name='"+radio_inputName[radio_option-3]+"' required  lay-verify='required' placeholder='请输入选项内容' autocomplete='off' class='layui-input'>"
				+"</div></div>"
				+"<div class='layui-col-xs1 layui-col-sm1 layui-col-md1'>"
				+"&nbsp;&nbsp;&nbsp;&nbsp;<span onclick='radioRemove("+radio_option+")' style='margin-top:10px; cursor: pointer;' class='layui-badge layui-bg-green'><i class='layui-icon layui-icon-delete'></i></span>"
				+"</div>" );
		$("#option_last").before(dom);
		var dom2 = $("<input type='radio'  name='item_option' value='"+radio_inputName[radio_option-3]+"' title='"+radio_title[radio_option-3]+"'>");
		$("#radio_item_option").append(dom2);
		//更新渲染radio_filter
		layui.use(['form'],function(){
			var form = layui.form;
			form.render('radio', 'radio_filter');
		})
	}else{
		layui.use('layer',function(){
			var layer = layui.layer;
			layer.msg("最多添加6个选项");
			radio_option = 6;
		})
	}
	
}

//移除按钮
function radioRemove(index){
	var option_index = index;
	layui.use('layer',function(){
		var layer = layui.layer;
		layer.confirm("确定要移除"+radio_title[index-3]+"选项吗？",{icon: 3, title:'提示'},function(index){
			//移除该选项  并将其他选项修改  按ABC这样排列
			$("#option"+option_index).remove();
			option_index++;  //处理选项之后的选项   标题  name属性
			for(option_index;option_index<=radio_option;option_index++){
				var input = $("#option_input"+option_index).val();
				$("#option"+option_index).remove();
				var dom =  $("<div class='layui-row' id='"+radio_inputName[option_index-4]+"'><div class='layui-form-item layui-col-xs11 layui-col-sm11 layui-col-md11'>"
						+"<label class='layui-form-label'>"+radio_title[option_index-4]+"：</label>"
						+"<div class='layui-input-block'>"
						+"<input type='text' id='option_input"+(option_index-1)+"' name='"+radio_inputName[option_index-4]+"' required  lay-verify='required' placeholder='请输入选项内容' autocomplete='off' class='layui-input'>"
						+"</div></div>"
						+"<div class='layui-col-xs1 layui-col-sm1 layui-col-md1'>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;<span onclick='radioRemove("+(option_index-1)+")' style='margin-top:10px; cursor: pointer;' class='layui-badge layui-bg-green'><i class='layui-icon layui-icon-delete'></i></span>"
						+"</div>" );
				$("#option_last").before(dom);
				$("#option_input"+(option_index-1)).val(input);
			} 
			//移除了一个
			radio_option--;
			//进行正确答案的控制  先删除所有  在根据选项的个数  重新添加单选按钮
			$("#radio_item_option").html("");
			var A = $("<input type='radio'  name='item_option' value='option1' title='A' >");
			var B = $("<input type='radio'  name='item_option' value='option2' title='B' >");
			$("#radio_item_option").append(A);
			$("#radio_item_option").append(B);
			for(var temp=3;temp<=radio_option;temp++){
				var dom = $("<input type='radio'  name='item_option' value='"+radio_inputName[temp-3]+"' title='"+radio_title[temp-3]+"'>");
				$("#radio_item_option").append(dom);
			} 
			//更新渲染radio_filter
			layui.use(['form'],function(){
				var form = layui.form;
				form.render('radio', 'radio_filter');
			})
			layer.close(index);
		});
	})
}

//单选题重置按钮
function radio_reset(){
	//将文件数据框也重置
	$("#radio_imageId").val("-1"); 
}


//**********单选题结束


//对多选题的所有动态操作
//已有4个选项  其余选项为动态填充  所以 在这里定义填充的内容
var check_title = ["E","F"];
var check_inputName = ["check_option5","check_option6"];
var check_option = 4;  //选项个数  已经有四个
//添加按钮
function checkAdd(){
	check_option++;
	if(check_option<=6){
		var dom = $("<div class='layui-row' id='"+check_inputName[check_option-5]+"'><div class='layui-form-item layui-col-xs11 layui-col-sm11 layui-col-md11'>"
				+"<label class='layui-form-label'>"+check_title[check_option-5]+"：</label>"
				+"<div class='layui-input-block'>"
				+"<input type='text' id='check_option_input"+check_option+"' name='option"+check_option+"' required  lay-verify='required' placeholder='请输入选项内容' autocomplete='off' class='layui-input'>"
				+"</div></div>"
				+"<div class='layui-col-xs1 layui-col-sm1 layui-col-md1'>"
				+"&nbsp;&nbsp;&nbsp;&nbsp;<span onclick='checkRemove("+check_option+")' style='margin-top:10px; cursor: pointer;' class='layui-badge layui-bg-green'><i class='layui-icon layui-icon-delete'></i></span>"
				+"</div>" );
		$("#check_option_last").before(dom);
		var dom2 = $("<input type='checkbox' lay-skin='primary'  name='item_option' value='option"+check_option+"' title='"+check_title[check_option-5]+"'>");
		$("#check_item_option").append(dom2);
		//更新渲染radio_filter
		layui.use(['form'],function(){
			var form = layui.form;
			form.render('checkbox', 'check_filter');
		})
	}else{
		layui.use('layer',function(){
			var layer = layui.layer;
			layer.msg("最多添加6个选项");
			check_option = 6;
		})
	}
	
}

//移除按钮
function checkRemove(index){
	var option_index = index;
	layui.use('layer',function(){
		var layer = layui.layer;
		layer.confirm("确定要移除"+check_title[index-5]+"选项吗？",{icon: 3, title:'提示'},function(index){
			//移除该选项  并将其他选项修改  按ABC这样排列
			$("#check_option"+option_index).remove();
			option_index++;  //处理选项之后的选项   标题  name属性
			for(option_index;option_index<=check_option;option_index++){
				var input = $("#check_option_input"+option_index).val();
				$("#check_option"+option_index).remove();
				var dom =  $("<div class='layui-row' id='"+check_inputName[option_index-6]+"'><div class='layui-form-item layui-col-xs11 layui-col-sm11 layui-col-md11'>"
						+"<label class='layui-form-label'>"+check_title[option_index-6]+"：</label>"
						+"<div class='layui-input-block'>"
						+"<input  type='text' id='check_option_input"+(option_index-1)+"' name='option"+(option_index-1)+"' required  lay-verify='required' placeholder='请输入选项内容' autocomplete='off' class='layui-input'>"
						+"</div></div>"
						+"<div class='layui-col-xs1 layui-col-sm1 layui-col-md1'>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;<span onclick='checkRemove("+(option_index-1)+")' style='margin-top:10px; cursor: pointer;' class='layui-badge layui-bg-green'><i class='layui-icon layui-icon-delete'></i></span>"
						+"</div>" );
				$("#check_option_last").before(dom);
				$("#check_option_input"+(option_index-1)).val(input);
			} 
			//移除了一个
			check_option--;
			//进行正确答案的控制  先删除所有  在根据选项的个数  重新添加单选按钮
			$("#check_item_option").html("");
			var A = $("<input type='checkbox'  name='item_option' value='option1' title='A' lay-skin='primary'>");
			var B = $("<input type='checkbox'  name='item_option' value='option2' title='B' lay-skin='primary'>");
			var C = $("<input type='checkbox'  name='item_option' value='option3' title='C' lay-skin='primary'>");
			var D = $("<input type='checkbox'  name='item_option' value='option4' title='D' lay-skin='primary'>");
			$("#check_item_option").append(A);
			$("#check_item_option").append(B);
			$("#check_item_option").append(C);
			$("#check_item_option").append(D);
			for(var temp=5;temp<=check_option;temp++){
				var dom = $("<input type='checkbox' lay-skin='primary'  name='item_option' value='option"+temp+"' title='"+check_title[temp-5]+"'>");
				$("#check_item_option").append(dom);
			}
			//更新渲染radio_filter
			layui.use(['form'],function(){
				var form = layui.form;
				form.render('checkbox', 'check_filter');
			}) 
			layer.close(index);
		});
	})
}
//多选题重置按钮
function check_reset(){
	//将文件数据框也重置
	$("#check_imageId").val("-1");
}

//********多选题结束

//填空题相关动态操作

//填空题重置按钮
function gap_reset(){
	$("#gap_imageId").val("-1");
}


//************填空题结束

//判断题重置按钮
function judge_reset(){
	$("#judge_imageId").val("-1");
}


//************填空题结束

//表单提交
layui.use(['form','layer'],function(){
	var form = layui.form;
	var layer = layui.layer;

	//监听单选题提交按钮
  	form.on('submit(radioSub)', function(data){
  	  	//进行表单提交
  	  	var radio_val;
  	    radio_val = $('input:radio[name="item_option"]:checked').val();
  	  	if(radio_val!=null){
  	  		$.ajax({
			    url : "${path }/teacherItemInput/addRadioItem",
			    method : "post",
			    data : $('#radio_form').serialize(),
			    dataType : "json",
			    success : function(res){
					layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
						layer.close(index);
						$("#radio_resetBtn").click();
				    });  
				}
			}); 
  	  	}else{
  	  		layer.msg("请选择正确答案！");
  	  	}
  	  	
  	  	
	   	 return false;
 	 });

  //监听多选题提交按钮
  	form.on('submit(checkSub)', function(data){
  		var check_val;
  		check_val = $('input:checkbox:checked').length;
  	    if(check_val==0){
  	    	layer.msg("请选择正确答案！");
  	  	}else if(check_val==1){
  	  		layer.msg("正确答案个数必须大于两个！");
  	  	}else{
  	  	//进行表单提交
  	  	  	$.ajax({
  			    url : "${path }/teacherItemInput/addCheckItem",
  			    method : "post",
  			    data : $('#check_form').serialize(),
  			    dataType : "json",
  			    success : function(res){
  					layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
  						layer.close(index);
  						$("#check_resetBtn").click();
  				    });  
  				}
  			});  
  	  	}
	   	 return false;
 	 });

  //监听填空题提交按钮
  	form.on('submit(gapSub)', function(data){
  	  	//进行表单提交
	 	  $.ajax({
		    url : "${path }/teacherItemInput/addGapItem",
		    method : "post",
		    data : $('#gap_form').serialize(),
		    dataType : "json",
		    success : function(res){
				layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
					layer.close(index);
					$("#gap_resetBtn").click();
			    }); 
			}
		 });
	   	 return false;
 	 });

  	//监听判断题提交
  	form.on('submit(judgeSub)', function(data){
  	  	//进行表单提交
  	  	var radio_val;
  	    radio_val = $('input:radio[name="item_option1"]:checked').val();
  	  	if(radio_val!=null){
  	  		$.ajax({
			    url : "${path }/teacherItemInput/addJudgeItem",
			    method : "post",
			    data : $('#judge_form').serialize(),
			    dataType : "json",
			    success : function(res){
					layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
						layer.close(index);
						$("#judge_resetBtn").click();
				    });  
				}
			});  
  	  	}else{
  	  		layer.msg("请选择正确答案！");
  	  	}
  	  	
  	  	
	   	 return false;
 	 });
})



//文件上传
layui.use(['upload','layer'], function(){
	  var upload = layui.upload;
	  var layer = layui.layer;
	  //单选框的文件上传
	  var radio_update = upload.render({
	    elem: '#radio_upload' //绑定元素
	    ,url: '${path }/upload/imageUpLoad' //上传接口
		,accept : 'images'
		,acceptMime: 'image/*'
	    ,done: function(res){
	      //上传完成  返回该文件id
	      $("#radio_imageId").val(res.msg);  //文件id
	      layer.msg("文件上传成功！");
	    }
	    ,error: function(){
	    	layer.msg("上传接口异常");
	    }
	  });

	  var check_update = upload.render({
		    elem: '#check_upload' //绑定元素
		    ,url: '${path }/upload/imageUpLoad' //上传接口
			,accept : 'images'
			,acceptMime: 'image/*'
		    ,done: function(res){
		      //上传完成  返回该文件id
		      $("#check_imageId").val(res.msg);  //文件id
		      layer.msg("文件上传成功！");
		    }
		    ,error: function(){
		    	layer.msg("上传接口异常");
		    }
	 });
});
</script>
</html>