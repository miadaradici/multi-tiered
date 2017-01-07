// create the controller and inject Angular's $scope
angular.module('endavaQuizApp').controller(
		'EvaluationController',
		[
				'$scope',
				'$route',
				'$http',
				'$routeParams',
				'$location',
				'$compile',
				'toaster',
				function($scope, $route, $http, $routeParams, $location,
						$compile, toaster) {
					$scope.message = 'Comming soon...';
				} ]);
