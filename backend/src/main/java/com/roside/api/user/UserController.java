package com.roside.api.user;

import com.roside.mybatis.entity.User;
import com.roside.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 2017-01-17.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    protected org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @ApiOperation(value = "按ID获取用户信息", notes = "通过用户id来获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID",required = true, dataType = "Int")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {


        logger.info("------------getUserById-----------");
        return userService.getUserById(id);
    }


    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions(HttpServletRequest request){

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            logger.debug("-------1------"+username);
        } else {
            username = principal.toString();
            logger.debug("--------2-----"+username);
        }
        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("sessionId", request.getSession().getId());
        map.put("token", request.getHeader("x-auth-token"));
        map.put("creationTime", sd.format(request.getSession().getCreationTime()));
        map.put("MaxInactive", request.getSession().getMaxInactiveInterval());
        map.put("LastAccessedTime", sd.format(request.getSession().getLastAccessedTime()));
        map.put("SPRING_SECURITY_CONTEXT", request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));

        return map;
    }
}
