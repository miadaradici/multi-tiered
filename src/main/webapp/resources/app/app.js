'use strict';
var app = angular.module("MultiTiered", [ 'ngRoute', 'ngAnimate' ]);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : '/hello/resources/app/views/home.html',
		controller : 'MainController',
		resolve : {
			users : [ 'UserService', function(UserService) {
				return UserService.getAllUsers();
			} ]
		}
	}).when('/user', {
		templateUrl : '/hello/resources/app/views/user.html',
	}).otherwise({
		redirectTo : '/'
	});
} ]);