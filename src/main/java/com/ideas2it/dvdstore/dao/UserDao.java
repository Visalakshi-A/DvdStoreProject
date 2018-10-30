package com.ideas2it.dvdstore.dao;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.User;

/**
 * Performs all the authentication process and inserting new users to the Store.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.model.User
 */
public interface UserDao {

    /**
     * Gets the user object using the userName and checks if the password
     * matches, returns the user object if password matches 
     * else returns null.
     *
     * @param user
     *        User object that requested login.
     *
     * @return user
     *         null if login failed else the user object from the Store.
     */
    User login(User user) throws DVDException;

    /**
     * Checks if the user object already exists and returns null if already
     * exists otherwise creates a new user.
     *
     * @param user
     *        User object to be inserted.
     *
     * @return user
     *         User object if successfully inserted else returns null.
     */
    User signUp(User user) throws DVDException;
}
