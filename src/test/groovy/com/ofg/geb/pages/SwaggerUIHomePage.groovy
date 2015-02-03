package com.ofg.geb.pages

import geb.Page

class SwaggerUIHomePage extends Page {
    static url = "http://localhost:8095/swagger"
    static at = { title == "Swagger UI" }

    static content = {
        healthMvcEndpointText { $("li", id: "resource_health-mvc-endpoint") }
        metricsMvcEndpointText { $("li", id: "resource_microservice-configuration-controller") }
        showMicroservice { $("a", id: "endpointListTogger_microservice-configuration-controller") }
        showHealthMVCEndpoints { $("a", id: "endpointListTogger_health-mvc-endpoint") }
        heathEndpointsTable(wait: true) { $("ul#health-mvc-endpoint_endpoint_list") }
        microserviceJsonText { $("a", text: "/microservice.json") }
        microserviceGetTryButton {
            $("ul#microservice-configuration-controller_endpoint_list .submit[value='Try it out!']")
        }
        microserviceGetResponseBody {
            $("li#microservice-configuration-controller_getMicroserviceConfiguration pre.json")
        }
        microserviceGetResponseCode {
            $("li#microservice-configuration-controller_getMicroserviceConfiguration div.response_code pre")
        }
        showPairIdEndpoints { $("a#endpointListTogger_pairid") }
        pairIdPutText { $("ul#pairid_endpoint_list span.path a") }
    }
}
