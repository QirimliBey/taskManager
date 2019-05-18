(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('WorkspaceDialogController', WorkspaceDialogController);

    WorkspaceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Workspace', 'Project', 'WorkspaceColumn', 'Task'];

    function WorkspaceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Workspace, Project, WorkspaceColumn, Task) {
        var vm = this;

        vm.workspace = entity;
        vm.clear = clear;
        vm.save = save;
        vm.projects = Project.query();
        vm.workspacecolumns = WorkspaceColumn.query();
        vm.tasks = Task.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.workspace.id !== null) {
                Workspace.update(vm.workspace, onSaveSuccess, onSaveError);
            } else {
                Workspace.save(vm.workspace, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taskManagerApp:workspaceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
