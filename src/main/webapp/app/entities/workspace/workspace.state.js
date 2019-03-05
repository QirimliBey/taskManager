(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('workspace', {
            parent: 'entity',
            url: '/workspace',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Workspaces'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/workspace/workspaces.html',
                    controller: 'WorkspaceController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('workspace-detail', {
            parent: 'workspace',
            url: '/workspace/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Workspace'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/workspace/workspace-detail.html',
                    controller: 'WorkspaceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Workspace', function($stateParams, Workspace) {
                    return Workspace.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'workspace',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('workspace-detail.edit', {
            parent: 'workspace-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/workspace/workspace-dialog.html',
                    controller: 'WorkspaceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Workspace', function(Workspace) {
                            return Workspace.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('workspace.new', {
            parent: 'workspace',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/workspace/workspace-dialog.html',
                    controller: 'WorkspaceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('workspace', null, { reload: 'workspace' });
                }, function() {
                    $state.go('workspace');
                });
            }]
        })
        .state('workspace.edit', {
            parent: 'workspace',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/workspace/workspace-dialog.html',
                    controller: 'WorkspaceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Workspace', function(Workspace) {
                            return Workspace.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('workspace', null, { reload: 'workspace' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('workspace.delete', {
            parent: 'workspace',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/workspace/workspace-delete-dialog.html',
                    controller: 'WorkspaceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Workspace', function(Workspace) {
                            return Workspace.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('workspace', null, { reload: 'workspace' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
