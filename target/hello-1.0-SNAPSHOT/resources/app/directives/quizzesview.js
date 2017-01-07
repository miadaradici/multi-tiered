angular
		.module('endavaQuizApp')
		.directive(
				"quizzes", [ 'toaster' , '$timeout','$compile', 'QuizDirectiveService',
				function(toaster, $timeout, $compile, QuizDirectiveService) {
					return {
						restrict : "E",
						templateUrl : "/endava-quiz/resources/app/directives/templates/quizzesview.html",
						controller : function($scope) {
							$scope.editQuiz = function(quiz) {
								if ($scope.updateStarted === false) {
									$scope.updateStarted = true;
									$scope.setQuiz(quiz);
									$scope.setQuestions(quiz.questions);
									$scope.selected_quiz_level.name = quiz.quiz_level
									$scope.selected_quiz_category.name = quiz.quiz_category;
									QuizDirectiveService.reconstructQuiz(quiz,
											$scope, $compile, $timeout);
								} else {
									toaster
									.pop('error', "Error!",
											"A quiz is already being updated!");
								}
							}

							$scope.confirmDeleteQuiz = function(quiz_code) {
								$scope
										.$emit(
												'showDecisionPopupEvent',
												{
													message : 'Are you sure you want to delete the quiz ?',
													title : 'Confirm',
													quiz_code : quiz_code
												});
							}

							// show and hide quizzes
							$scope.showQuiz = function(id, btn1, btn2) {
								$('#' + id).removeClass('hidden');
								$('#' + btn1).removeClass('hidden');
								$('#' + btn2).addClass('hidden');
							};

							$scope.hideQuiz = function(id, btn1, btn2) {
								$('#' + id).addClass('hidden');
								$('#' + btn1).addClass('hidden');
								$('#' + btn2).removeClass('hidden');
							};

						}
					};
				}]);