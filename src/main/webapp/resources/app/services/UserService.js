"use strict"
angular.module("MultiTiered").factory('UserService',
		[ '$q', '$http', '$log', function($q, $http, $log) {

			var getAllUsers = function() {
				var deferred = $q.defer();
				$http.get('users').success(function(data) {
					deferred.resolve(data);
				}).error(function(msg, code) {
					deferred.reject(msg);
					$log.error(msg, code);
				});
				return deferred.promise;
			}
			
			return {
				getAllUsers: getAllUsers
			}

		} ])