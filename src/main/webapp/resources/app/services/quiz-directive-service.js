'use strict';
angular
		.module('QuizDirectiveService', [ 'toaster', 'QuestionUtils' ])
		.service(
				'QuizDirectiveService',
				function(toaster, QuestionUtils) {
					this.addSimpleQuestion = function(scope, $compile, type) {
						scope.newQuestion = angular.copy(scope.question);
						scope.newQuestion['question_type'] = type;
						scope.questions.push(scope.newQuestion);
						scope.size = scope.questions.length - 1;
						if (scope.questions.length > 0) {
							angular.element('#title').removeClass('hidden');
						}
						scope.pos = scope.questions.length;
						this.compileSimpleQuestion(scope, $compile, scope.pos,
								scope.size);
					};

					this.addTextQuestion = function(scope, $compile, type) {
						scope.newQuestion = angular.copy(scope.question);
						scope.newQuestion['question_type'] = type;
						scope.questions.push(scope.newQuestion);
						scope.size = scope.questions.length - 1;
						if (scope.questions.length > 0) {
							angular.element('#title').removeClass('hidden');
						}
						scope.pos = scope.questions.length;
						this.compileTextQuestion(scope, $compile, scope.pos,
								scope.size);

					};

					this.compileTextQuestion = function(scope, $compile, pos,
							size) {
						angular.element("#questions").append(
								$compile(
										QuestionUtils.generateTextQuestion(pos,
												size))(scope));
					}

					this.compileSimpleQuestion = function(scope, $compile,
							pos, size) {
						angular.element("#questions").append(
								$compile(
										QuestionUtils.generateSimpleQuestion(
												pos, size))(scope));

					};

					this.reconstructQuiz = function(quiz, scope, $compile,
							$timeout) {
						this.resetPageContent($compile, scope);
						scope.quiz.quiz_autoeval = quiz.quiz_autoeval;
						scope.quiz.questions = quiz.questions;
						scope.questions = quiz.questions;

						$timeout(
								function() {
									var questionLength = quiz.questions.length;
									for (var questionIt = 0; questionIt < questionLength; ++questionIt) {
										var questionCounter = questionIt + 1;
										var questionPosition = questionIt;
										if ((quiz.questions[questionIt].question_type === 'Standard')
												|| (quiz.questions[questionIt].question_type === 'Multiple')) {
											angular
													.element("#questions")
													.append(
															$compile(
																	QuestionUtils
																			.generateSimpleQuestion(
																					questionCounter,
																					questionPosition))
																	(scope));

										}
										if ((quiz.questions[questionIt].question_type === 'Text')
												|| (quiz.questions[questionIt].question_type === 'Line')) {
											angular
													.element("#questions")
													.append(
															$compile(
																	QuestionUtils
																			.generateTextQuestion(
																					questionCounter,
																					questionPosition))
																	(scope));
										}
									}
									;
								}, 500);

						$timeout(
								function() {
									var questionLength = quiz.questions.length;
									for (var questionIt = 0; questionIt < questionLength; ++questionIt) {
										if (quiz.questions[questionIt].question_type === 'Standard'
												|| quiz.questions[questionIt].question_type === 'Multiple') {
											var answerLength = quiz.questions[questionIt].answers.length;
											for (var answerIt = 0; answerIt < answerLength; ++answerIt) {
												var questionCounter = questionIt;
												var positionCorrection = questionIt + 1;
												var answerCounter = answerIt + 1;
												$(
														"#"
																+ 'question'
																+ positionCorrection
																+ " #answers")
														.append(
																$compile(
																		QuestionUtils
																				.generateAnswer(
																						questionCounter,
																						answerIt,
																						answerCounter))
																		(scope));
												$(
														'#answer'
																+ questionCounter
																+ '_'
																+ answerCounter)
														.attr('checked',
																'checked');
											}
										}
									}
									;
									scope.quiz_create_view = true;
									scope.quiz_list_view = false;
								}, 1500);

					};

					this.resetPageContent = function($compile, scope) {
						angular.element("#questions").empty();
						angular.element("#questions").append(
								$compile('')(scope));
					};

				});