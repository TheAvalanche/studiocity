'use strict';

angular.module('studiocity')
    .factory('testService', ['$http', function ($http) {

        return {
            test: function() {
                return $http.get("/test");
            }
        };
    }]);