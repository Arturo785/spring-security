package com.example.springsecurity.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

  private final DataSource securityDataSource;

  @Autowired
  public DemoSecurityConfig(DataSource securityDataSource) {
    this.securityDataSource = securityDataSource;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    // JDBC AUTH
    auth.jdbcAuthentication().dataSource(securityDataSource); // uses our bean with our config
    // also takes care to query and get the users by itself without us to manage the process

    //password for users is fun123
    // users are in mysql database local
    // we should use the same schema that spring security uses

    // super.configure(auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        //  .anyRequest()
        //  .authenticated()
        .antMatchers("/")
        .hasRole("EMPLOYEE") // ONLY EMPLOYEES ROLES
        .antMatchers("/leaders/**")
        .hasRole("MANAGER") // ONLY MANAGERS
        .antMatchers("/systems/**")
        .hasRole("ADMIN")
        .and()
        .formLogin()
        .loginPage("/showMyLoginPage") // both of this are created by me
        .loginProcessingUrl("/authenticateTheUser") // used in the form login
        .permitAll()
        .and()
        .logout() // this gets managed auto by springSecurity
        .permitAll()
        .and()
        .exceptionHandling()
        .accessDeniedPage("/access-denied");
  }
}
