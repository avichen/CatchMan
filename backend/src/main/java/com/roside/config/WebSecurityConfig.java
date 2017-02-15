package com.roside.config;

import com.roside.security.CustomAuthenticationProvider;
import com.roside.security.RestAuthenticationEntryPoint;
import com.roside.security.service.JwtAuthenticationTokenFilter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Eric on 2017-01-22.
 */
@Configuration
//@EnableAutoConfiguration
@EnableWebSecurity
@ComponentScan("com.roside.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restauthenticationEntryPoint;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(this.authenticationManagerBean());
        return authenticationTokenFilter;
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity
//                .csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(restauthenticationEntryPoint).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                .antMatchers("/auth").permitAll()
////                .antMatchers("/api/**").authenticated()
//                .anyRequest().authenticated()
//                .and()
////                .formLogin().disable();
//                .httpBasic();
        httpSecurity
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(restauthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth").permitAll()
                .antMatchers("/api/sessions").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .formLogin().disable();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(customAuthenticationProvider);
//        auth.userDetailsService(userDetailsService);
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
    }
}
