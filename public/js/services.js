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
    }])
    .factory('studioService', function ($http) {
        return {
            save: function(studio) {
                return $http.post("/studio/save", studio);
            },
            findByCurrentUser : function () {
                return $http.get("/studio/findByCurrentUser");
            }
        };
    });