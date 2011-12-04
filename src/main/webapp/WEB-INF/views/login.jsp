<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Education Zone</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>
<div id="topPan">
	<a href="index.html"><img src="images/logo.gif" alt="Education Zone" width="245" height="37" border="0"  class="logo" title="Education Zone"/></a>
	<p>Application Development Code Reference Search engine</p>
		
<div id="topContactPan">
  </div>
	<div id="topMenuPan">
  	
	
	</div>
</div>

<div id="bodyPan">
  <div id="bodyLeftPan">
  	<h2><span>login </span>  zone</h2>
		<p>Please enter your login details below....</p>
	
  
  
  <security:authorize ifAnyGranted="ROLE_USER">
		<a href="j_spring_security_logout">Logout <security:authentication property="principal.username"/>  </a> 
		</security:authorize>
	<c:if test="${not empty error}">
		<div class="errorblock">
			<br />
			Your login attempt was not successful, please try again.
			<br /><br />
			The reason for this was : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 	<br /><br /><br />
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='post'>
 
		User:<input type='text' name='j_username' value='' /><br></br>
		Password:<input type='password' name='j_password' /><br></br>
			<input name="submit" type="submit"
					value="submit" />
			<input name="reset" type="reset" />
				
 
	</form>
	 
	
   
   
  </div>
 
  <div id="bodyRightPan">
  	<h2><span>most recent</span>books</h2>
	<ul>
	<li>Top 5 (JSON/REST)</li>
	
		<c:forEach var="book" items="${top5books}" varStatus="index">
					
			<li><a href="api/book/${book.id}">${index.count}. ${book.title}</a> </li>
	
		</c:forEach>
		
	</ul>
		
		<!-- Role based logout section here -->
 		
		<h3><span>library</span> tasks</h3>
		<p class="boldtext"></p>
		<ul>
		<li><a href="index">Search for Books</a> </li>
		<security:authorize ifAnyGranted="ROLE_ADMIN">
		<li><a href="createbook">Add New Book</a></li>
		</security:authorize>
		
		<security:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
		<li><a href="j_spring_security_logout">Logout <security:authentication property="principal.username"/>  </a> </li>
		</security:authorize>
		
		 <security:authorize ifNotGranted="ROLE_USER, ROLE_ADMIN">
		<li><a href="login">Login  </a> </li>
		</security:authorize>
	
		</ul>
		
		<!-- END  Role based logout section here -->
		
		<!-- My Books section based on book currently logged out to this user -->
		<security:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN"> 
		<c:if test="${fn:length(checkedoutbooks)>0}">	
		<h2><span>my</span> books</h2>
		<ul>
			<c:forEach var="checkedoutbooks" items="${checkedoutbooks}" varStatus="index">   	
				<li>
				<c:if test="${checkedoutbooks.available==false}">
				<form action="reservebook?id=${checkedoutbooks.id}&checkInOut=true&query=${param.searchText}" method="post">
					${index.count}. ${checkedoutbooks.title} 
					<input type="submit" value="return"></input>
				</form>
				</c:if>
				</li>
				
			</c:forEach>
			
		</ul>
		</c:if>
		</security:authorize>
		<!--END OF My Books section based on book currently logged out to this user -->		
 

  </div>
</div>

<div id="footermainPan">
  <div id="footerPan">
  	
	<p class="copyright">©CIT Application Frameworks Development.</p>
	<ul class="templateworld">
  	<li>design by:</li>
	<li><a href="http://www.templateworld.com" target="_blank">Template World</a></li>
  </ul>
  <div id="footerPanhtml"><a href="http://validator.w3.org/check?uri=referer" target="_blank">HTML</a></div>
    <div id="footerPancss"><a href="http://jigsaw.w3.org/css-validator/check/referer" target="_blank">css</a></div>
  </div>
</div>
</body>
</html>
