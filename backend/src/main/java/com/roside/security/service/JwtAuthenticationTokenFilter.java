package com.roside.security.service;

import com.roside.security.JwtTokenUtil;
import com.roside.security.entity.JwtUser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * Created by Eric on 2017-01-22.
 */
public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    protected org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil tokenUtils;

    @Value("${jwt.token.header}")
    private String tokenHeader;

    private final String ORIGIN_LOCAL = "local";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        logger.debug("-------JwtAuthenticationTokenFilter: doFilter begin---------");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String authToken = httpRequest.getHeader(tokenHeader);
        String username = tokenUtils.getUsernameFromToken(authToken);
        logger.debug("-------JwtAuthenticationTokenFilter: username---------{}", username);
        logger.debug("Created: " + tokenUtils.getCreatedDateFromToken(authToken) +
                "    Expiration: " + tokenUtils.getExpirationDateFromToken(authToken));
        if (username != null) {
            String orgin = tokenUtils.getOriginFromToken(authToken);
            UserDetails currentUser = null;
            logger.debug("-------JwtAuthenticationTokenFilter: doFilter middle---------");
            if (ORIGIN_LOCAL.equals(orgin)) currentUser = (UserDetails)userDetailsService.loadUserByUsername(username);
            if (tokenUtils.validateToken(authToken, currentUser)) {
                String commaSprAuthorities = tokenUtils.getAuthoritiesFromToken(authToken);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(commaSprAuthorities));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                logger.debug("-------JwtAuthenticationTokenFilter: token validate failed---------");
            }
        }
        try {
            chain.doFilter(httpRequest, httpResponse);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (ServletException e) {
            logger.error(e.getMessage());
        }
    }
}
