<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="comp/head.jsp" />
<style>
	body {
		background-color: #FF2F40;
	}
</style>
<div class="container overlay-slide">
	<div class="row">
		<div class="grid-100 tece"><h1 style="color: #FFFFFF;">Your account has been created</h1></div>
	</div>
	<div class="row">
		<div class="grid-30 marg-35 tece">
				<button id="loginButton" class="button-green" type="submit">Home</button>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function () {
		$('#loginButton').click(function (){
			window.top.location.href = "${pageContext.request.contextPath}/";
		})
	})
</script>

<jsp:include page="comp/blank-footer.jsp" />