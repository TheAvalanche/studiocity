'use strict';

angular
    .module('studiocity', [
        'ngRoute',
        'duScroll',
        'ui.bootstrap',
        'uiGmapgoogle-maps'
    ])
    .config(function($routeProvider, $httpProvider, uiGmapGoogleMapApiProvider) {

        $routeProvider.when('/', {
            templateUrl : 'views/home.html',
            controller : 'homeCtrl'
        }).when('/login', {
            templateUrl : 'views/login.html',
            controller : 'loginCtrl'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        uiGmapGoogleMapApiProvider.configure({
            //    key: 'your api key',
            v: '3.20', //defaults to latest 3.X anyhow
            libraries: 'weather,geometry,visualization'
        });

    }).run(["$rootScope", "$http", function($rootScope, $http) {

        $http.get('user').success(function(data) {
            if (data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
        }).error(function() {
            $rootScope.authenticated = false;
        });

    }]);