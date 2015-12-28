'use strict';

angular.module('studiocity')
    .controller('loginCtrl', function ($rootScope, $scope, $http, $location, $modalInstance) {

        var authenticate = function (credentials) {

            var headers = credentials ? {
                authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('/auth/user', {headers: headers}).success(function (data) {
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
    .controller("menuCtrl", function ($rootScope, $scope, $http, $modal, $location) {
        $scope.logout = function () {
            $http.post('logout', {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function () {
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
    .controller("frontCtrl", function ($scope, $http, $document, searchService) {
        $scope.newCredentials = {};
        $scope.signIn = function () { //todo more serious implementation
            $http.post('/auth/signIn', $scope.newCredentials);
        };

        var init = function () {
            $scope.selected = {};
            $scope.currentPage = 1;

            searchService.cities().success(function (data) {
                $scope.cities = data;
            });

            searchService.studioTypes().success(function (data) {
                $scope.studioTypes = data;
            });
        };

        var search = function (studioType, city, skip, limit) {
            searchService.search(studioType, city, skip, limit).success(function (data) {
                $scope.studios = data;

                searchService.count(studioType, city).success(function (data) {
                    $scope.totalItems = data;
                });
            });
        };

        $scope.pageChanged = function () {
            $document.duScrollTo(angular.element(document.getElementById("search")), 0, 1000).then(function () {
                search($scope.selected.studioType, $scope.selected.city, ($scope.currentPage - 1) * 10, 10);
            });
        };

        $scope.search = function () {
            search($scope.selected.studioType, $scope.selected.city);
        };

        init();
        search();
    })
    .controller("studiosCtrl", function ($scope, $modal, studioService) {
        $scope.studios = [];
        $scope.newStudio = {};

        $scope.save = function (newStudio) {
            studioService.save(newStudio);
        };

        var init = function () {
            studioService.findByCurrentUser().success(function (data) {
                $scope.studios = data;
            });
        };

        $scope.editStudio = function (studio) {
            var modalInstance = $modal.open({
                templateUrl: 'views/editStudio.html',
                controller: 'editStudioCtrl',
                windowClass: 'modal-custom',
                backdrop: 'static',
                resolve: {
                    studio: function () {
                        return studio;
                    }
                }
            });

            modalInstance.result.then(function () {
                init();
            }, function () {
                init();
            });
        };

        $scope.removeStudio = function (studio) {
            studioService.remove(studio).success(function () {
                init();
            });
        };

        init();
    })
    .controller("editStudioCtrl", function ($scope, $cookies, $modalInstance, studioService, FileUploader, studio) {
        var init = function () {
            $scope.studio = studio || {};
        };

        $scope.uploader = new FileUploader({
            url: '/upload/image',
            headers: {
                'X-XSRF-TOKEN': $cookies.get("XSRF-TOKEN")
            },

            onAfterAddingFile: function (item) {
                $scope.uploader.uploadItem(item);
            },

            onCompleteItem: function (item, response) {
                item.file.name = response.name;
                $scope.studio.logoUrl = item.file.name;
            }
        });

        $scope.save = function () {
            studioService.save($scope.studio).success(function () {
                $modalInstance.close();
            });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss();
        };

        init();
    });