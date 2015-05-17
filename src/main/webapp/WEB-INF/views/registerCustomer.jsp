<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
		   uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="comp/head.jsp" />

<div class="container overlay-slide">
	<sf:form id="details" method="post" action="${pageContext.request.contextPath}/createCustomer" commandName="customer">
		<div class="row">
			<div class="grid-70 marg-15 tece">
				<sf:input path="firstName" name="firstName" type="text" placeholder="First Name"/>
				<sf:errors class="form-error" path="firstName"></sf:errors>
			</div>
		 </div>
		 <div class="row">
			<div class="grid-70 marg-15 tece">
				<sf:input path="lastName" name="lastName" type="text" placeholder="Last Name"/>
				<sf:errors class="form-error" path="lastName"></sf:errors>
			</div>
		 </div>		 		 
		<div class="row">
			<div class="grid-70 marg-15 tece">
				<sf:input path="email" name="email" type="text" placeholder="E-Mail"/>
				<sf:errors class="form-error" path="email"></sf:errors>
			</div>
		 </div>
		 <div class="row">
			 <div class="grid-70 marg-15 tece">
				<sf:input path="password" id="password" name="password" type="password" placeholder="Password"/>
				<sf:errors class="form-error" path="password"></sf:errors>
			 </div>
			 <div class="form-error" id="matchpass"></div>
		</div>
		<div class="row">
			<div class="grid-70 marg-15 tece">
			  <input name="confirmpassword" id="confirmpassword" type="password" placeholder="Confirm Password"/>
			</div>
		 </div>		 
		<div class="row">
			<div class="grid-70 marg-15 tece">
		  		<button class="button-green" type="submit">Register</button>
			</div>
		</div>
		<div class="row no-bot">
			<div class="grid-70 marg-15 tece" style="font-size: 14px;">
				By creating an account, you're agreeing with our Terms and Conditions and Privacy Statement.
			</div>
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
<jsp:include page="comp/blank-footer.jsp" />


