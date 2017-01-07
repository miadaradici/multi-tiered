'use strict';
angular.module('QuizValidationService', [ 'toaster', 'QuestionUtils' ]).service(
		'QuizValidationService', function(toaster, QuestionUtils) {
			this.validateQuiz = function(quiz){
				if (!angular.isDefined(quiz.quiz_name) || quiz.quiz_name.length <= 0){
					toaster.pop('error', "Error!", "Quiz name is mandatory!");
					return false;
				}
				// TODO: continue validation
				// TODO: validate questions
				// TODO: validate answers
				
				// TODO: keep in mind to add validation adding new question when the current question does not have answers
				return true;
			}
		});