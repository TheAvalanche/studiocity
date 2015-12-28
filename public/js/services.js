'use strict';

angular.module('studiocity')
    .factory('searchService', ['$http', function ($http) {

        return {
            search: function (studioType, city, skip, limit) {
                return $http.get("/rest/search", {params: {studioType: studioType, city: city, skip: skip, limit: limit}});
            },
            count: function (studioType, city) {
                return $http.get("/rest/count", {params: {studioType: studioType, city: city}});
            },
            cities: function () {
                return $http.get("/rest/cities");
            },
            studioTypes: function () {
                return $http.get("/rest/studioTypes");
            }
        };
    }])
    .factory('studioService', function ($http) {
        return {
            save: function (studio) {
                return $http.post("/studio/save", studio);
            },
            remove: function (studio) {
                return $http.post("/studio/remove", studio);
            },
            findByCurrentUser : function () {
                return $http.get("/studio/findByCurrentUser");
            }
        };
    });