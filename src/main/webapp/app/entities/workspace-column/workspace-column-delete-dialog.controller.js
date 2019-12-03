(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('WorkspaceColumnDeleteController',WorkspaceColumnDeleteController);

    WorkspaceColumnDeleteController.$inject = ['$uibModalInstance', 'entity', 'WorkspaceColumn'];

    function WorkspaceColumnDeleteController($uibModalInstance, entity, WorkspaceColumn) {
        var vm = this;

        vm.workspaceColumn = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            WorkspaceColumn.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
