'use strict';
angular
		.module('QuestionUtils', [])
		.service(
				'QuestionUtils',
				function() {
					// deprecated
					this.generateStandardQuestion = function(pos, size) {
						return '<li id="question'
								+ pos
								+ '"><div class="num">'
								+ pos
								+ '.</div><div class="question"> <div text-angular="text-angular" ng-model="questions['
								+ size
								+ '].question" name="questions['
								+ size
								+ '].question" ta-disabled="false"></div>'
								+ '<input type="text" class="form-control-score" ng-model="questions['
								+ size
								+ '].question_score">'
								+ '<div style="width: 100%;"><ul id="answers"></ul> <div class="answer-holder">'
								+ '<a ng-click="addAnswer(\'question' + pos
								+ '\',' + pos
								+ ')" class="btn">+ ADD ANSWER</a>'
								+ '</div></div></li>';
					};

					// deprecated
					this.generateMultipleQuestion = function(pos, size) {
						return '<li id="question'
								+ pos
								+ '"><div class="num">'
								+ pos
								+ '.</div><div class="question"> <div text-angular="text-angular" ng-model="questions['
								+ size
								+ '].question" name="questions['
								+ size
								+ '].question" ta-disabled="false"></div>'
								+ '<input type="text" class="form-control-score" ng-model="questions['
								+ size
								+ '].question_score">'
								+ '<div style="width: 100%;"><ul id="answers"></ul> <div class="answer-holder">'
								+ '<a ng-click="addAnswer(\'question' + pos
								+ '\',' + pos
								+ ')" class="btn">+ ADD ANSWER</a>'
								+ '</div></div></li>'
					};

					// deprecated
					this.generateTextQuestion = function(pos, size) {
						return '<li id="question'
								+ pos
								+ '"><div class="num">'
								+ pos
								+ '.</div><div class="question"><div class="question"> <div text-angular="text-angular" ng-model="questions['
								+ size
								+ '].question" name="questions['
								+ size
								+ '].question" ta-disabled="false"></div>'
								+ '<div style="width: 100%;">'
								+ '<div class="answer-holder">'
								+ '<input type="text" class="form-control-score form-exclude" ng-model="questions['
								+ size + '].question_score"></div></div></li>'
					};

					// deprecated
					this.generateLineQuestion = function(pos, size) {
						return '<li id="question'
								+ pos
								+ '"><div class="num">'
								+ pos
								+ '.</div><div class="question"><div class="question"> <div text-angular="text-angular" ng-model="questions['
								+ size
								+ '].question" name="questions['
								+ size
								+ '].question" ta-disabled="false"></div>'
								+ '<div class="answer-holder">'
								+ '<input type="text" class="form-control-score form-exclude" ng-model="questions['
								+ size + '].question_score"></div></div></li>'
					};

					this.generateAnswer = function(qCounter, actPos, size) {
						var answerPos = size - 1;
						return '<li><div class="num">'
								+ size
								+ '.</div><div class="answer-check"> <input id="answer'
								+ actPos
								+ '_'
								+ size
								+ '" type="checkbox" ng-model="questions['
								+ qCounter
								+ '].answers['
								+ answerPos
								+ '].correct_answer"></div><div class="answer"><input type="text" class="form-control" '
								+ 'ng-model="questions['
								+ qCounter
								+ '].answers['
								+ answerPos
								+ '].answer" '
								+ 'name="questions['
								+ qCounter
								+ '].answers['
								+ answerPos
								+ '].answer" '
								+ ' required="true" /></div><div class="answer-check" ng-show="quiz.quiz_autoeval"> <input type="text" class="form-control" ng-model="questions['
								+ qCounter + '].answers[' + answerPos
								+ '].answer_score"></div></li>';
					};

					this.generateSimpleQuestion = function(actPos,
							size) {
						return '<li id="questionHolder'
						+ actPos
						+ '" position="'
						+ actPos
						+ '" size="'
						+ size
						+ '" param-score="questions['
						+ size
						+ '].question_score" param="questions['
						+ size
						+ '].question" simplequestion/>';
					};

					this.generateTextQuestion = function(actPos, size) {
						return '<li id="questionHolder'
								+ actPos
								+ '" position="'
								+ actPos
								+ '" size="'
								+ size
								+ '" param-score="questions['
								+ size
								+ '].question_score"param="questions['
								+ size
								+ '].question" textquestion/>';
					};

				});
