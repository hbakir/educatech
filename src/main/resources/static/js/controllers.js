'use strict';

/* Controllers */

var videoControllers = angular.module('videoControllers', []);

videoControllers.controller('categoryListCtrl', ['$scope', '$http','$routeParams',
  function($scope, $http,$routeParams) {
    $http.get('http://educatech.herokuapp.com/ws/category/'+$routeParams.category).success(function(data) {
      $scope.videos = data;
    });

    $scope.orderProp = 'title';
  }]);
