(function() {
    'use strict';
    angular
        .module('taskManagerApp')
        .factory('Project', Project);

    Project.$inject = ['$resource'];

    function Project ($resource) {
        var resourceUrl =  'api/projects/:id/:dst1';

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

            'addWorkspace': {
                method:'PUT',
                params: {dst1: "workspaces"}
            },

            'getWorkspaces': {
                method:'GET',
                params: {dst1: "workspaces"},
                isArray: true
            }
        });
    }
})();
