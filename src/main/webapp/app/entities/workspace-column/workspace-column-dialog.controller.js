(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('WorkspaceColumnDialogController', WorkspaceColumnDialogController);

    WorkspaceColumnDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'WorkspaceColumn', 'Workspace', 'Task'];

    function WorkspaceColumnDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, WorkspaceColumn, Workspace, Task) {
        var vm = this;

        vm.workspaceColumn = entity;
        vm.clear = clear;
        vm.save = save;
        vm.workspaces = Workspace.query();
        vm.tasks = Task.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.workspaceColumn.id !== null) {
                WorkspaceColumn.update(vm.workspaceColumn, onSaveSuccess, onSaveError);
            } else {
                WorkspaceColumn.save(vm.workspaceColumn, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taskManagerApp:workspaceColumnUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
