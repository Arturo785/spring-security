package com.example.springsecurity.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties") // takes the data from the resources file
public class DemoAppConfig {

  private final Logger logger = Logger.getLogger(getClass().getName());
  private final Environment environment;

  @Autowired
  public DemoAppConfig(Environment environment) {
    this.environment = environment;
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/view/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  private int getIntProperty(String propName) {
    String value = environment.getProperty(propName);

    if (value != null) {
      return Integer.parseInt(value);
    }

    return -1;
  }

  @Bean
  public DataSource securityDataSource() {
    ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

    logger.info("JDBC CONFIG FILE: URL: " + environment.getProperty("jdbc.url"));
    logger.info("JDBC CONFIG FILE: USER: " + environment.getProperty("jdbc.user"));

    try {
      securityDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
      securityDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
      securityDataSource.setUser(environment.getProperty("jdbc.user"));
      securityDataSource.setPassword(environment.getProperty("jdbc.password"));

    } catch (PropertyVetoException ex) {
      logger.info("An exception occurred while setting the data source : " + ex.toString());
      ex.printStackTrace();
    }

    // our connection pool settings
    securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
    securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
    securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
    securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

    return securityDataSource;
  }
}
