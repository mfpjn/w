<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container padding-header">
	<div class="row">
		<div class="grid-50">
			<a href="${pageContext.request.contextPath}/inputForm"><button
					class="button-green">Post to Platform</button> </a>
		</div>
		<div class="grid-50">
			<a href="${pageContext.request.contextPath}/receiverForm"><button
					class="button-green">Get from Platform</button> </a>
		</div>
	</div>
	<div class="row">
		<div class="grid-100 tece">
			<a href="${pageContext.request.contextPath}/updateTwitterForm"><button class="button-green" type="submit">Update Twitter
				Account</button></a>
				${success}
		</div>
	</div>
</div>
<jsp:include page="comp/footer.jsp" />
