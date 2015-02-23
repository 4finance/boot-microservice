'use strict';

angular.module('BootstrapApplication.services')
    .factory('CityService', ['$http', function($http) {
        var cityService = {};
        cityService.findCity = function (coords) {
            return $http({
                url: '/city/'+coords.lat+'/'+coords.lon,
                dataType: 'json',
                method: 'POST',
                data: '',
                headers: {
                    'Content-Type': 'application/vnd.com.ofg.twitter-places-analyzer.v1+json'
                }
            });
        };

        return cityService;
    }
]);
