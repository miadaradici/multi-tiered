// create the controller and inject Angular's $scope
angular
		.module('endavaQuizApp')
		.controller(
				'QuizController',
				[
						'$scope',
						'$route',
						'$http',
						'$routeParams',
						'$location',
						'$timeout',
						'$compile',
						'$rootScope',
						'toaster',
						'QuestionUtils',
						'StaticDataUtils',
						'QuizService',
						'QuizDirectiveService',
						function($scope, $route, $http, $routeParams,
								$location, $timeout, $compile, $rootScope,
								toaster, QuestionUtils, StaticDataUtils,
								QuizService, QuizDirectiveService) {

							// declaration
							$scope.quizzes = [];
							$scope.questions = [];

							// visibility
							$scope.quiz_create_view = true;
							$scope.quiz_list_view = false;

							// update flag
							$scope.updateStarted = false;

							// single quiz
							$scope.quiz = {
								quiz_name : '',
								quiz_level : '',
								quiz_autoeval : false,
								quiz_category : '',
								quiz_code : '',
								questions : []
							};

							// single question
							$scope.question = {
								question : '',
								question_score: 0,
								question_type : 'Standard',
								answers : []
							}

							$scope.answer = {
								answer : '',
								answer_score : 0,
								answer_image : '',
								correct_answer : false
							}

							// init
							$scope.initQuizzes = function() {
								QuizService.initQuizzes($scope, $http);
								if (!$scope.$$phase) {
									$scope.$apply();
								}
							};

							$scope.saveQuiz = function(quiz) {
								QuizService.saveQuiz(quiz, $scope, $http,
										$route);
								if (!$scope.$$phase) {
									$scope.$apply();
								}
							};

							$scope.deleteQuiz = function(quiz_code) {
								QuizService
										.deleteQuiz(quiz_code, $scope, $http);
								$scope.hideDecisionPopup();
							};

							$scope.updateQuiz = function(quiz) {
								QuizService.updateQuiz(quiz, $scope, $http,
										$route);
								if (!$scope.$$phase) {
									$scope.$apply();
								}
							};

							$scope.cancelUpdate = function() {
								$scope.updateStarted = false;
								$route.reload();
								if (!$scope.$$phase) {
									$scope.$apply();
								}
							}

							// add new question to page
							$scope.addQuestion = function(type) {
								switch (type) {
								case 1:
									QuizDirectiveService.addSimpleQuestion(
											$scope, $compile, 'Standard');
									break;
								case 2:
									QuizDirectiveService.addSimpleQuestion(
											$scope, $compile, 'Multiple');
									break;
								case 3:
									QuizDirectiveService.addTextQuestion(
											$scope, $compile, 'Text');
									break;
								case 4:
									QuizDirectiveService.addTextQuestion(
											$scope, $compile, 'Line');
									break;
								default:
									// think about it
								}
								angular.element('#addnew-question-popup')
										.addClass('hidden');
							};

							// init path
							$scope.init = function() {
								// init variables
								$scope.quiz_categories = StaticDataUtils
										.getQuizCategories();
								$scope.selected_quiz_category = $scope.quiz_categories[0];

								// quiz levels
								$scope.quiz_levels = StaticDataUtils
										.getQuizLevels();
								$scope.selected_quiz_level = $scope.quiz_levels[0];

								// init location
								$location.path('/quizzes');
							};

							$scope.getQuestionAsText = function(question) {
								return question.question + "("
										+ question.question_score + " points)";
							}

							$scope.getAnswerAsText = function(answer) {
								return answer.answer + "("
										+ answer.answer_score + " points)";
							}
							
							$scope.init();
							$scope.initQuizzes();

							// deprecated
							$scope.getCompile = function() {
								return $compile;
							}

							// deprecatged
							$scope.getBuilder = function() {
								return QuizBuilderService;
							}

							// deprecated
							$scope.getTimeout = function() {
								return $timeout;
							}

							// deprecated
							$scope.addSingleAnswerQuestion = function() {
								QuizDirectiveService.addSimpleQuestion($scope,
										$compile);

							};

							// deprecated
							$scope.addMultipleAnswerQuestion = function() {
								QuizDirectiveService.addSimpleQuestion($scope,
										$compile);
							};

							// deprecated
							$scope.addTextAnswerQuestion = function() {
								QuizDirectiveService.addTextQuestion($scope,
										$compile);
							};

							// deprecated
							$scope.addSingleLineAnswerQuestion = function() {
								QuizDirectiveService.addTextQuestion($scope,
										$compile);
							};
							
							$scope.setQuestions = function(questions){
								$scope.questions = questions;
							}
							
							$scope.setQuiz = function(quiz){
								$scope.quiz = quiz;
							}
							
							$scope.getQuestions = function(){
								return $scope.questions;
							}

							$scope.addAnswer = function(parent, posValue) {
								$scope.newAnswer = angular.copy($scope.answer);
								var actPos = posValue - 1;
								$scope.getQuestions()[actPos].answers
										.push($scope.newAnswer);
								var size = $scope.getQuestions()[actPos].answers.length;
								$("#" + parent + " #answers").append(
										$compile(
												QuestionUtils.generateAnswer(actPos, actPos,
														size))($scope));
							};
							
							$scope.$on('addAnswerEvent', function(event, payload) {
								$scope.addAnswer(payload['parent'], payload['position']);
							});
							
						} ]);
