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
<body>
<div id="topPan">
	<a href="index.html"><img src="images/logo.gif" alt="Education Zone" width="245" height="37" border="0"  class="logo" title="Education Zone"/></a>
	<p>Application Development Code Reference Search engine</p>
		
<div id="topContactPan">
  </div>
	<div id="topMenuPan">
  	
  	<!-- Logout section here -->
 		<security:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
		<a href="j_spring_security_logout">Logout <security:authentication property="principal.username"/>  </a> 
		</security:authorize>

	
	</div>
</div>


		

	
	
<div id="bodyPan">
  <div id="bodyLeftPan">
  	<h2><span>search </span>  zone</h2>
		<p>Please enter your search criteria (Author or Book) in box to search....</p>
	<form action="search.html" method="get">
			<input name="searchText" type="text" size="60"></input>
			<br></br>
			
			<input type="submit"></input>
		</form>
  
	
	 
	<c:if test="${fn:length(searchbooks)>0}">	
	<h2><span>search</span> results</h2>
	 searching Books (total: ${fn:length(searchbooks)}) :
	
	    <div id="bookcatagories"></div>
	 	 <div id="namePan">Author</div>
	  	<div id="pricePan">Title</div> 
      	<div id="discountPan">Available</div>
	
	<c:forEach var="book" items="${searchbooks}" varStatus="index">
		  
      <div id="nameonePan">
	  	<ul>
			<li>${book.author}.</li>
		</ul>
	  </div>

	  <div id="priceonePan">
	  	<ul>
			<li>${book.title}.</li>
		</ul>
	  </div>
	  
	  
	  <div id="discountonePan">
	  	<ul>
		<!-- Check if book is available to reserve for the USER role 
			If it is then the user should be allowed to reserve the book
		-->
		<security:authorize ifAnyGranted="ROLE_USER">
		<c:if test="${book.available==true}">
		<li>
		<form action="reservebook?id=${book.id}&checkInOut=false&query=${param.searchText}" method="post">
			<input type="submit" value="reserve"></input>
		</form>
		</li>
		</c:if>
		</security:authorize>
		
		<!-- Check if login is ADMIN ROLE.
			If it is then they should be able to edit or delete the book
		-->
		<security:authorize ifAnyGranted="ROLE_ADMIN">
			<li><a href="editbook?id=${book.id}">edit</a></li>	
			<li><a href="deletebook?id=${book.id}&query=${param.searchText}">delete</a></li>	
		</security:authorize>
		
	
	
		</ul>
	  </div>
	  
  	</c:forEach>
  </c:if>
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
					 ${checkedoutbooks.title} 
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
