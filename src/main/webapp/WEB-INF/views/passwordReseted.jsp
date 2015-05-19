<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container padding-header" style="padding-bottom: 120px;">
    <div class="row no-bot">
        <div class="grid-100 tece">
            <h1>
                New password created
            </h1>
        </div>
    </div>
    <div class="row">
        <div class="grid-100 tece">
        A new password has been sent to your e-mail account. Please check your inbox.
        </div>
    </div>
    <div class="row">
        <div class="grid-20 marg-40">
            <a href="${pageContext.request.contextPath}/"><button class="button-gray" type="submit">
                Home
            </button></a>
        </div>
    </div>
</div>
<jsp:include page="comp/footer.jsp" />