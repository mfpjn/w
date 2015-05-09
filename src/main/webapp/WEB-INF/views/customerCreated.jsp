<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container">
	<div class="row">
		<div class="grid-100 tece"><h1>Your account has been created</h1></div>
	</div>
	<div class="row">
		<div class="grid-100 tece">
			<sf:form method="post" action="${pageContext.request.contextPath}/login" commandName="customer">
				<button class="button-green" type="submit">Go to login page</button>
			</sf:form>
		</div>
	</div>
</div>

<jsp:include page="comp/footer.jsp" />