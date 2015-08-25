'use strict';

angular
    .module('studiocity', [
        'ngRoute'
    ])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl : 'views/home.html',
            controller : 'homeCtrl'
        }).when('/login', {
            templateUrl : 'views/login.html',
            controller : 'loginCtrl'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    });