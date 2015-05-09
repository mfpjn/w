<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Workflow</title>

<!-- Stylesheets -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/outdatedbrowser/outdatedbrowser.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/jquery.fancybox.css?v=2.1.5"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/ladastock.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/workflow.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/libs/bootstrap-tagsinput/bootstrap-tagsinput.css" />

<!-- Fonts -->
<link
	href='http://fonts.googleapis.com/css?family=Fira+Sans:400,300,500,700'
	rel='stylesheet' type='text/css'>

<!-- Jquery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

<!-- Modernizr -->
<script src="${pageContext.request.contextPath}/resources/js/modernizr.js"></script>

<!-- Smartphone Optimierung -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no">

<!-- Fav Icon -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resoures/img/favicon.ico" type="image/x-icon">
<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resoures/img/favicon.ico" />

<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="${pageContext.request.contextPath}/resources/libs/bootstrap-3.1.1/css/bootstrap.min.css"> --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/dropzone.css">
</head>

<body onLoad="hideLoadingLayer();">
	<div id="loading-layer">
		<img id="loadinggif"
			src="${pageContext.request.contextPath}/resources/img/preloader.gif"
			alt="" width="250" border="0" style="margin-top: 20%;" />
	</div>
	<a id="top"></a>