<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div id="header">
	<div class="container">
		<div class="row no-bot">
			<div class="grid-50">

				<!-- Logout Button  -->
				<sec:authorize access="isAuthenticated()">
					<div class="grid-65 header_username">
						Hello
						<sec:authorize access="isAuthenticated()">
							<sec:authentication property="principal.username" />!
                        </sec:authorize>
					</div>
					<div class=" marg-5 grid-30">
						<a href="<c:url value ='/j_spring_security_logout'/>">
							<button class="button-green"
								style="line-height: 30px; height: 30px;">Log out</button>
						</a>
					</div>
				</sec:authorize>
				<!-- Login Button  -->
				<sec:authorize access="isAnonymous()">
					<div class="grid-30">
						<button id="login-button" class="button-green"
							style="line-height: 30px; height: 30px;">Log in</button>
					</div>
					<div class="grid-70">
						or <a href="${pageContext.request.contextPath}/registerCustomer">register</a>
					</div>
				</sec:authorize>

			</div>
			<div class="grid-50 teri">
				<a href="${pageContext.request.contextPath}/">Home</a> General
				Buttons
			</div>
		</div>
	</div>
</div>
<div id="login-overlay">
	<div class="container">
		<c:if test="${param.error != null }">
			<div class="row">
				<div class="grid-100 tece" style="color: #e25d5e">Login
					failed. Reason: Incorrect username or password. Please try again.</div>
			</div>
		</c:if>
		<form name='f'
			action='${pageContext.request.contextPath}/j_spring_security_check'
			method='POST'>
			<div class="row">
				<div class="grid-50 tece">
					<input type='text' name='j_username' value=''
						placeholder="E-mail address">
				</div>
				<div class="grid-50 tece">
					<input type='password' name='j_password' placeholder="Password" />
				</div>
			</div>
			<div class="row no-bot">
				<div class="grid-100 tece">
					<a href="/home">
						<button name="submit" type="submit" class="button-green">Login</button>
					</a>
				</div>
			</div>
		</form>
	</div>
</div>

<!-- Out of fixed -->

<div class="wrapper">
	<a id="top"></a>