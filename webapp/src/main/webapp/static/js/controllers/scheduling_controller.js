/**
 * 
 */
 
 'use strict';
 
angular
	.module('myApp')
	.controller('SchedulingController', SchedulingController) 

SchedulingController.$inject = ['SchedulingService','$log']

function SchedulingController(SchedulingService, $log){
	
	var vm = this;
	vm.job = {};
	vm.jobs = [];
	
	vm.submit = submit;
	vm.update = update;
	vm.deleteJob = deleteJob
	vm.reset = reset;
	
	getAllJobs();
	
	function getAllJobs() {
		SchedulingService.getAllJobs()
		.then(
			function(data) {
				vm.jobs = data;
				console.log(typeof(vm.jobs[0].dateTime))
				console.log(vm.jobs)
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
		vm.job.dateTime = vm.job.dateTime.getTime()
		if(!vm.job.id) {
			$log.log('Adding New Job', vm.job);
			addJob(vm.job);
		} else {
			updateJob(vm.job, vm.job.id)
		}
		
		reset()
	}
	
	function updateJob(job, id) {
		$log.log("job: " + id + " will be updated")
		SchedulingService.updateJob(job, id)
			.then(
				getAllJobs,
				function(err) {
					$log.error('Error while updating Job ', err)
				}
			)
	}
	
	function update(job){
		job.dateTime = new Date(job.dateTime)
		$log.log("job: " + angular.copy(job).id + " will be edited")
		vm.job = angular.copy(job);
	}
	
	function deleteJob(id) {
		$log.log("job: " + id + " will be deleted")
		if(id === vm.job.id){
			reset()
		}
		SchedulingService.deleteJob(id)
			.then(
				getAllJobs,
				function(err) {
					$log.error('Error while deleting Job ', err)
				}
			)
	}
	
	function reset() {
		vm.job = {}
	}
}