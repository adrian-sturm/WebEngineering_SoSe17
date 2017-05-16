package com.micromata.webengineering.myforum.authentication;

import com.micromata.webengineering.myforum.user.User;
import com.micromata.webengineering.myforum.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    UserService userService;

    private String secret = "Topsy Cretts is 23";

    /**
     * Creates a JWT token if the login credentials correspond to a user in the database
     *
     * @param email the email address of the user logging in
     * @param password the password of the user logging in
     * @return a UserToken Object or null if the login credentials matched no user in the database
     */
    public UserToken login(String email, String password) {
        User user = userService.getUser(email, password);

        if (user == null) {
            LOG.error("Error: User with email="+ email +" and specified password not found!");
            return null;
        }

        String token = Jwts.builder()
                .setSubject(email)
                .setId(user.getId().toString())
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
}
