package com.ofg.config.web

import groovy.transform.InheritConstructors
import groovy.transform.PackageScope
import groovy.transform.TypeChecked

@TypeChecked
@InheritConstructors
@PackageScope
class ResponseException extends RuntimeException {}
