<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery.js"></script>

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




 
	<c:set var="customer" value="${customer}"/>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/updateCustomer" commandName="customer"> 
  	<sf:input path="firstName"  name="firstName" type="text" placeholder="First name"/>
  	<div class="grid-100 tece"><sf:errors class="form-error" path="firstName"></sf:errors></div>
  	
  	<sf:input path="lastName"  name="lastName" type="text" placeholder="Last name"/>
  	<div class="grid-100 tece"><sf:errors class="form-error" path="lastName"></sf:errors></div>

	<sf:input path="title" name="title" type="text" placeholder="Title"/>
	<div class="grid-100 tece"><sf:errors class="form-error" path="title"></sf:errors></div>

  	<sf:input path="email" name="email" type="text" placeholder="Email"/>
	<div class="grid-100 tece"><sf:errors class="form-error" path="email"></sf:errors></div>

  	<sf:input path="password" id="password" name="password" type="password"  placeholder="Enter the old password or a new one"/>
  	<div class="form-error" id="matchpass"><sf:errors class="form-error" path="password"/></div>
 
	  <input name="confirmpassword" id="confirmpassword" type="password" placeholder="Confirm Password"/>  

  	<sf:input path="language" name="language" type="text" placeholder="Language"/>
    <div class="grid-100 tece"><sf:errors class="form-error" path="address"></sf:errors></div>

  <div class="grid-70  marg-15 norm-bot"><button class="button-green" type="submit">Update account</button></div>
</sf:form>


 
   

