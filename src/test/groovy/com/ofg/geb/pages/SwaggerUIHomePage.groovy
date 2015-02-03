package com.ofg.geb.pages

import geb.Page

class SwaggerUIHomePage extends Page {
    static url = "http://localhost:8095/swagger"
    static at = {title == "Swagger UI"}


    static content = {
        healthMvcEndpointText { $("li", id: "resource_health-mvc-endpoint") }
        metricsMvcEndpointText { $("li", id: "resource_microservice-configuration-controller") }
        showMicroservice { $("a", id: "endpointListTogger_microservice-configuration-controller") }
        showHealthMVCEndpoints { $("a", id: "endpointListTogger_health-mvc-endpoint") }
        microserviceJsonText { $("a", text: "/microservice.json") }
        heathEndpointsTable(wait: true) { $("ul#health-mvc-endpoint_endpoint_list") }

    }

}
