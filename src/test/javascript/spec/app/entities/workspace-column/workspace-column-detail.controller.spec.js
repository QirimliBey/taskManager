'use strict';

describe('Controller Tests', function() {

    describe('WorkspaceColumn Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockWorkspaceColumn, MockWorkspace, MockTask;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockWorkspaceColumn = jasmine.createSpy('MockWorkspaceColumn');
            MockWorkspace = jasmine.createSpy('MockWorkspace');
            MockTask = jasmine.createSpy('MockTask');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'WorkspaceColumn': MockWorkspaceColumn,
                'Workspace': MockWorkspace,
                'Task': MockTask
            };
            createController = function() {
                $injector.get('$controller')("WorkspaceColumnDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taskManagerApp:workspaceColumnUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
