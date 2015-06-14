<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container padding-header">
	<div class="row">
		<div class="grid-100 tece">
			<form action="${pageContext.request.contextPath}/updateTwitter"
				method="post">
				<div class="row">
					<div class="grid-100">
						<input placeholder="Pin from Twitter" name="twitterPin" type="text"/>
					</div>
				</div>
				<div class="row">
					<div class="grid-100">
						<input class="button-green" type="submit" value="Validate Pin"/>
						${error}
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="comp/footer.jsp" />
