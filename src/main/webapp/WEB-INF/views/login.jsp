<!DOCTYPE html>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<html>
<head>
    <title>上海宝冶集团汽车租赁管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${path}/resources/style/login/style/alogin.css" rel="stylesheet" type="text/css" />
</head>
<body onkeydown="handleEnter(event)">
    <form id="loginForm" method="post">
	    <div class="Main">
	        <ul>
	            <li class="top"></li>
	            <li class="top2"></li>
	            <li class="topA"></li>
	            <li class="topB">
	            	<span><img src="${path}/resources/style/login/images/login/logo.gif" alt="" style="" /></span>
            	</li>
	            <li class="topC"></li>
	            <li class="topD">
	                <ul class="login">
	                	<li>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    	<span id="userTip" style="color:red"></span>
                    	</li>
	                 	<li>
	                    	<span class="left">用户名：</span> 
	                    	<span style="left">
	                    		<input id="user_login_name_id" class="easyui-validatebox txt" data-options="required:true" name="user_login_name" type="text"/>
                    		</span>
                    	</li>
	                    <li>
	                    	<span class="left">密 码：</span>
	                        <span style="left">
	                        	<input id="password_id" class="easyui-validatebox txt" data-options="required:true" name="password" type="password"/>
                        	</span>
                        </li>
                        <li>
	                    	<span class="left">验证码：</span>
	                        <span style="left">
                        		<img id="verifyCodeImg" onclick="javascript:refresh();" style="width:80px;height:26px;"/>
	                        	<input id="verifyCode_id" name="verifyCode" class="easyui-validatebox txt" data-options="required:true"id="user_login_name_id"  style="width:124px;"/>
                        	</span>
                        </li>
                    </ul>
	            </li>
	            <li class="topE"></li>	         
	            <li class="middle_A"></li>
	            <li class="middle_B"></li>	    
	            <li class="middle_C">
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<input id="register" type="button" value="" onclick="login();"/>
	    		 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    		 	<input id="reset" type="button" value="" onclick="loginReset();"/>
	            </li>
	            <li class="middle_D"></li>
	            <li class="bottom_A"></li>
	            <li class="bottom_B">
	            	经纬科技&copy版权所有
	            </li>
	        </ul>
	    </div>
    <form>
<script type="text/javascript" src="${path}/resources/js/ui/login.js"></script>
</body>
</html>
