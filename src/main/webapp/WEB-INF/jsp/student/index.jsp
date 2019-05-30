<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/public/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎访问</title>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo"><font size="5">C++</font></div>
		<ul id="head_menu" class="layui-nav layui-layout-left" lay-filter="head_menu_filter" style="margin-left: 100px;">
		</ul>
		
		 <ul class="layui-nav layui-layout-right" style="margin-right: 50px;">
		   <li class="layui-nav-item">
		     <a href="javascript:;">
		       	${userName } 
		     </a>
		     <dl class="layui-nav-child">
		       <dd><a href="javascript:userMsgPage()">基本资料</a></dd>
	           <dd><a href="javascript:updatePassword()">修改密码</a></dd>
		     </dl>
		   </li>
	      <li class="layui-nav-item"><a href="javascript:loginOut()">退出</a></li>
	    </ul>
	</div>
</div>

<!-- <div class="layui-fluid"> -->
	<iframe id="index_iframe" src="${path }/studentMenu/studentHome" scrolling="no" frameborder="0" style="width:100%;"></iframe>
<!-- </div> -->


<hr>
<div class="layui-footer" align="center">
	<!-- 底部固定区域 -->
    © CPlusExam.com - 2019 卢莹
</div>

</body>
<div hidden="true" id="userMsgDom" class="layui-fluid">

	<fieldset class="layui-elem-field" style="margin-top: 5px;">
	  <legend>用户信息</legend>
	  <div class="layui-field-box" align="center">
	   		<form class="layui-form" lay-filter="editForm" id="editForm" style="padding: 10px; margin-top: 10px;">

			  <div class="layui-form-item">
			    <label class="layui-form-label">编号：</label>
			    <div class="layui-input-block" >
			      <input style="width: 300px;" class="layui-input layui-disabled" disabled type="text" name="loginName1" id="loginName" required  lay-verify="required"  autocomplete="off" />
			    </div>
			  </div>
			  
			  <div class="layui-form-item" >
			    <label class="layui-form-label">姓名：</label>
			    <div class="layui-input-block" >
			      <input style="width: 300px;"  type="text" name="name" id="name" required  lay-verify="required"  autocomplete="off" class="layui-input" />
			    </div>
			  </div>
		  
			  <div class="layui-form-item ">
			    <label class="layui-form-label">性别：</label>
			    <div class="layui-input-block">
			      <input type="radio" name="sex" value="0" title="男" checked>
			      <input type="radio" name="sex" value="1" title="女" >
			    </div>
			  </div>
		  
			  <div class="layui-form-item ">
			    <label class="layui-form-label">联系方式：</label>
			    <div class="layui-input-block" >
			      <input style="width: 300px;"  type="text" name="phone" id="pnohe" required  lay-verify="phone" autocomplete="off" class="layui-input" />
			    </div>
			  </div>
			  
			  <div class="layui-form-item " >
			    <label class="layui-form-label">邮箱地址：</label>
			    <div class="layui-input-block" >
			      <input style="width: 300px;"  type="text" name="email" id="email" required  lay-verify="email" autocomplete="off" class="layui-input" />
			    </div>
			  </div>
		  
		  <div hidden="true" class="layui-form-item">
		    <div class="layui-input-block">
		      <input type="hidden" name="loginName" value="${loginName }"> 
		      <button class="layui-btn" id="subForm" lay-filter="subForm" lay-submit="">提交</button>
		      <button id="resetForm" type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
		</form>
	  </div>
	</fieldset>
	
</div>

<div hidden="true" id="userPasswordDom" class="layui-fluid">
		<fieldset class="layui-elem-field" style="margin-top: 5px;">
	  <legend>修改密码</legend>
	  <div class="layui-field-box" align="center">
	   		<form class="layui-form layui-form-pane" lay-filter="editPswForm" id="editPswForm" style="padding: 10px; margin-top: 10px;">
	   		
	   		<div class="layui-form-item" >
		      <label class="layui-form-label">旧密码</label>
		      <div class="layui-input-inline">
		        <input onchange="passwordChange()" type="password" name="old_password" id="old_password" required lay-verify="required" placeholder="请输入原密码" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    
		    <div class="layui-form-item" style="margin-top: 20px;">
		      <label class="layui-form-label">新密码</label>
		      <div class="layui-input-inline">
		        <input type="password" name="new_password" id="new_password" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    
		    
		    <div class="layui-form-item" style="margin-top: 20px;">
		      <label class="layui-form-label">确认密码</label>
		      <div class="layui-input-inline">
		        <input type="password" name="new1_password" id="new1_password" required lay-verify="required" placeholder="请确认新密码" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div hidden="true" class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" id="subForm2" lay-filter="subForm2" lay-submit="">提交</button>
			    </div>
		   </div>
	   	 	
	   		</form>
	  </div>
	</fieldset>
