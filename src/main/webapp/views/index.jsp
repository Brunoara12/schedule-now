<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html lang="en">
	<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1" isELIgnored="false"%>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
		
		<title>ScheduleNow</title>
	</head>
	<body>
		<main>
			<div class="topnav">
				<a class="active" href="/ScheduleNow/">Home</a>
				<a href="/ScheduleNow/scheduling/">Scheduling</a>
			</div>
			
			<h1>Welcome to ScheduleNow!</h1>
			<span>Task 1: Develop a new Web Application </span>
			
			<form action="login" method="POST" modelAttribute="user">
				<div class="formcontainer">
					<label for="username"><span><strong>Username</strong></span>
						<input id="username" type="text" placeholder="Enter username" name="username" required>
					</label>
					
					<label for="password"><span><strong>Password</strong></span>
						<input id="password" type="password" placeholder="Enter password" name="password" required>
					</label>
					
					<button type="submit">Login</button>
					<p class="has-error">${validation}</p>
				</div>
			</form>
		</main>
	</body>
</html>