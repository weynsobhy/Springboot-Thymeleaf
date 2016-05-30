package com.es;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class AppConfig  extends WebMvcConfigurerAdapter{
	
	
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		// Set gmail email id
		mailSender.setUsername("email@gmail.com");
		// Set gmail email password
		mailSender.setPassword("password");
		Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "true");
		return mailSender;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations(
				"classpath:/static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations(
				"classpath:/static/js/");

	}
	
	@Bean
	// @Description("Thymeleaf template engine with Spring integration")
	public TemplateEngine templateEngine() {
		// SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		// templateEngine.setTemplateResolver();
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
