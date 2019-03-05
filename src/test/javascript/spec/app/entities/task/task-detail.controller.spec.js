'use strict';

describe('Controller Tests', function() {

    describe('Task Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockTask, MockClient, MockWorkspaceColumn;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockTask = jasmine.createSpy('MockTask');
            MockClient = jasmine.createSpy('MockClient');
            MockWorkspaceColumn = jasmine.createSpy('MockWorkspaceColumn');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Task': MockTask,
                'Client': MockClient,
                'WorkspaceColumn': MockWorkspaceColumn
            };
            createController = function() {
                $injector.get('$controller')("TaskDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taskManagerApp:taskUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
