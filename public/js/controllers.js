'use strict';

angular.module('studiocity')
    .controller('loginCtrl', function ($rootScope, $scope, $http, $location, $modalInstance) {

        var authenticate = function (credentials) {

            var headers = credentials ? {
                authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('user', {headers: headers}).success(function (data) {
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
    .controller("frontCtrl", function ($rootScope, $scope, $http, $modal, searchService) {
        $scope.logout = function() {
            $http.post('logout', {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function() {
                $rootScope.authenticated = false;
            });
        };

        $scope.totalItems = 100;
        $scope.currentPage = 1;

        searchService.search().success(function (data) {
            $scope.studios = data;
            $scope.studios.forEach(function(item, i, arr) {
                item.map = {latitude: item.latitude, longitude: item.longitude};
            });
        });


        var styleArray = [{
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

        $scope.login = function () {

            $modal.open({
                animation: true,
                templateUrl: 'views/login.html',
                controller: 'loginCtrl',
                size: "sm",
                windowClass: "modal-custom"
            });

        };
    });