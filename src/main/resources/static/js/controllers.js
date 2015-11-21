'use strict';

/* Controllers */

var videoControllers = angular.module('videoControllers', []);

videoControllers.controller('categoryListCtrl', ['$scope', '$http','$routeParams',
  function($scope, $http,$routeParams) {
    $http.get('http://localhost:8080/ws/category/'+$routeParams.category).success(function(data) {
      $scope.videos = data;
    });

    $scope.orderProp = 'title';
  }]);
