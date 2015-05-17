<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container padding-header">
	<div class="row">
		<div class="grid-100 tece">
			<h1>
				Change User settings
			</h1>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<c:set var="customer" value="${customer}"/>
			<sf:form id="details" method="post" action="${pageContext.request.contextPath}/updateCustomer" commandName="customer">
			<sf:input path="firstName"  name="firstName" type="text" placeholder="First name"/>
			<div class="grid-100 tece"><sf:errors class="form-error" path="firstName"></sf:errors></div>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<sf:input path="lastName"  name="lastName" type="text" placeholder="Last name"/>
			<div class="grid-100 tece"><sf:errors class="form-error" path="lastName"></sf:errors></div>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<sf:input path="title" name="title" type="text" placeholder="Title"/>
			<div class="grid-100 tece"><sf:errors class="form-error" path="title"></sf:errors></div>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<sf:input path="email" name="email" type="text" placeholder="Email"/>
			<div class="grid-100 tece"><sf:errors class="form-error" path="email"></sf:errors></div>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<sf:input path="dob" name="dob" type="text" placeholder="Date Of Birth"/>
			<div class="grid-100 tece"><sf:errors class="form-error" path="dob"></sf:errors></div>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<sf:input path="password" id="password" name="password" type="password"  placeholder="Enter the old password or a new one"/>
			<div class="form-error" id="matchpass"><sf:errors class="form-error" path="password"/></div>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<input name="confirmpassword" id="confirmpassword" type="password" placeholder="Confirm Password"/>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<sf:input path="language" name="language" type="text" placeholder="Language"/>
			<div class="grid-100 tece"><sf:errors class="form-error" path="language"></sf:errors></div>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<span class="custom-dropdown">
				<select name="states">
					<option class="custom-option" value="NONE" >States
						<c:forEach var="stateTranslations" items="${stateTranslations}">
					<option class="custom-option" <c:if test="${city.stateFK.id == stateTranslations.id}"> selected="selected" </c:if>  value='<c:out value="${stateTranslations.id}"/>'><c:out value="${stateTranslations.name}"/></option>
					</c:forEach>
					</option>
				</select>
			</span>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<input name="city" id="city" type="city" value="<c:out value = "${city.name}"/>" placeholder="City"/>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<input name="address" id="address" type="address" value="<c:out value = "${address.address}"/>" placeholder="Address"/>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<input name="address2" id="address2" type="address2" value="<c:out value = "${address.address2}"/>" placeholder="Address2"/>
		</div>
	</div>
	<div class="row small-bot">
		<div class="grid-30 marg-35">
			<input name="zipCode" id="zipCode" type="zipCode" value="<c:out value = "${address.zipCode}"/>" placeholder="Zip Code"/>
		</div>
	</div>
	<div class="row">
		<div class="grid-20 marg-40 norm-bot"><button class="button-green" type="submit">Update account</button></div>
	</div>
	</sf:form>
</div>

<script type="text/javascript">

	function onLoad()
	{
		$("#password").keyup(checkPasswordMatch);
		$("#confirmpassword").keyup(checkPasswordMatch);

		$("#details").submit(canSubmit);
	}

	function canSubmit(){
		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if(password != confirmpassword){
			alert("Passwords do not match!");
			return false;
		}else{
			return true;
		}
	}

	function checkPasswordMatch(){
		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if(password.length < 5 || confirmpassword.length < 5){
			return;
		}

		if(password == confirmpassword){
			$("#matchpass").text("Passwords match!");
		}else{
			$("#matchpass").text("Passwords do not match!");
		}
	}

	$(document).ready(onLoad);
</script>

<jsp:include page="comp/footer.jsp" />
   

