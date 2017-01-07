'use strict';
angular
		.module('QuizService', [ 'toaster', 'QuizValidationService' ])
		.service(
				'QuizService',
				function(toaster, QuizValidationService) {
					this.initQuizzes = function(scope, $http) {
						$http
								.get(
										"http://localhost:8080/endava-quiz/list_quiz")
								.success(function(data) {
									scope.quizzes = data;
								})
								.error(
										function(data) {
											toaster
													.pop(
															'error',
															"An error has occured!",
															"Quiz list could not be retrieved!");
										});
					};

					this.deleteQuiz = function(quiz_code, scope, $http) {

						var request = $http({
							method : "delete",
							url : "http://localhost:8080/endava-quiz/delete_quiz/"
									+ quiz_code,
							params : {
								action : "delete"
							}
						});

						request.then(function(data) {
							toaster.pop('success', "Success!",
									"Quiz was succesfully deleted!");
							scope.initQuizzes();
						}, function(data) {
							toaster.pop('error', "An error has occured!",
									"Quiz could not be deleted!");
						});

					};

					this.saveQuiz = function(quiz, scope, $http, $route) {
						quiz.quiz_level = scope.selected_quiz_level.name;
						quiz.quiz_category = scope.selected_quiz_category.name;

						// add questions when it's ready
						quiz.questions = scope.questions;
						if (QuizValidationService.validateQuiz(quiz)) {
							$http
									.post(
											"http://localhost:8080/endava-quiz/save_quiz",
											quiz)
									.success(
											function(data) {
												// defensive copy
												var savedQuiz = angular
														.copy(quiz);

												// add to list
												scope.quizzes
														.unshift(savedQuiz);
												// re-init quiz
												scope.quiz = {
													quiz_name : '',
													quiz_level : '',
													quiz_category : '',
												};
												scope.selected_quiz_level = scope.quiz_levels[0];
												scope.selected_quiz_category = scope.quiz_categories[0];

												scope.quiz.questions = [];
												scope.questions = [];

												if (!scope.$$phase) {
													scope.$apply();
												}
												$route.reload();

												// brag about it
												toaster
														.pop('success',
																"Success!",
																"Quiz was succesfully saved!");

											})
									.error(
											function(data) {
												// brag about it
												toaster
														.pop('error', "Error!",
																"Quiz could not be saved!");
											});
						}
						;
					};

					this.updateQuiz = function(quiz, scope, $http, $route) {
						quiz.quiz_level = scope.selected_quiz_level.name;
						quiz.quiz_category = scope.selected_quiz_category.name;

						// add questions when it's ready
						quiz.questions = scope.questions;
						if (QuizValidationService.validateQuiz(quiz)) {
							console.log(quiz);
							$http
									.put(
											"http://localhost:8080/endava-quiz/update_quiz",
											quiz)
									.success(
											function(data) {
												// defensive copy
												var savedQuiz = angular
														.copy(quiz);

												// add to list
												scope.quizzes
														.unshift(savedQuiz);
												// re-init quiz
												scope.quiz = {
													quiz_name : '',
													quiz_level : '',
													quiz_category : '',
												};
												scope.selected_quiz_level = scope.quiz_levels[0];
												scope.selected_quiz_category = scope.quiz_categories[0];

												scope.quiz.questions = [];
												scope.questions = [];
												$route.reload();

												scope.updateStarted = false;
												// brag about it
												toaster
														.pop('success',
																"Success!",
																"Quiz was succesfully updated!");

											})
									.error(
											function(data) {
												// brag about it
												toaster
														.pop('error', "Error!",
																"Quiz could not be updated!");
											});
						}
						;
					};
				});