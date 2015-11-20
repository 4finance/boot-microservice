package com.mangofactory.swagger.readers.operation;

import com.mangofactory.swagger.scanners.RequestMappingContext;

/**
 * Terrible hack until they fix https://github.com/springfox/springfox/issues/1055
 */
public class OperationDeprecatedReader implements RequestMappingReader {
  @Override
  public void execute(RequestMappingContext context) {
    context.put("deprecated", String.valueOf(false));
  }
}
