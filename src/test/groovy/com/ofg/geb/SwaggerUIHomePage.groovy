package com.ofg.geb

import geb.Page

class SwaggerUIHomePage extends Page {
    static url = "http://localhost:8095/swagger"
    static at = {title == "Swagger UI"}
}
