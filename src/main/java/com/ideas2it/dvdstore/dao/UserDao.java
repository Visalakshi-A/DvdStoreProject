package com.ideas2it.dvdstore.dao;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.User;

/**
 * Performs retrieve and insert User on the DVD Store.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.exception.DVDException
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
    User retrieveUser(User user) throws DVDException;

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
    User insertUser(User user) throws DVDException;
}
