'use strict';

angular.module('studiocity')
    .controller('homeCtrl', function ($scope, testService) {
        testService.test().success(function (data) {
            $scope.test = data;
        })
    })
    .controller('loginCtrl', function ($rootScope, $scope, $http, $location) {

        var authenticate = function (credentials, callback) {

            var headers = credentials ? {
                authorization: "Basic "
                + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('user', {headers: headers}).success(function (data) {
                $rootScope.authenticated = !!data.name;
                callback && callback();
            }).error(function () {
                $rootScope.authenticated = false;
                callback && callback();
            });

        };

        authenticate();
        $scope.credentials = {};
        $scope.login = function () {
            authenticate($scope.credentials, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    $scope.error = false;
                } else {
                    $location.path("/login");
                    $scope.error = true;
                }
            });
        };
    })
    .controller("navCtrl", function ($rootScope, $scope, $http) {
        $scope.logout = function() {
            $http.post('logout', {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function() {
                $rootScope.authenticated = false;
            });
        }
    });