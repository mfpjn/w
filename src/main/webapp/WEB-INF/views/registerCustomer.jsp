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
		<div class="row">
			<div class="grid-70 marg-15 tece">
				<select name="facebook" id="facebookSelect">
					<option class="custom-option" value="0" >Do you want to use Facebook?</option>
					<option class="custom-option" value="1" >I want to use Facebook</option>
					<option class="custom-option" value="2" >I do not want to use Facebook</option>
				</select>
			</div>
		 </div>
		 <div id="facebookTokens">
			 <div class="row">
				<div class="grid-70 marg-15 tece">
					<input path="accesstoken" name="accesstoken" type="text" placeholder="Access Token"/>
<%-- 					<sf:errors class="form-error" path="accestoken"></sf:errors> --%>
				</div>
			 </div>	
			 <div class="row">
				<div class="grid-70 marg-15 tece">
					<input path="accesstokensecret" name="accesstokensecret" type="text" placeholder="Access Token Secret"/>
<%-- 					<sf:errors class="form-error" path="accestokensecret"></sf:errors> --%>
				</div>
			 </div>
		 </div>
		 <style>
		 	#facebookTokens {
		 		height: 0;
		 		visibility: hidden;
		 		overflow: hidden;
		 	}
		 </style>
		 <script>
		 	$(document).ready(function() {
		 		$('#facebookSelect').on('change', function () {
		 			var selected = $(this).val();
		 			if (selected == 1) {
		 				$('#facebookTokens').css('height', '165px');
		 				$('#facebookTokens').css('visibility', 'visible');
		 				$('#facebookTokens').css('overflow', 'visible');
		 			} else {
		 				$('#facebookTokens').css('height', '0');
			 			$('#facebookTokens').css('visibility', 'hidden');
			 			$('#facebookTokens').css('overflow', 'hidden');
		 			}
		 		})
		 	})
		 </script>		 
		 <div class="row">
			<div class="grid-70 marg-15 tece">
				<select name="twitter" id="twitterSelect">
					<option class="custom-option" value="0" >Do you want to use Twitter?</option>
					<option class="custom-option" value="1" >I want to use Twitter</option>
					<option class="custom-option" value="2" >I do not want to use Twitter</option>
				</select>
			</div>
		 </div>
		 <div id="twitterTokens">
		 	<div class="row">
				<div class="grid-70 marg-15 tece">
					<input path="accesstokenT" name="accesstokenT" type="text" placeholder="Access Token"/>
<%-- 					<sf:errors class="form-error" path="accestoken"></sf:errors> --%>
				</div>
			 </div>	
			 <div class="row">
				<div class="grid-70 marg-15 tece">
					<input path="accesstokensecretT" name="accesstokensecretT" type="text" placeholder="Access Token Secret"/>
<%-- 					<sf:errors class="form-error" path="accestokensecret"></sf:errors> --%>
				</div>
			 </div>
		 </div>
		 <style>
		 	#twitterTokens {
		 		height: 0;
		 		visibility: hidden;
		 		overflow: hidden;
		 	}
		 </style>
		 <script>
		 	$(document).ready(function() {
		 		$('#twitterSelect').on('change', function () {
		 			var selected = $(this).val();
		 			if (selected == 1) {
		 				$('#twitterTokens').css('height', '165px');
		 				$('#twitterTokens').css('visibility', 'visible');
		 				$('#twitterTokens').css('overflow', 'visible');
		 			} else {
		 				$('#twitterTokens').css('height', '0');
			 			$('#twitterTokens').css('visibility', 'hidden');
			 			$('#twitterTokens').css('overflow', 'hidden');
		 			}
		 		})
		 	})
		 </script>
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


