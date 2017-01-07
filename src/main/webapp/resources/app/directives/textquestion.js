angular
		.module('endavaQuizApp')
		.directive(
				"textquestion",
				function() {
					return {
						restrict : "A",
						templateUrl : "/endava-quiz/resources/app/directives/templates/textquestion.html",
						scope : {
							position : '=',
							size : '=',
							paramScore : '=',
							param : '='
						},
						link : function($scope, $rootScope) {
							$scope.getQuestion = function() {
								return 'question' + $scope.position;
							}

						}
					};
				});