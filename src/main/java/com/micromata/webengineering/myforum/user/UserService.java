package com.micromata.webengineering.myforum.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieve the currently active user or null, if no user is logged in.
     *
     * @return the current user.
     */
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * Retrieve a User by a specific email address and password.
     *
     * @param email the email address of the requested user
     * @param password the password of the requested user
     * @return the retrieved user or null if no user with the specified credentials was found
     */
    public User getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
