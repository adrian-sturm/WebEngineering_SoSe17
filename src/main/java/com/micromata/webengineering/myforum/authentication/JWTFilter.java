package com.micromata.webengineering.myforum.authentication;

import com.micromata.webengineering.myforum.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {
    private static final Logger LOG = LoggerFactory.getLogger(JWTFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";

    private AuthenticationService authenticationService;

    private UserService userService;

    public JWTFilter(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // check for authorization token
        String authorization = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if (!StringUtils.startsWithIgnoreCase(authorization, TOKEN_PREFIX)) {
            LOG.info("No authorization token submitted");
            // allow anonymous users
            LOG.info("Setting anonymous user");
            userService.setAnonymous();
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // extract token contents
        String token = authorization.substring(7);
        try {
            Claims body = (Claims) authenticationService.parseToken(token);
            LOG.info("Authenticated request from user {}/{}", body.getSubject(), body.getId());
            authenticationService.setUser(Long.parseLong(body.getId()), body.getSubject());
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (SignatureException e) {
            LOG.warn("Invalid token: {}", token);
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
