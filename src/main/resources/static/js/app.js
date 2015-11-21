'use strict';

/* App Module */

var videoApp = angular.module('videoApp', [
  'ngRoute',
  'videoControllers'
]);

videoApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/category/:category', {
        templateUrl: 'partials/video-list.html',
        controller: 'categoryListCtrl'
      }).
      otherwise({
        redirectTo: '/empty.html'
      });
  }]);
