'use strict';

/**
 * Main module of the application.
 */
angular.module('BootstrapApplication.services', ['ngResource']);
angular.module('BootstrapApplication.controllers',
    ['ngResource', 'ui.bootstrap', 'fiestah.money', 'ngSanitize', 'BootstrapApplication.services']);

angular.module('BootstrapApplication', ['BootstrapApplication.services', 'BootstrapApplication.controllers']);
