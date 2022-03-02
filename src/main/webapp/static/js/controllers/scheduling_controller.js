/**
 * 
 */
 
 'use strict';
 
angular.module('myApp').controller('SchedulingController', ['$scope', 'SchedulingService', function($scope, SchedulingService) {
	
	this.job = {id: null};
	this.jobs = [];
	this.submit = submit;
	
	getAllJobs();
	
	function getAllJobs() {
		SchedulingService.getAllJobs()
		.then(
			function(e) {
				self.jobs = e;
			},
			function(err){
				$log.error('Error while fetching Jobs ', err);
			}
		)
	}
	
	function addJob(job) {
		SchedulingService.addJob(job)
		.then(
			getAllJobs,
			function(err) {
				$log.error('Error while adding Job ', err);
			}
		)
	}
	
	function submit() {
		if(this.job === null) {
			$log.log('Adding New Job', this.job);
			addJob(this.job);
		}
	}
}]);