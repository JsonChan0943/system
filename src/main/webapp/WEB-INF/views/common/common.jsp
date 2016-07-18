<%@ page isELIgnored="false" contentType="text/html;charset=utf-8" language="java" import="java.util.*"%>
<% 
	String path=request.getContextPath(); 
	request.setAttribute("path",path); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("basePath", basePath);
%>
<script type="text/javascript">
	var webroot = "${path}/";
</script>
<!-- ---------------------------------------------开发路径描述------------------------------------------------>
<!-- 1：当前页面是通过“${actionName}”类跳转到当前页面 -->
<!-- 2：具体的跳转方法为：“${actionMathod}” -->
<!-- 3：访问的命名空间为：“${namespace}” -->
<!-- 4：当前页面的地址：<%=request.getRequestURI()%>  -->
<!---- ----------------------------------------------END--------------------------------------------------->

<!-- [my97日期时间控件] -->
<script type="text/javascript" src="${path}/resources/js/plugin/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- [jQuery] -->
<script type="text/javascript" src="${path}/resources/js/plugin/jquery/jquery-1.8.2.min.js"></script>

<!-- [EasyUI] -->
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${path}/resources/js/plugin/jquery-easyui-1.4.2/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/js/plugin/jquery-easyui-1.4.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/js/plugin/jquery-easyui-1.4.2/demo/demo.css">
<script type="text/javascript" src="${path}/resources/js/plugin/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/plugin/jquery-easyui-extend/easyui-extend-chj.js"></script>
<script type="text/javascript" src="${path}/resources/js/plugin/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>