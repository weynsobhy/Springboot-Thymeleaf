package com.es;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class AppConfig  extends WebMvcConfigurerAdapter{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/app/**").addResourceLocations(
//				"classpath:/static/app/");
//		registry.addResourceHandler("/js/**").addResourceLocations(
//				"classpath:/static/js/");
//
//	}
//	
	@Bean
	// @Description("Thymeleaf template engine with Spring integration")
	public TemplateEngine templateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addDialect(new LayoutDialect());
		templateEngine.setTemplateResolver(new TemplateResolver());
		return templateEngine;
	}
	
	@Bean
	public TemplateResolver springThymeleafTemplateResolver() {  
	    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	    resolver.setPrefix("classpath:/templates/");
	    resolver.setSuffix(".html");
	    resolver.setOrder(1);
	    resolver.setTemplateMode("HTML5");
	    return resolver;
	}

}
