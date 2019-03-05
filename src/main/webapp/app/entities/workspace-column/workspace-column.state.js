(function() {
    'use strict';

    angular
        .module('taskManagerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('workspace-column', {
            parent: 'entity',
            url: '/workspace-column',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'WorkspaceColumns'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/workspace-column/workspace-columns.html',
                    controller: 'WorkspaceColumnController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('workspace-column-detail', {
            parent: 'workspace-column',
            url: '/workspace-column/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'WorkspaceColumn'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/workspace-column/workspace-column-detail.html',
                    controller: 'WorkspaceColumnDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'WorkspaceColumn', function($stateParams, WorkspaceColumn) {
                    return WorkspaceColumn.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'workspace-column',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('workspace-column-detail.edit', {
            parent: 'workspace-column-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/workspace-column/workspace-column-dialog.html',
                    controller: 'WorkspaceColumnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['WorkspaceColumn', function(WorkspaceColumn) {
                            return WorkspaceColumn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('workspace-column.new', {
            parent: 'workspace-column',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/workspace-column/workspace-column-dialog.html',
                    controller: 'WorkspaceColumnDialogController',
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
                    $state.go('workspace-column', null, { reload: 'workspace-column' });
                }, function() {
                    $state.go('workspace-column');
                });
            }]
        })
        .state('workspace-column.edit', {
            parent: 'workspace-column',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/workspace-column/workspace-column-dialog.html',
                    controller: 'WorkspaceColumnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['WorkspaceColumn', function(WorkspaceColumn) {
                            return WorkspaceColumn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('workspace-column', null, { reload: 'workspace-column' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('workspace-column.delete', {
            parent: 'workspace-column',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/workspace-column/workspace-column-delete-dialog.html',
                    controller: 'WorkspaceColumnDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['WorkspaceColumn', function(WorkspaceColumn) {
                            return WorkspaceColumn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('workspace-column', null, { reload: 'workspace-column' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
