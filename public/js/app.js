'use strict';

angular
    .module('studiocity', [
        'ngRoute',
        'ngCookies',
        'duScroll',
        'ui.bootstrap',
        'uiGmapgoogle-maps',
        'flow'
    ])
    .config(function ($routeProvider, $httpProvider, uiGmapGoogleMapApiProvider, flowFactoryProvider) {
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

        flowFactoryProvider.defaults = {
            target: '/upload/image',
            headers: {
                "X-Requested-With" : 'XMLHttpRequest'
            },
            permanentErrors: [404, 500, 501],
            maxChunkRetries: 1,
            chunkRetryInterval: 5000,
            simultaneousUploads: 4,
            singleFile: true
        };

    }).run(["$rootScope", "$http", function($rootScope, $http) {

        $http.get('/auth/user').success(function(data) {
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