angular.module('endavaQuizApp').directive("decisionpopup", function () {
    return {
        restrict: "E",
        templateUrl: "/endava-quiz/resources/app/directives/templates/decision-popup.html",
        controller: function ($scope) {
        	$scope.popupMessage = 'Message';
        	$scope.popupTitle = 'Error';
        	$scope.popupQuizCode = '';
        	$scope.confirm = $scope.deleteQuiz;

        	$scope.$on('showDecisionPopupEvent', function(event, payload) {
        		$scope.popupMessage = payload['message'];
        		$scope.popupTitle = payload['title'];
        		$scope.popupQuizCode = payload['quiz_code'];
        		$scope.showDecisionPopup();
        	});
        	
        	$scope.getTitle = function(){
        		return $scope.popupTitle;
        	};
        	
        	$scope.getMessage = function(){
        		return $scope.popupMessage;
        	};
        	
			// popup control
			$scope.showDecisionPopup = function() {
				angular.element('#decision-popup')
						.removeClass('hidden');
			};
			
			$scope.hideDecisionPopup = function() {
				angular.element('#decision-popup')
						.addClass('hidden');
			};
        }
    };
});