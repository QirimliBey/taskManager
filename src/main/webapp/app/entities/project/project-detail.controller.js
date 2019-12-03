(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .controller('ProjectDetailController', ProjectDetailController);

    ProjectDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Project', 'Client', 'Workspace'];

    function ProjectDetailController($scope, $rootScope, $stateParams, previousState, entity, Project, Client, Workspace) {
        var vm = this;

        vm.project = entity;
        vm.previousState = previousState.name;

        Project.getWorkspaces({id: vm.project.id}, function(data){
            vm.project.workspaces = data;
        });

        var unsubscribe = $rootScope.$on('taskManagerApp:projectUpdate', function(event, result) {
            vm.project = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
