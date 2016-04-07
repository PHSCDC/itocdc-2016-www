package org.iseage.ito.config;

import org.iseage.ito.Application;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
    public class WebConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/**").addResourceLocations("file:" + Application.getUploadDir());
            registry.addResourceHandler("/css/**").addResourceLocations("WEB-INF/css/");
            registry.addResourceHandler("/js/**").addResourceLocations("WEB-INF/js/");
            registry.addResourceHandler("/simg/**").addResourceLocations("WEB-INF/img/");
            super.addResourceHandlers(registry);
        }

        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.add(new MappingJackson2HttpMessageConverter());

            super.configureMessageConverters(converters);
        }
    }
