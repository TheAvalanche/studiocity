'use strict';

angular.module('studiocity')
    .controller('testCtrl', function ($scope, testService) {
        testService.test().success(function(data) {
            $scope.test = data;
        })
    });