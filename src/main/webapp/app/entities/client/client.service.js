(function() {
    'use strict';
    angular
        .module('taskManagerApp')
        .factory('Client', Client);

    Client.$inject = ['$resource'];

    function Client ($resource) {
        var resourceUrl =  'api/clients/:id/:dst1';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' },
            'getProjects': {
                method:'GET',
                params: {dst1: "projects"},
                isArray: true
            }
        });
    }
})();
