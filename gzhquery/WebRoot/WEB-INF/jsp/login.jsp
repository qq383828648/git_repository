<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>人民币冠字号追踪系统</title>
		<link href="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/showloading/css/showLoading.css" type="text/css" media="screen"></link>
		<!-- <style type="text/css">
			div#activity_pane {
	            float: left;
	            width: 350px;
	            height: 200px;
	            border: 1px solid #CCCCCC;
	            background-color: #EEEEEE;
	            padding-top: 200px;
	            text-align: center;
        	}
		</style> -->
		<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/showloading/js/jquery.showLoading.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/DD_belatedPNG_0.0.8a-min.js"></script>
		<script>
			function loginSubmit(){
				var flag = document.getElementById("login").value;
				var usercode = document.getElementById("J_usercode").value;
				var userpassword = document.getElementById("J_userpassword").value;
				var errInfo = document.getElementById("errorInfo");
				if( usercode == "" || userpassword == "" ){
					errInfo.innerHTML = "<font style='color:red; font-size:12px;'>请输入正确的用户名和密码！</font>";
				}else{
				//$('#loginLoading').showLoading();                                    
					$.ajax({      
			  	 	  type: "POST",                                 
			  	 	  url: "${pageContext.request.contextPath}/GZHQueryServlet?method=login&login="+flag,
			  	 	  data: "usercode="+usercode+"&userpassword="+userpassword,
			  	 	  success: function(msg){
			  	 	  	if( msg == "success" ){
			  	 	  		errInfo.innerHTML = "";
			  	 	  		window.location = "${pageContext.request.contextPath}/GZHQueryServlet?method=query&login="+flag;
				  	 	}else if( msg == "errorLogin" ){
				  	 		errInfo.innerHTML = "<font style='color:red; font-size:12px;'>用户名或密码不正确！</font>";
				  	 	}else if( msg == "failed" ){
				  	 		errInfo.innerHTML = "<font style='color:red; font-size:12px;'>登录异常，请重试！</font>";
				  	 	}
		  	 		//$('#loginLoading').hideLoading();
					  }
		  	 	 	}); 
				}
			}
			
			/*让IE6 支持PNG透明图片*/
	        DD_belatedPNG.fix('div, ul, img, li, input , a');
	        
	        $(document).keyup(function(event){
			  if(event.keyCode ==13){
				loginSubmit();
			  }
			});
		</script>
	</head>

	<body id="loginLoading" leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 class=PageBody>
		<div>
		<input type="hidden" value=${login }  id = "login" name="login"/>
		<form method="post" name="actForm">
	    <div id="CenterAreaBg">
	        <div id="CenterArea">
	            <div id="LogoImg">
						<img border="0" src="${pageContext.request.contextPath}/style/blue/images/logo.png" />
					</div>
	            <div id="LoginInfo">
	                <table border=0 cellspacing=0 cellpadding=0 width=100%>
	                    <tr>
	                        <td width=45 class="Subject">
	                        	<img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" />
							</td>
	                        <td>
	                        	<input size="20" class="TextField" type="text" name="usercode" tabIndex="1" id="J_usercode"/>
							</td>
	                        <td rowspan="2" style="padding-left:10px;">
	                        	<input type="button" class="login_btn" onClick="return loginSubmit();"/>
							</td>
	                    </tr>
	                    <tr>
	                        <td class="Subject">
	                        	<img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/password.gif" />
							</td>
	                        <td>
	                        	<input size="20" class="TextField" type="password" name="userpassword" tabIndex="2" id="J_userpassword"/>
							</td>
	                    </tr>
	                    <tr align="center">
	                    	<td class="Subject">
							</td>
	                    	<td align="center">
	                    		<div id="errorInfo"><font style='color:#999999; font-size:12px;'>提示：请输入用户名和密码！</font></div>
	                    	</td>
	                    </tr>
	                </table>
	            </div>
	            <div id="CopyRight">
					<a href="javascript:void(0)">&copy;版权所有:深圳宝嘉电子设备有限公司  </a>
				</div>
	        </div>
	    </div>
	</form>
	</div>
	</body>
</html>
