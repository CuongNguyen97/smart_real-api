package com.smartreal.api.security;

import com.smartreal.api.util.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

  private final JwtTokenUtil jwtTokenUtil;

  public JwtTokenFilter(JwtTokenUtil jwtTokenUtil) {
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    String token = jwtTokenUtil.resolveToken(httpServletRequest);
    try {
      if (token != null && jwtTokenUtil.validateToken(token)) {
        Authentication auth = jwtTokenUtil.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (RuntimeException ex) {
      //this is very important, since it guarantees the user is not authenticated at all
      SecurityContextHolder.clearContext();
      httpServletResponse.sendError(500);
      return;
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

}