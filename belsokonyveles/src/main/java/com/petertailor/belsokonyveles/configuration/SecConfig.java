//package com.petertailor.belsokonyveles.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecConfig  extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests().
//                antMatchers("/add").hasRole("USER").
//                antMatchers("/post").hasRole("USER").
//                antMatchers("/query").hasRole("USER").
//                antMatchers("/sentQuery").hasRole("USER").
//                antMatchers("/base").permitAll().
//                and().
//                formLogin().loginPage("/login").permitAll();
//
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//
//    }
//}
