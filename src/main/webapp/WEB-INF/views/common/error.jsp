<%@ page isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<jsp:include page="/WEB-INF/views/common/common.jsp" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>JavaWeb快速开发框架</title>
  	</head>
	<body>
		报错时间：${errTime}</br>
		报错类型：${ex}</br>
		异常信息：${errInfo}</br>
		错误堆栈信息：${exceptionMsg}
	</body>
</html>
