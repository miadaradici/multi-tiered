angular
		.module('endavaQuizApp')
		.directive(
				"simplequestion",
				function() {
					return {
						restrict : "A",
						templateUrl : "/endava-quiz/resources/app/directives/templates/simplequestion.html",
						scope : {
							position : '=',
							size : '=',
							paramScore : '=',
							param : '='
						},
						link : function($scope, $rootScope) {

							$scope.getQuestion = function() {
								return 'question' + $scope.position;
							};

							$scope.callAddAnswer = function() {
								$scope.$emit('addAnswerEvent', {
									parent : $scope.getQuestion(),
									position : $scope.position
								});
							}
						}
					};
				});