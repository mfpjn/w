<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container padding-header">
	<div class="row">
		<div class="grid-25">
			<sec:authorize access="isAuthenticated()">
			<a href="<c:url value ='/createoffer1'/>">
				<div id="donation-button" class="button-green"><div class="add-icon">+</div>Make Donation</div>
			</a>
			</sec:authorize>
		</div>
		<!--
		<div class="grid-20">
			<span class="custom-dropdown-blue">
				<select class="select-blue" name="orderBy">
					<option value="destance">Entfernung</option>
				</select>
			</span>
		</div>
		-->
	</div>
	<c:set var="count" value="0" scope="page" />
	<div class="row">
		<c:forEach var="product" items="${products}">
			<c:set var="count" value="${count + 1}" scope="page"/>
			<div class="grid-25">
				<a href="${pageContext.request.contextPath}/product?id=<c:out value="${product.id}"></c:out>">
				<div class="article-tile">
					<div class="grid-100 quart picture">
						<div class="article-name"><c:out value="${product.name}"></c:out></div>
					</div>
						<div class="row separator">
							<div class="grid-100"><div class="user-pic">&nbsp;</div>
								<div class="user-name"><c:out value="${product.customerFK.firstName}"></c:out> <c:out value="${product.customerFK.lastName}"></c:out></div>
								<fmt:formatNumber var="roundedPrice" type="number" minFractionDigits="0" maxFractionDigits="0" value="${product.price}" />
								<span class="price-tag">&euro; <c:out value="${roundedPrice}"></c:out></span>
							</div>
						</div>
					<div class="row no-bot">
						<div class="grid-100">
							<div class="ngo-group" style="background-image: url(${pageContext.request.contextPath}/resources/img/huaman-aid.svg); background-size: 10px auto">&nbsp;</div>
							<div class="ngo-group" style="background-image: url(${pageContext.request.contextPath}/resources/img/huaman-rights.svg)">&nbsp;</div>
							<div class="ngo-group" style="background-image: url(${pageContext.request.contextPath}/resources/img/handicap-help.svg); background-size: 12px auto">&nbsp;</div>
						</div>
					</div>
				</div>
				</a>
			</div>
			<c:if test="${count % 4 == 0}">
				</div><div class="row">
			</c:if>
		</c:forEach>
	</div>
	<!--
	Language : <a href="?lang=en">English</a> | <a href="?lang=de">German</a>

	<h3>
		<spring:message code="home.test_text" text="default text" />
	</h3>

	Current Locale: ${pageContext.response.locale}
	-->
</div>
<jsp:include page="comp/footer.jsp" />
