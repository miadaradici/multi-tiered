angular
		.module('endavaQuizApp')
		.config(
				[
						'$httpProvider',
						function($httpProvider) {
							$httpProvider.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8';
							$httpProvider.defaults.headers.common['X-CSRF-Token'] = $(
									'meta[name=csrf-token]').attr('content');
						} ])
		.controller('mainController',
				function($scope, $route, $routeParams, $location) {
					// do stuff
				})
		.config(
				[
						'$provide',
						function($provide) {
							$provide
									.decorator(
											'taOptions',
											[
													'$delegate',
													function(taOptions) {
														taOptions.toolbar = [
																[
																		'bold',
																		'italics',
																		'underline',
																		'ul',
																		'ol',
																		'clear' ],
																[
																		'justifyLeft',
																		'justifyCenter',
																		'justifyRight' ],
																[
																		'insertImage',
																		'insertLink' ] ];
														return taOptions;
													} ]);
						} ])
		.config(
				[
						'$routeProvider',
						function($routeProvider) {
							$routeProvider

									// quizzes
									.when(
											'/quizzes',
											{
												templateUrl : '/endava-quiz/resources/app/views/pages/quizzes.html',
												controller : 'QuizController'
											})
									// evaluation
									.when(
											'/evaluation',
											{
												templateUrl : '/endava-quiz/resources/app/views/pages/evaluation.html',
												controller : 'EvaluationController'
											});

							$routeProvider.otherwise({
								redirectTo : '/quizzes'
							});
						} ]).controller(
				'configCtrl',
				function($scope, $http) {
					// test variable

					$scope.memberEntity = {};
					$http.get("http://localhost:8080/endava-quiz/test")
							.success(function(data) {
								$scope.memberEntity = data;
							});

					$scope.testPost = function(memberEntity) {
						$http.post(
								"http://localhost:8080/endava-quiz/test/post",
								memberEntity).success(function(data) {
							// success
							$scope.memberEntity = data;
						});
					};
					// END OF DEMO
				});
