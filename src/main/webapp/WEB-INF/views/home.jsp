<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />

	<h1>This is the homepage!</h1>
	<h2>Data fetched from table students:</h2>
	</br>
	<c:forEach var="product" items="${products}">
		<p>
			<c:out value="${product} }"></c:out>
		</p>
	</c:forEach>
	Language : <a href="?lang=en">English</a> | <a href="?lang=de">German</a>

	<h3>
		<spring:message code="home.test_text" text="default text" />
	</h3>

	Current Locale: ${pageContext.response.locale}
<jsp:include page="comp/footer.jsp" />
