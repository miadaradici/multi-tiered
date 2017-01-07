'use strict';
angular.module('StaticDataUtils', []).service('StaticDataUtils', function() {
	this.getQuizCategories = function() {
		return [{
			name : 'Java Core',
			value : 'Java Core'
		}, {
			name : 'Java Web',
			value : 'Java Web'
		}, {
			name : 'SQL',
			value : 'SQL'
		}, {
			name : 'JPA',
			value : 'JPA'
		}, {
			name : 'Spring ',
			value : 'Spring'
		}, {
			name : 'Spring Advanced',
			value : 'Spring Advanced'
		}, {
			name : 'J2EE',
			value : 'J2EE'
		}, {
			name : 'JS',
			value : 'JS'
		} ];
	};
	this.getQuizLevels = function() {
		return  [ {
			name : 'Interview',
			value : 'Interview'
		}, {
			name : 'Begginer',
			value : 'Begginer'
		}, {
			name : 'Intermediate',
			value : 'Intermediate'
		}, {
			name : 'Senior',
			value : 'Senior'
		}, {
			name : 'Elite',
			value : 'Elite'
		} ];
	};
});