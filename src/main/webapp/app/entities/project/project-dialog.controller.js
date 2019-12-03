(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('ProjectDialogController', ProjectDialogController);

    ProjectDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Project', 'Client', 'Workspace'];

    function ProjectDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Project, Client, Workspace) {
        var vm = this;

        vm.project = entity;
        vm.clear = clear;
        vm.save = save;
        vm.clients = Client.query();
        vm.workspaces = Workspace.query();


        /*Project.addWorkspace({id: vm.project.id}, function(data){
            vm.project.workspaces = data;
        });*/



        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.project.id !== null) {
                Project.update(vm.project, onSaveSuccess, onSaveError);

                //addWorkspace method giving http err 405
                /*Project.addWorkspace({id: vm.project.id}, function(data){
                            vm.project.workspaces = data;
                        });*/
            } else {
                Project.save(vm.project, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('taskManagerApp:projectUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
