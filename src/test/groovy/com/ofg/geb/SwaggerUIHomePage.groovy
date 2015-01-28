package com.ofg.geb

import geb.Page

class SwaggerUIHomePage extends Page {
    static url = "http://localhost:8095/swagger"
    static at = {title == "Swagger UI"}


    static content = {
        healthMvcEndpointText{$("li",id:"resource_health-mvc-endpoint")}
        //FIXME api link is not present add it when it will be working
        apiEndpointText{$("li",id:"resource_api-endpoint")}
        metricsMvcEndpointText{$("li",id:"resource_microservice-configuration-controller")}
        showMicroservice{$("a",id:"endpointListTogger_microservice-configuration-controller")}
        microserviceJsonText{$("a",text: "/microservice.json")}

        dupahealthMvcEndpointText{$("li",id:"resource_health-mvc-endpointdupa")}
    }
    


}
