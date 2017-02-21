package com.roside.api.auth;

import com.roside.mybatis.domain.User;
import com.roside.security.JwtTokenUtil;
import com.roside.security.service.JwtAuthenticationRequest;
import com.roside.security.service.JwtAuthenticationResponse;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by Eric on 2017-01-22.
 */
@RestController
@RequestMapping("/api")
public class AuthRestController {
    protected org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Autowired
    private UserDetailsService userDetailsService;

    @ApiOperation(value = "登录后获取token", notes = "")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                   Device device) throws AuthenticationException {

        // Perform the authentication
        Authentication requestAuth = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        Authentication resultAuth = authenticationManagerBean.authenticate(requestAuth);
        SecurityContextHolder.getContext().setAuthentication(resultAuth);

        // generate Token (User ID, Authorities, Device Type, Created Date)
        @SuppressWarnings("unchecked")
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)resultAuth.getAuthorities();
        String commaSprAuthorities = StringUtils.join(authorities, ",");
        User loginUser = new User();
        loginUser.setUsername(resultAuth.getName());
        loginUser.setAuthorities(commaSprAuthorities);
        String token = this.jwtTokenUtil.generateToken(loginUser, device);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
}
