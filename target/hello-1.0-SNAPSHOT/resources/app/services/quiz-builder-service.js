'use strict';
angular
		.module('QuizBuilderService', [ 'toaster', 'QuestionUtils' ])
		.service(
				'QuizBuilderService',
				function(toaster, QuestionUtils) {
					this.addSingleAnswerQuestion = function($scope, $compile) {
						$scope.newQuestion = angular.copy($scope.question);
						$scope.newQuestion['question_type'] = 'Standard';
						$scope.questions.push($scope.newQuestion);
						$scope.size = $scope.questions.length - 1;
						if ($scope.questions.length > 0) {
							angular.element('#title').removeClass('hidden');
						}
						$scope.pos = $scope.questions.length;
						angular.element("#questions").append(
								$compile(
										QuestionUtils.generateStandardQuestion(
												$scope.pos, $scope.size))(
										$scope));
					};

					this.addMultipleAnswerQuestion = function($scope, $compile) {
						$scope.newQuestion = angular.copy($scope.question);
						$scope.newQuestion['question_type'] = 'Multiple';
						$scope.questions.push($scope.newQuestion);
						$scope.size = $scope.questions.length - 1;
						if ($scope.questions.length > 0) {
							angular.element('#title').removeClass('hidden');
						}
						$scope.pos = $scope.questions.length;
						$("#questions").append(
								$compile(
										QuestionUtils.generateMultipleQuestion(
												$scope.pos, $scope.size))(
										$scope));
					};

					this.addTextAnswerQuestion = function($scope, $compile) {
						$scope.newQuestion = angular.copy($scope.question);
						$scope.newQuestion['question_type'] = 'Text';
						$scope.questions.push($scope.newQuestion);
						$scope.size = $scope.questions.length - 1;
						if ($scope.questions.length > 0) {
							angular.element('#title').removeClass('hidden');
						}
						$scope.pos = $scope.questions.length;
						$("#questions").append(
								$compile(
										QuestionUtils.generateTextQuestion(
												$scope.pos, $scope.size))(
										$scope));
					};

					this.addSingleLineAnswerQuestion = function($scope,
							$compile) {
						$scope.newQuestion = angular.copy($scope.question);
						$scope.newQuestion['question_type'] = 'Line';
						$scope.questions.push($scope.newQuestion);
						$scope.size = $scope.questions.length - 1;
						if ($scope.questions.length > 0) {
							angular.element('#title').removeClass('hidden');
						}
						$scope.pos = $scope.questions.length;
						$("#questions").append(
								$compile(
										QuestionUtils.generateLineQuestion(
												$scope.pos, $scope.size))(
										$scope));
					};

					this.addAnswer = function(parent, posValue, $scope,
							$compile) {
						$scope.newAnswer = angular.copy($scope.answer);
						$scope.questions[posValue - 1].answers
								.push($scope.newAnswer);
						var size = $scope.questions[posValue - 1].answers.length - 1;
						var epos = $scope.questions[posValue - 1].answers.length;
						var actPos = posValue - 1;
						var counter = size + 1;
						$("#" + parent + " #answers").append(
								$compile(
										QuestionUtils.generateAnswer(actPos,
												size, counter))($scope));
					};

					this.reconstructQuiz = function(quiz, $scope, $compile,
							$timeout) {
						$scope.questions = [];
						this.resetPageContent($compile, $scope);
						$scope.quiz.quiz_autoeval = quiz.quiz_autoeval;
						var len = quiz.questions.length;
						for (var i = 0; i < len; ++i) {
							$scope.questions.push(quiz.questions[i]);
						}
						;

						$timeout(
								function() {
									var len = quiz.questions.length;
									for (var i = 0; i < len; ++i) {
										if (quiz.questions[i].question_type === 'Standard') {
											angular
													.element("#questions")
													.append(
															$compile(
																	QuestionUtils
																			.generateMultipleQuestion(
																					i + 1,
																					i))
																	($scope));
										}
										if (quiz.questions[i].question_type === 'Multiple') {
											angular
													.element("#questions")
													.append(
															$compile(
																	QuestionUtils
																			.generateMultipleQuestion(
																					i + 1,
																					i))
																	($scope));
										}
										if (quiz.questions[i].question_type === 'Text') {
											angular
													.element("#questions")
													.append(
															$compile(
																	QuestionUtils
																			.generateTextQuestion(
																					i + 1,
																					i))
																	($scope));
										}
										if (quiz.questions[i].question_type === 'Line') {
											angular
													.element("#questions")
													.append(
															$compile(
																	QuestionUtils
																			.generateLineQuestion(
																					i + 1,
																					i))
																	($scope));
										}

										if (quiz.questions[i].question_type === 'Standard'
												|| quiz.questions[i].question_type === 'Multiple') {
											var answerLen = quiz.questions[i].answers.length;
											for (var j = 0; j < answerLen; ++j) {
												var size = $scope.questions[i].answers.length - 1;
												var epos = $scope.questions[i].answers.length;
												var actPos = i;
												var correction = i + 1;
												var counter = j + 1;
												angular
														.element(
																"#" + 'question' + correction
																		+ " #answers").append(
																$compile(
																		QuestionUtils
																				.generateAnswer(
																						actPos, j,
																						counter))(
																		$scope));
												angular.element('#answer' + actPos + '_' + j).attr(
														'checked', 'checked');
											}
										}
									}
									;
									$scope.quiz_create_view = true;
									$scope.quiz_list_view = false;
								}, 1500);

					};

					this.resetPageContent = function($compile, $scope) {
						angular.element("#questions").empty();
						angular.element("#questions").append(
								$compile('')($scope));
					};

				});
