/**
 * 
 */
 
 'use strict';
 
 angular.module('myApp').factory('SchedulingService', ['$http', '$q', function($http, $q){
	
	var REST_SERVICE_URI = 'http://localhost:8080/ScheduleNow/scheduling/jobs/';
	
	var factory = {
		getAllJobs: getAllJobs,
		addJob: addJob
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
}]);