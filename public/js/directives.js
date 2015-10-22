'use strict';

angular.module('studiocity')
    .directive('collapseOnScroll', function ($window) {
        return function (scope, element, attrs) {
            angular.element($window).bind("scroll", function () {
                if (this.pageYOffset > 50) {
                    element.addClass("top-nav-collapse");
                } else {
                    element.removeClass("top-nav-collapse");
                }
            });
        };
    });