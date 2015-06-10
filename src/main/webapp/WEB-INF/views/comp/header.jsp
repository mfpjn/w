<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div id="header">
	<div class="container">
		<div class="row no-bot">
				<!-- Logout Button  -->
				<sec:authorize access="isAuthenticated()">
					<div class="grid-45 header_username">
						<ul class="user-menu pointer">
							<li>
								<img class="user-pic" src="${pageContext.request.contextPath}/resources/img/unknown-user.jpg" style="margin-right: 15px;" /><span class="text-red">Hello,</span>&nbsp;
								<sec:authorize access="isAuthenticated()">
									<sec:authentication property="principal.username" /><img src="${pageContext.request.contextPath}/resources/img/dropdown-arrow-white.svg" style="width: 12px; margin-left: 15px; " />
								</sec:authorize>
							</li>
							<a href="<c:url value ='${pageContext.request.contextPath}/../editCustomerInformation/'/>">
								<li>
									<img src="${pageContext.request.contextPath}/resources/img/user-settings.svg" style="width: 12px; margin-right: 8px;" /> User Settings
								</li>
							</a>

							<a href="<c:url value ='/j_spring_security_logout'/>">
								<li>
									<img src="${pageContext.request.contextPath}/resources/img/user-profile.svg" style="width: 9px; margin-right: 8px;" /> Log out
								</li>
							</a>
						</ul>
					</div>
				</sec:authorize>
				<!-- Login Button  -->
				<sec:authorize access="isAnonymous()">
					<div class="grid-45">
						<button id="register-button" class="button-green" style="width: 30%; line-height: 30px; height: 30px; margin-right: 6px;">Register</button>
						or<span id="login-button" class="text-red pointer" style="margin-left: 10px; vertical-align: middle;">login</span>
					</div>
				</sec:authorize>
			<a href="${pageContext.request.contextPath}/"><div class="grid-10">&nbsp;</div></a>
			<div class="grid-10">&nbsp;</div>
			<div class="grid-10">&nbsp;</div>
			<div class="grid-10">&nbsp;</div>
		</div>
	</div>
</div>
<div id="overly-container">
	<div id="login-overlay" class="overlay">
		<div class="container padding-header">
			<c:if test="${param.error != null }">
				<script type="text/javascript">
					$(window).on('load', function () {
						$('#login-button').click();
					})
				</script>
				<div class="row">
					<div class="grid-100 tece" style="color: #FFFFFF">Login failed. Reason: Incorrect username or password. Please try again.</div>
				</div>
			</c:if>
			<form name='f'
				action='${pageContext.request.contextPath}/j_spring_security_check'
				method='POST'>
				<div class="row">
					<div class="grid-40 marg-30">
						<input type='text' name='j_username' value=''
							placeholder="E-mail address">
					</div>
				</div>
				<div class="row">
					<div class="grid-40 marg-30">
						<input type='password' name='j_password' placeholder="Password" />
					</div>
				</div>
				<div class="row small-bot">
					<div class="grid-30 marg-35">
						<a href="/home">
							<button name="submit" type="submit" class="button-green">Login</button>
						</a>
					</div>
				</div>
			</form>
			<div class="row no-bot">
				<div class="grid-100 tece"><a href="${pageContext.request.contextPath}/resetPassword/"><span class="resend-password">Forgot Password</span></a></div>
			</div>
		</div>
	</div>
	<div id="register-overlay" class="overlay">
		<div class="container padding-header">
		<iframe id="register-form" class="window-80" src="${pageContext.request.contextPath}/registerCustomer"></iframe>
		</div>
	</div>
</div>

<!-- Out of fixed -->

<div class="wrapper">
	<a id="top"></a>