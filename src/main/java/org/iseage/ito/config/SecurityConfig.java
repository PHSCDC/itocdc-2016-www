package org.iseage.ito.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name="userService")
    UserDetailsService userService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
            .ignoring().antMatchers("/css/**")
                .and()
            .ignoring().antMatchers("/js/**")
                .and()
            .ignoring().antMatchers("/img/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()
                .antMatchers("/profile").hasRole("USER")
                .antMatchers("/changepass").hasRole("USER")
                .antMatchers("/addcomment").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/change_download_link").hasRole("ADMIN")
                .antMatchers("/approve_image").hasRole("ADMIN")
                .antMatchers("/reject_image").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/index")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout=true")
                .and()
            .csrf()
                .disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
            .inMemoryAuthentication()
                .withUser("root")
                    .password("cdc")
                    .roles("ADMIN");
    }

}
