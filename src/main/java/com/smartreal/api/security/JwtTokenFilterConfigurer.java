package com.smartreal.api.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private final JwtTokenFilter jwtTokenFilter;

  public JwtTokenFilterConfigurer(JwtTokenFilter jwtTokenFilter) {
    this.jwtTokenFilter = jwtTokenFilter;
  }

  @Override
  public void configure(HttpSecurity http) {
    JwtTokenFilter customFilter = this.jwtTokenFilter;
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }

}