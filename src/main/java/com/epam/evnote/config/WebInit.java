package com.epam.evnote.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Mikhail Chtetsov on 19/12/2017.
 */
public class WebInit  extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{
        WebConfig.class
    };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[]{
        WebConfig.class
    };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  public void onStartup(ServletContext servletContext)
      throws ServletException {
    super.onStartup(servletContext);
    ServletRegistration.Dynamic servlet = servletContext.addServlet(
        "h2-console", new WebServlet());
    servlet.setLoadOnStartup(2);
    servlet.addMapping("/console/*");
  }


}
