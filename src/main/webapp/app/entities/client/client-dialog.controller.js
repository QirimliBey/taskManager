(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('ClientDialogController', ClientDialogController);

    ClientDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Client', 'Task', 'Project'];

    function ClientDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Client, Task, Project) {
        var vm = this;

        vm.client = entity;
        vm.clear = clear;
        vm.save = save;
        vm.tasks = Task.query();
        vm.projects = Project.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.client.id !== null) {
                Client.update(vm.client, onSaveSuccess, onSaveError);
            } else {
                Client.save(vm.client, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taskManagerApp:clientUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
