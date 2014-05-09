package com.ofg.config.web

import groovy.transform.TypeChecked
import org.springframework.http.HttpStatus

@TypeChecked
class RestUtil {

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
    
}
