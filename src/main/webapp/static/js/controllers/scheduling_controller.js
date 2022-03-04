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
	vm.job = {id: null, customerName: '', address: '', email: '',
		phone: '', data: '', description: ''};
	vm.jobs = [];
	
	vm.submit = submit;
	vm.update = update;
	vm.deleteJob = deleteJob
	vm.reset = reset;
	
	getAllJobs();
	
	function getAllJobs() {
		console.log("WRONG")
		SchedulingService.getAllJobs()
		.then(
			function(e) {
				vm.jobs = e;
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
		if(vm.job.id === null) {
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
	
	function update(id){
		$log.log("job: " + id + " will be edited")
		for(let i = 0; i < vm.jobs.length; i++){
			if(vm.jobs[i].id === id) {
				vm.job = angular.copy(vm.jobs[i])
				break;
			}
		}
		
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
		vm.job = {id: null, customerName: '', address: '', email: '',
		phone: '', data: '', description: ''}
	}
}