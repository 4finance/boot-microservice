'use strict';

/**
 * @ngdoc function
 * # MainCtrl
 */
angular.module('BootstrapApplication.controllers')
        .controller('MainCtrl', ['$scope', 'CityService', function ($scope, CityService) {
            $scope.awesomeThings = [
                'HTML5 Boilerplate',
                'AngularJS',
                'Karma'
            ];

            $scope.coords = {
                lat: '52.232222',
                lon: '21.008333'
            };

            $scope.alerts = [];

            $scope.city = '';

            $scope.findCity = function() {
                CityService
                    .findCity($scope.coords)
                    .success(function (data) {
                        $scope.city = data;
                    })
                    .error(function(reason) {
                        $scope.alerts = [{msg: reason}];
                    });
            };

            $scope.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };
        }]);
