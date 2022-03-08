<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1" isELIgnored="false"%>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
		
		<title>ScheduleNow</title>
	</head>
	<body>
		<div class="topnav">
			<a class="active" href="/ScheduleNow/">Home</a>
			<a href="/ScheduleNow/scheduling/">Scheduling</a>
		</div>
		
		<h1>Welcome to ScheduleNow!</h1>
		<span>Task 1: Develop a new Web Application </span>
		
		<form action="login" method="POST">
			<div class="formcontainer">
				<label for="username"><span><b>Username</b></span>
					<input type="text" placeholder="Enter username" name="username" required>
				</label>
				
				<label for="password"><span><b>Password</b></span>
					<input type="password" placeholder="Enter password" name="password" required>
				</label>
				
				<button type="submit">Login</button>
				<p class="has-error">${validation}</p>
			</div>
		</form>
		
	</body>
</html>