package com.ideas2it.dvdstore.service.impl;

import com.ideas2it.dvdstore.common.enums.UserRole;
import com.ideas2it.dvdstore.dao.impl.UserDaoImpl;
import com.ideas2it.dvdstore.dao.UserDao;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.service.UserService;
/**
 * Interface to provide service for login, logout requests.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.dao.impl.UserDaoImpl
 * @see com.ideas2it.dvdstore.dao.UserDao
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.User
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /** @{inheritDoc} */
    public User login(User user) throws DVDException {
        return userDao.login(user);
    }

    /** @inheritDoc} */
    public User signUp(User user) throws DVDException {
        user.setRole(UserRole.CUSTOMER);
        return userDao.signUp(user);
    }
}
