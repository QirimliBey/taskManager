(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('WorkspaceColumnDetailController', WorkspaceColumnDetailController);

    WorkspaceColumnDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'WorkspaceColumn', 'Workspace', 'Task'];

    function WorkspaceColumnDetailController($scope, $rootScope, $stateParams, previousState, entity, WorkspaceColumn, Workspace, Task) {
        var vm = this;

        vm.workspaceColumn = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('taskManagerApp:workspaceColumnUpdate', function(event, result) {
            vm.workspaceColumn = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
