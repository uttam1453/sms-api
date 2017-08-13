package com.appster.sms.config;

import java.util.Date;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * created by atul on 18/11/16.
 */
public class Bootstrap implements WebApplicationInitializer {

	private final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
	
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	Date date = new Date(); 
		logger.info("********************************************************");
		logger.info("***************** Turtle-2 Server Status ***************");
		logger.info("========================================================");
		logger.info("***** Context Initialized       @  : "+date);
		logger.info("***** Server Startup/Reloaded   @  : "+date);
		logger.info("========================================================");
		logger.info("********************************************************");
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(MvcConfig.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
    }
}
