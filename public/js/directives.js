'use strict';

angular.module('studiocity')
    .directive('testService', ['$http', function ($http) {

        return {
            test: function() {
                return $http.get("/test");
            }
        };
    }]);