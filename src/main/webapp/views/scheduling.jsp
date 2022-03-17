<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html lang="en">
  <head>  
    <title>ScheduleNow Scheduler</title>  
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
 
      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
 
    </style>
    	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
     	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
  </head>
  <body data-ng-app="myApp" class="ng-cloak">
  	<main>
	  	<div class="topnav">
			<a class="active" href="/ScheduleNow/">Home</a>
			<a href="/ScheduleNow/scheduling/">Scheduling</a>
		</div>
		
	    <div class="generic-container" data-ng-controller="SchedulingController as ctrl">
	          <div class="panel panel-default">
	              <div class="panel-heading"><h1><span class="lead">Welcome ${username}, Work Scheduling Form</span></h1></div>
	              <div class="formcontainer">
	                  <form data-ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                      <input type="hidden" data-ng-model="ctrl.job.id" />
	                      <div class="row">
	                          <div class="form-group col-md-12">
	                              <label class="col-md-2 control-lable" for="customer">Customer Name</label>
	                              <div class="col-md-7">
	                                  <input type="text" data-ng-model="ctrl.job.customerName" id="customer" class="customer-name form-control input-sm" placeholder="Enter customer's name" required data-ng-minlength="3"/>
	                                  <div class="has-error" data-ng-show="myForm.$dirty">
	                                      <span data-ng-show="myForm.customer.$error.required">This is a required field.</span>
	                                      <span data-ng-show="myForm.customer.$error.minlength">Minimum length required is 3.</span>
	                                      <span data-ng-show="myForm.customer.$invalid">This field is invalid </span>
	                                  </div>
	                              </div>
	                          </div>
	                      </div>
	                         
	                       
	                      <div class="row">
	                          <div class="form-group col-md-12">
	                              <label class="col-md-2 control-lable" for="address">Address</label>
	                              <div class="col-md-7">
	                                  <input type="text" data-ng-model="ctrl.job.address" id="address" class="form-control input-sm" placeholder="Enter the Address"/>
	                              </div>
	                          </div>
	                      </div>
	 
	                      <div class="row">
	                          <div class="form-group col-md-12">
	                              <label class="col-md-2 control-lable" for="email">Email</label>
	                              <div class="col-md-7">
	                                  <input type="email" data-ng-model="ctrl.job.email" id="email" class="email form-control input-sm" placeholder="Enter the Email"/>
	                                  <div class="has-error" data-ng-show="myForm.$dirty">
	                                      <span data-ng-show="myForm.email.$invalid">This field is invalid </span>
	                                  </div>
	                              </div>
	                          </div>
	                      </div>
	 
	 					<div class="row">
	                          <div class="form-group col-md-12">
	                              <label class="col-md-2 control-lable" for="phone">Phone</label>
	                              <div class="col-md-7">
	                                  <input type="text" data-ng-model="ctrl.job.phone" id="phone" class="phone form-control input-sm" placeholder="Enter the Phone Number" required/>
	                                  <div class="has-error" data-ng-show="myForm.$dirty">
	                           				<span data-ng-show="myForm.phone.$error.required">This is a required field.</span>
	                                  </div>
	                              </div>
	                          </div>
	                      </div>
	                      
	                      <div class="row">
	                          <div class="form-group col-md-12">
	                              <label class="col-md-2 control-lable" for="date">Date</label>
	                              <div class="col-md-7">
	                                  <input type="date" data-ng-model="ctrl.job.date" id="date" class="date form-control input-sm" required/>
	                                  <div class="has-error" data-ng-show="myForm.$dirty">
	                                      <span data-ng-show="myForm.description.$error.required">This is a required field. </span>
	                                  </div>
	                              </div>
	                          </div>
	                      </div>
	                      
	                      <div class="row">
	                          <div class="form-group col-md-12">
	                              <label class="col-md-2 control-lable" for="description">Description</label>
	                              <div class="col-md-7">
	                                  <input type="text" data-ng-model="ctrl.job.description" id="description" class="description form-control input-sm" placeholder="Enter the Description" required/>
	                                  <div class="has-error" data-ng-show="myForm.$dirty">
	                                      <span data-ng-show="myForm.description.$error.required">This is a required field. </span>
	                                  </div>
	                              </div>
	                          </div>
	                      </div>
	                      
	                      <div class="row">
	                          <div class="form-actions floatRight">
	                              <input type="submit"  value="{{!ctrl.job.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" data-ng-disabled="myForm.$invalid">
	                              <button type="button" data-ng-click="ctrl.reset()" class="btn btn-warning btn-sm" data-ng-disabled="myForm.$pristine">Reset Form</button>
	                          </div>
	                      </div>
	                  </form>
	              </div>
	          </div>
	          <div class="panel panel-default">
	                <!-- Default panel contents -->
	              <div class="panel-heading"><span class="lead">List of Jobs</span></div>
	              <div class="tablecontainer">
	                  <table class="table table-hover">
	                      <thead>
	                          <tr>
	                              <th>ID.</th>
	                              <th>Customer Name</th>
	                              <th>Address</th>
	                              <th>Email</th>
	                              <th>Phone</th>
	                              <th>Description</th>
	                              <th>Actions</th>
	                          </tr>
	                      </thead>
	                      <tbody>
	                          <tr data-ng-repeat="j in ctrl.jobs">
	                              <td><span data-ng-bind="j.id"></span></td>
	                              <td><span data-ng-bind="j.customerName"></span></td>
	                              <td><span data-ng-bind="j.address"></span></td>
	                              <td><span data-ng-bind="j.email"></span></td>
	                              <td><span data-ng-bind="j.phone"></span></td>
	                              <td><span data-ng-bind="j.description"></span></td>
	                           
	                              <td>
	                              <button type="button" data-ng-click="ctrl.update(j)" class="btn btn-success custom-width">Edit</button>  <button type="button" data-ng-click="ctrl.deleteJob(j.id)" class="btn btn-danger custom-width">Remove</button>
	                              </td>
	                          </tr>
	                      </tbody>
	                  </table>
	              </div>
	          </div>
	      </div>
	       
	      <script src="<c:url value='/static/js/app.js' />"></script>
	      <script src="<c:url value='/static/js/services/scheduling_service.js' />"></script>
	      <script src="<c:url value='/static/js/controllers/scheduling_controller.js' />"></script>
  	</main>
  </body>
</html>