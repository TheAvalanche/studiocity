'use strict';

angular.module('studiocity')
    .factory('searchService', ['$http', function ($http) {

        return {
            search: function() {
                return $http.get("/rest/search");
            },
            count: function() {
                return $http.get("/rest/count");
            }
        };
    }]);