</div>
<script>
var userPassword = false;   //用户标注用户输入的是否正确
 function setIframeHeight(iframe) {
		if (iframe) {
			var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
			if (iframeWin.document.body) {
				iframe.height = iframeWin.document.documentElement.scrollHeight && iframeWin.document.body.scrollHeight+40;
				iframe.height = iframe.height<550?550:iframe.height
			}
		}
		};
window.setInterval("setIframeHeight(document.getElementById('index_iframe'))", 100);   //页面每0.1秒刷新一次  修改当前iframe页面的大小  因为iframe页面存在显示和隐藏的标签  所以只能用这种方式了  这里设计成了这样  就先这样了
/*  window.onload = function () {
	setIframeHeight(document.getElementById('index_iframe'));
	reinitIframe(); 
};   */
/*  function reinitIframe(){
	var iframe = document.getElementById("index_iframe");
	try{
	var bHeight = iframe.contentWindow.document.body.scrollHeight;
	var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
	var height = Math.max(bHeight, dHeight);
	iframe.height = height;
	var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
	console.log(iframeWin.document.documentElement.scrollHeight);
	console.log(iframeWin.document.body.scrollHeight);
	}catch (ex){}
	}
window.setInterval("reinitIframe()", 1000);  */

	layui.use('element', function() {
		var element = layui.element;
		renderMenu(element);
	});

	function renderMenu(element){
		//获取菜单树
		$.ajax({
			url : "${path }/studentMenu/getMenuTree",
			method : 'post',
			dataType : "json",
			success : function (res){
				$("#head_menu").find('span.layui-nav-bar').remove();
				var ul = document.getElementById("head_menu");
				var menuTree = res.extend.tree;
				menuTree.forEach(function(list,index){
					var li;
					if(index==0){
						li = $("<li class='layui-nav-item layui-this' ></li>");					
					}else{
						li = $("<li class='layui-nav-item'></li>");
					}
					var a;
					if(list.url!=null && list.url.length!=0){
						if(list.icon!=null){
							a = $("<a href='javascript:menuUrlClick(\""+list.url+"\")'><i class='layui-icon "+list.icon+"'></i>&nbsp;&nbsp"+list.name+"</a>");	
						}else{
							a = $("<a href='javascript:menuUrlClick(\""+list.url+"\")'>"+list.name+"</a>");	
						}
					} else{
						if(list.icon!=null){
							a = $("<a href='javascript:;'><i class='layui-icon "+list.icon+"'></i>&nbsp;&nbsp"+list.name+"</a>");	
						}else{
							a = $("<a href='javascript:;'>"+list.name+"</a>");	
						}
					}
					a.appendTo(li);
					if(list.children!=null){
						forEachMenu(list.children,li);
					}
					li.appendTo(ul);
				});
				element.render('nav','head_menu_filter');
			}
			
		});
	}

	function forEachMenu(treeData,dom){
		var dl = $("<dl class='layui-nav-child'></dl>");
		treeData.forEach(function(list,index){
			var dd = $("<dd></dd>");
			var a;
			if(list.url!=null && list.url.length!=0){
				if(list.icon!=null){
					a = $("<a href='javascript:menuUrlClick(\""+list.url+"\")'><i class='layui-icon "+list.icon+"'></i>&nbsp;&nbsp"+list.name+"</a>");	
				}else{
					a = $("<a href='javascript:menuUrlClick(\""+list.url+"\")'>"+list.name+"</a>");	
				}
			} else{
				if(list.icon!=null){
					a = $("<a href='javascript:;'><i class='layui-icon "+list.icon+"'></i>&nbsp;&nbsp"+list.name+"</a>");	
				}else{
					a = $("<a href='javascript:;'>"+list.name+"</a>");	
				}
			}
			a.appendTo(dd);
			/* if(list.children!=null){
				forEachMenu(list.children,dd,nbsp+"&nbsp;&nbsp;")
			} */
			dd.appendTo(dl);
		});
		dl.appendTo(dom);
	}

