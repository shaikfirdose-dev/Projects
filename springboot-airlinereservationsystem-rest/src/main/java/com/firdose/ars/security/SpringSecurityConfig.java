package com.firdose.ars.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {



    /**
     * Authentication : set user/password details and mention the role.
     */


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication().withUser("Admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("User").password(passwordEncoder().encode("user")).roles("USER");
        auth.inMemoryAuthentication().withUser("Shaik").password(passwordEncoder().encode("shaik@123")).roles("USER");
    }


    //
//	// Authorization : mention which role can access which URL
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/v3/api-docs").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/scheduledflight").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.PUT).permitAll()
                .antMatchers(HttpMethod.DELETE).permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

