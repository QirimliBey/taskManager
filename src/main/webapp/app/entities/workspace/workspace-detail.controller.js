(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('WorkspaceDetailController', WorkspaceDetailController);

    WorkspaceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Workspace', 'Project', 'WorkspaceColumn'];

    function WorkspaceDetailController($scope, $rootScope, $stateParams, previousState, entity, Workspace, Project, WorkspaceColumn) {
        var vm = this;

        vm.workspace = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('taskManagerApp:workspaceUpdate', function(event, result) {
            vm.workspace = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
