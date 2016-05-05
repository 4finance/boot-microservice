package com.ofg.twitter.geb.pages

import geb.Page

class SwaggerUIHomePage extends Page {
    static url = "/swagger-ui.html"
    static at = { title == "Swagger UI" }

    static content = {
        healthMvcEndpointText { $("li", id: "resource_health-mvc-endpoint") }
        metricsMvcEndpointText { $("li", id: "resource_microservice-configuration-controller") }
        showMicroservice { $("a", id: "endpointListTogger_microservice-configuration-controller") }
        showHealthMVCEndpoints { $("a", id: "endpointListTogger_health-mvc-endpoint") }
        healthEndpointsTable(wait: true) { $("#health-mvc-endpoint_endpoint_list") }
        healthEndpointTraceText { $("health-mvc-endpoint_endpoint_list") }
        microserviceJsonText { $("a", text: "/microserviceDescriptor") }
        microserviceGetTryButton {
            $("li#microservice-configuration-controller_getMicroserviceConfiguration input.submit")
        }
        microserviceGetResponseBody {
            $("li#microservice-configuration-controller_getMicroserviceConfiguration pre.json")
        }
        microserviceGetResponseCode {
            $("li#microservice-configuration-controller_getMicroserviceConfiguration div.response_code pre")
        }
        showPairIdEndpoints { $("#endpointListTogger_pair-id-controller") }
        pairIdEndpoints { $("#pair-id-controller_endpoint_list") }
        pairIdPutText { $("ul#pair-id-controller_endpoint_list span.path a") }
    }


}
