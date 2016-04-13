package io.fourfinanceit.setup;

import groovy.transform.Immutable;
import groovy.transform.InheritConstructors;

@InheritConstructors
@Immutable
public class ConsoleNotPresentException extends RuntimeException {}