<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container padding-header">
    <form action="${pageContext.request.contextPath}/sender" method="post">

        <input name="postMessage" type="text" placeholder="Enter Message here"/><br>
        <button class="button-green" type="submit">Post on Twitter!</button>
    </form>

</div>
<jsp:include page="comp/footer.jsp" />