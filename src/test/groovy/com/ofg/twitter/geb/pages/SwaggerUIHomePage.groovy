package com.ofg.twitter.geb.pages

import geb.Page

class SwaggerUIHomePage extends Page {
    static url = '/swagger-ui.html'

    static at = {
        if (swaggerUi3) {
            waitFor {  $('div', id: 'swagger-ui').children() }
        }
        title == 'Swagger UI'
    }

    static content = {

        healthMvcEndpoint {
            if (swaggerUi3) {
                return $('h4', id: 'operations-tag-health-mvc-endpoint')
            }
            $('li', id: 'resource_health-mvc-endpoint')
        }
        healthMvcEndpointOperationsToggle {
            if (swaggerUi3) {
                return healthMvcEndpoint.$('button', class: 'expand-operation')
            }
            healthMvcEndpoint.$('a', id: 'endpointListTogger_health-mvc-endpoint')
        }
        healthMvcEndpointOperationsContainer(wait: true) {
            if (swaggerUi3) {
                return healthMvcEndpoint.next('div')
            }
            healthMvcEndpoint.$('ul', id: 'health-mvc-endpoint_endpoint_list')
        }
        healthMvcEndpointOperationToggleTemplate {
            String operationToggleText -> healthMvcEndpointOperationsContainer.$('a', text: operationToggleText)
        }


        pairIdController {
            if (swaggerUi3) {
                return $('h4', id: 'operations-tag-pair-id-controller')
            }
            $('li', id: 'resource_pair-id-controller')
        }
        pairIdControllerOperationsToggle {
            if (swaggerUi3) {
                return pairIdController.$('button', class: 'expand-operation')
            }
            pairIdController.$('a', id: 'endpointListTogger_pair-id-controller')
        }
        pairIdControllerOperationsContainer(wait: true) {
            if (swaggerUi3) {
                return pairIdController.next('div')
            }
            pairIdController.$('ul', id: 'pair-id-controller_endpoint_list')
        }
        pairIdPutOperationToggle {
            pairIdControllerOperationsContainer.$('a', text: '/api/{pairId}')
        }


        microserviceController {
            if (swaggerUi3) {
                return $('h4', id: 'operations-tag-microservice-configuration-controller')
            }
            $('li', id: 'resource_microservice-configuration-controller')
        }
        microserviceControllerOperationsToggle {
            if (swaggerUi3) {
                return microserviceController.$('button', class: 'expand-operation')
            }
            microserviceController.$('a', id: 'endpointListTogger_microservice-configuration-controller')
        }
        microserviceControllerOperationsContainer(wait: true) {
            if (swaggerUi3) {
                return microserviceController.next('div')
            }
            microserviceController.$('ul', id: 'microservice-configuration-controller_endpoint_list')
        }
        microserviceDescriptorGetOperationToggle {
            microserviceControllerOperationsContainer.$('a', text: '/microserviceDescriptor')
        }
        microserviceDescriptorGetOperationContainer {
            if (swaggerUi3) {
                return microserviceControllerOperationsContainer.$('div', id: 'operations-microservice-configuration-controller-getMicroserviceConfigurationUsingGET')
            }
            microserviceControllerOperationsContainer.$('div', id: 'microservice-configuration-controller_getMicroserviceConfigurationUsingGET_content')
        }
        microserviceDescriptorGetOperationTryButton {
            if (swaggerUi3) {
                def microserviceDescriptorGetOperationExecuteButton = microserviceDescriptorGetOperationContainer.$('button', class: 'execute')
                if (!microserviceDescriptorGetOperationExecuteButton.displayed) {
                    microserviceDescriptorGetOperationContainer.$('div', class: 'try-out').click()
                }
                return microserviceDescriptorGetOperationExecuteButton
            }
            microserviceDescriptorGetOperationContainer.$('form > div.sandbox_header > input')
        }
        microserviceDescriptorGetOperationResponseBody {
            if (swaggerUi3) {
                return microserviceDescriptorGetOperationContainer.$('h4', text: 'Server response').next('table.responses-table').$('tr.response > td.response-col_description').$('h5', text: 'Response body').next('pre')
            }
            microserviceDescriptorGetOperationContainer.$('div.response > div.block.response_body.json > pre > code')
        }
        microserviceDescriptorGetOperationResponseCode {
            if (swaggerUi3) {
                return microserviceDescriptorGetOperationContainer.$('h4', text: 'Server response').next('table.responses-table').$('tr.response > td.response-col_status')
            }
            microserviceDescriptorGetOperationContainer.$('div.response > div.block.response_code > pre')
        }
    }

    boolean isSwaggerUi3() {
        $('div', id: 'swagger-ui').displayed
    }

}
