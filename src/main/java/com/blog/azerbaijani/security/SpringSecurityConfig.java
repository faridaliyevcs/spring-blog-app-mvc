package com.blog.azerbaijani.security;

import com.blog.azerbaijani.service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


        @Autowired
        private CustomUserDetailsServiceImpl userDetailsService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(NoOpPasswordEncoder.getInstance());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests().antMatchers("/login").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/documents").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/register").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/document").hasAnyAuthority("USER")
                    .and()
                    .authorizeRequests().antMatchers("/show-document/**").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/add-comment").hasAnyAuthority("USER")
                    .and()
                    .authorizeRequests().antMatchers("/like").hasAnyAuthority("USER")
                    .and()
                    .csrf().disable()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/documents")
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll();
        }
}