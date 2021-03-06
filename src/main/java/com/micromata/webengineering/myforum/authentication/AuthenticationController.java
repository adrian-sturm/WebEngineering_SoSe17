package com.micromata.webengineering.myforum.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationService.UserToken> login(@RequestBody UserLogin userLogin) {
        AuthenticationService.UserToken token = authenticationService.login(userLogin.email, userLogin.password);

        if (token == null) {
            LOG.info("Login rejected.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        LOG.info("Successful login from user {}", userLogin.email);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @RequestMapping(value = "register", method = RequestMethod.PUT)
    public ResponseEntity<AuthenticationService.UserToken> register(@RequestBody UserLogin userLogin) {
        AuthenticationService.UserToken token = authenticationService.register(userLogin.email, userLogin.password);

        if (token == null) {
            LOG.info("Registration or login rejected.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        LOG.info("Successfully registered new user '{}'", userLogin.email);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    public static class UserLogin {
        public String email;
        public String password;
    }

}
