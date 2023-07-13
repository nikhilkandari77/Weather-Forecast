package com.example.weatherforecast.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableGlobalAuthentication
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        return http.
                authorizeRequests(authorize->authorize.requestMatchers("/weather","/weather/**","/").permitAll())
                .authorizeRequests(authorize ->authorize.requestMatchers("/user/*").authenticated())
                .formLogin(form->form
                        .loginPage("/user/login")
                        .loginProcessingUrl("/dologin")
                        .failureForwardUrl("/user/login")
                        .defaultSuccessUrl("/user/admin")

                )
                .logout(logout->logout.logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/login")
                        .permitAll()
                )
                .authenticationProvider(daoProvider())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .build();
    }
    @Bean
    public BCryptPasswordEncoder getPassword(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoProvider(){
        DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        dao.setPasswordEncoder(getPassword());
        return dao;
    }
}
