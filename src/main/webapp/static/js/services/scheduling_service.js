/**
 * 
 */
 
 'use strict';
 
 angular
 	.module('myApp')
 	.factory('SchedulingService', SchedulingService)
	
SchedulingService.$inject = ['$http', '$log']

function SchedulingService($http, $log) {
	var REST_SERVICE_URI = 'http://localhost:8080/ScheduleNow/scheduling/jobs/';

	var factory = {
		getAllJobs: getAllJobs,
		addJob: addJob,
		updateJob: updateJob,
		deleteJob: deleteJob
	}
		
	return factory;
		
	function getAllJobs() {
		return $http.get(REST_SERVICE_URI).then(
			function (res) {
				return res.data;
			},
			function (err) {
				$log.error('Error while fetching Jobs ', err);
			}
		)
	}
		
	function addJob(job) {
        return $http.post(REST_SERVICE_URI, job).then(
            function (res) {
		        return res.data;
	        },
	        function(err){
	            $log.error('Error while adding Job', err);
	        }
	    );
	 }
	 
	 function updateJob(job) {
		return $http.put(REST_SERVICE_URI + job.id, job).then(
			function(res) {
				return res.data;
			},
			function(err) {
				$log.error('Error while updating Job', err);
			}
		)
	}
	
	function deleteJob(id) {
		return $http.delete(REST_SERVICE_URI + id)
			.then(
				function(res) {
					return res.data;
				},
				function(err) {
					$log.error('Error while deleting Job', err);
				}
			)
	}
}