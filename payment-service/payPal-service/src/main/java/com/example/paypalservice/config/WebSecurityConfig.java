package com.example.paypalservice.config;


import com.example.commonsmodule.security.JwtTokenFilter;
import com.example.commonsmodule.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Value("${app.security.jwt.secret}")
    private String secret;
    @Value("${app.security.jwt.expiry}")
    private Long jwtExpiry;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable().httpBasic()
                .and()
                .authorizeRequests(req -> req
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage())))
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider()), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider(){
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(secret, jwtExpiry);
        return jwtTokenProvider;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}

