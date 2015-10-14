'use strict';

angular.module('studiocity')
    .controller('loginCtrl', function ($rootScope, $scope, $http, $location, $modalInstance) {

        var authenticate = function (credentials) {

            var headers = credentials ? {
                authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('/auth/user', {headers: headers}).success(function (data) { //todo separate logic
                $rootScope.authenticated = !!data.name;
                $rootScope.user = data.principal;
                $scope.error = false;
                $modalInstance.close();
            }).error(function () {
                $rootScope.authenticated = false;
                $scope.error = true;
            });

        };

        $scope.credentials = {};
        $scope.login = function () {
            authenticate($scope.credentials);
        };

        $scope.cancel = function () {
            $modalInstance.close();
        };
    })
    .controller("menuCtrl", function($rootScope, $scope, $http, $modal) {
        $scope.logout = function() { //todo separate logic and fix csrf bug
            $http.post('logout', {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function() {
                $rootScope.authenticated = false;
            });
        };

        $scope.login = function () {

            $modal.open({
                animation: true,
                templateUrl: 'views/login.html',
                controller: 'loginCtrl',
                size: "sm",
                windowClass: "modal-custom"
            });

        };
    })
    .controller("frontCtrl", function ($scope, $http, searchService) {
        $scope.newCredentials = {};
        $scope.signIn = function() { //todo more serious implementation
            $http.post('/auth/signIn', $scope.newCredentials)
        };

        searchService.count().success(function (data) {
            $scope.totalItems = data;
        });

        $scope.currentPage = 1;

        searchService.search().success(function (data) {
            $scope.studios = data;
            $scope.studios.forEach(function(item, i, arr) {
                item.map = {latitude: item.latitude, longitude: item.longitude};
            });
        });


        var styleArray = [{ //todo move away
            "featureType": "water",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 17
            }]
        }, {
            "featureType": "landscape",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 20
            }]
        }, {
            "featureType": "road.highway",
            "elementType": "geometry.fill",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 17
            }]
        }, {
            "featureType": "road.highway",
            "elementType": "geometry.stroke",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 29
            }, {
                "weight": 0.2
            }]
        }, {
            "featureType": "road.arterial",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 18
            }]
        }, {
            "featureType": "road.local",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 16
            }]
        }, {
            "featureType": "poi",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 21
            }]
        }, {
            "elementType": "labels.text.stroke",
            "stylers": [{
                "visibility": "on"
            }, {
                "color": "#000000"
            }, {
                "lightness": 16
            }]
        }, {
            "elementType": "labels.text.fill",
            "stylers": [{
                "saturation": 36
            }, {
                "color": "#000000"
            }, {
                "lightness": 40
            }]
        }, {
            "elementType": "labels.icon",
            "stylers": [{
                "visibility": "off"
            }]
        }, {
            "featureType": "transit",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 19
            }]
        }, {
            "featureType": "administrative",
            "elementType": "geometry.fill",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 20
            }]
        }, {
            "featureType": "administrative",
            "elementType": "geometry.stroke",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 17
            }, {
                "weight": 1.2
            }]
        }];

        $scope.options = {
            disableDefaultUI: true,
            styles: styleArray
        };
    })
    .controller("profileCtrl", function ($scope, studioService) {
        $scope.newStudio = {};

        $scope.save = function(newStudio) {
            studioService.save(newStudio);
        }
    });