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
    .controller("frontCtrl", function ($rootScope, $scope, $http) {
        $scope.logout = function() {
            $http.post('logout', {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function() {
                $rootScope.authenticated = false;
            });
        };

        $scope.loadStudios = function(type, city) {
            $scope.studios = [{
                name: "Studio 1",
                logoUrl: "http://lorempixel.com/400/200/technics/1",
                address: "Visku iela 13-55",
                phone: "28272988",
                workingHours: "24/7",
                description: "The best studio ever in the city center"
            },{
                name: "Studio 2",
                logoUrl: "http://lorempixel.com/400/200/technics/2",
                address: "Mukusalas iela 13-55",
                phone: "28272988",
                workingHours: "24/7",
                description: "The best studio ever in the city center"
            },{
                name: "Studio 3",
                logoUrl: "http://lorempixel.com/400/200/technics/3",
                address: "Krasta iela 13-55",
                phone: "28272988",
                workingHours: "24/7",
                description: "The best studio ever in the city center"
            }]
        };

        $scope.loadStudios();
    });