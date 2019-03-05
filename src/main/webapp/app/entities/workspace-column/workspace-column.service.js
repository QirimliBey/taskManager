(function() {
    'use strict';
    angular
        .module('taskManagerApp')
        .factory('WorkspaceColumn', WorkspaceColumn);

    WorkspaceColumn.$inject = ['$resource'];

    function WorkspaceColumn ($resource) {
        var resourceUrl =  'api/workspace-columns/:id';

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
            'update': { method:'PUT' }
        });
    }
})();
