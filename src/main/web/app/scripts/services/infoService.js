'use strict';

angular.module('BootstrapApplication.services')
    .factory('InfoService', ['$http', function($http) {
        var infoService = {};
        infoService.loadApplicationInfo = function (successFn) {
            $http.get('/info').success(function (data) {
                successFn(data);
            });
        };

        return infoService;
    }
]);
