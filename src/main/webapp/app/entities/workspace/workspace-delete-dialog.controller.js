(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('WorkspaceDeleteController',WorkspaceDeleteController);

    WorkspaceDeleteController.$inject = ['$uibModalInstance', 'entity', 'Workspace'];

    function WorkspaceDeleteController($uibModalInstance, entity, Workspace) {
        var vm = this;

        vm.workspace = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Workspace.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
