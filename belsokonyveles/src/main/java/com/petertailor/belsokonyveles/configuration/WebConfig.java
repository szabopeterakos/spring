package com.petertailor.belsokonyveles.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) { // registry is hold connections between endpoint and view
        super.addViewControllers(registry);
        registry.addViewController("/login").setViewName("alogin");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}