package com.furntrade.furntrademanagmentservet.Security;

import com.furntrade.furntrademanagmentservet.Filter.CustomAuthFilter;
import com.furntrade.furntrademanagmentservet.Filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //raspored je ovde bitan
        http.authorizeRequests().antMatchers("/login/**","/users/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/users/all/**").hasAuthority("admin");
        http.authorizeRequests().antMatchers(POST,"/users/add-employee/**").hasAuthority("admin");
        http.authorizeRequests().antMatchers(DELETE,"/users/**").hasAuthority("admin");
        http.authorizeRequests().antMatchers(POST,"/customers/add/**").hasAuthority("admin");
        http.authorizeRequests().antMatchers(DELETE,"/customers/**").hasAuthority("admin");
        http.authorizeRequests().antMatchers(PUT,"/customers/update/**").hasAuthority("admin");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
