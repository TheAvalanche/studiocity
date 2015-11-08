package org.symphodia.studiocity.web

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class WebConfig extends WebMvcConfigurerAdapter {

    @Value('${upload.dir.path}')
    String uploadDirPath

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploaded/**").addResourceLocations("file:$uploadDirPath");
    }

}
