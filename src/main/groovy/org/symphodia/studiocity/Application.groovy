package org.symphodia.studiocity

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@SpringBootApplication
class Application {

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    static class SecurityConfig extends WebSecurityConfigurerAdapter {

        void configure(HttpSecurity http) {
            http
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/static/**", "/index.html", "/views/home.html", "/views/login.html", "/").permitAll()
                    .anyRequest().authenticated();
        }
    }

    static void main(String... args) {
        SpringApplication.run(Application.class, args)
    }
}
