package com.ofg.conf.web

import org.springframework.stereotype.Component
import org.springframework.web.servlet.View
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.view.json.MappingJackson2JsonView
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import groovy.transform.TypeChecked;

@Component("viewResolver")
@TypeChecked
class JsonViewResolver implements ViewResolver {
	@Override
	View resolveViewName(String viewName, Locale locale) throws Exception {
		MappingJackson2JsonView view = new MappingJackson2JsonView()
		view.getObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
		view.setPrettyPrint(true)
		return view
	}
}