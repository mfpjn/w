<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container padding-header">
    <div class="row">
        <div class="grid-100">
            <h1>Your last Posts</h1>
        </div>
    </div>
    <div class="row">
        <ul>
            <c:forEach var="post" items="${postString}">
                <li>${post}</li>
            </c:forEach>
        </ul>
    </div>
</div>
<jsp:include page="comp/footer.jsp" />
