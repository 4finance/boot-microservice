package com.ofg.api.zabbix

import groovy.transform.TypeChecked
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@TypeChecked
class PingController {
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    String ping() {
        return "OK"
    }
}
