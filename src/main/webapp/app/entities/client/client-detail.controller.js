(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('ClientDetailController', ClientDetailController);

    ClientDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Client', 'Task', 'Project'];

    function ClientDetailController($scope, $rootScope, $stateParams, previousState, entity, Client, Task, Project) {
        var vm = this;

        vm.client = entity;
        vm.previousState = previousState.name;

        Client.getProjects({id: vm.client.id}, function(data){
            vm.client.projects = data;
        });

        var unsubscribe = $rootScope.$on('taskManagerApp:clientUpdate', function(event, result) {
            vm.client = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
