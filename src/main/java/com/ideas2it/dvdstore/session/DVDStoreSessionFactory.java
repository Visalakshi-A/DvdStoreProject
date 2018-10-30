package com.ideas2it.dvdstore.session;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException; 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.logging.Logger;

/**
 * A singleton class to build a Session factory object for the Database.
 * Contains a static function that returns the instance of the class.
 *
 * @version 1
 * @author Visalakshi
 */
public class DVDStoreSessionFactory {

    private static final String XML_CONFIGURATION 
        = "hibernate.cfg.xml";

    private static final String SESSION_CREATE_FAILED
        = " Failed to create Session factory. ";

    private static final String SESSION_CLOSE_FAILED
        = " Session could not be closed. ";

    private static DVDStoreSessionFactory dvdStoreSessionFactory;

    private static SessionFactory factory;

    /**
     * Private constructor to avoid multiple instances to an object.
     */
    private DVDStoreSessionFactory() {
    }

    /**
     * Returns the single instance of the class.
     *
     * @return dvdStoreSessionFactory
     *         DVDStoreSessionFactory object.
     */
    public static DVDStoreSessionFactory getInstance() {
        if (null == dvdStoreSessionFactory) {
            dvdStoreSessionFactory = new DVDStoreSessionFactory();
        }
        return dvdStoreSessionFactory;
    }

    /**
     * Builds a factory object for the Database if factory object is null.
     * Creates a session to access Database and returns the session.
     *
     * @return connection
     *         Returns a connection object after successful connection
     *         to the Database.
     */
    public Session getSession() {
        Configuration configuration;
        try {
            if (null == factory) {
                configuration = new Configuration();
                configuration.configure(XML_CONFIGURATION);
                factory = configuration.buildSessionFactory();
            }
            return factory.openSession();
        } catch (Throwable e) {
            throw new HibernateException(SESSION_CREATE_FAILED + e);
        }
    }
    
    /**
     * Closes the session object if opened.
     */
    public void closeSession(Session session) {
        try {
            if (null != session) {
                session.close();
            }
        } catch (Throwable e) {
            Logger.error(SESSION_CLOSE_FAILED + e);
        }
    }
}
