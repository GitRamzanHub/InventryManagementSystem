package com.inventrymanagementsystem.ramzan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Till Spring Security 4
 * to provide the configuration one needs to extends WebSecurityConfigurerAdapter.
 * and override the configuration of the HttpSecurity , PasswordEncoder and UserDetailsService , AuthorizationManager
 * <p>
 * After Spring security 5
 * <p>
 * To provide the configuration one needs to create a bean for FilterChain, PasswordEncoder, UserDetailsService and AuthorityProvider
 * <p>
 * BLOG: https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 */

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/user/delete/{userId}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/allUsers").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/user/signup").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic();

    }
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
