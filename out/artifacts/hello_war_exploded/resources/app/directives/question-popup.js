angular.module('endavaQuizApp').directive("questionpopup", function () {
    return {
        restrict: "E",
        templateUrl: "/endava-quiz/resources/app/directives/templates/question-popup.html",
        controller: function ($scope) {
        	
			// popup control
			$scope.showQuestionPopup = function() {
				angular.element('#addnew-question-popup')
						.removeClass('hidden');
			};
			

			$scope.hideQuestionPopup = function() {
				angular.element('#addnew-question-popup')
						.addClass('hidden');
			};
        }
    };
});