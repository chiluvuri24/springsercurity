package com.eazybytes.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {

    /*
     * Below is the Custom
     * Code Security
     */


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((req)->req.disable())
                .authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/myaccount","/myBalance","/myLoans","/myCards").authenticated();
            requests.requestMatchers("/notices","/contact","/register").permitAll();
        });
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.csrf(req->req.disable());
        return (SecurityFilterChain)http.build();
    }

    /*@Bean
    public InMemoryUserDetailsManager userDetailsService(){

        //Approach1 where we use

        UserDetails admin = User.withUsername("admin")
                .password("12345")
                .authorities("admin")
                .build();

        UserDetails user = User.withUsername("user")
                .password("12345")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin,user);

    }*/

    /*@Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
         return new JdbcUserDetailsManager(dataSource);
    }*/





    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }




}
