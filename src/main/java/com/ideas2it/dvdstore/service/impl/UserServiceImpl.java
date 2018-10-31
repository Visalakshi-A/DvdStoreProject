package com.ideas2it.dvdstore.service.impl;

import com.ideas2it.dvdstore.common.enums.UserRole;
import com.ideas2it.dvdstore.dao.UserDao;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.service.UserService;
/**
 * Provides service for login, logout requests.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.enums.UserRole
 * @see com.ideas2it.dvdstore.dao.UserDao
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.User
 * @see com.ideas2it.dvdstore.service.UserService
 */
public class UserServiceImpl implements UserService {

    private static UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /** @{inheritDoc} */
    public User login(User user) throws DVDException {
        return userDao.retrieveUser(user);
    }

    /** @inheritDoc} */
    public User signUp(User user) throws DVDException {
        user.setRole(UserRole.CUSTOMER);
        return userDao.insertUser(user);
    }
}
