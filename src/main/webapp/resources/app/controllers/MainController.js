"use strict"
angular.module("MultiTiered").controller('MainController',
		[ '$scope', 'users', function($scope, users) {
				$scope.users = users;
		} ])