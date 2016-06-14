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
        microserviceDescriptorText { $("a", text: "/microserviceDescriptor") }
        microserviceGetTryButton {
            $("#microservice-configuration-controller_getMicroserviceConfigurationUsingGET_content > form > div.sandbox_header > input")
        }
        microserviceGetResponseBody {
            $("#microservice-configuration-controller_getMicroserviceConfigurationUsingGET_content > div.response > div.block.response_body.json > pre > code")
        }
        microserviceGetResponseCode {
            $("#microservice-configuration-controller_getMicroserviceConfigurationUsingGET_content > div.response > div.block.response_code > pre")
        }
        showPairIdEndpoints { $("#endpointListTogger_pair-id-controller") }
        pairIdEndpoints { $("#pair-id-controller_endpoint_list") }
        pairIdPutText { $("ul#pair-id-controller_endpoint_list span.path a") }
    }
}
