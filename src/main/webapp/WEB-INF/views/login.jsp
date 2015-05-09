<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container">
		<c:if test="${param.error != null }">
				<p style="color: #e25d5e">Login failed. Reason: Incorrect username or password. Please try again.</p>
		</c:if>
	<form name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
		<div class="row">
			<div class="grid-50 tece"><input type='text' name='j_username' value='' placeholder="E-mail address"></div>
			<div class="grid-50 tece"><input type='password' name='j_password' placeholder="Password" /></div>
		</div>
		<div class="row no-bot">
			<div class="grid-100 tece"><a href="/home"><button name="submit" type="submit" class="button-green">Login</button></a></div>
		</div>	
	</form>
</div>
<jsp:include page="comp/footer.jsp" />

<script>
	$(document).ready(function() {
		document.f.j_username.focus();
	})
</script>
</html>