<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />


<h1>Ops! Something went wrong</h1>
    <h3>Application has encountered an error. Please contact support on ...</h3>
<br>

    <h3>Support may ask you to right click to view page source</h3>
<br>
<div class="row norm-bot">
    <div class="grid-20 marg-40">
        <a href="${pageContext.request.contextPath}/">
            <div class="button-gray">Home</div>
        </a>
    </div>
</div>

Language : <a href="?lang=en">English</a> | <a href="?lang=de">German</a>

<br>
<!--
<strong>Debug Information:</strong>
</br>

${name}: ${message}<br><br>

<strong>Exception Stack Trace:</strong><br>
<c:forEach items="${stc}" var="ste">
  ${ste}
</c:forEach>

-->

Current Locale: ${pageContext.response.locale}
<jsp:include page="comp/footer.jsp" />
