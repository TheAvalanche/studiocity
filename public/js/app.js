'use strict';

angular
    .module('studiocity', [
        'ngRoute',
        'ngCookies',
        'duScroll',
        'ui.bootstrap',
        'angularFileUpload'
    ])
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider.when('/', {
            templateUrl: 'views/front.html',
            controller: 'frontCtrl'
        }).when('/studios', {
            templateUrl: 'views/studios.html',
            controller: 'studiosCtrl'
        }).otherwise({redirectTo: '/'});

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

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