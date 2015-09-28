'use strict';

angular
    .module('studiocity', [
        'ngRoute',
        'duScroll',
        'ui.bootstrap',
        'uiGmapgoogle-maps'
    ])
    .config(function($routeProvider, $httpProvider, uiGmapGoogleMapApiProvider) {

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        uiGmapGoogleMapApiProvider.configure({
            //    key: 'your api key',
            v: '3.20',
            libraries: 'weather,geometry,visualization'
        });

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