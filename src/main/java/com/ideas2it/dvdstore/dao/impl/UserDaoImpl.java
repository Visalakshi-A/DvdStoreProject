package com.ideas2it.dvdstore.dao.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Filter;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.UserDao;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.session.DVDStoreSessionFactory;

/**
 * Performs all the authentication process and inserting new users to the Store.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.model.User
 */
public class UserDaoImpl implements UserDao {

    private static final String HQL_GET_USER =
        "FROM User WHERE NAME = :name AND PASSWORD = SHA2(:password,224)";

    private DVDStoreSessionFactory factory =
                                           DVDStoreSessionFactory.getInstance();

    /** @{inheritDoc} */
    public User login(User user) throws DVDException {

        Session session = null;
        try {
            session = factory.getSession();
            user = (User) session.createQuery(HQL_GET_USER).
                setParameter(Constants.LABEL_NAME, user.getName()).
                setParameter(Constants.LABEL_PASSWORD, user.getPassword()).
                uniqueResult();
            return user;
        } catch (HibernateException e) {
            Logger.error(Constants.LOG_LOGIN_USER_EXCEPTION + user.getName(), e);
            throw new DVDException(Constants.MESSAGE_PROBLEM_LOGIN_USER);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public User signUp(User user) throws DVDException {

        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root).where(builder.
                equal(root.get(Constants.LABEL_NAME), user.getName()));
            User existingUser = session.createQuery(query).uniqueResult();

            if (null == existingUser) {

                MessageDigest sha224 = MessageDigest.getInstance("SHA-224");
                byte[] hash = sha224.digest(user.getPassword().getBytes());
                String hashText = new BigInteger(1, hash).toString(16);
                while (hashText.length() < 32) {
                    hashText = "0" + hashText;
                }
                user.setPassword(hashText);

                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
                Logger.info(Constants.LOG_USER_SIGNUP + user.getName());
                return user;
            }
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            Logger.error(Constants.LOG_SIGNUP_EXCEPTION, e);
            throw new DVDException(Constants.MESSAGE_SIGNUP_EXCEPTION);
        } catch (NoSuchAlgorithmException e) { 
            Logger.error(Constants.LOG_PROBLEM_HASHING_PASSWORD);
        } finally {
            factory.closeSession(session);
        }
        return null;
    }
}
