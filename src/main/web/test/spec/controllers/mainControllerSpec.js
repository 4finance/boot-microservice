'use strict';

describe('Controller: MainCtrl', function () {

  // load the controller's module
  beforeEach(module('BootstrapApplication'));

  var MainCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MainCtrl = $controller('MainCtrl', {
      $scope: scope
    });
  }));

  it('there should be no alerts on startup', function () {
    expect(scope.alerts.length).toBe(0);
  });
});
