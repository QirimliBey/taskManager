(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('TaskDetailController', TaskDetailController);

    TaskDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Task', 'Client', 'WorkspaceColumn', 'Workspace'];

    function TaskDetailController($scope, $rootScope, $stateParams, previousState, entity, Task, Client, WorkspaceColumn, Workspace) {
        var vm = this;

        vm.task = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('taskManagerApp:taskUpdate', function(event, result) {
            vm.task = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
