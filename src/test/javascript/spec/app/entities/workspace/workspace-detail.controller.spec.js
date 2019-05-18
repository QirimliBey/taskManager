'use strict';

describe('Controller Tests', function() {

    describe('Workspace Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockWorkspace, MockProject, MockWorkspaceColumn, MockTask;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockWorkspace = jasmine.createSpy('MockWorkspace');
            MockProject = jasmine.createSpy('MockProject');
            MockWorkspaceColumn = jasmine.createSpy('MockWorkspaceColumn');
            MockTask = jasmine.createSpy('MockTask');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Workspace': MockWorkspace,
                'Project': MockProject,
                'WorkspaceColumn': MockWorkspaceColumn,
                'Task': MockTask
            };
            createController = function() {
                $injector.get('$controller')("WorkspaceDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taskManagerApp:workspaceUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
