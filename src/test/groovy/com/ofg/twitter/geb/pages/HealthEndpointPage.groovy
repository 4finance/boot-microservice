package com.ofg.twitter.geb.pages
import geb.Page
import groovy.json.JsonSlurper

class HealthEndpointPage extends Page{
    static url = "/health"
    
    static at ={
        statusJson.isDisplayed()
    }
    
    static content = {
        statusJson {$("body pre")}
    }

    def jsonSlurper = new JsonSlurper()
    Map json
    
    public void parseStatus(){
       json = jsonSlurper.parse(statusJson.text().toCharArray())
    }
    
}