function menuUrlClick(url){
	$("#index_iframe").attr("src","${path }/"+url);
}	


function loginOut(){
	$.post("${path }/Login/loginOut",{},function(res){
		layui.use('layer', function(){
			var layer = layui.layer;
			layer.alert("退出成功",{icon: 0,closeBtn : 0 , title : '系统提示'},function(index){
				window.location.href="${path}/Login.jsp";
			});
		}); 
    },"json");    
}


//用户信息页面数据初始化
layui.use(['form'], function(){
	var form = layui.form;
	//form表单数据填充
	var url = "${path }/studentHome/getStudentById";
	$.post(url,{loginName : "${loginName }"},function(res){
		var user = res.extend.user;
		form.val("editForm", {
			  "loginName1" : user.loginName
			  ,"name": user.name
			  ,"sex": $("input[name=sex][value=0]").attr("checked", user.sex == 0 ? true : false)
			  ,"sex": $("input[name=sex][value=1]").attr("checked", user.sex == 1 ? true : false)
			  ,"phone" : user.phone
			  ,"email" : user.email
		}); 
		form.render('radio');
	},"json");

	form.on('submit(subForm)', function(data){
		  $.ajax({
			    url : "${path }/studentHome/updateStudent",
			    method : "post",
			    data : $('#editForm').serialize(),
			    dataType : "json",
			    success : function(res){
					//关闭当前弹出层
					layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
						layer.close(index);
						layer.close(layuiIndexMsg);
				    }); 
				}
			  });  
		  return false;
	  });

	form.on('submit(subForm2)', function(data){
		layui.use('layer',function(){
			var layer = layui.layer;
			if($("#new_password").val()==$("#new1_password").val()){
				 $.post("${path }/studentHome/updatePassword",{password : $("#new_password").val() , loginName : "${loginName }"},function(res){
					  layer.alert(res.msg,{closeBtn : 0 , title : '系统提示'},function(index){
							layer.close(index);
							layer.close(layuiIndexPas);
					    }); 
				  },"json");
			}else{
				layer.msg("两次密码不一致");	
			}
			 
		});
		  return false;
	  });
	
})



function userMsgPage(){
   	layui.use(['form','layer'], function(){
  		var layer = layui.layer;

		$("#userMsgDom").show();
	  		  layer.open({
	  			  title : '用户信息页面',
	  			  type: 1, 
	  			  area : ['500px','470px'],
	  			  shadeClose : true,
	  			  content: $('#userMsgDom') , 
	  			  btn : ['修改'],
	  			  yes : function(index, layero){
	      			  //进行信息的修改
	      			  layuiIndexMsg = index;
	      			  $("#subForm").click();
	  			  }, 
	  			  end : function(){
	  				  $("#userMsgDom").hide();
	  			  }
	  		  	});
   	});
    	  	
    	
};



function updatePassword(){
	$("#userPasswordDom").show();
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.open({
			  title : '密码修改页面',
			  type: 1, 
			  area : ['400px','380px'],
			  shadeClose : true,
			  content: $('#userPasswordDom') , 
			  btn : ['提交'],
			  yes : function(index, layero){
				  if(userPassword){
					  layuiIndexPas = index;
					  $("#subForm2").click();
				  }else{
					  	layer.msg("旧密码输入有误");
				  }
			  }, 
			  end : function(){
				  $("#userPasswordDom").hide();
				  $("#old_password").val("");
				  $("#new_password").val("");
				  $("#new1_password").val("");
			  }
		  });
	});    
};


//判断用户输入密码是否正确
function passwordChange(){
	
	$.post("${path }/studentHome/confimPasd",{password : $("#old_password").val() , loginName : "${loginName }"},function(res){
			if(res.success)
				userPassword = true;
			else{
				layui.use(['layer'], function(){
			  		var layer = layui.layer;
			  		layer.msg("旧密码输入有误");
					userPassword = false;
				});
			}
	},"json");
}
</script>
</html>