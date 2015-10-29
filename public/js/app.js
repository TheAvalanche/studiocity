'use strict';

angular
    .module('studiocity', [
        'ngRoute',
        'ngCookies',
        'duScroll',
        'ui.bootstrap',
        'uiGmapgoogle-maps',
        'angularFileUpload'
    ])
    .config(function ($routeProvider, $httpProvider, uiGmapGoogleMapApiProvider) {
        $routeProvider.when('/', {
            templateUrl: 'views/front.html',
            controller: 'frontCtrl'
        }).when('/profile', {
            templateUrl: 'views/profile.html',
            controller: 'profileCtrl'
        }).when('/studios', {
            templateUrl: 'views/studios.html',
            controller: 'studiosCtrl'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        uiGmapGoogleMapApiProvider.configure({
            //    key: 'your api key',
            v: '3.20',
            libraries: 'weather,geometry,visualization'
        });
    }).run(["$rootScope", "$http", function ($rootScope, $http) {

        $http.get('/auth/user').success(function (data) {
            if (data.name) {
                $rootScope.authenticated = true;
                $rootScope.user = data.principal;
            } else {
                $rootScope.authenticated = false;
            }
        }).error(function() {
            $rootScope.authenticated = false;
        });

    }]);