package com.ideas2it.dvdstore.service;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.User;
/**
 * Interface to provide service for login, logout requests.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.model.User
 */
public interface UserService {

    /**
     * Invokes the login method of {@code UserDao} and returns the object
     * returned by it.
     *
     * @param user
     *        User object requesting login.
     *
     * @return User object returned by Dao method.
     */
    User login(User user) throws DVDException;

    /**
     * Invokes the insert method of {@code UserDao} and returns the object
     * returned by it.
     *
     * @param user
     *        User object to be inserted.
     *
     * @return User object returned by the method.
     */
    User signUp(User user) throws DVDException;
}
