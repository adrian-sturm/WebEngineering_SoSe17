package com.micromata.webengineering.myforum.authentication;

import com.micromata.webengineering.myforum.user.User;
import com.micromata.webengineering.myforum.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // millis * sec * minute = 60 mins

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private String secret = "Topsy Cretts is 23";

    /**
     * Creates a JWT token if the login credentials correspond to a user in the database
     *
     * @param email the email address of the user logging in
     * @param password the password of the user logging in
     * @return a UserToken Object or null if the login credentials matched no user in the database
     */
    public UserToken login(String email, String password) {
        String encryptedPassword = passwordEncoder.encode(password);
        User user = userService.getUser(email, encryptedPassword);

        if (user == null) {
            LOG.error("Error: User with email="+ email +" and specified password not found!");
            return null;
        }

        String token = Jwts.builder()
                .setSubject(email)
                .setId(user.getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        UserToken userToken = new UserToken();
        userToken.user = user;
        userToken.token = token;
        return userToken;
    }

    /**
     * Validate a given JWT token and return its body
     *
     * Throws a SignatureException if the token is invalid
     * @param jwtToken the JWT token to be validated
     * @return the body of the token
     */
    public Object parseToken(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parse(jwtToken)
                .getBody();
    }

    public void setUser(Long id, String email) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        UsernamePasswordAuthenticationToken userPwAuthToken = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(userPwAuthToken);
    }

    /**
     * A Return Object containing a valid user and a corresponding JWT token
     */
    public static class UserToken {
        public User user;
        public String token;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
