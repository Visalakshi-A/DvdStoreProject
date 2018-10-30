package com.ideas2it.dvdstore.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.CustomerDao;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.session.DVDStoreSessionFactory;

/**
 * Accesses the Database that stores the Customer information and 
 * performs manipulations on the Customer details
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.dao.CustomerDao
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.logging.Logger
 * @see com.ideas2it.dvdstore.model.Customer
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.session.DVDStoreSessionFactory
 */
public class CustomerDaoImpl implements CustomerDao {

    private static final String HQL_GET_CUSTOMER_BY_USER_ID =
        "FROM Customer WHERE USER_ID= :id";

    private DVDStoreSessionFactory factory
                                        = DVDStoreSessionFactory.getInstance();

    /** @{inheritDoc} */
    public Boolean addNewCustomer(Customer customer) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            Logger.info(Constants.LOG_CUSTOMER_ADDED + customer.getId());
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            StringBuilder exceptionMessage = new StringBuilder();
            exceptionMessage.append(customer.getName())
                            .append(Constants.MESSAGE_ADD_CUSTOMER_EXCEPTION);
            Logger.error(Constants.LOG_ADD_CUSTOMER_EXCEPTION, e);
            throw new DVDException(exceptionMessage.toString());
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Boolean updateCustomerDetails(Customer customer) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            Logger.info(Constants.LOG_CUSTOMER_UPDATED + customer.getId());
            return Boolean.TRUE;	
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            StringBuilder exceptionMessage = new StringBuilder();
            exceptionMessage.append(customer.getName())
                            .append(Constants.MESSAGE_UPDATE_CUSTOMER_EXCEPTION);
            Logger.error(exceptionMessage.toString(), e);
            throw new DVDException(exceptionMessage.toString());
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Boolean checkAlreadyExists(Customer customer) throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            query.select(root).where(builder.or(
                builder.equal(root.get(Constants.LABEL_CONTACT_NUMBER),
                                                   customer.getContactNumber()),
                builder.equal(root.get(Constants.LABEL_EMAIL),
                                                        customer.getEmail()) ));
            Customer oldCustomer = session.createQuery(query).uniqueResult();
            if (null != oldCustomer) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (HibernateException e) {
            Logger.error(Constants.MESSAGE_ADD_CUSTOMER_EXCEPTION, e);
            throw new DVDException(Constants.MESSAGE_ADD_CUSTOMER_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Customer getCustomerByUserId(Integer userId) throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            return (Customer) session.createQuery(HQL_GET_CUSTOMER_BY_USER_ID).
                setParameter(Constants.LABEL_ID, userId).uniqueResult();
        } catch (HibernateException e) {
            Logger.error(Constants.MESSAGE_RETRIEVE_CUSTOMER_EXCEPTION, e);
            throw new DVDException(Constants.MESSAGE_RETRIEVE_CUSTOMER_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Customer getCustomerById(Integer id) throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            return session.get(Customer.class, id);
        } catch (HibernateException e) {
            Logger.error(Constants.LOG_RETRIEVE_CUSTOMER_EXCEPTION + id, e);
            throw new DVDException(Constants.
                                           MESSAGE_RETRIEVE_CUSTOMER_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public List<Customer> retrieveCustomers() throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            Logger.error(Constants.LOG_RETRIEVE_CUSTOMERS_EXCEPTION, e);
            throw new DVDException(Constants.
                                          MESSAGE_RETRIEVE_CUSTOMERS_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }
}












