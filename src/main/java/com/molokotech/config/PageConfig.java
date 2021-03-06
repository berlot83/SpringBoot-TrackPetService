/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molokotech.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("com.molokotech.controllers")
public class PageConfig implements WebMvcConfigurer {

	@Autowired
	ApplicationContext applicationContext;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/create-qr").setViewName("create-qr");
		registry.addViewController("/pricing").setViewName("pricing");
		registry.addViewController("/default").setViewName("default");
		registry.addViewController("/about").setViewName("about");
		registry.addViewController("/sign-up").setViewName("sign-up");
		registry.addViewController("/success").setViewName("success");
		registry.addViewController("/prepaid-qr").setViewName("prepaid-qr");
		registry.addViewController("/create-prepaid-qr").setViewName("create-prepaid-qr");
		registry.addViewController("/vet-cloud").setViewName("vet-cloud");
		registry.addViewController("/db-lost-pet").setViewName("db-lost-pet");
		registry.addViewController("/id").setViewName("id");
		registry.addViewController("/payment-success").setViewName("payment-success");
		registry.addViewController("/faq").setViewName("faq");
		registry.addViewController("/online-checkout").setViewName("online-checkout");
		registry.addViewController("/account").setViewName("account");
		registry.addViewController("/delete").setViewName("delete");
		registry.addViewController("/change-pass").setViewName("change-pass");
		registry.addViewController("/send-link").setViewName("send-link");
		registry.addViewController("/download").setViewName("download");
		registry.addViewController("/temporal-qr").setViewName("temporal-qr");
		registry.addViewController("/user").setViewName("user");
		registry.addViewController("/bootstrap").setViewName("bootstrap");
	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setCacheable(false);
		templateResolver.setPrefix("classpath:/static/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.addTemplateResolver(templateResolver());
		return springTemplateEngine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(1);
		return viewResolver;
	}
	
	/* Thymeleaf local config start */
	
	/* Set  default lang to English, deactivate, but active if needed */
//	@Bean
//	public SessionLocaleResolver localeResolver() {
//	    SessionLocaleResolver slr = new SessionLocaleResolver();
//	    slr.setDefaultLocale(Locale.US);
//	    return slr;
//	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	}
	/* Thymeleaf local config end */

}
