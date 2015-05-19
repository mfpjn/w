<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container padding-header" style="padding-bottom: 120px;">
	<div class="row no-bot">
		<div class="grid-100 tece">
			<h1>
				Forgot password
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="grid-100 tece">
			Enter your e-mail address in the field below
		</div>
	</div>
	<div class="row">

		<sf:form id="details" method="post" action="${pageContext.request.contextPath}/sendTempPass" commandName="customer">
			<div class="grid-30 marg-35">
			<sf:input path="email" name="email" type="text" placeholder="Email"/>
			</div>
	</div>
			<c:if test="${error == true}">
			<div class="row">
			<div class="grid-40 marg-30 tece">
				<label>The email address you have entered is incorrect. Please try again.</label>
			</div>
				</div>
			</c:if>

	<div class="row">
			<div class="grid-20 marg-40">
				<button class="button-green" type="submit">Reset Password</button>
			</div>
		</sf:form>
	</div>
</div>
<jsp:include page="comp/footer.jsp" />