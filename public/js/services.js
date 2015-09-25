'use strict';

angular.module('studiocity')
    .factory('searchService', ['$http', function ($http) {

        return {
            search: function() {
                return $http.get("/search");
            }
        };
    }]);